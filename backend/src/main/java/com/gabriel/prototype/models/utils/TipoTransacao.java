package com.gabriel.prototype.models.utils;

public enum TipoTransacao {
    RECEITA ("Receita"),
    DESPESA ("Despesa"),
    APLICACAO ("Aplicação"),
    RETIRADA  ("Retirada");

    public final String descricao;

    private TipoTransacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
