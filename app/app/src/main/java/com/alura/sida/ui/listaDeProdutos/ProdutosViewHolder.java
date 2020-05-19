package com.alura.sida.ui.listaDeProdutos;


import androidx.recyclerview.widget.RecyclerView;

import com.alura.sida.databinding.ItemListaProdutosActivityBinding;

public class ProdutosViewHolder extends RecyclerView.ViewHolder{

    ItemListaProdutosActivityBinding _binding;

    public ProdutosViewHolder(ItemListaProdutosActivityBinding binding) {
        super(binding.getRoot());
        this._binding = binding;
        _binding.executePendingBindings();
    }

    public ItemListaProdutosActivityBinding getViewDataBinding()
    {
        return _binding;
    }




}
