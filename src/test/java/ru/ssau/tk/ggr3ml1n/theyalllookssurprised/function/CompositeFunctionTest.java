package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA = 0.01;
    private final MathFunction firstFunction = new ReverseSin();
    private final MathFunction secondFunction = new ReverseSin();
    private final MathFunction compositeFunction = new CompositeFunction(firstFunction, secondFunction);

    @Test
    public void testApply() {
        assertEquals(compositeFunction.apply(4.111), -1.06, DELTA);
        assertNotEquals(compositeFunction.apply(1.111), 7, DELTA);
        assertNotEquals(compositeFunction.apply(1.111), 8, DELTA);
    }
}