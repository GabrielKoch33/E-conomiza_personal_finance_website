package com.gabriel.prototype.exceptions.meta;

import com.gabriel.prototype.exceptions.EconomizaException;

public abstract class MetaException extends EconomizaException {
    protected MetaException(String message) {
        super(message);
    }
}
