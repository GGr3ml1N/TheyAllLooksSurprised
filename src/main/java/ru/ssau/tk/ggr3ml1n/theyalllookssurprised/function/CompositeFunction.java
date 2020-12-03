package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

public class CompositeFunction implements MathFunction {

    private final MathFunction k_firstFunction;
    private final MathFunction secondFunction;

    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.k_firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    @Override
    public double apply(double x) {
        return secondFunction.apply(k_firstFunction.apply(x));
    }

}
