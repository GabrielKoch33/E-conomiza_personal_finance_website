package com.gabriel.prototype.exceptions.cofrinho;

public class ValorAutoDepositoException extends CofrinhoException {
    public ValorAutoDepositoException(String message) {
        super("[ERRO] Campo: 'valor auto-depósito' - Motivo: cofrinhos devem possuir um valor de auto-depósito positivo!");
    }
}
