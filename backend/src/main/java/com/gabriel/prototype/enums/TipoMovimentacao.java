package com.gabriel.prototype.enums;

public enum TipoMovimentacao {
    ENTRADA ("Entrada"),
    SAIDA ("Saída");

    private final String descricao;

    TipoMovimentacao(String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}
