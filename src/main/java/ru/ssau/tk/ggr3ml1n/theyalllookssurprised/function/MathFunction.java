package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

interface MathFunction {

    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }

}