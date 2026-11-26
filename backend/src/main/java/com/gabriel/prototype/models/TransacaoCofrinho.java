package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.transacao.CofrinhoDestinoException;
import com.gabriel.prototype.models.enums.EnumTipoOperacao;
import com.gabriel.prototype.models.enums.EnumTipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoCofrinho extends TransacaoBase {
    private final Long idTransacaoCofrinho;
    private final Cofrinho cofrinho;

    public TransacaoCofrinho(Long id, Usuario usuarioTransacao, LocalDateTime dataTransacao,
                             EnumTipoOperacao enumTipoOperacao,
                             BigDecimal valorTransacao, Cofrinho cofrinho, Long idTransacaoCofrinho)
    {
        super(id, usuarioTransacao, enumTipoOperacao, EnumTipoTransacao.COFRINHO, dataTransacao, valorTransacao);
        this.idTransacaoCofrinho = idTransacaoCofrinho;
        if (cofrinho == null) throw new CofrinhoDestinoException();
        this.cofrinho = cofrinho;
    }

    public Long getIdTransacaoCofrinho() {
        return idTransacaoCofrinho;
    }

    public Cofrinho getCofrinho() {
        return cofrinho;
    }
}
