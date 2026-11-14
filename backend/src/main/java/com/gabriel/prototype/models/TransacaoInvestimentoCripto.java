package com.gabriel.prototype.models;

import com.gabriel.prototype.enums.TipoInvestimento;
import com.gabriel.prototype.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoInvestimentoCripto extends TransacaoInvestimento {
    private final Long idInvestimentoCripto ;
    private final String ticker ;
    private final int quantidade;

    public TransacaoInvestimentoCripto(Usuario usuarioTransacao, TipoTransacao tipoTransacao,
                                       LocalDate dataTransacao, BigDecimal valorTransacao,
                                       TipoInvestimento tipoInvestimento,
                                       String ticker, int quantidade)
    {
        super(usuarioTransacao, tipoTransacao, dataTransacao, valorTransacao, tipoInvestimento);
        this.idInvestimentoCripto = null;
        this.ticker               = ticker;
        this.quantidade           = quantidade;
    }

    public Long getIdInvestimentoCripto() {
        return idInvestimentoCripto;
    }

    public String getTicker() {
        return ticker;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public Long getIdTransacaoInvestimento() {
        return super.getIdTransacaoInvestimento();
    }

    @Override
    public TipoInvestimento getTipoInvestimento() {
        return super.getTipoInvestimento();
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
