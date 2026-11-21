package com.gabriel.prototype.exceptions.cofrinho;

public class DataCriacaoException extends CofrinhoException {
    public DataCriacaoException() {
        super("[ERRO] Campo: 'data de criação' - Motivo: a data de criação de cofrinhos não pode ser futura!");
    }
}
