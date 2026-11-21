package com.gabriel.prototype.exceptions.transacao;

public class NomeTituloInvalidoException extends TransacaoException {
    public NomeTituloInvalidoException(String message) {
        super("[ERRO] Campo: 'título' - Motivo: o nome do título não pode ser vazio!");
    }
}
