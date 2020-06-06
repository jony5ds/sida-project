package com.alura.sida.ui.listaDeProdutos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alura.sida.R;
import com.alura.sida.dao.ProdutoDao;
import com.alura.sida.databinding.ListaDeProdutosActivityBinding;
import com.alura.sida.model.ProdutoObj;
import com.alura.sida.ui.formulario.FormularioProdutosActivity;
import com.alura.sida.widget.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.alura.sida.ui.Const.CHAVE_POSICAO;
import static com.alura.sida.ui.Const.CHAVE_PRODUTO;
import static com.alura.sida.ui.Const.POSICAO_INVALIDA;
import static com.alura.sida.ui.Const.REQUEST_CODE_ALTERA_PRODUTO;
import static com.alura.sida.ui.Const.REQUEST_CODE_INSERE_PRODUTO;

public class ListaDeProdutosActivity extends AppCompatActivity {

    ListaDeProdutosActivityBinding _binding;
    ProdutosAdapter _adapter;
    ProdutoDao _produtoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _binding = DataBindingUtil.setContentView(this,
                R.layout.lista_de_produtos_activity);
        getSupportActionBar().hide();

        _produtoDao = new ProdutoDao();

        List<ProdutoObj> todosProdutos = getProdutos();

        configuraRecyclerView(todosProdutos);
    }

    private List<ProdutoObj> getProdutos() {
        ProdutoDao produtoDao = new ProdutoDao();
        return produtoDao.todosProdutos();
    }

    private void configuraRecyclerView(List<ProdutoObj> todosProdutos) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        configuracaoPadraoDaLista(linearLayoutManager);
        popularListaProdutos(todosProdutos);

    }

    private void configuraItemTouchHelper(RecyclerView listaProduto) {
        ItemTouchHelper touchHelper = new ItemTouchHelper
                (new NotasItemTouchHelper(_adapter,this));
        touchHelper.attachToRecyclerView(listaProduto);
    }

    private void configuracaoPadraoDaLista(LinearLayoutManager linearLayoutManager) {
        _binding.rvListaProduto.setHasFixedSize(true);
        _binding.rvListaProduto.setNestedScrollingEnabled(false);
        _binding.rvListaProduto.setLayoutManager(linearLayoutManager);
        _binding.rvListaProduto.addItemDecoration(new SimpleDividerItemDecoration(this));
    }


    public void popularListaProdutos(List<ProdutoObj> todosProdutos) {
        _adapter = new ProdutosAdapter(todosProdutos, this);
        _binding.rvListaProduto.setAdapter(_adapter);

        configuraItemTouchHelper(_binding.rvListaProduto);

        controleVisaoLista(todosProdutos);
    }

    public void controleVisaoLista(List<ProdutoObj> todosProdutos) {

        if (todosProdutos.size() != 0) {
            _binding.mensagemListaVazia.setVisibility(View.GONE);
            _binding.imagemSeta.setVisibility(View.GONE);
        } else {
            _binding.mensagemListaVazia.setVisibility(View.VISIBLE);
            _binding.imagemSeta.setVisibility(View.VISIBLE);
        }
    }

    public void irParaFormulario(View v) {
        Intent intent = new Intent(this, FormularioProdutosActivity.class);
        startActivityForResult(intent, REQUEST_CODE_INSERE_PRODUTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ehResultadoNovoProduto(requestCode, resultCode, data)) {
            ProdutoObj produtoRecebido = (ProdutoObj) data.getSerializableExtra(CHAVE_PRODUTO);
            adicionaProduto(produtoRecebido);
        }
        else if (ehResultadoDeAlteracao(requestCode, resultCode, data)) {
            ProdutoObj produtoRecebido = (ProdutoObj) data.getSerializableExtra(CHAVE_PRODUTO);
            int posicaoRecebida = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            alteraProduto(produtoRecebido, posicaoRecebida);
        }
    }

    private void alteraProduto(ProdutoObj produto, int posicao) {
        _produtoDao.altera(posicao, produto);
        _adapter.altera(posicao, produto);
    }

    private boolean ehResultadoDeAlteracao(int requestCode, int resultCode, Intent data) {
        return requestCode == REQUEST_CODE_ALTERA_PRODUTO
                && resultCode == Activity.RESULT_OK
                && data != null
                && data.hasExtra(CHAVE_PRODUTO);

    }

    private void adicionaProduto(ProdutoObj produtoRecebido) {
        _produtoDao.insere(produtoRecebido);
        _adapter.adicionaProduto(produtoRecebido);
    }

    private boolean ehResultadoNovoProduto(int requestCode, int resultCode, Intent data) {
        return requestCode == REQUEST_CODE_INSERE_PRODUTO
                && resultCode == Activity.RESULT_OK
                && data != null
                && data.hasExtra(CHAVE_PRODUTO);
    }

    @Override
    protected void onResume() {
        super.onResume();
        controleVisaoLista(_produtoDao.todosProdutos());
    }
}
