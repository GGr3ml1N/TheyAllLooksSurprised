package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions;

import java.io.Serializable;

public class DifferentLengthOfArraysException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1127237149181296890L;
    public DifferentLengthOfArraysException() {
    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
