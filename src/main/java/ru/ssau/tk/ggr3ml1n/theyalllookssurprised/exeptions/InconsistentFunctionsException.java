package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions;

import java.io.Serializable;

public class InconsistentFunctionsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -177530522746757248L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
