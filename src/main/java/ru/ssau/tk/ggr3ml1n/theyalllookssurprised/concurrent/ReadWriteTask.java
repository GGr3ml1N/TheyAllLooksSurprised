package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.concurrent;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;

public class ReadWriteTask implements Runnable {
    private final TabulatedFunction function;

    public ReadWriteTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < function.getCount(); i++) {
            x = function.getX(i);
            synchronized (function) {
                y = function.getY(i);
                System.out.printf("%s, before write: i = %d, x = %f, y = %f \n", Thread.currentThread().getName(), i, x, y);
                function.setY(i, y + 1);
                y = function.getY(i);
            }
            System.out.printf("%s, after write: i = %d, x = %f, y = %f \n", Thread.currentThread().getName(), i, x, y);
        }
    }
}