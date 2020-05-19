package com.alura.sida.ui.listaDeProdutos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.sida.R;
import com.alura.sida.databinding.ItemListaProdutosActivityBinding;
import com.alura.sida.model.ProdutoObj;

import java.util.List;

public class ProdutosAdapter extends  RecyclerView.Adapter<ProdutosViewHolder> {


    private List<ProdutoObj> _listaDeProdutos;

    public ProdutosAdapter(List<ProdutoObj> _listaDeProdutos) {
        this._listaDeProdutos = _listaDeProdutos;
    }

    @NonNull
    @Override
    public ProdutosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         final ItemListaProdutosActivityBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_lista_produtos_activity,parent,false);
        return new ProdutosViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutosViewHolder holder, int position) {
        ProdutoObj produtoObj = _listaDeProdutos.get(position);
        holder.getViewDataBinding().setProduto(produtoObj);

    }

    @Override
    public int getItemCount() {
        return _listaDeProdutos.size();
    }


}
