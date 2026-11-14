package com.gabriel.prototype.models;

public class Usuario {
    private final Long   id;
    private final String nome;
    private final String username;
    private final String senha;

    public Usuario (String nome, String username, String senha) {
        this.id       = null;
        this.nome     = nome;
        this.username = username;
        this.senha    = senha;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getUsername() {
        return this.username;
    }

    public String getSenha() {
        return this.senha;
    }
}
