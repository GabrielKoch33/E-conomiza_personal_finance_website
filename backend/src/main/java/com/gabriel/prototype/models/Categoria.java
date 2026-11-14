package com.gabriel.prototype.models;

public class Categoria {
    private final Long    id;
    private final String  nome;
    private final boolean isPadrao;
    private final Usuario usuarioCriador;

    public Categoria(String nome, boolean padrao, Usuario usuarioCriador) {
        this.id       = null;
        this.nome     = nome;
        this.isPadrao = padrao;
        this.usuarioCriador = usuarioCriador;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public boolean isPadrao() {
        return this.isPadrao;
    }

    public Usuario getUsuarioCriador() {
        return this.usuarioCriador;
    }
}
