package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.transacao.CofrinhoDestinoException;
import com.gabriel.prototype.models.enums.EnumTipoOperacao;
import com.gabriel.prototype.models.enums.EnumTipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoCofrinho extends TransacaoBase {
    private final Long idTransacaoCofrinho;
    private final Cofrinho cofrinhoDestino;

    public TransacaoCofrinho(Long id, Usuario usuarioTransacao, LocalDate dataTransacao,
                             EnumTipoOperacao enumTipoOperacao,
                             BigDecimal valorTransacao, Cofrinho cofrinhoDestino, Long idTransacaoCofrinho)
    {
        super(id, usuarioTransacao, enumTipoOperacao, EnumTipoTransacao.COFRINHO, dataTransacao, valorTransacao);
        this.idTransacaoCofrinho = idTransacaoCofrinho;
        if (cofrinhoDestino == null) throw new CofrinhoDestinoException();
        this.cofrinhoDestino = cofrinhoDestino;
    }

    public Long getIdTransacaoCofrinho() {
        return idTransacaoCofrinho;
    }

    public Cofrinho getCofrinhoDestino() {
        return cofrinhoDestino;
    }
}
