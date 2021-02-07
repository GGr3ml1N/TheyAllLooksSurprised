package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.MathFunction;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x) - function.apply(x - step)) / step;
    }
}
