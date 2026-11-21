package com.gabriel.prototype.exceptions.meta;

public class ValorAlvoInvalidoException extends MetaException {
    public ValorAlvoInvalidoException(String message) {
        super("[ERRO] Campo: 'valor alvo' - Motivo: metas devem possuir um valor alvo positivo!");
    }
}
