package com.gabriel.prototype.models;

import com.gabriel.prototype.exceptions.transacao.CofrinhoDestinoException;
import com.gabriel.prototype.models.enums.TipoOperacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoCofrinho extends TransacaoBase {
    private final Long idTransacaoCofrinho;
    private final Cofrinho cofrinhoDestino;

    public TransacaoCofrinho(Usuario usuarioTransacao, LocalDate dataTransacao,
                             TipoOperacao tipoOperacao, BigDecimal valorTransacao,
                             Cofrinho cofrinhoDestino)
    {
        super(usuarioTransacao, tipoOperacao, dataTransacao, valorTransacao);
        this.idTransacaoCofrinho = null;
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
