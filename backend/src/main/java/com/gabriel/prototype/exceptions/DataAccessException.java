package com.gabriel.prototype.exceptions;

public class DataAccessException extends Exception {
    public DataAccessException(Throwable cause) {
        super("[ERRO] Conexão à base de dados' - Motivo: "+cause);
    }
}
