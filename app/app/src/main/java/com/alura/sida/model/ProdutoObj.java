package com.alura.sida.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.alura.sida.utils.NumberUtils;
import java.io.Serializable;

@Entity
public class ProdutoObj implements Serializable {

    @PrimaryKey(autoGenerate = true)
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

    public String getPesoFormatado()
    {
        if (getKg().isEmpty())
        {
            return "";
        }
        return "KG " + getKg();
    }

    public String getPrecoString()
    {
        String valor = NumberUtils.formatarDecimal(preco);
        return "R$ " + valor;
    }


}
