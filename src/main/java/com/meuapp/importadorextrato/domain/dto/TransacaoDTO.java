package com.meuapp.importadorextrato.domain.dto;

public class TransacaoDTO {

    private String data;
    private String descricao;
    private String valor;
    private String tipo;

    public TransacaoDTO() {
    }

    public TransacaoDTO(String data, String descricao, String valor, String tipo) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
