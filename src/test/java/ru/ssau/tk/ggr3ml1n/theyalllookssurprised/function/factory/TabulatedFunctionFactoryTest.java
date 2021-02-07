package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.ArrayTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;

import static org.testng.Assert.*;

public class TabulatedFunctionFactoryTest {

    @Test
    public void testCreate() {
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {10, 20, 30, 40, 50};
        TabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction newListFactory = listFactory.create(x, y);
        assertTrue(newListFactory instanceof LinkedListTabulatedFunction);
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction newArrayFactory = arrayFactory.create(x, y);
        assertTrue(newArrayFactory instanceof ArrayTabulatedFunction);
    }
}