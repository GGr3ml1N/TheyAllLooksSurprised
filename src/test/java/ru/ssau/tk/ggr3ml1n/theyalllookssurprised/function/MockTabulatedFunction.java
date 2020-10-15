package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;


public class MockTabulatedFunction extends AbstractTabulatedFunction {

    private static final double x0 = 1.0;
    private static final double x1 = 2.0;
    private static final double y0 = 3.0;
    private static final double y1 = 4.0;

    @Override
    protected int floorIndexOfX(double x) {
        return indexOfX(x1);
    }

    @Override
    protected double extrapolateLeft(double x) {
        return getY(indexOfX(x0));
    }

    @Override
    protected double extrapolateRight(double x) {
        return getY(indexOfX(x1));
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }

    @Override
    public double getX(int index) {
        if (index == 0) {
            return x0;
        } else {
            return x1;
        }
    }

    @Override
    public double getY(int index) {
        if (index == 0) {
            return y0;
        } else {
            return y1;
        }
    }

    @Override
    public void setY(int index, double value) {
    }

    @Override
    public int indexOfX(double x) {
        if (x == x0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int indexOfY(double y) {
        if (y == y0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public double leftBound() {
        return x0;
    }

    @Override
    public double rightBound() {
        return x1;
    }
    @Override
    public int getCount() {
        return 2;
    }
}
