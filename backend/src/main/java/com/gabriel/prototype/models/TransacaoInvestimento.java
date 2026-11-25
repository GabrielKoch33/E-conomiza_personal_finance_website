package com.gabriel.prototype.models;

import com.gabriel.prototype.models.enums.EnumTipoInvestimento;
import com.gabriel.prototype.models.enums.EnumTipoOperacao;
import com.gabriel.prototype.models.enums.EnumTipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class TransacaoInvestimento extends TransacaoBase {
    private final Long idTransacaoInvestimento;
    private final EnumTipoInvestimento tipoInvestimento;

    protected TransacaoInvestimento(Long id, Usuario usuarioTransacao, EnumTipoOperacao enumTipoOperacao,
                                    LocalDate dataTransacao, BigDecimal valorTransacao,
                                    EnumTipoInvestimento enumTipoInvestimento, Long idTransacaoInvestimento)
    {
        super(id, usuarioTransacao, enumTipoOperacao, EnumTipoTransacao.INVESTIMENTO, dataTransacao, valorTransacao);
        this.idTransacaoInvestimento = idTransacaoInvestimento;
        this.tipoInvestimento        = enumTipoInvestimento;
    }

    public Long getIdTransacaoInvestimento() {
        return idTransacaoInvestimento;
    }

    public EnumTipoInvestimento getTipoInvestimento() {
        return tipoInvestimento;
    }
}
