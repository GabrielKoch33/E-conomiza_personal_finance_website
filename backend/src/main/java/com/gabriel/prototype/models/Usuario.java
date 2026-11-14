package com.gabriel.prototype.models;

public class Usuario {
    private final Long   id;
    private final String nome ;
    private final String username;
     private final String senhaHash;

    public Usuario (String nome, String username, String senhaHash) {
        this.id        = null;
        this.nome      = nome ;
        this.username  = username;
        this.senhaHash = senhaHash;
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
        return this.senhaHash;
    }
}
