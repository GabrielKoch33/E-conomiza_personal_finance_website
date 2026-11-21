package com.gabriel.prototype.exceptions.categoria;

public class CategoriaDuplicadaException extends CategoriaException {
    public CategoriaDuplicadaException() {
        super("[ERRO] Campo: 'categoria' - Motivo: categoria já existe em nossa base de dados! Duplicadas não são permitidas");
    }
}
