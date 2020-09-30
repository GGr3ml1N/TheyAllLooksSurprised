package function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {
    private final static double DELTA=0.01;
    private final MathFunction identityFunction = new IdentityFunction();

    @Test
    public void testApply() {
        assertEquals(identityFunction.apply(4.111), 4.11,DELTA);
        assertNotEquals(identityFunction.apply(1.111), 7,DELTA);
        assertNotEquals(identityFunction.apply(1.111), 8,DELTA);


    }
}
