package com.gabriel.prototype.models;

import java.math.BigDecimal;

public class Meta {
    private final Long       id;
    private final String     nomeMeta;
    private final BigDecimal valorAlvo;
    private final Cofrinho   cofrinho;

    public Meta (Long id, String nomeMeta, BigDecimal valorAlvo, Cofrinho cofrinho) {
        this.id        = id;
        this.nomeMeta  = nomeMeta;
        this.valorAlvo = valorAlvo;
        this.cofrinho  = cofrinho;
    }

    public Long getId() {
        return id;
    }

    public String getNomeMeta() {
        return nomeMeta;
    }

    public BigDecimal getValorAlvo() {
        return valorAlvo;
    }

    public Cofrinho getCofrinho() {
        return cofrinho;
    }
}
