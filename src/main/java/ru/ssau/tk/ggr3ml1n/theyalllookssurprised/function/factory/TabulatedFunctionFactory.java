package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.*;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);
}
