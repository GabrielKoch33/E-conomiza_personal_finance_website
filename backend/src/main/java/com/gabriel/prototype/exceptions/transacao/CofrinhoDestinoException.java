package com.gabriel.prototype.exceptions.transacao;

public class CofrinhoDestinoException extends TransacaoException {
    public CofrinhoDestinoException() {
        super("[ERRO] Campo: 'cofrinho destino' - Motivo: transações devem estar relacionadas a cofrinhos existentes!");
    }
}
