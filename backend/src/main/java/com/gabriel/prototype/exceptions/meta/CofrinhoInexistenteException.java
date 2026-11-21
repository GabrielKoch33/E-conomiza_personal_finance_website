package com.gabriel.prototype.exceptions.meta;

public class CofrinhoInexistenteException extends MetaException {
    public CofrinhoInexistenteException() {
        super("[ERRO] Campo: 'cofrinho' - Motivo: metas devem estar vinculadas à um cofrinho válido!");
    }
}
