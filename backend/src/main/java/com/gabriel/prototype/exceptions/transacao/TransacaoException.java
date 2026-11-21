package com.gabriel.prototype.exceptions.transacao;

import com.gabriel.prototype.exceptions.EconomizaException;

public abstract class TransacaoException extends EconomizaException {
    protected TransacaoException(String message) {
        super(message);
    }
}
