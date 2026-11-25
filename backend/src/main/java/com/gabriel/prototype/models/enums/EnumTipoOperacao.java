package com.gabriel.prototype.models.enums;

public enum EnumTipoOperacao {
    ENTRADA ("Entrada"),
    SAIDA ("Saída");

    private final String descricao;

    EnumTipoOperacao(String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}
