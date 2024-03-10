package com.dmch.math.exception;

public class OperationValidationException extends RuntimeException{

    private static final String MSG = "Operation is not recognized, allowed: +, -, *, /";

    private final int errorCode = 200;

    private final int first;
    private final int second;

    public OperationValidationException(int first, int second) {
        super(MSG);
        this.first = first;
        this.second = second;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}
