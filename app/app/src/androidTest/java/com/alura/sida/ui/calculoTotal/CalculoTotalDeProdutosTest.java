package com.alura.sida.ui.calculoTotal;

import com.alura.sida.model.ProdutoObj;
import com.alura.sida.validarProduto.ValidarProdutoTest;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class CalculoTotalDeProdutosTest {

    @Test
    public void somaCom2Produtos() {
        Assert.assertEquals(CalculoTotalDeProdutos.somar(ValidarProdutoTest.getInstance2produtos()),30f,0);
    }

    @Test
    public void somaCom0Produtos() {
        List<ProdutoObj> todosProdutos = new ArrayList<>();
        ProdutoObj produtonovo1 = new ProdutoObj();
        todosProdutos.add(produtonovo1);
        Assert.assertEquals(CalculoTotalDeProdutos.somar(todosProdutos),0f,0);
    }

    @Test
    public void somaComProdutosNulos() {
        List<ProdutoObj> todosProdutos = new ArrayList<>();
        Assert.assertEquals(CalculoTotalDeProdutos.somar(todosProdutos),0,0);
    }
}