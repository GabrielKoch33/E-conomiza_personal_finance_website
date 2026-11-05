package com.gabriel.prototype.models;

public class Categoria {
    private final Long    id;
    private final String  nome;
    private final boolean isPadrao;

    public Categoria(Long id, String nome, boolean padrao) {
        this.id       = id;
        this.nome     = nome;
        this.isPadrao = padrao;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public boolean getPadrao() {
        return this.isPadrao;
    }
}
