package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.cofrinho.UsuarioCriadorInvalidoException;
import com.gabriel.prototype.exceptions.transacao.DataTransacaoException;
import com.gabriel.prototype.exceptions.transacao.ValorTransacaoException;
import com.gabriel.prototype.models.enums.EnumTipoOperacao;
import com.gabriel.prototype.models.enums.EnumTipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class TransacaoBase {
    private Long id;
    private final Usuario usuarioTransacao;
    private final EnumTipoOperacao enumTipoOperacao;
    private final EnumTipoTransacao enumTipoTransacao;
    private final LocalDate dataTransacao;
    private final BigDecimal valorTransacao;

    protected TransacaoBase(Long id, Usuario usuarioTransacao,
                            EnumTipoOperacao enumTipoOperacao, EnumTipoTransacao enumTipoTransacao,
                            LocalDate dataTransacao, BigDecimal valorTransacao)
    {
        this.id = id;
        this.enumTipoOperacao = enumTipoOperacao;
        this.enumTipoTransacao = enumTipoTransacao;
        if (usuarioTransacao == null) throw new UsuarioCriadorInvalidoException();
        this.usuarioTransacao = usuarioTransacao;
        if (dataTransacao.isAfter(LocalDate.now())) throw new DataTransacaoException();
        this.dataTransacao = dataTransacao;
        if (valorTransacao.compareTo(BigDecimal.ZERO) <= 0) throw new ValorTransacaoException();
        this.valorTransacao = valorTransacao;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuarioTransacao() {
        return usuarioTransacao;
    }

    public EnumTipoOperacao getTipoOperacao() {
        return enumTipoOperacao;
    }


    public EnumTipoTransacao getTipoTransacao() {
        return enumTipoTransacao;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public BigDecimal getValorTransacao() {
        return valorTransacao;
    }
}
