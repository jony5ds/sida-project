package com.alura.sida.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.alura.sida.R;
import com.alura.sida.databinding.ListaDeProdutosActivityBinding;

import static com.alura.sida.ui.Const.REQUEST_CODE_INSERE_PRODUTO;

public class ListaDeProdutosActivity extends AppCompatActivity {

    ListaDeProdutosActivityBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = DataBindingUtil.setContentView(this,
                R.layout.lista_de_produtos_activity);
        getSupportActionBar().hide();
    }

    public void irParaFormulario(View v)
    {
        Intent intent = new Intent(this, FormularioProdutosActivity.class);
        startActivityForResult(intent,REQUEST_CODE_INSERE_PRODUTO);
    }
}
