package com.gabriel.prototype.exceptions.categoria;

public class UsuarioCriadorInvalidoException extends CategoriaException {
    public UsuarioCriadorInvalidoException() {
        super("[ERRO] Campo: 'usuário' - Motivo: categorias devem ter um usuário válido vinculado!");
    }
}
