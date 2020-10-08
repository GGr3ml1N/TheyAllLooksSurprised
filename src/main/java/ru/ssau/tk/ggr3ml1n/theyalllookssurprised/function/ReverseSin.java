package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import static java.lang.Math.sin;

public class ReverseSin implements MathFunction {
    @Override
    public double apply ( double x) {
        return 1/sin(x);
    }



}
