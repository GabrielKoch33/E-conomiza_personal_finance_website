package com.gabriel.prototype.exceptions;

public class CofrinhoInvalidoException extends RuntimeException {
    public CofrinhoInvalidoException() {
        super("O cofrinho deve ser um campo válido");
    }
}
