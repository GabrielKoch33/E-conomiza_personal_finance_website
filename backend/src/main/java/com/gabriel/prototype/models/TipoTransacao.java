package com.gabriel.prototype.models;

public enum TipoTransacao {
    RECEITA ("Receita"),
    DESPESA ("Despesa"),
    DEPOSITO_COFRINHO ("Depósito em Cofrinho"),
    RETIRADA_COFRINHO ("Retirada de Cofrinho");

    public final String DESCRICAO;

    private TipoTransacao(String descricao) {
        this.DESCRICAO = descricao;
    }

    public String getDESCRICAO() {
        return this.DESCRICAO;
    }
}
