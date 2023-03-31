package org.example.util.validators;

import org.example.model.Equation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {
    public static final String EXPRESSION = "[\\d. x+/*=()-]+";
    public static final String CHECK_BRACKETS = "\\([\\d .x+/*=-]*\\)";
    public static final String CHECK_OPERATIONS = "[/*+-][+/*]";

    public boolean isValid(String equation) {
        Pattern pattern = Pattern.compile(EXPRESSION);
        Matcher matcher = pattern.matcher(equation);
        return matcher.matches() && checkExpression(equation);
    }

    private boolean checkExpression(String input) {
        Pattern pattern = Pattern.compile(CHECK_OPERATIONS);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find())
            return false;
        Pattern pattern2 = Pattern.compile(CHECK_BRACKETS);
        Matcher matcher2 = pattern2.matcher(input);
        do {
            input = matcher2.replaceAll("");
            matcher2 = pattern2.matcher(input);
        } while (matcher2.find());
        return input.matches("[\\d .x+/*=-]*");
    }

    public boolean isRoot(Equation equation, double root) {
        List<Integer> list = findX(equation.getExpression());
        Pattern pattern = Pattern.compile("x");
        Matcher matcher = pattern.matcher(equation.getExpression());
        String s = matcher.replaceAll(String.valueOf(root));

        //there should be logic for the method "isRoot"

        return true;
    }

    private List<Integer> findX(String equation) {
        List<Integer> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("x");
        Matcher matcher = pattern.matcher(equation);
        while (matcher.find()) {
            int start = matcher.start();
            list.add(start);
        }
        return list;
    }
}
