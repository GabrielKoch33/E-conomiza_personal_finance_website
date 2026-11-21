package com.gabriel.prototype.exceptions.cofrinho;

public class UsuarioCriadorInvalidoException extends CofrinhoException {
    public UsuarioCriadorInvalidoException(String message) {
        super("[ERRO] Campo: 'usuário' - Motivo: cofrinhos devem possuir um usuário válido vinculado!");
    }
}
