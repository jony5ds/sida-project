package com.alura.sida.ui.listaDeProdutos;

import android.content.Context;
import com.alura.sida.asyncTask.AlterarProdutoTask;
import com.alura.sida.asyncTask.BuscarTodosProdutosTask;
import com.alura.sida.asyncTask.InserirProdutoTask;
import com.alura.sida.asyncTask.ObterListaDeProdutosTask;
import com.alura.sida.dao.ProdutoDataBase;
import com.alura.sida.dao.ProdutoRoomDao;
import com.alura.sida.model.ProdutoObj;

import java.util.List;

public class ListaDeProdutosPresenter {
    public ListaDeProdutosPresenter(IlistaDeProdutosView view) {
        this._view = view;
    }

    private IlistaDeProdutosView _view;
    private ProdutoRoomDao _produtoDao;
    private List<ProdutoObj> _todosProdutos;

    public void onCreate(Context context)
    {
        _produtoDao = ProdutoDataBase.getInstance(context).getProdutoDao();
        carregarProdutos();
    }

     void carregarProdutos() {
        new BuscarTodosProdutosTask(_produtoDao,produtos ->{
            _view.configuraRecyclerView(produtos);
        } ).execute();
    }

    public void insereProduto(ProdutoObj produtoRecebido) {
        new InserirProdutoTask(_produtoDao,produtoRecebido).execute();
    }

    public void alteraProduto(int posicao, ProdutoObj produto) {
        new AlterarProdutoTask(_produtoDao,produto).execute();
    }





}
