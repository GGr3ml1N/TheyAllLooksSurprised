package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

public class ConstantFunction implements MathFunction {

    private final double constanta;

    public ConstantFunction(double constanta) {
        this.constanta = constanta;
    }

    @Override
    public double apply(double x) {
        return constanta;
    }

    public double getConstanta() {
        return constanta;
    }
}

