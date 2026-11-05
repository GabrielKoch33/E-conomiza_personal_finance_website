package com.gabriel.prototype.models;

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
    private final LocalDate     dtTransacao;
    private final TipoTransacao tipoTransacao;
    private final Categoria     categoria;
    private final Cofrinho      cofrinho;
    private final Usuario       usuario;

    private Transacao(Long id, BigDecimal valor,
                     String descricao, LocalDate dtTransacao,
                     TipoTransacao tipoTransacao, Categoria categoria,
                     Cofrinho cofrinho, Usuario usuario)
    {
        this.id            = id;
        this.valor         = valor;
        this.descricao     = descricao;
        this.dtTransacao   = dtTransacao;
        this.tipoTransacao = tipoTransacao;
        this.categoria     = categoria;
        this.cofrinho      = cofrinho;
        this.usuario       = usuario;
    }

    public static Transacao factoryTransacao() {

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
        return dtTransacao;
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
