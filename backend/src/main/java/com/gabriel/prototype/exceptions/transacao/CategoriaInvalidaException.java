package com.gabriel.prototype.exceptions.transacao;

public class CategoriaInvalidaException extends TransacaoException {
    public CategoriaInvalidaException() {
        super("[ERRO] Campo: 'categoria' - Motivo: transações devem estar relacionadas a categorias válidas!");
    }
}
