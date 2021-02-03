package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5479335392755749232L;

    public InterpolationException() {
    }

    public InterpolationException(String message) {
        super(message);
    }
}
