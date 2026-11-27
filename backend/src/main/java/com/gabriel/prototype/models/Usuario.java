package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.usuario.*;

public class Usuario {
    private final Long   id;
    private final String nome ;
    private final String username;
    private final String senha;

    public Usuario (Long id, String nome, String username, String senha) {
        this.id = id;
        if (nome == null || nome.isBlank()) throw new NomeUsuarioInvalidoException();
        this.nome = nome ;
        if (username == null || username.isBlank() || username.length() > 30) throw new UsernameInvalidoException();
        this.username = username;
        if (senha == null || senha.isBlank() || senha.length() < 6) throw new SenhaFracaException();
        this.senha = senha;
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

    public String getSenhaHash() {
        return this.senha;
    }
}
