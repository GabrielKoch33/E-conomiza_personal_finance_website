package com.gabriel.prototype.exceptions;

public class DataFuturaException extends RuntimeException {
    public DataFuturaException() {
        super("A data não pode ser futura!");
    }
}
