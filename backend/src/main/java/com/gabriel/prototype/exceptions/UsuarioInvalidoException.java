package com.gabriel.prototype.exceptions;

public class UsuarioInvalidoException extends RuntimeException {
    public UsuarioInvalidoException() {
        super("O usuário criador deve ser um campo válido!");
    }
}
