package com.gabriel.prototype.exceptions.categoria;

public class NomeCategoriaVazioException extends CategoriaException {
    public NomeCategoriaVazioException() {
        super("[ERRO] Campo: 'nome' - Motivo: categorias não podem possuir existir sem nome!");
    }
}
