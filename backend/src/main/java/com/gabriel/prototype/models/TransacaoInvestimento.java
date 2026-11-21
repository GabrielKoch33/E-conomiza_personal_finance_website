package com.gabriel.prototype.models;

import com.gabriel.prototype.models.enums.TipoInvestimento;
import com.gabriel.prototype.models.enums.TipoOperacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class TransacaoInvestimento extends TransacaoBase {
    private final Long idTransacaoInvestimento;
    private final TipoInvestimento tipoInvestimento;

    protected TransacaoInvestimento(Usuario usuarioTransacao, TipoOperacao tipoOperacao,
                                    LocalDate dataTransacao, BigDecimal valorTransacao,
                                    TipoInvestimento tipoInvestimento)
    {
        super(usuarioTransacao, tipoOperacao, dataTransacao, valorTransacao);
        this.idTransacaoInvestimento = null;
        this.tipoInvestimento        = tipoInvestimento;
    }

    public Long getIdTransacaoInvestimento() {
        return idTransacaoInvestimento;
    }

    public TipoInvestimento getTipoInvestimento() {
        return tipoInvestimento;
    }
}
