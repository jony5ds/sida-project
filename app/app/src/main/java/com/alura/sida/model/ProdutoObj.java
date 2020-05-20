package com.alura.sida.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProdutoObj implements Serializable {

    private long produtoId;
    private String nome;
    private String marca;
    private double preco;
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

    public double getPreco() {
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

    public String getPrecoString()
    {
        String valor;
        DecimalFormat formato = new DecimalFormat("#.00");
        valor = formato.format(preco);
        return "R$ " + valor;
    }


}
