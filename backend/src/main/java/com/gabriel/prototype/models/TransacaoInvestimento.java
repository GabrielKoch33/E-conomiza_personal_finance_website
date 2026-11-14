package com.gabriel.prototype.models;

import com.gabriel.prototype.enums.TipoInvestimento;
import com.gabriel.prototype.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoInvestimento extends TransacaoBase {
    private final Long idTransacaoInvestimento;
    private final TipoInvestimento tipoInvestimento;

    public TransacaoInvestimento(Usuario usuarioTransacao, TipoTransacao tipoTransacao,
                                 LocalDate dataTransacao, BigDecimal valorTransacao,
                                 TipoInvestimento tipoInvestimento)
    {
        super(usuarioTransacao, tipoTransacao, dataTransacao, valorTransacao);
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
