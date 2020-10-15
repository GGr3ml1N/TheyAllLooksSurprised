package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA = 0.01;
    private final double[] xValues1 = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues1 = new double[]{6, 7, 8, 9, 10};
    private final double[] xValues2 = new double[]{11, 12, 13, 14, 15};
    private final double[] yValues2 = new double[]{16, 17, 18, 19, 20};

    @Test
    public void testApply() {

        MathFunction identityFunction = new IdentityFunction();
        MathFunction reverseSin = new ReverseSin();
        MathFunction doubleIdentityFunction = new CompositeFunction(identityFunction, identityFunction);
        assertEquals(doubleIdentityFunction.apply(1), 1, DELTA);
        MathFunction identityReverseFunction = new CompositeFunction(identityFunction, reverseSin);
        assertEquals(identityReverseFunction.apply(1), 1.188, DELTA);

        MathFunction sqrtFunction = new SqrtFunction();
        MathFunction unitFunction = new UnitFunction();
        MathFunction sqrReverseUnitFunction = sqrtFunction.andThen(reverseSin).andThen(unitFunction);
        assertEquals(sqrReverseUnitFunction.apply(4), 1, DELTA);
        assertNotEquals(sqrReverseUnitFunction.apply(1), 0, DELTA);
        assertEquals(sqrReverseUnitFunction.apply(1651), 1, DELTA);

        double result = sqrtFunction.andThen(identityFunction).andThen(reverseSin).apply(5);
        assertEquals(result, 1.27, DELTA);
        assertNotEquals(result, 100, DELTA);
        assertNotEquals(result, 1, DELTA);

        MathFunction listFunction = new LinkedListTabulatedFunction(xValues1, yValues1);
        MathFunction arrayFunction = new ArrayTabulatedFunction(xValues2, yValues2);
        MathFunction arrayListSqrFunction = arrayFunction.andThen(listFunction).andThen(sqrtFunction);
        assertEquals(arrayListSqrFunction.apply(1), 3.31, DELTA);
        assertEquals(arrayListSqrFunction.apply(2.55), 3.54, DELTA);
        assertEquals(arrayListSqrFunction.apply(44), 7.34, DELTA);

    }
}