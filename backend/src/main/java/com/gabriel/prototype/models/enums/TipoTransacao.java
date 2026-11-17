package com.gabriel.prototype.models.enums;

public enum TipoTransacao {
    MOVIMENTACAO ("Movimentação"),
    COFRINHO ("Cofrinho"),
    INVESTIMENTO ("Investimento");

    private final String descricao;

    TipoTransacao(String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}
