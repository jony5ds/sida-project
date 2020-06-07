package com.alura.sida.ui.listaDeProdutos;

import androidx.recyclerview.widget.RecyclerView;

import com.alura.sida.model.ProdutoObj;

import java.util.List;

public interface IlistaDeProdutosView {
    void configuraRecyclerView(List<ProdutoObj> todosProdutos);
}
