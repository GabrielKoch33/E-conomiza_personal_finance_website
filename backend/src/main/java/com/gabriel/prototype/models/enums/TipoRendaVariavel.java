package com.gabriel.prototype.models.enums;

public enum TipoRendaVariavel {
    ACAO ("Ação"),
    ETF  ("ETF"),
    FII  ("FII");

    private final String descricao;

    TipoRendaVariavel (String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}
