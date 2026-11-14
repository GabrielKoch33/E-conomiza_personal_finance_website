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

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public Usuario getUsuarioTransacao() {
        return super.getUsuarioTransacao();
    }

    @Override
    public TipoTransacao getTipoTransacao() {
        return super.getTipoTransacao();
    }

    @Override
    public LocalDate getDataTransacao() {
        return super.getDataTransacao();
    }

    @Override
    public BigDecimal getValorTransacao() {
        return super.getValorTransacao();
    }
}
