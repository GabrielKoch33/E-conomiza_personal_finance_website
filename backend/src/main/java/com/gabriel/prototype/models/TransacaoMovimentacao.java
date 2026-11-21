package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.CategoriaInvalidaException;
import com.gabriel.prototype.models.enums.TipoOperacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoMovimentacao extends TransacaoBase {
    private final Long idTransacaoMovimentacao;
    private final Categoria categoriaMovimentacao;
    private final String descricao;

    public TransacaoMovimentacao(Usuario usuarioTransacao, TipoOperacao tipoOperacao,
                                 LocalDate dataTransacao, BigDecimal valorTransacao,
                                 Categoria categoriaMovimentacao, String descricao)
    {
        super(usuarioTransacao, tipoOperacao, dataTransacao, valorTransacao);
        this.idTransacaoMovimentacao = null;
        if (categoriaMovimentacao == null) throw new CategoriaInvalidaException();
        this.categoriaMovimentacao = categoriaMovimentacao;
        this.descricao = descricao;
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
