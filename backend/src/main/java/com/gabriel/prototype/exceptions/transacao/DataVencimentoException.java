package com.gabriel.prototype.exceptions.transacao;

public class DataVencimentoException extends TransacaoException {
    public DataVencimentoException(String message) {
        super("[ERRO] Campo: 'data de vencimento' - Motivo: a data de vencimento do título não pode ser futura!");
    }
}
