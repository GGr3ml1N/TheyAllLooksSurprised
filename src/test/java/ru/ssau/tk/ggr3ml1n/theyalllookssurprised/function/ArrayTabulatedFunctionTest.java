package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    double[] xValues = new double[]{1.0, 1.1, 1.2, 1.3, 1.4};
    double[] yValues = new double[]{2.0, 2.1, 2.2, 2.3, 2.4};
    private final MathFunction source = new SqrtFunction();
    private final ArrayTabulatedFunction testingArrayFunction = new ArrayTabulatedFunction(source, 1, 16, 6);

    @Test
    public void testApply() {
        ArrayTabulatedFunction testingApply = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingApply.apply(2.0), 3.0, delta);
        assertEquals(testingApply.apply(8.84), 9.84, delta);
        assertEquals(testingApply.apply(7.82), 8.82, delta);
        assertNotEquals(testingApply.apply(7.82), 1.23, delta);
        assertEquals(testingArrayFunction.apply(-8.97), -48.85, delta);
        assertEquals(testingArrayFunction.apply(27), 575.0, delta);
        assertEquals(testingArrayFunction.apply(8.46), 73.82, delta);
        assertNotEquals(testingArrayFunction.apply(8.46), 59.25, delta);
    }

    @Test
    public void testFloorIndexOfX() {
        ArrayTabulatedFunction testingFloorIndexOfX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.35), 3, delta);
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 5, delta);
        assertNotEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 4);
        assertEquals(testingArrayFunction.floorIndexOfX(8.93), 2);
        assertEquals(testingArrayFunction.floorIndexOfX(66.67), 6);
        assertNotEquals(testingArrayFunction.floorIndexOfX(66.67), 4);
    }

    @Test
    public void testExtrapolateLeft() {
        ArrayTabulatedFunction testingExtrapolateLeft = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateLeft.extrapolateLeft(1.1), 2.1, delta);
        assertEquals(testingExtrapolateLeft.extrapolateLeft(-1.5), -0.5, delta);
        assertNotEquals(testingExtrapolateLeft.extrapolateLeft(1.5), 40.0);
        assertEquals(testingArrayFunction.extrapolateLeft(-80), -404, delta);
        assertEquals(testingArrayFunction.extrapolateLeft(-2.1), -14.5, delta);
        assertNotEquals(testingArrayFunction.extrapolateLeft(-2), 70, delta);
    }

    @Test
    public void testExtrapolateRight() {
        ArrayTabulatedFunction testingExtrapolateRight = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateRight.extrapolateRight(7.82), 8.82, delta);
        assertEquals(testingExtrapolateRight.extrapolateRight(3.56), 4.56, delta);
        assertNotEquals(testingExtrapolateRight.extrapolateRight(2.45), 2.18);
        assertEquals(testingArrayFunction.extrapolateRight(25), 517, delta);
        assertEquals(testingArrayFunction.extrapolateRight(69), 1793, delta);
        assertNotEquals(testingArrayFunction.extrapolateRight(69), 89, delta);

    }

    @Test
    public void testInterpolate() {
        ArrayTabulatedFunction testingInterpolate = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.00001;
        assertEquals(testingInterpolate.interpolate(7.478, 1), 8.478, delta);
        assertEquals(testingInterpolate.interpolate(7.473, 1), 8.473, delta);
        assertNotEquals(testingInterpolate.interpolate(68.247, 1), 4.237, delta);
        assertEquals(testingArrayFunction.interpolate(15, 3), 215, delta);
        assertEquals(testingArrayFunction.interpolate(19, 3), 307, delta);
        assertNotEquals(testingArrayFunction.interpolate(11, 3), 121, delta);

    }

    @Test
    public void testGetCount() {
        assertEquals(testingArrayFunction.getCount(), 6);
        assertNotEquals(testingArrayFunction.getCount(), 7);
        assertNotEquals(testingArrayFunction.getCount(), 5);
    }

    @Test
    public void testGetX() {
        ArrayTabulatedFunction testingGetX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetX.getX(1), 1.1, delta);
        assertEquals(testingGetX.getX(2), 1.2, delta);
        assertNotEquals(testingGetX.getX(1), 2.2, delta);
        assertEquals(testingArrayFunction.getX(1), 4, delta);
        assertEquals(testingArrayFunction.getX(3), 10, delta);
        assertNotEquals(testingArrayFunction.getX(3), 16, delta);

    }

    @Test
    public void testGetY() {
        ArrayTabulatedFunction testingGetY = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetY.getY(1), 2.1, delta);
        assertEquals(testingGetY.getY(2), 2.2, delta);
        assertNotEquals(testingGetY.getY(1), 3.2, delta);
        assertEquals(testingArrayFunction.getY(1), 16, delta);
        assertEquals(testingArrayFunction.getY(3), 100, delta);
        assertNotEquals(testingArrayFunction.getY(3), 162, delta);
    }

    @Test
    public void testSetY() {
        ArrayTabulatedFunction testingSetY = new ArrayTabulatedFunction(xValues, yValues);
        testingSetY.setY(1, 2.28);
        final double delta = 0.0001;
        assertEquals(testingSetY.getY(1), 2.28, delta);
        testingSetY.setY(2, 2.35);
        assertEquals(testingSetY.getY(2), 2.35, delta);
        assertNotEquals(testingSetY.getY(2), 1.45, delta);
        testingArrayFunction.setY(2, 93);
        assertEquals(testingArrayFunction.getY(2), 93, delta);
        assertNotEquals(testingArrayFunction.getY(2), 49, delta);
        testingArrayFunction.setY(4, 23);
        assertEquals(testingArrayFunction.getY(4), 23, delta);

    }

    @Test
    public void testIndexOfX() {
        ArrayTabulatedFunction testingIndexOfX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfX.indexOfX(1.3), 3, delta);
        assertEquals(testingIndexOfX.indexOfX(1.4), 4, delta);
        assertNotEquals(testingIndexOfX.indexOfX(1.5), 1, delta);
        assertEquals(testingArrayFunction.indexOfX(13), 4);
        assertEquals(testingArrayFunction.indexOfX(16), 5);
        assertNotEquals(testingArrayFunction.indexOfX(13), 1);
    }

    @Test
    public void testIndexOfY() {
        ArrayTabulatedFunction testingIndexOfY = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfY.indexOfY(2.5), -1, delta);
        assertEquals(testingIndexOfY.indexOfY(2.4), 4, delta);
        assertNotEquals(testingIndexOfY.indexOfY(2.1), 4, delta);
        assertEquals(testingArrayFunction.indexOfY(49), 2, delta);
        assertEquals(testingArrayFunction.indexOfY(169), 4, delta);
        assertNotEquals(testingArrayFunction.indexOfY(49), 6, delta);
    }

    @Test
    public void testLeftBound() {
        ArrayTabulatedFunction testingLeftBound = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingLeftBound.leftBound(), 1.0, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, 0.0001);
        final double delta = 0.0001;
        assertEquals(testingLeftBound.leftBound(), 1.0, delta);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, delta);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, delta);
        assertEquals(testingArrayFunction.leftBound(), 1, delta);
        assertNotEquals(testingArrayFunction.leftBound(), 2, delta);
        assertNotEquals(testingArrayFunction.leftBound(), 5, delta);

    }

    @Test
    public void testRightBound() {
        ArrayTabulatedFunction testingRightBound = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingRightBound.rightBound(), 1.4, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.6, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.3, 0.0001);
        final double delta = 0.0001;
        assertEquals(testingRightBound.rightBound(), 1.4, delta);
        assertNotEquals(testingRightBound.rightBound(), 1.6, delta);
        assertNotEquals(testingRightBound.rightBound(), 1.3, delta);
        assertEquals(testingArrayFunction.rightBound(), 16, delta);
        assertNotEquals(testingArrayFunction.rightBound(), 19, delta);
        assertNotEquals(testingArrayFunction.rightBound(), 27, delta);

    }
}