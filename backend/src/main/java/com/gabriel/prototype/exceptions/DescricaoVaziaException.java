package com.gabriel.prototype.exceptions;

public class DescricaoVaziaException extends RuntimeException {
    public DescricaoVaziaException() {
        super("A descrição não pode ser vazia!");
    }
}
