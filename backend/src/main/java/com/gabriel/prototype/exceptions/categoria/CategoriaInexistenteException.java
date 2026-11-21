package com.gabriel.prototype.exceptions.categoria;

public class CategoriaInexistenteException extends CategoriaException {
    public CategoriaInexistenteException() {
        super("[ERRO] Campo: 'categoria' - Motivo: categoria não encontrada na base de dados!");
    }
}
