package com.gabriel.prototype.enums;

public enum TipoOperacao {
    ENTRADA ("Entrada"),
    SAIDA ("Saída");

    private final String descricao;

    TipoOperacao (String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}
