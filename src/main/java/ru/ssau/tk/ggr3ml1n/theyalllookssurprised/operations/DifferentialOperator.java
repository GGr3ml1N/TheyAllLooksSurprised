package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.MathFunction;

public interface DifferentialOperator<T extends MathFunction>  {
    T derive(T function);
}
