package com.gabriel.prototype.models.enums;

public enum EnumTipoTransacao {
    MOVIMENTACAO("Movimentação"),
    COFRINHO("Cofrinho"),
    INVESTIMENTO("Investimento");

    private final String descricao;

    private EnumTipoTransacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
