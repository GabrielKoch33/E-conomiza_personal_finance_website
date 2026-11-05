package com.gabriel.prototype.models;

public class Ativos {
    private final Long id;
    private final String ticker;
    private final String nome;
    private final int quantidadeTotalAtivo;
    private final Usuario usuario;

    public Ativos(Long id, String ticker, String nome, int quantidadeTotalAtivo, Usuario usuario) {
        this.id                   = id;
        this.ticker               = ticker;
        this.nome                 = nome;
        this.quantidadeTotalAtivo = quantidadeTotalAtivo;
        this.usuario              = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getTicker() {
        return ticker;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeTotalAtivo() {
        return quantidadeTotalAtivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
