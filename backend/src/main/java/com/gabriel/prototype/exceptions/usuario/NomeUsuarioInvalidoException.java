package com.gabriel.prototype.exceptions.usuario;

public class NomeUsuarioInvalidoException extends UsuarioException {
    public NomeUsuarioInvalidoException() {
        super("[ERRO] Campo: 'nome' - Motivo: usuários não podem existir sem nome!");
    }
}