package com.gabriel.prototype.exceptions.cofrinho;

public class NomeCofrinhoVazioException extends CofrinhoException {
    public NomeCofrinhoVazioException() {
        super("[ERRO] Campo: 'nome' - Motivo: cofrinhos não podem existir sem nome!");
    }
}
