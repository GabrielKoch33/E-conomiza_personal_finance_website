package com.gabriel.prototype.exceptions.categoria;

import com.gabriel.prototype.exceptions.EconomizaException;

public abstract class CategoriaException extends EconomizaException {
    protected CategoriaException(String message) {
        super(message);
    }
}
