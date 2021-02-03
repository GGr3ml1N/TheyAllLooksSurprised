package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions;

import java.io.Serializable;

public class ArrayIsNotSortedException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 6296865812995382161L;

    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
