package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.io;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.ArrayTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        File fileArray = new File("output/array function.bin");
        File fileList = new File("output/linked list function.bin");

        double[] xValue = new double[]{1.1, 1.2, 1.3, 1.4, 1.5};
        double[] yValue = new double[]{2.1, 2.2, 2.3, 2.4, 2.5};

        TabulatedFunction functionList = new LinkedListTabulatedFunction(xValue,yValue);
        TabulatedFunction functionArray = new ArrayTabulatedFunction(xValue, yValue);

        try(BufferedOutputStream outArray = new BufferedOutputStream(
                new FileOutputStream(fileArray));
            BufferedOutputStream outList = new BufferedOutputStream(
                    new FileOutputStream(fileList))){

            FunctionsIO.writeTabulatedFunction(outArray, functionArray);
            FunctionsIO.writeTabulatedFunction(outList, functionList);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
