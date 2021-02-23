package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.io;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("res/input/array function.bin"))) {
            ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction functionArray = FunctionsIO.readTabulatedFunction(in, factory);

            System.out.println(functionArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции:");
            LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction LinkedList = FunctionsIO.readTabulatedFunction(in, factory);
            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            TabulatedFunction diffFunctionList = differentialOperator.derive(LinkedList);

            System.out.println(diffFunctionList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
