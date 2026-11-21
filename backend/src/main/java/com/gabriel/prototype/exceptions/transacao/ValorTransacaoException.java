package com.gabriel.prototype.exceptions.transacao;

public class ValorTransacaoException extends TransacaoException {
    public ValorTransacaoException() {
        super("[ERRO] Campo: 'valor' - Motivo: o valor da transação deve ser maior que zero!");
    }
}