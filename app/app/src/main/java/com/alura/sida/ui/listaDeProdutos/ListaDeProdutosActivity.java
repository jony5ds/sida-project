package com.alura.sida.ui.listaDeProdutos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.sida.R;
import com.alura.sida.asyncTask.BuscarTodosProdutosTask;
import com.alura.sida.dao.ProdutoDataBase;
import com.alura.sida.dao.ProdutoRoomDao;
import com.alura.sida.databinding.ListaDeProdutosActivityBinding;
import com.alura.sida.model.ProdutoObj;
import com.alura.sida.ui.formulario.FormularioProdutosActivity;
import com.alura.sida.utils.NumberUtils;
import com.alura.sida.widget.SimpleDividerItemDecoration;

import java.util.List;
import java.util.Objects;

import static com.alura.sida.ui.Const.CHAVE_POSICAO;
import static com.alura.sida.ui.Const.CHAVE_PRODUTO;
import static com.alura.sida.ui.Const.POSICAO_INVALIDA;
import static com.alura.sida.ui.Const.REQUEST_CODE_ALTERA_PRODUTO;
import static com.alura.sida.ui.Const.REQUEST_CODE_INSERE_PRODUTO;

public class ListaDeProdutosActivity extends AppCompatActivity implements IlistaDeProdutosView {

    ListaDeProdutosActivityBinding _binding;
    ListaDeProdutosPresenter _presenter;
    ProdutosAdapter _adapter;
    ProdutoRoomDao _produtoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = DataBindingUtil.setContentView(this,
                R.layout.lista_de_produtos_activity);
        _presenter = new ListaDeProdutosPresenter(this);
        _presenter.onCreate(this);
        _produtoDao = ProdutoDataBase.getInstance(this).getProdutoDao();
        Objects.requireNonNull(getSupportActionBar()).hide();
    }


    public void configuraRecyclerView(List<ProdutoObj> todosProdutos) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        configuracaoPadraoDaLista(linearLayoutManager);
        popularListaProdutos(todosProdutos);
    }



    public void configuraItemTouchHelper(RecyclerView listaProduto) {
        ItemTouchHelper touchHelper = new ItemTouchHelper
                (new NotasItemTouchHelper(_adapter, this));
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
        atualizarQuantidadeESoma();
        configuraItemTouchHelper(_binding.rvListaProduto);
        controleVisaoLista(todosProdutos);
    }

    private void atualizarQuantidadeESoma() {
        _binding.valorTotal.setText(NumberUtils.formatarDecimal(_adapter.getTotal()));
        _binding.quantidade.setText(String.valueOf(_adapter.getItemCount()));
    }

    public void controleVisaoLista(List<ProdutoObj> todosProdutos) {
        if (todosProdutos.size() > 0) {
            _binding.mensagemListaVazia.setVisibility(View.GONE);
            _binding.imagemSeta.setVisibility(View.GONE);
        } else {
            _binding.mensagemListaVazia.setVisibility(View.VISIBLE);
            _binding.imagemSeta.setVisibility(View.VISIBLE);
        }
        atualizarQuantidadeESoma();
    }

    public void irParaFormulario(View v) {
        Intent intent = new Intent(this, FormularioProdutosActivity.class);
        startActivityForResult(intent, REQUEST_CODE_INSERE_PRODUTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ehResultadoNovoProduto(requestCode, resultCode, data)) {
            ProdutoObj produtoRecebido = null;
            if (data != null) {
                produtoRecebido = (ProdutoObj) data.getSerializableExtra(CHAVE_PRODUTO);
            }
            adicionaProduto(produtoRecebido);
        } else if (ehResultadoDeAlteracao(requestCode, resultCode, data)) {
            ProdutoObj produtoRecebido = null;
            if (data != null) {
                produtoRecebido = (ProdutoObj) data.getSerializableExtra(CHAVE_PRODUTO);
            }

            int posicaoRecebida = Objects.requireNonNull(data).
                    getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);

            alteraProduto(produtoRecebido, posicaoRecebida);
        }
    }

    private void alteraProduto(ProdutoObj produto, int posicao) {
        _presenter.alteraProduto(produto);
        _adapter.altera(posicao, produto);
        atualizarQuantidadeESoma();
    }

    private boolean ehResultadoDeAlteracao(int requestCode, int resultCode, Intent data) {
        return requestCode == REQUEST_CODE_ALTERA_PRODUTO
                && resultCode == Activity.RESULT_OK
                && data != null
                && data.hasExtra(CHAVE_PRODUTO);
    }

    private void adicionaProduto(ProdutoObj produtoRecebido) {
        _presenter.insereProduto(produtoRecebido);
        _adapter.adicionaProduto(produtoRecebido);
        atualizarQuantidadeESoma();
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
        new BuscarTodosProdutosTask(_produtoDao, this::controleVisaoLista).execute();
    }
}
