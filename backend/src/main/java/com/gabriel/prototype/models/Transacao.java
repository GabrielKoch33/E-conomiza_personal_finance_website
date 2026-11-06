package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.DataFuturaInvalidaException;
import com.gabriel.prototype.exceptions.ValorInvalidoException;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * As classes dentro de Model buscam representar tabelas do banco de dados. <br><br>
 * Dentro dela estará contida a lógica intrínseca do objeto, ou seja, aquela não depende
 * necessáriamente de outros objetos. <br><br>
 *
 * Validar o valor positivo ou o preenchimento de um campo pode ser feito pelo Model.<br><br>
 * Evitar criar um objeto duplicado deve ser feito pelo Service, pois o service é capaz também
 * de conversar com Repository.
 * **/
public class Transacao {
    private final Long          id;
    private final BigDecimal    valor;
    private final String        descricao;
    private final LocalDate     dataTransacao;
    private final TipoTransacao tipoTransacao;
    private final Categoria     categoria;
    private final Cofrinho      cofrinho;
    private final Usuario       usuario;

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

        private String    descricao;
        private Cofrinho  cofrinho;
        private Categoria categoria;

        public Builder(Long id, BigDecimal valor,
                       LocalDate dataTransacao, TipoTransacao tipoTransacao,
                       Usuario usuario) {
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
            if (this.valor.compareTo(BigDecimal.ZERO) == 0) {
                throw new ValorInvalidoException("O valor não pode ser menor ou igual à 0!");
            }
            if (this.dataTransacao.isAfter(LocalDate.now())) {
                throw new DataFuturaInvalidaException("A data não pode possuir um valor futuro!");
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

    public LocalDate getDtTransacao() {
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
