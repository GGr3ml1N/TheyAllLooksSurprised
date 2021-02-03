package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.ArrayTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.Point;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

    private final double[] valuesX = new double[]{-3, -2, -1, 0, 1, 2, 3};
    private final double[] valuesY = new double[]{-6, -4, -2, 0, 2, 4, 6};
    ArrayTabulatedFunction testArrayFunction = new ArrayTabulatedFunction(valuesX, valuesY);
    LinkedListTabulatedFunction testListFunction = new LinkedListTabulatedFunction(valuesX, valuesY);

    @Test
    public void testAsPoints() {
        Point[] Points = TabulatedFunctionOperationService.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), 0.00001);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), 0.00001);
        }
        assertEquals(testArrayFunction.getCount(), i);

        Points = TabulatedFunctionOperationService.asPoints(testListFunction);
        i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testListFunction.getX(i), 0.00001);
            assertEquals(myPoint.y, testListFunction.getY(i++), 0.00001);
        }
        assertEquals(testListFunction.getCount(), i);
    }
}
