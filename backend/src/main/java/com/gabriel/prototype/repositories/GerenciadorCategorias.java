package com.gabriel.prototype.repositories;

import com.gabriel.prototype.models.Categoria;

import java.util.HashMap;
import java.util.Map;

/**
 * Basicamente nosso Service, valida as regras de não inserir duplicadas
 * assim como faz um papel de banco de dados ao guardar nossas Categorias.
 * **/
public class GerenciadorCategorias {
    private static final Map<String, Categoria> categorias;
    static {
        categorias = new HashMap<>();
        categorias.put("Sem categoria",     Categoria.
                criarCategoriaSistema("Sem categoria", true));
        categorias.put("Alimentação",       Categoria.
                criarCategoriaSistema("Alimentação",true));
        categorias.put("Transporte",        Categoria.
                criarCategoriaSistema("Transporte",true));
        categorias.put("Saúde",             Categoria.
                criarCategoriaSistema("Saúde",true));
        categorias.put("Educação",          Categoria.
                criarCategoriaSistema("Educação", true));
        categorias.put("Assinatura",        Categoria.
                criarCategoriaSistema("Assinatura",true));
        categorias.put("Contas",            Categoria.
                criarCategoriaSistema("Contas",true));
        categorias.put("PIX",               Categoria.
                criarCategoriaSistema("PIX",true));
        categorias.put("Salário",           Categoria.
                criarCategoriaSistema("Salário",true));
        categorias.put("Cartão de crédito", Categoria.
                criarCategoriaSistema("Cartão de crédito", true));
    }

    /**
     * Adiciona uma nova categoria no sistema.
     *
     * @param nome O nome da categoria a ser adicionada.
     * @return true se foi adicionada, false se a categoria já existia.
     * @throws IllegalArgumentException se o nome for inválido ou nulo (validaTexto).
     */
    public static boolean adicionarNovaCategoria(String nome, Categoria categoria) throws IllegalArgumentException {
        String nomeFormat = validaTexto(nome);

        if (!jaExiteCategoria(nomeFormat)) {
            categorias.put(nomeFormat, Categoria.criarCategoriaUsuario(nomeFormat));
            return true;
        }

        return false;
    }

    public static void alterarCategoriaExistente (String categoriaAlvo, String novoNomeCategoria ) {
        // lógica aqui
    }

    public static void removerCategoriaExistente (String categoriaAlvo) {
        // lógica aqui
    }

    public static boolean jaExiteCategoria(String nome) {
        return categorias.containsKey(nome);
    }

    private static String validaTexto(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio!");
        }
        return nome.toLowerCase().substring(0,1).toUpperCase().substring(1);
    }
}
