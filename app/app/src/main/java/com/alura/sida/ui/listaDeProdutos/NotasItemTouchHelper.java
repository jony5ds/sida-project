package com.alura.sida.ui.listaDeProdutos;

import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.sida.dao.ProdutoDao;
import com.alura.sida.dao.ProdutoDataBase;
import com.alura.sida.dao.ProdutoRoomDao;
import com.alura.sida.model.ProdutoObj;

import java.util.List;

public class NotasItemTouchHelper extends ItemTouchHelper.Callback {
    private final ProdutosAdapter _adapter;
    private ProdutoRoomDao _produtoDao;
    private final ListaDeProdutosActivity _activity;


    public NotasItemTouchHelper(ProdutosAdapter adapter, ListaDeProdutosActivity context) {
        _adapter = adapter;
        _activity = context;
        _produtoDao = ProdutoDataBase.getInstance(context).getProdutoDao();
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder) {

        int marcacoesDeDeslize = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int marcacoesDeArrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP
                | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int produtoPosicao = viewHolder.getAdapterPosition();
        long idProduto = viewHolder.getItemId();
        removeProduto(produtoPosicao);
    }

    private void removeProduto(int produtoPosicao) {
        new AlertDialog.Builder(_activity)
                .setTitle("Remover Produto")
                .setMessage("Você está preste a remover um produto, deseja continaur ?")
                .setPositiveButton("SIM", (dialogInterface, i) -> {
                    acaoDeletar();
                })
                .setNegativeButton("NÃO", (dialog, which) -> {
                    _activity.popularListaProdutos(_produtoDao.obterTodosProdutos());
                })
                .show();
    }

    private void acaoDeletar() {
        _produtoDao.deletar(produtoPosicao);
        _adapter.remove(produtoPosicao);
        _activity.controleVisaoLista(_produtoDao.obterTodosProdutos());
    }
}
