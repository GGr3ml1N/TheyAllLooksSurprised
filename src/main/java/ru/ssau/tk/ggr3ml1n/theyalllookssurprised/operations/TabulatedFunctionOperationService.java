package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.InconsistentFunctionsException;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.Point;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point myPoint : tabulatedFunction) {
            points[i++] = myPoint;
        }
        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException("The number of tabulated points in the tabulated functions is different");
        }

        Point[] aPoints = asPoints(a);
        Point[] bPoints = asPoints(b);
        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[a.getCount()];

        for (int i = 0; i < a.getCount(); i++) {
            if (aPoints[i].x != bPoints[i].x) {
                throw new InconsistentFunctionsException("X coordinates of functions are various");
            }
            xValues[i] = aPoints[i].x;
            yValues[i] = operation.apply(aPoints[i].y, bPoints[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction divide(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }
}
