package com.gabriel.prototype.models;

/**
 * Não irei implementar funções de excluir e editar categorias nesse 'projeto',
 * isso fica para a versão web, aqui vai focar em aprendizado e tradução do
 * Python para Java.
 * **/
public class Categoria {
    private final String nome;
    private final boolean isDefault;

    /**
     * Construtores não param sua execução ao esbarrar com 'return;'
     * por isso lançamos uma exception no construtor, para garantir
     * que sempre um objeto válido seja criado.
     * **/
    private Categoria(String nome) {
        this.nome      = nome;
        this.isDefault = false;
    }

    private Categoria(String nome, boolean isDefault) {
        this.nome      = nome;
        this.isDefault = isDefault;
    }

    public static Categoria criarCategoriaSistema(String nome, boolean isDefault) {
        return new Categoria(nome, isDefault);
    }

    public static Categoria criarCategoriaUsuario(String nome) {
        return new Categoria(nome);
    }

    public String getNome() {
        return this.nome;
    }

    public boolean getIsDefault() {
        return this.isDefault;
    }
}
