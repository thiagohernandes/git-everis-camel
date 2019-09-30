package com.everis.camel.domain;

import java.math.BigDecimal;

public class Pedido {

    private final String descricao;
    private final BigDecimal valor;
    private final int qtd;

    public Pedido(String descricao, BigDecimal valor, int qtd) {
        this.descricao = descricao;
        this.valor = valor;
        this.qtd = qtd;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQtd() {
        return qtd;
    }

    public String toString() {
        return "Pedido detalhes: ".concat(descricao)
        		.concat("\t").concat(String.valueOf(qtd)).concat("\t").concat(String.valueOf(valor));
    }
}
