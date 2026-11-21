package com.gabriel.prototype.exceptions.transacao;

public class UsuarioCriadorException extends TransacaoException {
    public UsuarioCriadorException() {
        super("[ERRO] Campo: 'usuário' - Motivo: transações devem possuir um usuário válido vinculado!");
    }
}