package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.SqrtFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(0.1);
        SqrtFunction check = new SqrtFunction();
        assertEquals(differentialOperator.derive(new SqrtFunction()).apply(1), 0.513, 0.01);
        assertEquals(differentialOperator.derive(new SqrtFunction()).apply(0.9), 0.54, 0.01);
        double[] xValue = {0.9, 1};
        assertEquals((check.apply(xValue[1]) - check.apply(xValue[0])) / (xValue[1] - xValue[0]), 0.513, 0.01);
    }
}