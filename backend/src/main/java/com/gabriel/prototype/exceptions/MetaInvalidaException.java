package com.gabriel.prototype.exceptions;

public class MetaInvalidaException extends RuntimeException {
    public MetaInvalidaException() {
        super("Uma meta não pode existir sem estar vinculada à um cofrinho!");
    }
}
