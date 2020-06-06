package com.alura.sida.ui.listaDeProdutos;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.sida.R;
import com.alura.sida.databinding.ItemListaProdutosActivityBinding;
import com.alura.sida.model.ProdutoObj;
import com.alura.sida.ui.formulario.FormularioProdutosActivity;

import java.util.List;

import static com.alura.sida.ui.Const.CHAVE_POSICAO;
import static com.alura.sida.ui.Const.CHAVE_PRODUTO;
import static com.alura.sida.ui.Const.REQUEST_CODE_ALTERA_PRODUTO;

public class ProdutosAdapter extends  RecyclerView.Adapter<ProdutosViewHolder> {

    private List<ProdutoObj> _listaDeProdutos;
    private  ListaDeProdutosActivity _context;

    public ProdutosAdapter(List<ProdutoObj> _listaDeProdutos) {
        this._listaDeProdutos = _listaDeProdutos;
    }

    public ProdutosAdapter(List<ProdutoObj> todosProdutos, ListaDeProdutosActivity context) {
        this._listaDeProdutos = todosProdutos;
        this._context = context;
    }

    @NonNull
    @Override
    public ProdutosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         final ItemListaProdutosActivityBinding binding = DataBindingUtil
                 .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_lista_produtos_activity,parent,false);
        return new ProdutosViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProdutosViewHolder holder, final int position) {
        final ProdutoObj produtoObj = _listaDeProdutos.get(position);
        holder.getViewDataBinding().setProduto(produtoObj);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encaminharParaFormulario(produtoObj, position);
            }
        });
    }

    private void encaminharParaFormulario(ProdutoObj produtoObj, int position) {
        Intent iniciaFormulario = new Intent(_context, FormularioProdutosActivity.class);
        iniciaFormulario.putExtra(CHAVE_PRODUTO,produtoObj);
        iniciaFormulario.putExtra(CHAVE_POSICAO,position);
        _context.startActivityForResult(iniciaFormulario,REQUEST_CODE_ALTERA_PRODUTO);
    }

    @Override
    public int getItemCount() {
        return _listaDeProdutos.size();
    }

    public void remove(int produtoPosicao) {
            _listaDeProdutos.remove(produtoPosicao);
            notifyItemRemoved(produtoPosicao);
    }

    public void adicionaProduto(ProdutoObj produto) {
        _listaDeProdutos.add(produto);
        notifyDataSetChanged();

    }

    public void altera(int posicao, ProdutoObj produto) {
        _listaDeProdutos.set(posicao,produto);
        notifyDataSetChanged();
    }
}
