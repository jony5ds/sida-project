package com.alura.sida.dao;

import com.alura.sida.model.ProdutoObj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProdutoDao {
    private final static ArrayList<ProdutoObj> produtos = new ArrayList<>();

    public List<ProdutoObj> todosProdutos()
    {
        return (List<ProdutoObj>) produtos.clone();
    }

    public void insere(ProdutoObj... produtos)
    {
        ProdutoDao.produtos.addAll(Arrays.asList(produtos));
    }

    public void altera(int posicao, ProdutoObj produto) {
        produtos.set(posicao, produto);
    }

    public void remove(int posicao) {
        produtos.remove(posicao);
    }

    public void troca(int posicaoInicio, int posicaoFim) {
        Collections.swap(produtos, posicaoInicio, posicaoFim);
    }

    public void removeTodos() {
        produtos.clear();
    }
}
