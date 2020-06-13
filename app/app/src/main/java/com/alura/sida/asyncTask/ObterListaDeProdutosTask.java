package com.alura.sida.asyncTask;

import android.os.AsyncTask;

import com.alura.sida.dao.ProdutoRoomDao;
import com.alura.sida.model.ProdutoObj;

import java.util.List;

public class ObterListaDeProdutosTask extends AsyncTask<Void, Void, List<ProdutoObj>> {

    private final ProdutoRoomDao _produtoDao;


    public ObterListaDeProdutosTask(ProdutoRoomDao produtoDao) {
        _produtoDao = produtoDao;
    }

    @Override
    protected List<ProdutoObj> doInBackground(Void... voids) {
        return _produtoDao.obterTodosProdutos();
    }
}
