package com.gabriel.prototype.models;

import com.gabriel.prototype.enums.TipoIndexador;
import com.gabriel.prototype.enums.TipoInvestimento;
import com.gabriel.prototype.enums.TipoRendaFixa;
import com.gabriel.prototype.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoInvestimentoRendaFixa extends TransacaoInvestimento {
    private final Long idInvestimentoRendaFixa;
    private final TipoRendaFixa tipoRendaFixa;
    private final String nomeTitulo;
    private final TipoIndexador indexador;
    private final double taxa;
    private final LocalDate dataVencimento;

    public TransacaoInvestimentoRendaFixa(Usuario usuarioTransacao, TipoTransacao tipoTransacao,
                                          LocalDate dataTransacao, BigDecimal valorTransacao,
                                          TipoInvestimento tipoInvestimento, LocalDate dataVencimento,
                                          double taxa, TipoIndexador indexador,
                                          String nomeTitulo, TipoRendaFixa tipoRendaFixa)
    {
        super(usuarioTransacao, tipoTransacao, dataTransacao, valorTransacao, tipoInvestimento);
        this.idInvestimentoRendaFixa = null;
        this.dataVencimento          = dataVencimento;
        this.taxa                    = taxa;
        this.indexador               = indexador;
        this.nomeTitulo              = nomeTitulo;
        this.tipoRendaFixa           = tipoRendaFixa;
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

    @Override
    public Long getIdTransacaoInvestimento() {
        return super.getIdTransacaoInvestimento();
    }

    @Override
    public TipoInvestimento getTipoInvestimento() {
        return super.getTipoInvestimento();
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public Usuario getUsuarioTransacao() {
        return super.getUsuarioTransacao();
    }

    @Override
    public TipoTransacao getTipoTransacao() {
        return super.getTipoTransacao();
    }

    @Override
    public LocalDate getDataTransacao() {
        return super.getDataTransacao();
    }

    @Override
    public BigDecimal getValorTransacao() {
        return super.getValorTransacao();
    }
}
