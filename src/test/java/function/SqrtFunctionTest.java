package function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrtFunctionTest {
    private final static double DELTA=0.01;
    private final MathFunction sqrtFunction = new SqrtFunction();

    @Test
    public void testApply() {
        assertEquals(sqrtFunction.apply(49), 7,DELTA);
        assertNotEquals(sqrtFunction.apply(1.111), 7,DELTA);
        assertNotEquals(sqrtFunction.apply(1.111), 8,DELTA);
    }
}