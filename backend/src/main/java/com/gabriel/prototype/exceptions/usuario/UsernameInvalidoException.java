package com.gabriel.prototype.exceptions.usuario;

public class UsernameInvalidoException extends UsuarioException {
    public UsernameInvalidoException() {
        super("[ERRO] Campo: 'username' - Motivo: o username não pode ser vazio nem possuir mais de 30 caracteres!");
    }
}