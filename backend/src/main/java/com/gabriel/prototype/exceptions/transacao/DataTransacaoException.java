package com.gabriel.prototype.exceptions.transacao;

public class DataTransacaoException extends TransacaoException {
    public DataTransacaoException() {
        super("[ERRO] Campo: 'data da transação' - Motivo: a data da transação não pode ser futura!");
    }
}
