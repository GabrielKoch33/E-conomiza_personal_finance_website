package com.gabriel.prototype.models;

import com.gabriel.prototype.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoCofrinho extends TransacaoBase {
    private final Long idTransacaoCofrinho;
    private final Cofrinho cofrinhoDestino;

    public TransacaoCofrinho(Usuario usuarioTransacao, TipoTransacao tipoTransacao,
                             LocalDate dataTransacao, BigDecimal valorTransacao,
                             Cofrinho cofrinhoDestino)
    {
        super(usuarioTransacao, tipoTransacao, dataTransacao, valorTransacao);
        this.idTransacaoCofrinho = null;
        this.cofrinhoDestino     = cofrinhoDestino;
    }

    public Long getIdTransacaoCofrinho() {
        return idTransacaoCofrinho;
    }

    public Cofrinho getCofrinhoDestino() {
        return cofrinhoDestino;
    }
}
