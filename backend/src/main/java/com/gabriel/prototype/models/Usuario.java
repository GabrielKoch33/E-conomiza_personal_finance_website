package com.gabriel.prototype.models;

public class Usuario {
    private final Long   id;
    private final String nome;
    private final String senha;

    public Usuario (Long id, String nome, String senha) {
        this.id    = id;
        this.nome  = nome;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
