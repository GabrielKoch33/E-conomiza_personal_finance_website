package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.DataFuturaException;
import com.gabriel.prototype.exceptions.ValorInvalidoException;
import com.gabriel.prototype.exceptions.cofrinho.UsuarioCriadorInvalidoException;
import com.gabriel.prototype.exceptions.transacao.DataTransacaoException;
import com.gabriel.prototype.exceptions.transacao.ValorTransacaoException;
import com.gabriel.prototype.models.enums.TipoOperacao;
import com.gabriel.prototype.exceptions.UsuarioInvalidoException;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class TransacaoBase {
    private final Long id;
    private final Usuario usuarioTransacao;
    private final TipoOperacao tipoOperacao;
    private final LocalDate dataTransacao;
    private final BigDecimal valorTransacao;

    protected TransacaoBase(Usuario usuarioTransacao,
                            TipoOperacao tipoOperacao, LocalDate dataTransacao,
                            BigDecimal valorTransacao)
    {
        this.id = null;
        this.tipoOperacao = tipoOperacao;
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

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public BigDecimal getValorTransacao() {
        return valorTransacao;
    }
}
