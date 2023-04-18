package org.example.logic;

import org.example.dao.EquationDao;
import org.example.interfaces.MathAssistant;
import org.example.model.Equation;
import org.example.util.helper.EquationHelper;
import org.example.util.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MathAssistantImpl implements MathAssistant {
    private final Validator validator;
    private final EquationDao equationDao;
    private final Scanner scanner;
    private final EquationHelper helper;
    private final ApplicationContext context;

    @Autowired
    public MathAssistantImpl(Validator validator, EquationDao equationDao, Scanner scanner, EquationHelper helper, ApplicationContext context) {
        this.validator = validator;
        this.equationDao = equationDao;
        this.scanner = scanner;
        this.helper = helper;
        this.context = context;
    }

    @Override
    public void start() {
        boolean isStart = true;
        while (isStart) {
            System.out.println("Hello! I am your assistant, enter mathematical equation: ");
            createEquation();
            System.out.println("You can view equations, or end the session.\n" +
                    "Enter command: show or exit: ");
            isStart = keepWork();
        }
        scanner.close();
    }

    private void createEquation() {
        try {
            String expression = scanner.nextLine();
            if (validator.isValid(expression)) {
                Equation equation = context.getBean("equation", Equation.class);
                equation.setExpression(expression);
                equationDao.save(equation);
                System.out.println("the equation has: " + helper.amountNumbers(equation) + " numbers");
                System.out.println("enter root for: " + expression);
                enterRoot(equation);
            } else System.out.println("math equation is not correct");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enterRoot(final Equation equation) {
        if (scanner.hasNextDouble()) {
            double root = scanner.nextDouble();
            if (validator.isRoot(equation, root)) {
                equationDao.update(equation.getId(), root);
            } else System.out.println("root is not correct");
        } else System.out.println("root is not double");
        scanner.nextLine();
    }

    private boolean keepWork() {
        try {
            String command = scanner.next();
            switch (command) {
                case "show":
                    System.out.println("enter root of equation");
                    if (scanner.hasNextDouble()) {
                        double root = scanner.nextDouble();
                        scanner.nextLine();
                        Optional<List<Equation>> optional = equationDao.get(root);
                        if (optional.isPresent() && !optional.get().isEmpty()) {
                            optional.get().forEach(System.out::println);
                        } else System.out.println("no such root");
                    } else {
                        scanner.next();
                        scanner.nextLine();
                        System.out.println("root is not valid");
                    }
                    break;
                case "exit":
                    System.out.println("Have a nice day!");
                    return false;
                default:
                    scanner.nextLine();
                    System.out.println("command should be correct");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
