package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected int count;

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return (extrapolateLeft(x));
        }
        if (x > rightBound()) {
            return (extrapolateRight(x));
        }
        if (indexOfX(x) != -1) {
            return (getY(indexOfX(x)));
        }
        return (interpolate(x, floorIndexOfX(x)));
    }


}
