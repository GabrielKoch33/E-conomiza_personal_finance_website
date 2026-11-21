package com.gabriel.prototype.exceptions.transacao;

public class TaxaTituloInvalidaException extends TransacaoException {
    public TaxaTituloInvalidaException(String message) {
        super("[ERRO] Campo: 'usuário' - Motivo: cofrinhos devem possuir um usuário válido vinculado!");
    }
}
