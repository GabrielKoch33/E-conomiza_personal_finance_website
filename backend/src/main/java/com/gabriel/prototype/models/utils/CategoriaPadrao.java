package com.gabriel.prototype.models.utils;

public enum CategoriaPadrao {
    SAUDE ("Saúde"),
    EDUCACAO ("Educação"),
    TRANSPORTE ("Transporte"),
    ALIMENTACAO ("Alimentação"),
    CUIDADOS_PESSOAIS("Cuidados Pessoais"),
    ASSINATURA ("Assinaturas"),
    FATURA ("Fatura"),
    SALARIO ("Salário"),
    CONTA_DE_ENERGIA("Conta de Energia"),
    CONTA_DE_AGUA ("Conta de Água"),
    CONTA_DE_INTERNET ("Conta de Energia"),
    PIX ("Pix"),
    SEM_CATEGORIA ("Sem Categoria");

    private final String descricao;

    private CategoriaPadrao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
