package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;


public interface TabulatedFunction extends MathFunction, Iterable<Point> {
    int getCount();

    double getX(int index);

    double getY(int index);

    void setY(int index, double value);

    int indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();
}
