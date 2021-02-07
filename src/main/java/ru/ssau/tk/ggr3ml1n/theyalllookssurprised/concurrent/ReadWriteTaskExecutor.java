package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.concurrent;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.ZeroFunction;

import java.util.ArrayList;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        ArrayList<Thread> list = new ArrayList<>();
        ReadWriteTask myTask = new ReadWriteTask(function);
        for (int i = 0; i < 20; i++) {
            list.add(new Thread(myTask));
        }
        for (Thread thread : list) {
            thread.start();
        }
        Thread.sleep(2_000);
        System.out.println(function.toString());
    }
}
