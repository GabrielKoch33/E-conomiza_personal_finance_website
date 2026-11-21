package com.gabriel.prototype.exceptions.transacao;

public class ValorTransacaoException extends TransacaoException {
    public ValorTransacaoException(String message) {
        super("[ERRO] Campo: 'usuário' - Motivo: cofrinhos devem possuir um usuário válido vinculado!");
    }
}
