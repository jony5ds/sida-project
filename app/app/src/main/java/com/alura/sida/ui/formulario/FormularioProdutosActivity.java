package com.alura.sida.ui.formulario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alura.sida.R;
import com.alura.sida.dao.ProdutoDao;
import com.alura.sida.databinding.ActivityFormularioProdutosBinding;
import com.alura.sida.model.ProdutoObj;
import com.alura.sida.utils.NumberUtils;
import com.alura.sida.widget.MoneyTextWatcher;

import java.util.Locale;

import static com.alura.sida.ui.Const.CHAVE_POSICAO;
import static com.alura.sida.ui.Const.EDITAR_PRODUTO;
import static com.alura.sida.ui.Const.POSICAO_INVALIDA;

public class FormularioProdutosActivity extends AppCompatActivity {

    ActivityFormularioProdutosBinding _binding;
    ProdutoDao _dao;
    private int _posicao = POSICAO_INVALIDA;
    private boolean ehAcaoAlterar = false;
    private ProdutoObj _produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = DataBindingUtil.setContentView(this,
                R.layout.activity_formulario_produtos);
        getSupportActionBar().hide();
        _dao = new ProdutoDao();
        mascaraMonetaria();

        Intent dadosRecebidos = getIntent();

        veficaEdicao(dadosRecebidos);

    }

    private void veficaEdicao(Intent dadosRecebidos) {
        if(dadosRecebidos.hasExtra(EDITAR_PRODUTO))
        {
            _produto = (ProdutoObj) dadosRecebidos
                    .getSerializableExtra(EDITAR_PRODUTO);

            _posicao = dadosRecebidos.getIntExtra(CHAVE_POSICAO,POSICAO_INVALIDA);

            preencheCampos(_produto);
            ehAcaoAlterar = true;
        }
    }

    private void preencheCampos(ProdutoObj produtoRecebido) {
        _binding.formNome.setText(produtoRecebido.getNome());
        _binding.formMarca.setText(produtoRecebido.getMarca());
        _binding.formPreco.setText(produtoRecebido.getPrecoString());
        _binding.formPeso.setText(produtoRecebido.getKg());
    }

    private void mascaraMonetaria() {
        Locale locale = new Locale("pt", "BR");
        _binding.formPreco.addTextChangedListener(new MoneyTextWatcher(_binding.formPreco, locale));
    }

    public void salvar(View v) {
        if(!ehAcaoAlterar) {
            if (!temCampoInvalido()) {
                ProdutoObj novo_produto = criaProduto();
                _dao.insere(novo_produto);
                finish();
            }
        }else
        {
            _produto = criaProduto();
            _dao.altera(_posicao,_produto);
            finish();
        }

    }

    private ProdutoObj criaProduto() {
        return _produto = new ProdutoObj(_binding.formNome.getText().toString(),
                _binding.formMarca.getText().toString(),
                NumberUtils.dinheiroParaFloat(_binding.formPreco.getText().toString()),
                _binding.formPeso.getText().toString());
    }


    public boolean temCampoInvalido() {
        ValidadorFormulário validarFormulario =
                new ValidadorFormulário(_binding.formNome.getText().toString(),
                        _binding.formPreco.getText().toString(),
                        _binding.formMarca.getText().toString(),
                        _binding.formPeso.getText().toString());

        String mensagem = validarFormulario.validar();

        if (!mensagem.isEmpty()) {
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
