package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.InconsistentFunctionsException;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.ArrayTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.Point;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.LinkedListTabulatedFunctionFactory;


import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

    private final double[] valuesX = new double[]{-3, -2, -1, 0, 1, 2, 3};
    private final double[] valuesY = new double[]{-6, -4, -2, 0, 2, 4, 6};
    private final double[] valuesYForList = new double[]{10, 20, 30, 40, 50, 60, 70};

    public TabulatedFunctionOperationService serviceArray = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
    public TabulatedFunctionOperationService serviceList = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
    public LinkedListTabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
    public ArrayTabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
    public TabulatedFunction testArrayFunction = getTestArray();
    public TabulatedFunction testListFunction = getTestList();

    public TabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    public TabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesYForList);
    }


    @Test
    public void testAsPoints() {
        int i = 0;
        for (Point myPoint : TabulatedFunctionOperationService.asPoints(testArrayFunction)) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), 0.00001);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), 0.00001);
        }
        assertEquals(testArrayFunction.getCount(), i);
        assertTrue(testArrayFunction instanceof ArrayTabulatedFunction);

        i = 0;
        for (Point myPoint : TabulatedFunctionOperationService.asPoints(testListFunction)) {
            assertEquals(myPoint.x, testListFunction.getX(i), 0.00001);
            assertEquals(myPoint.y, testListFunction.getY(i++), 0.00001);
        }
        assertEquals(testListFunction.getCount(), i);
        assertTrue(testListFunction instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testGetFactory() {
        assertTrue(serviceArray.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(serviceList.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        TabulatedFunctionOperationService serviceSetList = new TabulatedFunctionOperationService();
        serviceSetList.setFactory(listFactory);
        assertTrue(serviceSetList.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        TabulatedFunctionOperationService serviceSetArray = new TabulatedFunctionOperationService();
        serviceSetArray.setFactory(arrayFactory);
        assertTrue(serviceSetArray.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {

        final double[] errorX = new double[]{0, 1, 2};
        final double[] errorY = new double[]{0, 1, 2};
        TabulatedFunction errorTest = new ArrayTabulatedFunction(errorX, errorY);
        assertThrows(InconsistentFunctionsException.class, () -> serviceArray.sum(testListFunction, errorTest));

        final double[] errorX1 = new double[]{-27, -8, -1, 0, 1, 8, 28};
        final double[] errorY1 = new double[]{-3, -2, -1, -0, 1, 2, 3};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(errorX1, errorY1);
        assertThrows(InconsistentFunctionsException.class, () -> serviceArray.sum(testListFunction, errorTest1));

        int i = 0;
        for (Point point : serviceArray.sum(testArrayFunction, testArrayFunction)) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }


        i = 0;
        for (Point point : serviceArray.sum(testListFunction, testListFunction)) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] + valuesYForList[i++]);
        }


        i = 0;
        for (Point point : serviceArray.sum(testArrayFunction, testListFunction)) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYForList[i++]);
        }
        assertTrue(serviceArray.sum(testArrayFunction, testArrayFunction) instanceof ArrayTabulatedFunction);
        assertTrue(serviceArray.sum(testListFunction, testListFunction) instanceof ArrayTabulatedFunction);
        assertTrue(serviceArray.sum(testArrayFunction, testListFunction) instanceof ArrayTabulatedFunction);

        final double[] errorListX = new double[]{0, 1, 2};
        final double[] errorListY = new double[]{0, 1, 2};
        TabulatedFunction errorListTest = new LinkedListTabulatedFunction(errorListX, errorListY);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.sum(testListFunction, errorListTest));

        final double[] errorListX1 = new double[]{-27, -8, -1, 0, 1, 8, 28};
        final double[] errorListY1 = new double[]{-3, -2, -1, -0, 1, 2, 3};
        TabulatedFunction errorListTest1 = new LinkedListTabulatedFunction(errorListX1, errorListY1);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.sum(testListFunction, errorListTest1));

        int j = 0;
        for (Point point : serviceList.sum(testArrayFunction, testArrayFunction)) {
            assertEquals(point.x, valuesX[j]);
            assertEquals(point.y, valuesY[j] + valuesY[j++]);
        }
        j = 0;
        for (Point point : serviceList.sum(testListFunction, testListFunction)) {
            assertEquals(point.x, valuesX[j]);
            assertEquals(point.y, valuesYForList[j] + valuesYForList[j++]);
        }
        j = 0;
        for (Point point : serviceList.sum(testArrayFunction, testListFunction)) {
            assertEquals(point.x, valuesX[j]);
            assertEquals(point.y, valuesY[j] + valuesYForList[j++]);
        }
        assertTrue(serviceList.sum(testArrayFunction, testArrayFunction) instanceof LinkedListTabulatedFunction);
        assertTrue(serviceList.sum(testListFunction, testListFunction) instanceof LinkedListTabulatedFunction);
        assertTrue(serviceList.sum(testArrayFunction, testListFunction) instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testSubtract() {

        int i = 0;
        for (Point point : serviceArray.subtract(testArrayFunction, testArrayFunction)) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }

        i = 0;
        for (Point point : serviceArray.subtract(testListFunction, testListFunction)) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] - valuesYForList[i++]);
        }

        i = 0;
        for (Point point : serviceArray.subtract(testArrayFunction, testListFunction)) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYForList[i++]);
        }
        assertTrue(serviceArray.subtract(testArrayFunction, testArrayFunction) instanceof ArrayTabulatedFunction);
        assertTrue(serviceArray.subtract(testListFunction, testListFunction) instanceof ArrayTabulatedFunction);
        assertTrue(serviceArray.subtract(testArrayFunction, testListFunction) instanceof ArrayTabulatedFunction);

        int j = 0;
        for (Point point : serviceList.subtract(testArrayFunction, testArrayFunction)) {
            assertEquals(point.x, valuesX[j]);
            assertEquals(point.y, valuesY[j] - valuesY[j++]);
        }
        j = 0;
        for (Point point : serviceList.subtract(testListFunction, testListFunction)) {
            assertEquals(point.x, valuesX[j]);
            assertEquals(point.y, valuesYForList[j] - valuesYForList[j++]);
        }
        j = 0;
        for (Point point : serviceList.subtract(testArrayFunction, testListFunction)) {
            assertEquals(point.x, valuesX[j]);
            assertEquals(point.y, valuesY[j] - valuesYForList[j++]);
        }
        assertTrue(serviceList.subtract(testArrayFunction, testArrayFunction) instanceof LinkedListTabulatedFunction);
        assertTrue(serviceList.subtract(testListFunction, testListFunction) instanceof LinkedListTabulatedFunction);
        assertTrue(serviceList.subtract(testArrayFunction, testListFunction) instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testMultiply() {
        double[] yValues1 = new double[]{2., 8., 32., 160.};
        double[] xValues1 = new double[]{2., 4., 6., 8.};
        double[] yValues2 = new double[]{20., 40., 60., 80.};
        double[] xValues2 = new double[]{2., 4., 6., 8.};


        int i = 0;
        for (Point point : serviceArray.multiply(arrayFactory.create(xValues1, yValues1), arrayFactory.create(xValues1, yValues1))) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] * yValues1[i++]);
        }
        i = 0;
        for (Point point : serviceArray.multiply(listFactory.create(xValues2, yValues2), listFactory.create(xValues2, yValues2))) {
            assertEquals(point.x, xValues2[i]);
            assertEquals(point.y, yValues2[i] * yValues2[i++]);
        }
        i = 0;
        for (Point point : serviceArray.multiply(arrayFactory.create(xValues1, yValues1), listFactory.create(xValues2, yValues2))) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] * yValues2[i++]);
        }
        assertTrue(serviceArray.multiply(arrayFactory.create(xValues1, yValues1), arrayFactory.create(xValues1, yValues1)) instanceof ArrayTabulatedFunction);
        assertTrue(serviceArray.multiply(listFactory.create(xValues2, yValues2), listFactory.create(xValues2, yValues2)) instanceof ArrayTabulatedFunction);
        assertTrue(serviceArray.multiply(arrayFactory.create(xValues1, yValues1), listFactory.create(xValues2, yValues2)) instanceof ArrayTabulatedFunction);


        int j = 0;
        for (Point point : serviceList.multiply(arrayFactory.create(xValues1, yValues1), arrayFactory.create(xValues1, yValues1))) {
            assertEquals(point.x, xValues1[j]);
            assertEquals(point.y, yValues1[j] * yValues1[j++]);
        }
        j = 0;
        for (Point point : serviceList.multiply(listFactory.create(xValues2, yValues2), listFactory.create(xValues2, yValues2))) {
            assertEquals(point.x, xValues2[j]);
            assertEquals(point.y, yValues2[j] * yValues2[j++]);
        }
        j = 0;
        for (Point point : serviceList.multiply(arrayFactory.create(xValues1, yValues1), listFactory.create(xValues2, yValues2))) {
            assertEquals(point.x, xValues1[j]);
            assertEquals(point.y, yValues1[j] * yValues2[j++]);
        }
        assertTrue(serviceList.multiply(arrayFactory.create(xValues1, yValues1), arrayFactory.create(xValues1, yValues1)) instanceof LinkedListTabulatedFunction);
        assertTrue(serviceList.multiply(listFactory.create(xValues2, yValues2), listFactory.create(xValues2, yValues2)) instanceof LinkedListTabulatedFunction);
        assertTrue(serviceList.multiply(arrayFactory.create(xValues1, yValues1), listFactory.create(xValues2, yValues2)) instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testDivide() {
        double[] yValues1 = new double[]{2., 8., 32., 160.};
        double[] xValues1 = new double[]{2., 4., 6., 8.};
        double[] yValues2 = new double[]{20., 40., 60., 80.};
        double[] xValues2 = new double[]{2., 4., 6., 8.};


        int i = 0;
        for (Point point : serviceArray.divide(arrayFactory.create(xValues1, yValues1), arrayFactory.create(xValues1, yValues1))) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] / yValues1[i++]);
        }
        i = 0;
        for (Point point : serviceArray.divide(listFactory.create(xValues2, yValues2), listFactory.create(xValues2, yValues2))) {
            assertEquals(point.x, xValues2[i]);
            assertEquals(point.y, yValues2[i] / yValues2[i++]);
        }
        i = 0;
        for (Point point : serviceArray.divide(arrayFactory.create(xValues1, yValues1), listFactory.create(xValues2, yValues2))) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] / yValues2[i++]);
        }
        assertTrue(serviceArray.divide(arrayFactory.create(xValues1, yValues1), arrayFactory.create(xValues1, yValues1)) instanceof ArrayTabulatedFunction);
        assertTrue(serviceArray.divide(listFactory.create(xValues2, yValues2), listFactory.create(xValues2, yValues2)) instanceof ArrayTabulatedFunction);
        assertTrue(serviceArray.divide(arrayFactory.create(xValues1, yValues1), listFactory.create(xValues2, yValues2)) instanceof ArrayTabulatedFunction);

        int j = 0;
        for (Point point : serviceList.divide(arrayFactory.create(xValues1, yValues1), arrayFactory.create(xValues1, yValues1))) {
            assertEquals(point.x, xValues1[j]);
            assertEquals(point.y, yValues1[j] / yValues1[j++]);
        }
        j = 0;
        for (Point point : serviceList.divide(listFactory.create(xValues2, yValues2), listFactory.create(xValues2, yValues2))) {
            assertEquals(point.x, xValues2[j]);
            assertEquals(point.y, yValues2[j] / yValues2[j++]);
        }
        j = 0;
        for (Point point : serviceList.divide(arrayFactory.create(xValues1, yValues1), listFactory.create(xValues2, yValues2))) {
            assertEquals(point.x, xValues1[j]);
            assertEquals(point.y, yValues1[j] / yValues2[j++]);
        }
        assertTrue(serviceList.divide(arrayFactory.create(xValues1, yValues1), arrayFactory.create(xValues1, yValues1)) instanceof LinkedListTabulatedFunction);
        assertTrue(serviceList.divide(listFactory.create(xValues2, yValues2), listFactory.create(xValues2, yValues2)) instanceof LinkedListTabulatedFunction);
        assertTrue(serviceList.divide(arrayFactory.create(xValues1, yValues1), listFactory.create(xValues2, yValues2)) instanceof LinkedListTabulatedFunction);
    }
}

