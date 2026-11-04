package com.gabriel.prototype.models;

public class Categoria {
    private final Long    id;
    private final String  nome;
    private final boolean padrao;

    public Categoria(Long id, String nome, boolean padrao) {
        this.id     = id;
        this.nome   = nome;
        this.padrao = padrao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isPadrao() {
        return padrao;
    }
}
