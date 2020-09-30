package function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA=0.01;
    public final MathFunction firstFunction = new IdentityFunction1();
    private final MathFunction secondFunction = new IdentityFunction1();
    private final MathFunction compositeFunction = new CompositeFunction(firstFunction, secondFunction);

    @Test
    public void testApply() {
        assertEquals(compositeFunction.apply(4.111), -1.07,DELTA);

    }
}