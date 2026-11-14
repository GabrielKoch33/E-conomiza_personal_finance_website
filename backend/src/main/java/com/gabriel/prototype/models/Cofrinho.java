package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.DescricaoVaziaException;
import com.gabriel.prototype.exceptions.ValorInvalidoException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cofrinho {
    private final Long id;
    private final String nome;
    private final String nomeCofrinho;
    private final LocalDate dataCriacao;
    private final boolean hasAutoDeposito;
    private final BigDecimal valorAutoDeposito;
    private final Usuario usuarioCriador;

    private Cofrinho(Builder builder)
    {
        this.id                = builder.id;
        this.nome              = builder.nome;
        this.nomeCofrinho      = builder.nomeCofrinho;
        this.dataCriacao       = builder.dataCriacao;
        this.hasAutoDeposito  = builder.hasAutoDeposito;
        this.valorAutoDeposito = builder.valorAutoDeposito;
        this.usuarioCriador    = builder.usuarioCriador;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return nomeCofrinho;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public boolean hasAutoDeposito() {
        return hasAutoDeposito;
    }

    public BigDecimal getValorAutoDeposito() {
        return valorAutoDeposito;
    }

    public Usuario getUsuario() {
        return usuarioCriador;
    }

    public static class Builder {
        private final Long id;
        private final String nome;
        private final LocalDate dataCriacao;
        private final Usuario usuarioCriador;

        private String nomeCofrinho          = "Sem descrição";
        private boolean hasAutoDeposito      = false;
        private BigDecimal valorAutoDeposito = BigDecimal.ZERO;

        public Builder(String nome,
                       LocalDate dataCriacao,Usuario usuarioCriador)
        {
            this.id             = null;
            this.nome           = nome;
            this.dataCriacao    = dataCriacao;
            this.usuarioCriador = usuarioCriador;
        }

        public Builder nomeCofrinho(String nomeCofrinho) {
            this.nomeCofrinho = nomeCofrinho;
            return this;
        }

        public Builder hasAutoDeposito(boolean hasAutoDeposito) {
            this.hasAutoDeposito = hasAutoDeposito;
            return this;
        }

        public Builder valorAutoDeposito(BigDecimal valor) {
            this.valorAutoDeposito = valor;
            return this;
        }

        public Cofrinho build() {

            if (this.nomeCofrinho == null || this.nomeCofrinho.isBlank()) {
                throw new DescricaoVaziaException();
            }

            if (this.valorAutoDeposito.compareTo(BigDecimal.ZERO) <  0)  {
                throw new ValorInvalidoException();
            }

            return new Cofrinho(this);
        }
    }
}
