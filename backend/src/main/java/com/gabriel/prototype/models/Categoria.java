package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.categoria.NomeCategoriaVazioException;

public class Categoria {
    private final Long id;
    private final String  nome;
    private final boolean isPadrao;
    private final Usuario usuarioCriador;

    public Categoria(String nome, boolean padrao, Usuario usuarioCriador) {
        this.id = null;
        this.isPadrao = padrao;
        if (nome == null || nome.isBlank()) throw new NomeCategoriaVazioException();
        this.nome = nome;
        if (usuarioCriador == null) throw new UsuarioInvalidoException();
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
