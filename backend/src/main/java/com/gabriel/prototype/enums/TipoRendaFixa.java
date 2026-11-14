package com.gabriel.prototype.enums;

public enum TipoRendaFixa {
    CDB("CDB"),
    LCI("LCI"),
    LCA("LCA"),
    CRI("CRI"),
    CRA("CRA"),
    LC("LC"),
    LF("LF"),
    DEBENTURE("Debêntures");

    private final String descricao;

    TipoRendaFixa (String descricao) {
        this.descricao = descricao;
    }

    public final String getDescricao() {
        return this.descricao;
    }
}
