package com.gabriel.prototype.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cofrinho {
    private final Long id;
    private final String nome;
    private final String descricao;
    private final LocalDate dtCriacao;
    private final boolean isAutoDepositoOn;
    private final BigDecimal valorAutoDeposito;
    private final Usuario usuario;

    public Cofrinho(Long id, String nome, String descricao,
                    LocalDate dtCriacao, boolean isAutoDepositoOn, BigDecimal valorAutoDeposito,
                    Usuario usuario)
    {
        this.id                = id;
        this.nome              = nome;
        this.descricao         = descricao;
        this.dtCriacao         = dtCriacao;
        this.isAutoDepositoOn  = isAutoDepositoOn;
        this.valorAutoDeposito = valorAutoDeposito;
        this.usuario           = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public boolean isAutoDepositoOn() {
        return isAutoDepositoOn;
    }

    public BigDecimal getValorAutoDeposito() {
        return valorAutoDeposito;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
