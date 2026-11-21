package com.gabriel.prototype.exceptions;

public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException() {
        super("O campo não pode conter um nome vazio!");
    }
}
