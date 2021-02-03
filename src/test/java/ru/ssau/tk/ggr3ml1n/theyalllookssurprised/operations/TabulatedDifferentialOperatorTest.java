package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.ArrayTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {

    @Test
    public void testDerive() {

        TabulatedFunction testList = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        testList = differentialListOperator.derive(testList);

        assertEquals(testList.getX(0), 1);
        assertEquals(testList.getX(1), 2);
        assertEquals(testList.getX(2), 3);
        assertEquals(testList.getX(3), 4);

        assertEquals(testList.getY(0), 3);
        assertEquals(testList.getY(1), 5);
        assertEquals(testList.getY(2), 7);
        assertEquals(testList.getY(3), 7);
        assertTrue(testList instanceof LinkedListTabulatedFunction);

        TabulatedFunction testArray = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        testArray = differentialArrayOperator.derive(testArray);

        assertEquals(testArray.getX(0), 1);
        assertEquals(testArray.getX(1), 2);
        assertEquals(testArray.getX(2), 3);
        assertEquals(testArray.getX(3), 4);

        assertEquals(testArray.getY(0), 1);
        assertEquals(testArray.getY(1), 1);
        assertEquals(testArray.getY(2), 1);
        assertEquals(testArray.getY(3), 1);
        assertTrue(testArray instanceof ArrayTabulatedFunction);

    }
}