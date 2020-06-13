package com.alura.sida.asyncTask.listeners;

import com.alura.sida.model.ProdutoObj;

import java.util.List;

public interface BuscarTodosProdutosListener {
    void quandoEncontrado(List<ProdutoObj> produtos);
}
