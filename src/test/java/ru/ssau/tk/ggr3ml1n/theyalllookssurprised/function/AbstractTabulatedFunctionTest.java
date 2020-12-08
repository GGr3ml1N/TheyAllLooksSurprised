package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import org.testng.annotations.Test;
import ru.ssau.tk.ggr3ml1n.theyalllookssurpriswd.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.ggr3ml1n.theyalllookssurpriswd.exeptions.DifferentLengthOfArraysException;

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
            double[] valuesY = new double[]{13, 14,-3};
            AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
        });
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{-80, -100, 5, 18, 90};
            AbstractTabulatedFunction.checkSorted(valuesX);
        });
    }
}