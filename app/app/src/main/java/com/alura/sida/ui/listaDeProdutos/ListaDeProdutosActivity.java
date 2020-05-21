package com.alura.sida.ui.listaDeProdutos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alura.sida.R;
import com.alura.sida.dao.ProdutoDao;
import com.alura.sida.databinding.ListaDeProdutosActivityBinding;
import com.alura.sida.model.ProdutoObj;
import com.alura.sida.ui.formulario.FormularioProdutosActivity;

import java.util.List;

import static com.alura.sida.ui.Const.REQUEST_CODE_INSERE_PRODUTO;

public class ListaDeProdutosActivity extends AppCompatActivity {

    ListaDeProdutosActivityBinding _binding;
    ProdutosAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _binding = DataBindingUtil.setContentView(this,
                R.layout.lista_de_produtos_activity);
        getSupportActionBar().hide();

        List<ProdutoObj> todosProdutos = getProdutos();
        configuraRecyclerView(todosProdutos);
    }

    private List<ProdutoObj> getProdutos() {
        ProdutoDao produtoDao = new ProdutoDao();
        return produtoDao.todosProdutos();
    }

    private void configuraRecyclerView(List<ProdutoObj> todosProdutos) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        _binding.rvListaProduto.setHasFixedSize(true);
        _binding.rvListaProduto.setNestedScrollingEnabled(false);
        _binding.rvListaProduto.setLayoutManager(linearLayoutManager);
        popularListaProdutos(todosProdutos);
    }


    private void popularListaProdutos(List<ProdutoObj> todosProdutos) {
        if (todosProdutos.size() != 0) {
            _adapter = new ProdutosAdapter(todosProdutos);
            _binding.rvListaProduto.setAdapter(_adapter);
            _binding.mensagemListaVazia.setVisibility(View.GONE);
        }else
        {
            _binding.mensagemListaVazia.setVisibility(View.VISIBLE);
        }
    }

    public void irParaFormulario(View v) {
        Intent intent = new Intent(this, FormularioProdutosActivity.class);
        startActivityForResult(intent, REQUEST_CODE_INSERE_PRODUTO);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<ProdutoObj> produtoObjs = new ProdutoDao().todosProdutos();
        popularListaProdutos(produtoObjs);
    }
}
