package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.InterpolationException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    protected int count;
    protected double[] xValues;
    protected double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Length less than 2 points");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Length less than 2 points");
        }
        if ((xFrom >= xTo) || (xFrom < 0) | (xTo < 0)) {
            throw new IllegalArgumentException("Incorrect parameter values");
        }

        xValues = new double[count];
        yValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = xFrom + i * (xTo - xFrom) / (count - 1);
            yValues[i] = source.apply(xValues[i]);
        }
        this.count = count;
    }

    public int getCount() {
        return count;
    }


    @Override
    protected int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            throw new IllegalArgumentException("X is less than the left border");
        }
        for (int i = 0; i + 1 < count; i++) {
            if (xValues[i + 1] > x) {
                return i;
            }
        }
        return count;
    }

    @Override
        protected double extrapolateLeft(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }


    @Override
    public double extrapolateRight(double x) {

        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {

        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }


    @Override
    public double getX(int index) {
        return xValues[index];
    }


    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }




}
