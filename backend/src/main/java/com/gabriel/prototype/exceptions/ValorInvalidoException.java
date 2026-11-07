package com.gabriel.prototype.exceptions;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException() {
        super("O valor não pode ser menor ou igual à 0!");
    }
}
