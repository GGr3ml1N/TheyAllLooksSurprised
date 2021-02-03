package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
