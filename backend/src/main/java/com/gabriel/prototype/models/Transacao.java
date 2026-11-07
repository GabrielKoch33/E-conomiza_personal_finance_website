package com.gabriel.prototype.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gabriel.prototype.exceptions.DataFuturaException;
import com.gabriel.prototype.exceptions.DescricaoVaziaException;
import com.gabriel.prototype.exceptions.ValorInvalidoException;
import com.gabriel.prototype.models.utils.TipoTransacao;

/**
 * @author Gabriel Koch da Silva
 * @since 06/11/2026
 * Transacao
 */
public class Transacao {
    private final Long          id;
    private final BigDecimal    valor;
    private final String        descricao;
    private final LocalDate     dataTransacao;
    private final TipoTransacao tipoTransacao;
    private final Categoria     categoria;
    private final Cofrinho      cofrinho;
    private final Usuario       usuario;

    private static final String SEM_DESCRICAO = "Sem descrição";

    private Transacao(Builder builder)
    {
        this.id            = builder.id;
        this.valor         = builder.valor;
        this.descricao     = builder.descricao;
        this.dataTransacao = builder.dataTransacao;
        this.tipoTransacao = builder.tipoTransacao;
        this.categoria     = builder.categoria;
        this.cofrinho      = builder.cofrinho;
        this.usuario       = builder.usuario;
    }

    public static class Builder {
        private final Long          id;
        private final BigDecimal    valor;
        private final LocalDate     dataTransacao;
        private final TipoTransacao tipoTransacao;
        private final Usuario       usuario;

        private String    descricao = Transacao.SEM_DESCRICAO;
        private Cofrinho  cofrinho;
        private Categoria categoria;



        public Builder(Long id, BigDecimal valor,
                       LocalDate dataTransacao, TipoTransacao tipoTransacao,
                       Usuario usuario)
        {
            this.id            = id;
            this.valor         = valor;
            this.dataTransacao = dataTransacao;
            this.tipoTransacao = tipoTransacao;
            this.usuario       = usuario;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder cofrinho(Cofrinho cofrinho) {
            this.cofrinho = cofrinho;
            return this;
        }

        public Builder categoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public Transacao build() {
            if (this.valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new ValorInvalidoException();
            }

            if (this.dataTransacao.isAfter(LocalDate.now())) {
                throw new DataFuturaException();
            }

            if (this.descricao == null || this.descricao.isBlank()) {
                throw new DescricaoVaziaException();
            }

            return new Transacao(this);
        }
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Cofrinho getCofrinho() {
        return cofrinho;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
