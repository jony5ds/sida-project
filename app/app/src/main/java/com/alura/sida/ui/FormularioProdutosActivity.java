package com.alura.sida.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.alura.sida.R;
import com.alura.sida.databinding.ActivityFormularioProdutosBinding;

public class FormularioProdutosActivity extends AppCompatActivity {

    ActivityFormularioProdutosBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = DataBindingUtil.setContentView(this,
                R.layout.activity_formulario_produtos);
        getSupportActionBar().hide();

    }

    public void salvar(View v)
    {

    }
}
