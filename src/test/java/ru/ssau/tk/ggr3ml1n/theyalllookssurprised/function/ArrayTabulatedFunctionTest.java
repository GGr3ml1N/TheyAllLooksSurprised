package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.DifferentLengthOfArraysException;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.InterpolationException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunctionTest {
    double[] xValues = new double[]{1.0, 1.1, 1.2, 1.3, 1.4};
    double[] yValues = new double[]{2.0, 2.1, 2.2, 2.3, 2.4};
    private final MathFunction source = new SqrtFunction();

    private AbstractTabulatedFunction testingArrayFunction() {
        return new ArrayTabulatedFunction(source, 1, 16, 6);
    }

    private ArrayTabulatedFunction getDefinedThroughArrays() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    private ArrayTabulatedFunction getDefinedThroughMathFunction() {
        return new ArrayTabulatedFunction(source, 1, 16, 6);
    }


    @Test
    public void testApply() {
        AbstractTabulatedFunction testingApply = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.1;
        assertEquals(testingApply.apply(-1.0), 0.0, delta);
        assertEquals(testingApply.apply(1.5), 2.5, delta);
        assertEquals(testingApply.apply(1.1), 2.1, delta);
        assertEquals(testingApply.apply(1.15), 2.15, delta);
        assertEquals(testingApply.apply(1.4), 2.4, delta);
        assertNotEquals(testingApply.apply(7.82), 1.23, delta);
        assertEquals(testingArrayFunction().apply(-1.0), 0.33, delta);
        assertEquals(testingArrayFunction().apply(1.5), 1.16, delta);
        assertEquals(testingArrayFunction().apply(1.1), 1.03, delta);
        assertEquals(testingArrayFunction().apply(1.15), 1.05, delta);
        assertEquals(testingArrayFunction().apply(1.4), 1.13, delta);
        assertNotEquals(testingArrayFunction().apply(8.46), 59.25, delta);
    }

    @Test
    public void testFloorIndexOfX() {
        AbstractTabulatedFunction testingFloorIndexOfX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.35), 3, delta);
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 5, delta);
        assertNotEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 4);
        assertEquals(testingArrayFunction().floorIndexOfX(8.93), 2);
        assertEquals(testingArrayFunction().floorIndexOfX(66.67), 6);
        assertNotEquals(testingArrayFunction().floorIndexOfX(66.67), 4);
        assertThrows(IllegalArgumentException.class, () -> getDefinedThroughArrays().floorIndexOfX(-1));
        assertThrows(IllegalArgumentException.class, () -> getDefinedThroughArrays().floorIndexOfX(-1));
        assertThrows(IllegalArgumentException.class, () -> getDefinedThroughArrays().floorIndexOfX(-3));
        assertThrows(IllegalArgumentException.class, () -> getDefinedThroughMathFunction().floorIndexOfX(-1));
        assertThrows(IllegalArgumentException.class, () -> getDefinedThroughMathFunction().floorIndexOfX(-10));
        assertThrows(IllegalArgumentException.class, () -> getDefinedThroughMathFunction().floorIndexOfX(-4));
    }

    @Test
    public void testExtrapolateLeft() {
        AbstractTabulatedFunction testingExtrapolateLeft = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.1;
        assertEquals(testingExtrapolateLeft.extrapolateLeft(1.1), 2.1, delta);
        assertEquals(testingExtrapolateLeft.extrapolateLeft(-1.5), -0.5, delta);
        assertNotEquals(testingExtrapolateLeft.extrapolateLeft(1.5), 40.0);
        assertEquals(testingArrayFunction().extrapolateLeft(-80), -26.0, delta);
        assertEquals(testingArrayFunction().extrapolateLeft(-2.1), -0.03, delta);
        assertNotEquals(testingArrayFunction().extrapolateLeft(-2), 70, delta);
    }

    @Test
    public void testExtrapolateRight() {
        AbstractTabulatedFunction testingExtrapolateRight = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.1;
        assertEquals(testingExtrapolateRight.extrapolateRight(7.82), 8.82, delta);
        assertEquals(testingExtrapolateRight.extrapolateRight(3.56), 4.56, delta);
        assertNotEquals(testingExtrapolateRight.extrapolateRight(2.45), 2.18);
        assertEquals(testingArrayFunction().extrapolateRight(25), 5.183, delta);
        assertEquals(testingArrayFunction().extrapolateRight(69), 10.96, delta);
        assertNotEquals(testingArrayFunction().extrapolateRight(69), 89, delta);

    }

    @Test
    public void testInterpolate() {
        AbstractTabulatedFunction testingInterpolate = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.1;
        assertEquals(testingInterpolate.interpolate(1.23, testingInterpolate.floorIndexOfX(1.23)), 2.23, delta);
        assertEquals(testingInterpolate.interpolate(1.15, testingInterpolate.floorIndexOfX(1.15)), 2.15, delta);
        assertNotEquals(testingInterpolate.interpolate(1.33, testingInterpolate.floorIndexOfX(1.33)), 8.43, delta);
        assertEquals(testingArrayFunction().interpolate(1.41, testingArrayFunction().floorIndexOfX(1.41)), 1.136666, delta);
        assertEquals(testingArrayFunction().interpolate(1.35, testingArrayFunction().floorIndexOfX(1.35)), 1.116, delta);
        assertNotEquals(testingArrayFunction().interpolate(1.33, testingArrayFunction().floorIndexOfX(1.33)), 8.43, delta);
        assertThrows(InterpolationException.class, () -> testingInterpolate.interpolate(0.5, 2));
        assertThrows(InterpolationException.class, () -> testingArrayFunction().interpolate(7.5, 3));
    }

    @Test
    public void testGetCount() {
        assertEquals(testingArrayFunction().getCount(), 6);
        assertNotEquals(testingArrayFunction().getCount(), 7);
        assertNotEquals(testingArrayFunction().getCount(), 5);
    }

    @Test
    public void testGetX() {
        AbstractTabulatedFunction testingGetX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.1;
        assertEquals(testingGetX.getX(1), 1.1, delta);
        assertEquals(testingGetX.getX(2), 1.2, delta);
        assertNotEquals(testingGetX.getX(1), 2.2, delta);
        assertEquals(testingArrayFunction().getX(1), 4, delta);
        assertEquals(testingArrayFunction().getX(3), 10, delta);
        assertNotEquals(testingArrayFunction().getX(3), 16, delta);

    }

    @Test
    public void testGetY() {
        AbstractTabulatedFunction testingGetY = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.1;
        assertEquals(testingGetY.getY(1), 2.1, delta);
        assertEquals(testingGetY.getY(2), 2.2, delta);
        assertNotEquals(testingGetY.getY(1), 3.2, delta);
        assertEquals(testingArrayFunction().getY(1), 2, delta);
        assertEquals(testingArrayFunction().getY(3), 3.16, delta);
        assertNotEquals(testingArrayFunction().getY(3), 162, delta);
    }

    @Test
    public void testSetY() {
        AbstractTabulatedFunction testingSetY = new ArrayTabulatedFunction(xValues, yValues);
        testingSetY.setY(1, 2.1);
        final double delta = 0.1;
        assertEquals(testingSetY.getY(1), 2.1, delta);
        testingSetY.setY(2, 2.2);
        assertEquals(testingSetY.getY(2), 2.2, delta);
        assertNotEquals(testingSetY.getY(2), 1.45, delta);
        testingArrayFunction().setY(2, 93);
        assertEquals(testingArrayFunction().getY(2), 2.64, delta);
        assertNotEquals(testingArrayFunction().getY(2), 49, delta);
        testingArrayFunction().setY(4, 23);
        assertEquals(testingArrayFunction().getY(4), 3.6, delta);

    }

    @Test
    public void testIndexOfX() {
        AbstractTabulatedFunction testingIndexOfX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.1;
        assertEquals(testingIndexOfX.indexOfX(1.3), 3, delta);
        assertEquals(testingIndexOfX.indexOfX(1.4), 4, delta);
        assertNotEquals(testingIndexOfX.indexOfX(1.5), 1, delta);
        assertEquals(testingArrayFunction().indexOfX(13), 4);
        assertEquals(testingArrayFunction().indexOfX(16), 5);
        assertNotEquals(testingArrayFunction().indexOfX(13), 1);
    }

    @Test
    public void testIndexOfY() {
        AbstractTabulatedFunction testingIndexOfY = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.1;
        assertEquals(testingIndexOfY.indexOfY(2.5), -1, delta);
        assertEquals(testingIndexOfY.indexOfY(2.4), 4, delta);
        assertNotEquals(testingIndexOfY.indexOfY(2.1), 4, delta);
        assertEquals(testingArrayFunction().indexOfY(49), -1, delta);
        assertEquals(testingArrayFunction().indexOfY(169), -1, delta);
        assertNotEquals(testingArrayFunction().indexOfY(49), 6, delta);
    }

    @Test
    public void testLeftBound() {
        AbstractTabulatedFunction testingLeftBound = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingLeftBound.leftBound(), 1.0, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, 0.0001);
        final double delta = 0.1;
        assertEquals(testingLeftBound.leftBound(), 1.0, delta);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, delta);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, delta);
        assertEquals(testingArrayFunction().leftBound(), 1, delta);
        assertNotEquals(testingArrayFunction().leftBound(), 2, delta);
        assertNotEquals(testingArrayFunction().leftBound(), 5, delta);

    }

    @Test
    public void testRightBound() {
        AbstractTabulatedFunction testingRightBound = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingRightBound.rightBound(), 1.4, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.6, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.3, 0.0001);
        final double delta = 0.1;
        assertEquals(testingRightBound.rightBound(), 1.4, delta);
        assertNotEquals(testingRightBound.rightBound(), 1.6, delta);
        assertEquals(testingArrayFunction().rightBound(), 16, delta);
        assertNotEquals(testingArrayFunction().rightBound(), 19, delta);
        assertNotEquals(testingArrayFunction().rightBound(), 27, delta);

    }

    @Test
    public void testIteratorWhile() {
        ArrayTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        Iterator<Point> myIterator = testDefinedThroughArrays.iterator();
        int i = 0;
        while (myIterator.hasNext()) {
            Point myPoint = myIterator.next();
            assertEquals(testDefinedThroughArrays.getX(i), myPoint.x);
            assertEquals(testDefinedThroughArrays.getY(i++), myPoint.y);
        }

        Iterator<Point> finalMyFirstIterator = myIterator;
        assertThrows(NoSuchElementException.class, () -> {
            finalMyFirstIterator.next();
        });
        assertEquals(testDefinedThroughArrays.getCount(), i);

        ArrayTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();
        myIterator = testDefinedThroughMathFunction.iterator();
        i = 0;
        while (myIterator.hasNext()) {
            Point myPoint = myIterator.next();
            assertEquals(testDefinedThroughMathFunction.getX(i), myPoint.x);
            assertEquals(testDefinedThroughMathFunction.getY(i++), myPoint.y);
        }
        Iterator<Point> finalMySecondIterator = myIterator;
        assertThrows(NoSuchElementException.class, () -> {
            finalMySecondIterator.next();
        });
        assertEquals(testDefinedThroughMathFunction.getCount(), i);

    }

    @Test
    public void testIteratorForEach() {
        ArrayTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        int i = 0;
        for (Point myPoint : testDefinedThroughArrays) {
            assertEquals(myPoint.x, testDefinedThroughArrays.getX(i));
            assertEquals(myPoint.y, testDefinedThroughArrays.getY(i++));
        }
        assertEquals(testDefinedThroughArrays.getCount(), i);

        ArrayTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();
        i = 0;
        for (Point myPoint : testDefinedThroughMathFunction) {
            assertEquals(myPoint.x, testDefinedThroughMathFunction.getX(i));
            assertEquals(myPoint.y, testDefinedThroughMathFunction.getY(i++));
        }
        assertEquals(testDefinedThroughMathFunction.getCount(), i);
    }

    @Test
    public void testConstructorExceptions() {
        final double[] brokenValues = {1, -1, 0};
        final double[] brokenValuesToo = {1, 8, 2};
        final double[] singleElementArray = {1};
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(singleElementArray, yValues));
        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(brokenValues, yValues));
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(brokenValues, brokenValues));
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(brokenValuesToo, brokenValuesToo));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(source, 21, 16, 10));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(source, 1, 6, 1));
    }
}