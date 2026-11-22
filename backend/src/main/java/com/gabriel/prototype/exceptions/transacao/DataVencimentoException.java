package com.gabriel.prototype.exceptions.transacao;

public class DataVencimentoException extends TransacaoException {
    public DataVencimentoException() {
        super("[ERRO] Campo: 'data de vencimento' - Motivo: a data de vencimento do título não pode ser no passado!");
    }
}
