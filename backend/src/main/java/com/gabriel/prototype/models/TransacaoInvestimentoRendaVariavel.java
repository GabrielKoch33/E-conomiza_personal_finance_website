package com.gabriel.prototype.models;

import com.gabriel.prototype.models.enums.TipoInvestimento;
import com.gabriel.prototype.models.enums.TipoOperacao;
import com.gabriel.prototype.models.enums.TipoRendaVariavel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoInvestimentoRendaVariavel extends TransacaoInvestimento {
    private final Long idInvestimentoRendaVariavel;
    private final TipoRendaVariavel tipoRendaVariavel;
    private final String ticker;
    private final int quantidadeComprada;

    public TransacaoInvestimentoRendaVariavel(Usuario usuarioTransacao, TipoOperacao tipoOperacao,
                                              LocalDate dataTransacao, BigDecimal valorTransacao,
                                              TipoInvestimento tipoInvestimento, TipoRendaVariavel tipoRendaVariavel,
                                              String ticker, int quantidadeComprada)
    {
        super(usuarioTransacao, tipoOperacao, dataTransacao, valorTransacao, tipoInvestimento);
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
}
