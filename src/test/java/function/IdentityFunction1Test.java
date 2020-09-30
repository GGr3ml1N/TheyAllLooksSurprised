package function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunction1Test {
    private final static double DELTA=0.01;
    private final MathFunction identityFunction1 = new IdentityFunction1();

    @Test
    public void testApply() {
        assertEquals(identityFunction1.apply(4.111), -1.21,DELTA);
        assertNotEquals(identityFunction1.apply(1.111), 7,DELTA);
        assertNotEquals(identityFunction1.apply(1.111), 8,DELTA);
    }
}