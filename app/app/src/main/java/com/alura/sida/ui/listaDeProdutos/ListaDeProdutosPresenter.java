package com.alura.sida.ui.listaDeProdutos;

import com.alura.sida.dao.ProdutoDao;
import com.alura.sida.model.ProdutoObj;

import java.util.List;

public class ListaDeProdutosPresenter {
    public ListaDeProdutosPresenter(IlistaDeProdutosView view) {
        this._view = view;
    }

    private IlistaDeProdutosView _view;
   private ProdutoDao _produtoDao;

    public void onCreate()
    {
        _produtoDao = new ProdutoDao();
        List<ProdutoObj> todosProdutos = getProdutos();
        _view.configuraRecyclerView(todosProdutos);
    }

    public List<ProdutoObj> getProdutos() {
        return  _produtoDao.todosProdutos();
    }

    public void insereProduto(ProdutoObj produtoRecebido) {
        _produtoDao.insere(produtoRecebido);
    }

    public void alteraProduto(int posicao, ProdutoObj produto) {
        _produtoDao.altera(posicao, produto);
    }
}
