package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ConstantFunctionTest {
    private final static double DELTA = 0.01;
    ConstantFunction testFunction = new ConstantFunction(666.1313);
    ConstantFunction testFunction1 = new ConstantFunction(13.1313);



    @Test
    public void testApply() {
        assertEquals(testFunction.apply(4.111), 4.11, DELTA);
        assertNotEquals(testFunction.apply(1.111), 7, DELTA);
        assertNotEquals(testFunction.apply(1.111), 8, DELTA);
    }

    @Test
    public void testGetConstanta() {
        assertEquals(testFunction.getConstanta(), 666.1313, DELTA);
        assertEquals(testFunction1.getConstanta(), 13.1313, DELTA);
    }
}