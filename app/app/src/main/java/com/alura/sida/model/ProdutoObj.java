package com.alura.sida.model;

import java.io.Serializable;

public class ProdutoObj implements Serializable {

    private long produtoId;
    private String nome;
    private String marca;
    private float preco;
    private String kg;

    public ProdutoObj() {
    }

    public ProdutoObj(String nome, String marca, float preco,String kg) {
        this.nome   = nome;
        this.marca  = marca;
        this.preco  = preco;
        this.kg     = kg;
    }

    public long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }
}
