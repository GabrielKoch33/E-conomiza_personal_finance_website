package com.gabriel.prototype.exceptions.transacao;

public class QuantidadeCompradaException extends TransacaoException {
    public QuantidadeCompradaException(String message) {
        super("[ERRO] Campo: 'quantidade comprada' - Motivo: a quantidade transacionada não pode ser menor ou igual a zero!");
    }
}
