package com.gabriel.prototype.models;

import com.gabriel.prototype.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class TransacaoBase {
    private final Long id;
    private final Usuario usuarioTransacao;
    private final TipoTransacao tipoTransacao;
    private final LocalDate dataTransacao;
    private final BigDecimal valorTransacao;

    protected TransacaoBase(Usuario usuarioTransacao,
                            TipoTransacao tipoTransacao, LocalDate dataTransacao,
                            BigDecimal valorTransacao)
    {
        this.id               = null;
        this.usuarioTransacao = usuarioTransacao;
        this.tipoTransacao    = tipoTransacao;
        this.dataTransacao    = dataTransacao;
        this.valorTransacao   = valorTransacao;
    }

    protected Long getId() {
        return id;
    }

    protected Usuario getUsuarioTransacao() {
        return usuarioTransacao;
    }

    protected TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    protected LocalDate getDataTransacao() {
        return dataTransacao;
    }

    protected BigDecimal getValorTransacao() {
        return valorTransacao;
    }
}
