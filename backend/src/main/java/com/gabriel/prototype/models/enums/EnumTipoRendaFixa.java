package com.gabriel.prototype.models.enums;

public enum EnumTipoRendaFixa {
    CDB("CDB"),
    LCI("LCI"),
    LCA("LCA"),
    CRI("CRI"),
    CRA("CRA"),
    LC("LC"),
    LF("LF"),
    DEBENTURE("Debêntures");

    private final String descricao;

    EnumTipoRendaFixa(String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}
