package com.gabriel.prototype.exceptions.transacao;

public class TaxaTituloInvalidaException extends TransacaoException {
    public TaxaTituloInvalidaException() {
        super("[ERRO] Campo: 'taxa' - Motivo: a taxa do título deve ser maior que zero!");
    }
}