package com.gabriel.prototype.exceptions.cofrinho;

import com.gabriel.prototype.exceptions.EconomizaException;

public abstract class CofrinhoException extends EconomizaException {
    protected CofrinhoException(String message) {
        super(message);
    }
}
