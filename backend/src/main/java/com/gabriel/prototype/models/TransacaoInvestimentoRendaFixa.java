package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.transacao.DataVencimentoException;
import com.gabriel.prototype.exceptions.transacao.NomeTituloInvalidoException;
import com.gabriel.prototype.exceptions.transacao.TaxaTituloInvalidaException;
import com.gabriel.prototype.models.enums.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoInvestimentoRendaFixa extends TransacaoInvestimento {
    private final Long idInvestimentoRendaFixa;
    private final EnumTipoRendaFixa enumTipoRendaFixa;
    private final String nomeTitulo;
    private final EnumTipoIndexador indexador;
    private final double taxa;
    private final LocalDateTime dataVencimento;

    public TransacaoInvestimentoRendaFixa(Long id, Usuario usuarioTransacao, EnumTipoOperacao enumTipoOperacao,
                                          LocalDateTime dataTransacao, BigDecimal valorTransacao,
                                          LocalDateTime dataVencimento,
                                          double taxa, EnumTipoIndexador indexador,
                                          String nomeTitulo, EnumTipoRendaFixa enumTipoRendaFixa, Long idTransacaoInvestimento,
                                          Long idInvestimentoRendaFixa)
    {
        super(id, usuarioTransacao, enumTipoOperacao, dataTransacao, valorTransacao, EnumTipoInvestimento.RENDA_FIXA, idTransacaoInvestimento);
        this.idInvestimentoRendaFixa = idInvestimentoRendaFixa;
        this.indexador = indexador;
        this.enumTipoRendaFixa = enumTipoRendaFixa;

        if (dataVencimento.isBefore(LocalDateTime.now())) throw new DataVencimentoException();
        this.dataVencimento = dataVencimento;
        if (taxa <= 0) throw new TaxaTituloInvalidaException();
        this.taxa = taxa;
        if (nomeTitulo == null || nomeTitulo.isBlank()) throw new NomeTituloInvalidoException();
        this.nomeTitulo = nomeTitulo;
    }

    public Long getIdInvestimentoRendaFixa() {
        return idInvestimentoRendaFixa;
    }

    public EnumTipoRendaFixa getTipoRendaFixa() {
        return enumTipoRendaFixa;
    }

    public String getNomeTitulo() {
        return nomeTitulo;
    }

    public EnumTipoIndexador getIndexador() {
        return indexador;
    }

    public double getTaxa() {
        return taxa;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }
}
