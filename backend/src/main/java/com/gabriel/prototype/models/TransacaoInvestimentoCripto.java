package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.transacao.QuantidadeCompradaException;
import com.gabriel.prototype.exceptions.transacao.TickerInvalidoException;
import com.gabriel.prototype.models.enums.EnumTipoInvestimento;
import com.gabriel.prototype.models.enums.EnumTipoOperacao;
import com.gabriel.prototype.models.enums.EnumTipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoInvestimentoCripto extends TransacaoInvestimento {
    private final Long idInvestimentoCripto ;
    private final String ticker ;
    private final BigDecimal quantidade;

    public TransacaoInvestimentoCripto(Long id, Usuario usuarioTransacao,
                                       EnumTipoOperacao tipoOperacao, LocalDateTime dataTransacao,
                                       BigDecimal valorTransacao, String ticker,
                                       BigDecimal quantidade, Long idTransacaoInvestimento, Long idInvestimentoCripto)
    {
        super(id, usuarioTransacao, tipoOperacao, dataTransacao, valorTransacao, EnumTipoInvestimento.CRIPTO, idTransacaoInvestimento);
        this.idInvestimentoCripto = idInvestimentoCripto;
        if (ticker == null || ticker.isBlank() || ticker.length() > 10) throw new TickerInvalidoException();
        this.ticker = ticker;
        if (quantidade.compareTo(BigDecimal.ZERO) <= 0) throw new QuantidadeCompradaException();
        this.quantidade = quantidade;
    }

    public Long getIdInvestimentoCripto() {
        return idInvestimentoCripto;
    }

    public String getTicker() {
        return ticker;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }
}
