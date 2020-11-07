package com.alura.sida.ui.calculo.total;

import com.alura.sida.mock.ProdutoMock;
import com.alura.sida.ui.calculo.CalculoTotalDeProdutos;
import org.junit.Assert;
import org.junit.Test;

public class CalculoTotalDeProdutosTest {


    // Jônatas
    @Test
    public void somaCom0Produtos() {
        Assert.assertEquals(CalculoTotalDeProdutos.
                somar(ProdutoMock.getInstance0produtos()),0f,0);
    }

    //José
    @Test
    public void somaCom2Produtos() {
        Assert.assertEquals(CalculoTotalDeProdutos.
                somar(ProdutoMock.getInstance2produtos()),30f,0);
    }

    //José
    @Test
    public void somaCom1Produto3quantidades() {
        Assert.assertEquals(CalculoTotalDeProdutos.
                somar(ProdutoMock.getInstance1produto3quantidades()),60f,0);
    }

    //Thiago
    @Test
    public void somaCom1produtoQuantidadesNegativas() {
        Assert.assertEquals(CalculoTotalDeProdutos.
                somar(ProdutoMock.getInstance1produto3quantidadesNegativas()),0f,0);
    }
}