package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.io;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.ArrayTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        File fileArray = new File("res/output/array function.txt.rtf.rtf");
        File fileList = new File("res/output/linked list function.txt.rtf.rtf");

        double[] xValue = new double[]{1, 2, 3, 4, 5};
        double[] yValue = new double[]{2, 4, 6, 8, 10};

        TabulatedFunction functionList = new LinkedListTabulatedFunction(xValue, yValue);
        TabulatedFunction functionArray = new ArrayTabulatedFunction(xValue, yValue);

        try (BufferedWriter outArray = new BufferedWriter(
                new FileWriter(fileArray));
             BufferedWriter outList = new BufferedWriter(
                     new FileWriter(fileList))) {

            FunctionsIO.writeTabulatedFunction(outArray, functionArray);
            FunctionsIO.writeTabulatedFunction(outList, functionList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
