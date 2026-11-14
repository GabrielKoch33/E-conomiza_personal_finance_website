package com.gabriel.prototype.models;

import com.gabriel.prototype.enums.TipoTransacao;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoMovimentacao extends TransacaoBase {
    private final Long idTransacaoMovimentacao;
    private final Categoria categoriaMovimentacao;
    private final String descricao;

    public TransacaoMovimentacao(Usuario usuarioTransacao, TipoTransacao tipoTransacao,
                                 LocalDate dataTransacao, BigDecimal valorTransacao,
                                 Categoria categoriaMovimentacao, String descricao)
    {
        super(usuarioTransacao, tipoTransacao, dataTransacao, valorTransacao);
        this.idTransacaoMovimentacao = null;
        this.categoriaMovimentacao   = categoriaMovimentacao;
        this.descricao               = descricao;
    }

    public TransacaoMovimentacao (Usuario usuarioTransacao, TipoTransacao tipoTransacao,
                                 LocalDate dataTransacao, BigDecimal valorTransacao,
                                 Categoria categoriaMovimentacao)
    {
        super(usuarioTransacao, tipoTransacao, dataTransacao, valorTransacao);
        this.idTransacaoMovimentacao = null;
        this.categoriaMovimentacao   = categoriaMovimentacao;
        this.descricao               = null;
    }

    public Long getIdTransacaoMovimentacao() {
        return idTransacaoMovimentacao;
    }

    public Categoria getCategoriaMovimentacao() {
        return categoriaMovimentacao;
    }

    public String getDescricao() {
        return descricao;
    }
}
