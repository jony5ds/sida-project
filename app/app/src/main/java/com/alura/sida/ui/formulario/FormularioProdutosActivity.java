package com.alura.sida.ui.formulario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.alura.sida.R;
import com.alura.sida.dao.ProdutoDao;
import com.alura.sida.databinding.ActivityFormularioProdutosBinding;
import com.alura.sida.model.ProdutoObj;
import com.alura.sida.widget.MoneyTextWatcher;

import java.util.Locale;

public class FormularioProdutosActivity extends AppCompatActivity {

    ActivityFormularioProdutosBinding _binding;
    ProdutoDao _dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = DataBindingUtil.setContentView(this,
                R.layout.activity_formulario_produtos);
        getSupportActionBar().hide();
        _dao = new ProdutoDao();

        mascaraMonetaria();

    }

    private void mascaraMonetaria() {
        Locale locale = new Locale("pt", "BR");
        _binding.formPreco.addTextChangedListener(new MoneyTextWatcher(_binding.formPreco,locale));
    }

    public void salvar(View v)
    {
        ProdutoObj novo_produto = criaProduto();
        _dao.insere(novo_produto);
        finish();

    }

    private ProdutoObj criaProduto() {
        return new ProdutoObj("Arroz", "Tio João",5f,"8");
    }
}
