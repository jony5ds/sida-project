package com.alura.sida.asyncTask;

import android.os.AsyncTask;

import com.alura.sida.dao.ProdutoRoomDao;
import com.alura.sida.model.ProdutoObj;

public class InserirProdutoTask extends AsyncTask<Void,Void,Void> {

    private ProdutoRoomDao _produtoDao;
    private ProdutoObj _produto;

    public InserirProdutoTask(ProdutoRoomDao produtoDao,
                              ProdutoObj produto) {
        _produtoDao = produtoDao;
        _produto = produto;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        _produtoDao.salvar(_produto);
        return null;
    }


}
