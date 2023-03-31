package org.example.util.helper;

import org.example.model.Equation;
import org.springframework.stereotype.Component;

@Component
public class EquationHelper {
    public int amountNumbers(Equation equation) {
        String s = equation.getExpression();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
