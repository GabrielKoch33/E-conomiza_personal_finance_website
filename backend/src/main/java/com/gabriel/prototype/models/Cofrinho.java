package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.DescricaoVaziaException;
import com.gabriel.prototype.exceptions.ValorInvalidoException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cofrinho {
    private final Long id;
    private final String nome;
    private final String descricao;
    private final LocalDate dataCriacao;
    private final boolean isAutoDepositoOn;
    private final BigDecimal valorAutoDeposito;
    private final Usuario usuario;

    private Cofrinho(Builder builder)
    {
        this.id                = builder.id;
        this.nome              = builder.nome;
        this.descricao         = builder.descricao;
        this.dataCriacao       = builder.dataCriacao;
        this.isAutoDepositoOn  = builder.isAutoDepositoOn;
        this.valorAutoDeposito = builder.valorAutoDeposito;
        this.usuario           = builder.usuario;
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
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

    public static class Builder {
        private final Long id;
        private final String nome;
        private final LocalDate dataCriacao;
        private final Usuario usuario;

        private String descricao             = "Sem descrição";
        private boolean isAutoDepositoOn     = false;
        private BigDecimal valorAutoDeposito = BigDecimal.ZERO;

        public Builder(Long id, String nome,
                       LocalDate dataCriacao,Usuario usuario)
        {
            this.id          = id;
            this.nome        = nome;
            this.dataCriacao = dataCriacao;
            this.usuario     = usuario;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder hasAutoDeposito(boolean isAutoDepositoOn) {
            this.isAutoDepositoOn = isAutoDepositoOn;
            return this;
        }

        public Builder valorAutoDeposito(BigDecimal valor) {
            this.valorAutoDeposito = valor;
            return this;
        }

        public Cofrinho build() {

            if (this.descricao == null || this.descricao.isBlank()) {
                throw new DescricaoVaziaException();
            }

            if (this.valorAutoDeposito.compareTo(BigDecimal.ZERO) < 0)  {
                throw new ValorInvalidoException();
            }

            return new Cofrinho(this);
        }
    }
}
