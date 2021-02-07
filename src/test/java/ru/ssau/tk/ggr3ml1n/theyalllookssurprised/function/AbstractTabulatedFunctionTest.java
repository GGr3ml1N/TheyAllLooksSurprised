package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.DifferentLengthOfArraysException;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    @Test
    public void testInterpolate() {
        AbstractTabulatedFunction mockedInterpolate = new MockTabulatedFunction();

        final double delta = 0.0001;
        assertEquals(mockedInterpolate.interpolate(1.4, 1.0, 2.0, 3.0, 4.0), 3.4, delta);
        assertEquals(mockedInterpolate.interpolate(1.5, 1.0, 2.0, 3.0, 4.0), 3.5, delta);
        assertEquals(mockedInterpolate.interpolate(1.6, 1.0, 2.0, 3.0, 4.0), 3.6, delta);
        assertNotEquals(mockedInterpolate.interpolate(1.5, 1.0, 2.0, 3.0, 4.0), 8.1);
    }


    @Test
    public void testApply() {
        AbstractTabulatedFunction mockedApply = new MockTabulatedFunction();

        final double delta = 0.0001;
        assertEquals(mockedApply.apply(1.3), 4.0, delta);
        assertEquals(mockedApply.apply(4.0), 4.0, delta);
        assertNotEquals(mockedApply.apply(1.3), 5.2);
    }

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] valuesX = new double[]{8, 78};
            double[] valuesY = new double[]{13, 14, -3};
            AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
            double[] valuesNewX = new double[]{1, 3};
            double[] valuesNewY = new double[]{2, 4};
            AbstractTabulatedFunction.checkLengthIsTheSame(valuesNewX, valuesNewY);
        });
    }

    @Test
    public void testCheckSorted() {

        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] xValues = new double[]{-10, -80, 5, 18, 90};
            AbstractTabulatedFunction.checkSorted(xValues);
            double[] xNewValues = new double[]{1, 3, 7, 9};
            AbstractTabulatedFunction.checkSorted(xNewValues);
        });
    }

    @Test
    public void testTestToString() {
        double[] x = {1.1, 1.2, 1.3};
        double[] y = {2.1, 2.2, 2.3};
        assertEquals(new ArrayTabulatedFunction(x, y).toString(), "ArrayTabulatedFunction size = 3\n[1.1; 2.1]\n[1.2; 2.2]\n[1.3; 2.3]");
        assertEquals(new LinkedListTabulatedFunction(x, y).toString(), "LinkedListTabulatedFunction size = 3\n[1.1; 2.1]\n[1.2; 2.2]\n[1.3; 2.3]");
        assertNotEquals(new ArrayTabulatedFunction(x, y).toString(), "ArrayTabulatedFunction size = 2\n[1.1; 2.1]\n[1.2; 2.2]\n[1.3; 2.3]");
        assertNotEquals(new LinkedListTabulatedFunction(x, y).toString(), "LinkedListTabulatedFunction size = 2\n[1.1; 2.1]\n[1.2; 2.2]\n[1.3; 2.3]");
    }

}