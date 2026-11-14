package com.gabriel.prototype.models;

import com.gabriel.prototype.enums.TipoInvestimento;
import com.gabriel.prototype.enums.TipoRendaVariavel;
import com.gabriel.prototype.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoInvestimentoRendaVariavel extends TransacaoInvestimento {
    private final Long idInvestimentoRendaVariavel;
    private final TipoRendaVariavel tipoRendaVariavel;
    private final String ticker;
    private final int quantidadeComprada;

    public TransacaoInvestimentoRendaVariavel(Usuario usuarioTransacao, TipoTransacao tipoTransacao,
                                              LocalDate dataTransacao, BigDecimal valorTransacao,
                                              TipoInvestimento tipoInvestimento, TipoRendaVariavel tipoRendaVariavel,
                                              String ticker, int quantidadeComprada)
    {
        super(usuarioTransacao, tipoTransacao, dataTransacao, valorTransacao, tipoInvestimento);
        this.idInvestimentoRendaVariavel = null;
        this.tipoRendaVariavel           = tipoRendaVariavel;
        this.ticker                      = ticker;
        this.quantidadeComprada          = quantidadeComprada;
    }

    public Long getIdInvestimentoRendaVariavel() {
        return idInvestimentoRendaVariavel;
    }

    public TipoRendaVariavel getTipoRendaVariavel() {
        return tipoRendaVariavel;
    }

    public String getTicker() {
        return ticker;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
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
