package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.transacao.QuantidadeCompradaException;
import com.gabriel.prototype.exceptions.transacao.TickerInvalidoException;
import com.gabriel.prototype.models.enums.EnumTipoInvestimento;
import com.gabriel.prototype.models.enums.EnumTipoOperacao;
import com.gabriel.prototype.models.enums.EnumTipoRendaVariavel;
import com.gabriel.prototype.models.enums.EnumTipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoInvestimentoRendaVariavel extends TransacaoInvestimento {
    private final Long idInvestimentoRendaVariavel;
    private final EnumTipoRendaVariavel enumTipoRendaVariavel;
    private final String ticker;
    private final int quantidadeComprada;

    public TransacaoInvestimentoRendaVariavel(Long id, Usuario usuarioTransacao, EnumTipoOperacao enumTipoOperacao,
                                              LocalDateTime dataTransacao, BigDecimal valorTransacao,
                                              EnumTipoRendaVariavel enumTipoRendaVariavel,
                                              String ticker, int quantidadeComprada,
                                              Long idTransacaoInvestimento, Long idInvestimentoRendaVariavel
                                              )
    {
        super(id, usuarioTransacao, enumTipoOperacao, dataTransacao, valorTransacao, EnumTipoInvestimento.RENDA_VARIAVEL, idTransacaoInvestimento);
        this.idInvestimentoRendaVariavel = idInvestimentoRendaVariavel;
        this.enumTipoRendaVariavel = enumTipoRendaVariavel;
        if (ticker == null || ticker.isBlank() || ticker.length() > 20) throw new TickerInvalidoException();
        this.ticker = ticker;
        if (quantidadeComprada <= 0) throw new QuantidadeCompradaException();
        this.quantidadeComprada = quantidadeComprada;
    }

    public Long getIdInvestimentoRendaVariavel() {
        return idInvestimentoRendaVariavel;
    }

    public EnumTipoRendaVariavel getTipoRendaVariavel() {
        return enumTipoRendaVariavel;
    }

    public String getTicker() {
        return ticker;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }
}
