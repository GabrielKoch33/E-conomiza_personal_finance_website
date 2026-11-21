package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.MetaInvalidaException;
import com.gabriel.prototype.exceptions.ValorInvalidoException;

import java.math.BigDecimal;

public class Meta {
    private final Long       id;
    private final BigDecimal valorAlvo;
    private final Cofrinho   cofrinho;

    public Meta (BigDecimal valorAlvo, Cofrinho cofrinho) {
        this.id = null;
        if (valorAlvo.compareTo(BigDecimal.ZERO) <= 0) throw new ValorInvalidoException();
        this.valorAlvo = valorAlvo;
        if (cofrinho == null) throw new MetaInvalidaException();
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
