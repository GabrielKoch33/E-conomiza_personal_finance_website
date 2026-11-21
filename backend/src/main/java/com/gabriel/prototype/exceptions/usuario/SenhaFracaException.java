package com.gabriel.prototype.exceptions.usuario;

public class SenhaFracaException extends UsuarioException {
    public SenhaFracaException() {
        super("[ERRO] Campo: 'senha' - Motivo: a senha deve possuir no mínimo 6 caracteres!");
    }
}