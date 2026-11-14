package com.gabriel.prototype.enums;

public enum TipoInvestimento {
    RENDA_FIXA ("Renda Fixa"),
    RENDA_VARIAVEL ("Renda Variável"),
    CRIPTO ("Criptomoeda");

    private final String descricao;

    TipoInvestimento(String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}
