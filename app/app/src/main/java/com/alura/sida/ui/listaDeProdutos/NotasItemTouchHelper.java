package com.alura.sida.ui.listaDeProdutos;

import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.sida.asyncTask.BuscarTodosProdutosTask;
import com.alura.sida.asyncTask.DeletarProdutoTask;
import com.alura.sida.asyncTask.listeners.BuscarTodosProdutosListener;
import com.alura.sida.asyncTask.listeners.FinalizadoListener;
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
        removeProduto(produtoPosicao);
    }

    private void removeProduto(int produtoPosicao) {
        new AlertDialog.Builder(_activity)
                .setTitle("Remover Produto")
                .setMessage("Você está preste a remover um produto, deseja continaur ?")
                .setPositiveButton("SIM", (dialogInterface, i) -> {
                    acaoDeletar(produtoPosicao);
                })
                .setNegativeButton("NÃO", (dialog, which) -> {
                    new BuscarTodosProdutosTask(_produtoDao, new BuscarTodosProdutosListener() {
                        @Override
                        public void quandoEncontrado(List<ProdutoObj> produtos) {
                            _activity.popularListaProdutos(produtos);
                        }
                    }).execute();
                })
                .show();
    }

    private void acaoDeletar(int produtoPosicao) {
        new DeletarProdutoTask(_produtoDao, produtoPosicao, () -> {
            _adapter.remove(produtoPosicao);
            new BuscarTodosProdutosTask(_produtoDao, produtos -> _activity.controleVisaoLista(produtos)).execute();
        }).execute();


    }
}
