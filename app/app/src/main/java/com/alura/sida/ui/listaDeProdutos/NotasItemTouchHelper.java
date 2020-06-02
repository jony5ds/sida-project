package com.alura.sida.ui.listaDeProdutos;

import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.sida.dao.ProdutoDao;

public class NotasItemTouchHelper extends ItemTouchHelper.Callback {
    private final ProdutosAdapter _adapter;
    private ProdutoDao _produtoDao;
    private final ListaDeProdutosActivity _activity;

    public NotasItemTouchHelper(ProdutosAdapter adapter, ListaDeProdutosActivity context) {
        _adapter = adapter;
        _activity = context;
        _produtoDao = new ProdutoDao();
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize  = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int marcacoesDeArrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP
                | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return  makeMovementFlags(marcacoesDeArrastar,marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int produtoPosicao = viewHolder.getAdapterPosition();
        removeProduto(produtoPosicao);
    }

    private void removeProduto(int produtoPosicao) {
        new AlertDialog.Builder(_activity)
                .setTitle("Remover Produto")
                .setMessage("Você está preste a remover um produto, deseja continaur ?")
                .setPositiveButton("SIM",(dialogInterface,i)->{
                    _produtoDao.remove(produtoPosicao);
                    _adapter.remove(produtoPosicao);
                    _activity.controleVisaoLista(_produtoDao.todosProdutos());

                })
                .setNegativeButton("NÃO",(dialog, which) -> {
                    _activity.popularListaProdutos(_produtoDao.todosProdutos());
                })
                .show();


    }
}
