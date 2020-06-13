package com.alura.sida.asyncTask;

import android.os.AsyncTask;

import com.alura.sida.asyncTask.listeners.FinalizadoListener;
import com.alura.sida.dao.ProdutoRoomDao;
import com.alura.sida.model.ProdutoObj;

import java.util.List;

public class DeletarProdutoTask extends AsyncTask <Void,Void,Void> {
    private final ProdutoRoomDao _produtoDao;
    private final int _posicao;
    private final FinalizadoListener _listener;

    public DeletarProdutoTask(ProdutoRoomDao produtoDao, int posicao, FinalizadoListener listener) {
        _produtoDao = produtoDao;
        _posicao = posicao;
        _listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ProdutoObj produto = _produtoDao.obterTodosProdutos().get(_posicao);
        _produtoDao.deletar(produto);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        _listener.quandoFinalizado();
    }
}
