package com.gabriel.prototype.exceptions.transacao;

public class TickerInvalidoException extends TransacaoException {
    public TickerInvalidoException() {
        super("[ERRO] Campo: 'ticker' - Motivo: o ticker não pode ser vazio!");
    }
}