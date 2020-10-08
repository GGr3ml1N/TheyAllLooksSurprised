package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    private final ZeroFunction testFunction = new ZeroFunction();

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(666.), 0, DELTA);
        assertEquals(testFunction.apply(0), 0, DELTA);
        assertEquals(testFunction.apply(Double.NEGATIVE_INFINITY), 0, DELTA);
    }

    @Test
    public void testGetNumber() {
        assertEquals(testFunction.getConstanta(), 0, DELTA);
    }
}