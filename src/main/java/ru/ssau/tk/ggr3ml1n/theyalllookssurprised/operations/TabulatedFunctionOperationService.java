package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.Point;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point myPoint : tabulatedFunction) {
            points[i++] = myPoint;
        }
        return points;
    }
}
