package com.gabriel.prototype.models.enums;

public enum EnumTipoRendaVariavel {
    ACAO ("Ação"),
    ETF  ("ETF"),
    FII  ("FII");

    private final String descricao;

    EnumTipoRendaVariavel(String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}
