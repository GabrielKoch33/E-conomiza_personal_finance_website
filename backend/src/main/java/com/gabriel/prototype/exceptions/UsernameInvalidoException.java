package com.gabriel.prototype.exceptions;

public class UsernameInvalidoException extends RuntimeException {
    public UsernameInvalidoException() {
        super("O campo 'username' não pode ser vazio ou possuir mais de 30 caracteres!");
    }
}
