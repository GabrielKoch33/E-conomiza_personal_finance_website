package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.NomeInvalidoException;
import com.gabriel.prototype.exceptions.ValorInvalidoException;
import com.gabriel.prototype.models.enums.TipoInvestimento;
import com.gabriel.prototype.models.enums.TipoOperacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoInvestimentoCripto extends TransacaoInvestimento {
    private final Long idInvestimentoCripto ;
    private final String ticker ;
    private final double quantidade;

    public TransacaoInvestimentoCripto(Usuario usuarioTransacao, TipoOperacao tipoOperacao,
                                       LocalDate dataTransacao, BigDecimal valorTransacao,
                                       TipoInvestimento tipoInvestimento,
                                       String ticker, double quantidade)
    {
        super(usuarioTransacao, tipoOperacao, dataTransacao, valorTransacao, tipoInvestimento);
        this.idInvestimentoCripto = null;
        if (ticker == null || ticker.isBlank()) throw new NomeInvalidoException();
        this.ticker = ticker;
        if (quantidade <= 0) throw new ValorInvalidoException();
        this.quantidade = quantidade;
    }

    public Long getIdInvestimentoCripto() {
        return idInvestimentoCripto;
    }

    public String getTicker() {
        return ticker;
    }

    public double getQuantidade() {
        return quantidade;
    }
}
