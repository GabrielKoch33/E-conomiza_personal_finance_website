package com.gabriel.prototype.exceptions.transacao;

public class TickerInvalidoException extends TransacaoException {
    public TickerInvalidoException(String message) {
        super("[ERRO] Campo: 'usuário' - Motivo: cofrinhos devem possuir um usuário válido vinculado!");
    }
}
