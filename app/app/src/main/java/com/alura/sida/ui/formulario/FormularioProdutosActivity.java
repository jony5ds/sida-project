package com.alura.sida.ui.formulario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
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
import static com.alura.sida.ui.Const.CHAVE_PRODUTO;
import static com.alura.sida.ui.Const.POSICAO_INVALIDA;

public class FormularioProdutosActivity extends AppCompatActivity {

    ActivityFormularioProdutosBinding _binding;
    private int _posicao = POSICAO_INVALIDA;
    private ProdutoObj _produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        montarLayout();

        mascaraMonetaria();

        Intent dadosRecebidos = getIntent();

        veficaEdicao(dadosRecebidos);

    }

    private void montarLayout() {
        _binding = DataBindingUtil.setContentView(this,
                R.layout.activity_formulario_produtos);
        getSupportActionBar().hide();
    }

    private void veficaEdicao(Intent dadosRecebidos) {
        if(dadosRecebidos.hasExtra(CHAVE_PRODUTO))
        {
            _produto = (ProdutoObj) dadosRecebidos
                    .getSerializableExtra(CHAVE_PRODUTO);

            _posicao = dadosRecebidos.getIntExtra(CHAVE_POSICAO,POSICAO_INVALIDA);

            preencheCampos(_produto);

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
            if (!temCampoInvalido()) {
                ProdutoObj novo_produto = criaProduto();
                retornaProduto(novo_produto);
                finish();
            }
    }

    private void retornaProduto(ProdutoObj novo_produto) {
        Intent resultadoProduto = new Intent();
        resultadoProduto.putExtra(CHAVE_PRODUTO,novo_produto);
        resultadoProduto.putExtra(CHAVE_POSICAO,_posicao);
        setResult(Activity.RESULT_OK,resultadoProduto);

    }

    private ProdutoObj criaProduto() {
        return _produto = new ProdutoObj(_binding.formNome.getText().toString(),
                _binding.formMarca.getText().toString(),
                NumberUtils.dinheiroParaFloat(_binding.formPreco.getText().toString()),
                _binding.formPeso.getText().toString());
    }


    public boolean temCampoInvalido() {
        ValidadorFormulário validarFormulario =
                new ValidadorFormulário(_binding.formNome.getText().toString());

        String mensagem = validarFormulario.validarNome();
        if (!mensagem.isEmpty()) {
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
