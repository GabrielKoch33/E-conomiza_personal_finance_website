package com.gabriel.prototype.models.enums;

public enum EnumTipoIndexador {
    SELIC ("Selic"),
    IPCA ("IPCA"),
    CDI ("CDI"),
    PRE_FIXADO ("Pré-Fixado");

    private final String descricao;

    EnumTipoIndexador(String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}