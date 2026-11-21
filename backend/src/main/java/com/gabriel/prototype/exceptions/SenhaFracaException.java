package com.gabriel.prototype.exceptions;

public class SenhaFracaException extends RuntimeException {
    public SenhaFracaException() {
        super("A senha deve possuir mais de 6 caracteres!");
    }
}
