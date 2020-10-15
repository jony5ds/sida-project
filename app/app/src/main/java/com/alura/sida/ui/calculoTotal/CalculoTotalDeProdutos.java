package com.alura.sida.ui.calculoTotal;

import com.alura.sida.model.ProdutoObj;

import java.util.List;

public class CalculoTotalDeProdutos {

    public static float somar (List<ProdutoObj> listaDeProdutos)
    {
        float total = 0;

        for(ProdutoObj novoProduto : listaDeProdutos )
        {
            total += novoProduto.getPreco();
        }
        return total;
    }
}
