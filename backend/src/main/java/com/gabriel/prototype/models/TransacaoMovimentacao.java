package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.transacao.CategoriaInvalidaException;
import com.gabriel.prototype.models.enums.EnumTipoOperacao;
import com.gabriel.prototype.models.enums.EnumTipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoMovimentacao extends TransacaoBase {
    private Long idTransacaoMovimentacao;
    private final Categoria categoriaMovimentacao;
    private final String descricao;

    public TransacaoMovimentacao(Long id, Usuario usuarioTransacao, EnumTipoOperacao enumTipoOperacao,
                                 LocalDate dataTransacao, BigDecimal valorTransacao,
                                 Categoria categoriaMovimentacao, String descricao,
                                 Long idTransacaoMovimentacao)
    {
        super(id, usuarioTransacao, enumTipoOperacao, EnumTipoTransacao.MOVIMENTACAO, dataTransacao, valorTransacao);
        this.idTransacaoMovimentacao = idTransacaoMovimentacao;
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
