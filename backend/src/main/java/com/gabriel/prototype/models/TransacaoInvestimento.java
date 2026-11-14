package com.gabriel.prototype.models;

import com.gabriel.prototype.enums.TipoInvestimento;
import com.gabriel.prototype.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class TransacaoInvestimento extends TransacaoBase {
    private final Long idTransacaoInvestimento;
    private final TipoInvestimento tipoInvestimento;

    protected TransacaoInvestimento(Usuario usuarioTransacao, TipoTransacao tipoTransacao,
                                    LocalDate dataTransacao, BigDecimal valorTransacao,
                                    TipoInvestimento tipoInvestimento)
    {
        super(usuarioTransacao, tipoTransacao, dataTransacao, valorTransacao);
        this.idTransacaoInvestimento = null;
        this.tipoInvestimento        = tipoInvestimento;
    }

    protected Long getIdTransacaoInvestimento() {
        return idTransacaoInvestimento;
    }

    protected TipoInvestimento getTipoInvestimento() {
        return tipoInvestimento;
    }
}
