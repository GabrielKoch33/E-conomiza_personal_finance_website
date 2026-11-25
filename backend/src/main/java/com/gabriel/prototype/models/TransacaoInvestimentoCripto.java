package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.transacao.QuantidadeCompradaException;
import com.gabriel.prototype.exceptions.transacao.TickerInvalidoException;
import com.gabriel.prototype.models.enums.EnumTipoInvestimento;
import com.gabriel.prototype.models.enums.EnumTipoOperacao;
import com.gabriel.prototype.models.enums.EnumTipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoInvestimentoCripto extends TransacaoInvestimento {
    private final Long idInvestimentoCripto ;
    private final String ticker ;
    private final double quantidade;

    public TransacaoInvestimentoCripto(Long id, Usuario usuarioTransacao, EnumTipoOperacao tipoOperacao,
                                       LocalDate dataTransacao, BigDecimal valorTransacao,
                                       EnumTipoInvestimento tipoInvestimento, String ticker,
                                       double quantidade, Long idTransacaoInvestimento, Long idInvestimentoCripto)
    {
        super(id, usuarioTransacao, tipoOperacao, dataTransacao, valorTransacao, tipoInvestimento, idTransacaoInvestimento);
        this.idInvestimentoCripto = idInvestimentoCripto;
        if (ticker == null || ticker.isBlank()) throw new TickerInvalidoException();
        this.ticker = ticker;
        if (quantidade <= 0) throw new QuantidadeCompradaException();
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
