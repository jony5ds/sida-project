package com.alura.sida.asyncTask;

import android.os.AsyncTask;

import com.alura.sida.dao.ProdutoRoomDao;
import com.alura.sida.model.ProdutoObj;

public class AlterarProdutoTask extends AsyncTask<Void,Void,Void> {

    private final ProdutoRoomDao _produtoDao;
    private final ProdutoObj _produto;

    public AlterarProdutoTask(ProdutoRoomDao produtoDao, ProdutoObj produto) {
        _produtoDao = produtoDao;
        _produto = produto;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        _produtoDao.editar(_produto);
        return null;
    }
}
