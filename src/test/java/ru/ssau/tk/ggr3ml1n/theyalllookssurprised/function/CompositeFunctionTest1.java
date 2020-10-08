package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest1 {
    private final static double DELTA=0.01;

    @Test
    public void testApply() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction sqrtFunction = new SqrtFunction();
        MathFunction composite = new CompositeFunction(identityFunction, sqrtFunction);
        assertEquals(composite.apply(0), 0, DELTA);
        MathFunction identityFunction1 = new ReverseSin();
        assertEquals(identityFunction1.apply(36), -1, DELTA);
        MathFunction composite1 = identityFunction.andThen(identityFunction1).andThen(identityFunction);
        assertEquals(composite1.apply(3.14/2),1, DELTA);
    }
}