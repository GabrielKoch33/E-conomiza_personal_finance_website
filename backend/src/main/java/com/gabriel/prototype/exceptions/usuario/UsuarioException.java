package com.gabriel.prototype.exceptions.usuario;

import com.gabriel.prototype.exceptions.EconomizaException;

public abstract class UsuarioException extends EconomizaException {
    protected UsuarioException(String message) {
        super(message);
    }
}
