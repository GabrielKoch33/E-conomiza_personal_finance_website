package com.gabriel.prototype.exceptions;

public abstract class EconomizaException extends RuntimeException {
    protected EconomizaException(String message) {
        super(message);
    }
}
