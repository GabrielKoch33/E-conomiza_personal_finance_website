package com.gabriel.prototype.exceptions.transacao;

public class UsuarioCriadorException extends TransacaoException {
    public UsuarioCriadorException(String message) {
        super("[ERRO] Campo: 'usuário' - Motivo: cofrinhos devem possuir um usuário válido vinculado!");
    }
}
