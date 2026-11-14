package com.gabriel.prototype.enums;

public enum TipoIndexador {
    SELIC ("Selic"),
    IPCA ("IPCA"),
    CDI ("CDI"),
    PRE_FIXADO ("Pré-Fixado");

    private final String descricao;

    TipoIndexador (String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}