package function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    UnitFunction testFunction = new UnitFunction();

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(666.), 1, DELTA);
    }

    @Test
    public void testGetNumber() {
        assertEquals(testFunction.getConstanta(), 1, DELTA);
    }

}