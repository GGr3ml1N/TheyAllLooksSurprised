package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;
import ru.ssau.tk.ggr3ml1n.theyalllookssurpriswd.exeptions.InterpolationException;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final MathFunction source = new SqrtFunction();
    private final static double DELTA = 0.1;
    private final MathFunction sqr = new SqrtFunction();
    private final static double ACCURACY = 0.1;
    private final double[] xValues = new double[]{1.0, 1.1, 1.2, 1.3, 1.4};
    private final double[] yValues = new double[]{2.0, 2.1, 2.2, 2.3, 2.4};
    private LinkedListTabulatedFunction getListOfArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
    private LinkedListTabulatedFunction getListOfMathFunction() {
        return new LinkedListTabulatedFunction(sqr, 0, 10, 101);
    }

    private AbstractTabulatedFunction listFunction() {
        return new LinkedListTabulatedFunction(source, 1, 5, 5);
    }

    private AbstractTabulatedFunction getListFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 5, 5);
    }

    private AbstractTabulatedFunction getFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 5, 7);
    }


    @Test
    public void testFloorIndexOfX() {
        assertEquals(listFunction().floorIndexOfX(2), 0, DELTA);
        assertEquals(getListFunction().floorIndexOfX(5), 3, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(listFunction().extrapolateRight(4), 2, DELTA);
        assertEquals(getListFunction().extrapolateRight(40), 10.49, ACCURACY);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(listFunction().extrapolateLeft(0), 0.58578, DELTA);
        assertEquals(getListFunction().extrapolateLeft(0), 0.58578, ACCURACY);

    }

    @Test
    public void testGetCount() {
        assertEquals(listFunction().getCount(), 10, DELTA);
        assertEquals(getListFunction().getCount(), 10, ACCURACY);
        final AbstractTabulatedFunction testFunction = new LinkedListTabulatedFunction(sqr, -1, 1, 1);
        assertEquals(getFunction().getCount(), 14);
        assertEquals(testFunction.getCount(), 2);
    }


    @Test
    public void testInterpolate() {
        final double delta = 0.001;
        assertEquals(getListOfArray().interpolate(1.23, getListOfArray().floorIndexOfX(1.23)), 2.23, delta);
        assertEquals(getListOfArray().interpolate(1.15, getListOfArray().floorIndexOfX(1.15)), 2.15, delta);
        assertNotEquals(getListOfArray().interpolate(1.33, getListOfArray().floorIndexOfX(1.33)), 8.43, delta);
        assertEquals(getListOfMathFunction().interpolate(1.41, getListOfMathFunction().floorIndexOfX(1.41)), 1.187, delta);
        assertEquals(getListOfMathFunction().interpolate(1.35, getListOfMathFunction().floorIndexOfX(1.35)), 1.161, delta);
        assertNotEquals(getListOfMathFunction().interpolate(1.33, getListOfMathFunction().floorIndexOfX(1.33)), 8.43, delta);
        assertThrows(InterpolationException.class, () -> getListOfArray().interpolate(0.5, 2));
        assertThrows(InterpolationException.class, () -> getListOfMathFunction().interpolate(7.5, 3));
    }


    @Test
    public void testIndexOfY() {
        assertEquals(listFunction().indexOfY(25), -1, DELTA);
        assertEquals(getListFunction().indexOfY(25), -1, ACCURACY);
        assertEquals(getFunction().indexOfY(1), 0);
        assertEquals(getFunction().indexOfY(2), -1);
        assertEquals(getFunction().indexOfY(-2), -1);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(listFunction().indexOfX(3), 2, DELTA);
        assertEquals(getListFunction().indexOfX(3), 2, ACCURACY);
        assertEquals(getFunction().indexOfX(0), -1);
        assertEquals(getFunction().indexOfX(5), 6);
        assertEquals(getFunction().indexOfX(-5), -1);
    }

    @Test
    public void testGetX() {
        assertEquals(listFunction().getX(1), 2, DELTA);
        assertEquals(getListFunction().getX(1), 2, ACCURACY);
        assertEquals(getFunction().getX(5), 4.3333, ACCURACY);
        assertEquals(getFunction().getX(1), 1.6666, ACCURACY);
    }

    @Test
    public void testGetY() {
        assertEquals(listFunction().getY(1), 1.41, DELTA);
        assertEquals(getListFunction().getY(1), 1.414, ACCURACY);
        assertEquals(getFunction().getY(1), 1.290, ACCURACY);
        assertEquals(getFunction().getY(1), 1.290, ACCURACY);
    }

    @Test
    public void testSetY() {
        listFunction().setY(2, 2.2);
        assertEquals(listFunction().getY(1), 1.41, DELTA);
        listFunction().setY(3, 2.3);
        AbstractTabulatedFunction function = getFunction();
        AbstractTabulatedFunction someFunction = getListFunction();
        someFunction.setY(1, 2.1);
        assertEquals(someFunction.getY(1), 2.1, ACCURACY);
        function.setY(2, 2.2);
        assertEquals(function.getY(2), 2.2, ACCURACY);
    }

    @Test
    public void testApply() {
        AbstractTabulatedFunction testingApply = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.1;
        assertEquals(testingApply.apply(-1.0), 0.0, delta);
        assertEquals(testingApply.apply(1.5), 2.5, delta);
        assertEquals(testingApply.apply(1.1), 2.1, delta);
        assertEquals(testingApply.apply(1.15), 2.15, delta);
        assertEquals(testingApply.apply(1.4), 2.4, delta);

        assertNotEquals(testingApply.apply(7.82), 1.23, delta);
        assertNotEquals(testingApply.apply(1.22), 1.23, delta);

        assertEquals(listFunction().apply(-1.0), 0.17, delta);
        assertEquals(listFunction().apply(1.5), 1.2, delta);
        assertEquals(listFunction().apply(1.1), 1.04, delta);
        assertEquals(listFunction().apply(1.4), 1.16, delta);
        assertEquals(listFunction().apply(1.15), 1.06, delta);

        assertNotEquals(listFunction().apply(7.25), 59.25, delta);
    }
}