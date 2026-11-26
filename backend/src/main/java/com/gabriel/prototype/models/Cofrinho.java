package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.cofrinho.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cofrinho {
    private final Long id;
    private final String nome;
    private final LocalDateTime dataCriacao;
    private final boolean hasAutoDeposito;
    private final BigDecimal valorAutoDeposito;
    private final Usuario usuarioCriador;

    private Cofrinho(Builder builder)
    {
        this.id                = builder.id;
        this.nome              = builder.nome;
        this.dataCriacao       = builder.dataCriacao;
        this.hasAutoDeposito   = builder.hasAutoDeposito;
        this.valorAutoDeposito = builder.valorAutoDeposito;
        this.usuarioCriador    = builder.usuarioCriador;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataCriacao() {
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
        private final LocalDateTime dataCriacao;
        private final Usuario usuarioCriador;

        private boolean hasAutoDeposito      = false;
        private BigDecimal valorAutoDeposito = BigDecimal.ZERO;

        public Builder(String nome,
                       LocalDateTime dataCriacao,Usuario usuarioCriador)
        {
            this.id = null;
            this.nome = nome;
            this.dataCriacao = dataCriacao;
            this.usuarioCriador = usuarioCriador;
        }

        public Builder hasAutoDeposito() {
            this.hasAutoDeposito = true;
            return this;
        }

        public Builder valorAutoDeposito(BigDecimal valor) {
            this.valorAutoDeposito = valor;
            return this;
        }

        public Cofrinho build() {
            if (this.nome == null || this.nome.isBlank()) throw new NomeCofrinhoVazioException();

            if (this.dataCriacao == null || this.dataCriacao.isAfter(LocalDateTime.now())) throw new DataCriacaoException();

            if (this.hasAutoDeposito && this.valorAutoDeposito.compareTo(BigDecimal.ZERO) <= 0) throw new ValorAutoDepositoException();

            if (this.usuarioCriador == null) throw new UsuarioCriadorInvalidoException();

            return new Cofrinho(this);
        }
    }
}
