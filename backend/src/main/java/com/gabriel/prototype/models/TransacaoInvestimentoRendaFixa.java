package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.NomeInvalidoException;
import com.gabriel.prototype.exceptions.ValorInvalidoException;
import com.gabriel.prototype.exceptions.transacao.DataVencimentoException;
import com.gabriel.prototype.exceptions.transacao.NomeTituloInvalidoException;
import com.gabriel.prototype.exceptions.transacao.TaxaTituloInvalidaException;
import com.gabriel.prototype.models.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoInvestimentoRendaFixa extends TransacaoInvestimento {
    private final Long idInvestimentoRendaFixa;
    private final TipoRendaFixa tipoRendaFixa;
    private final String nomeTitulo;
    private final TipoIndexador indexador;
    private final double taxa;
    private final LocalDate dataVencimento;

    public TransacaoInvestimentoRendaFixa(Usuario usuarioTransacao, TipoOperacao tipoOperacao,
                                          LocalDate dataTransacao, BigDecimal valorTransacao,
                                          TipoInvestimento tipoInvestimento, LocalDate dataVencimento,
                                          double taxa, TipoIndexador indexador,
                                          String nomeTitulo, TipoRendaFixa tipoRendaFixa)
    {
        super(usuarioTransacao, tipoOperacao, dataTransacao, valorTransacao, tipoInvestimento);
        this.idInvestimentoRendaFixa = null;
        this.indexador = indexador;
        this.tipoRendaFixa = tipoRendaFixa;
        if (dataVencimento.isBefore(LocalDate.now())) throw new DataVencimentoException();
        this.dataVencimento = dataVencimento;
        if (taxa <= 0) throw new TaxaTituloInvalidaException();
        this.taxa = taxa;
        if (nomeTitulo == null || nomeTitulo.isBlank()) throw new NomeTituloInvalidoException();
        this.nomeTitulo = nomeTitulo;
    }

    public Long getIdInvestimentoRendaFixa() {
        return idInvestimentoRendaFixa;
    }

    public TipoRendaFixa getTipoRendaFixa() {
        return tipoRendaFixa;
    }

    public String getNomeTitulo() {
        return nomeTitulo;
    }

    public TipoIndexador getIndexador() {
        return indexador;
    }

    public double getTaxa() {
        return taxa;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
}
