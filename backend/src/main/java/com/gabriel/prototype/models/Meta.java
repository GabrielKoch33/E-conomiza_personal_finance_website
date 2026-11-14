package com.gabriel.prototype.models;

import java.math.BigDecimal;

public class Meta {
    private final Long       id;
    private final BigDecimal valorAlvo;
    private final Cofrinho   cofrinho;

    public Meta (BigDecimal valorAlvo, Cofrinho cofrinho) {
        this.id        = null;
        this.valorAlvo = valorAlvo;
        this.cofrinho  = cofrinho;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValorAlvo() {
        return valorAlvo;
    }

    public Cofrinho getCofrinho() {
        return cofrinho;
    }
}
