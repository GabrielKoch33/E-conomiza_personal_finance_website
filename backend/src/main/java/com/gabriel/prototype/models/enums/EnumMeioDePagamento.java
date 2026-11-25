package com.gabriel.prototype.models.enums;

public enum EnumMeioDePagamento {
    PIX("Pix"),
    DINHEIRO_VIVO("Dinheiro vivo"),
    TRANSFERENCIA("Transferência"),
    DEBITO("Débito"),
    CREDITO("Crédito"),
    BOLETO("Boleto"),
    CHEQUE("Cheque");

    private final String descricao;

    private EnumMeioDePagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
