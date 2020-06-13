package com.alura.sida.asyncTask;

import android.os.AsyncTask;

import com.alura.sida.asyncTask.listeners.BuscarTodosProdutosListener;
import com.alura.sida.dao.ProdutoRoomDao;
import com.alura.sida.model.ProdutoObj;

import java.util.List;

public class BuscarTodosProdutosTask extends AsyncTask<Void,Void, List<ProdutoObj>> {
    private final ProdutoRoomDao _produtoDao;
    private final BuscarTodosProdutosListener _listener;

    public BuscarTodosProdutosTask(ProdutoRoomDao produtoDao,
                                   BuscarTodosProdutosListener listener) {
        _produtoDao = produtoDao;
        _listener = listener;
    }

    @Override
    protected List<ProdutoObj> doInBackground(Void... voids) {
        return _produtoDao.obterTodosProdutos();
    }

    @Override
    protected void onPostExecute(List<ProdutoObj> produtoObjs) {
        super.onPostExecute(produtoObjs);
        _listener.quandoEncontrado(produtoObjs);
    }
}
