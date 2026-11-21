package com.gabriel.prototype.exceptions;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException() {
        super("A categoria deve ser um campo válido!");
    }
}
