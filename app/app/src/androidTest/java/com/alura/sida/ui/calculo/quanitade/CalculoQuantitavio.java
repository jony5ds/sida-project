package com.alura.sida.ui.calculo.quanitade;

import com.alura.sida.mock.ProdutoMock;
import com.alura.sida.ui.calculo.CalculoTotalDeProdutos;

import org.junit.Assert;
import org.junit.Test;

public class CalculoQuantitavio {

    //Luis

    @Test
    public void listaCom4quantidades() {
        Assert.assertEquals(CalculoTotalDeProdutos.
                totalQuantidade(ProdutoMock.getInstance2produtos()),4,0);
    }


    @Test
    public void listaComValorNulo() {
        Assert.assertEquals(CalculoTotalDeProdutos.
                totalQuantidade(ProdutoMock.getInstance0produtos()),0,0);
    }

    //Caio

    @Test
    public void listaComQuantidadesNegativas() {
        Assert.assertEquals(CalculoTotalDeProdutos.
                totalQuantidade(ProdutoMock.getInstance1produto3quantidadesNegativas()),0,0);
    }




}
