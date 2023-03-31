package org.example;

import org.example.model.Equation;
import org.example.util.helper.EquationHelper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class EquationHelperTest {
    private static EquationHelper helper;
    private static Equation equation;

    @BeforeClass
    public static void init() {
        helper = new EquationHelper();
        equation = new Equation();
        equation.setExpression("x+1=2");
        System.out.println("Init test");
    }

    @AfterClass
    public static void end() {
        System.out.println("Test over");
    }

    @Test
    public void testAmountNumbers() {
        int res = helper.amountNumbers(equation);
        if (res != 2) Assert.fail();
    }
}
