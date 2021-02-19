package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.ui;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.DifferentLengthOfArraysException;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.InconsistentFunctionsException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ErrorsWindow {
    ErrorsWindow(Component parent, Exception e) {
        showErrorsWindow(parent, e);
    }

    public void showErrorsWindow(Component parent, Exception e) {
        String head = generateMessageForException(e);
        JOptionPane.showMessageDialog(parent, "Ошибка!", head, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException)
            return "Неверный формат числа";

        if (e instanceof ArrayIsNotSortedException)
            return "Массив точек неотсортирован";

        if (e instanceof DifferentLengthOfArraysException)
            return "Длина массивов неодинакова";

        if (e instanceof InconsistentFunctionsException)
            return "Несовместимые функции";

        if (e instanceof IllegalArgumentException)
            return "Задана некорректная функция";

        if (e instanceof IOException)
            return "Файл не найден или повреждён";

        e.printStackTrace();
        return "Неизвестная ошибка";

    }
}

