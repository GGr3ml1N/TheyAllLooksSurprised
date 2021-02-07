package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class ConstantFunctionTest {
    private final static double DELTA = 0.01;
    ConstantFunction testFunction = new ConstantFunction(4.111);
    ConstantFunction testFunction1 = new ConstantFunction(15.1);


    @Test
    public void testApply() {
        assertEquals(testFunction.apply(4.111), 4.11, DELTA);
        assertEquals(testFunction1.apply(15.1), 15.1, DELTA);
    }

    @Test
    public void testGetConstanta() {
        assertEquals(testFunction.getConstanta(), 4.11, DELTA);
        assertEquals(testFunction1.getConstanta(), 15.1, DELTA);
    }
}