package com.alura.sida.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.alura.sida.R;
import com.alura.sida.databinding.ListaDeProdutosActivityBinding;

public class ListaDeProdutosActivity extends AppCompatActivity {

    ListaDeProdutosActivityBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = DataBindingUtil.setContentView(this,R.layout.lista_de_produtos_activity);
        getSupportActionBar().hide();

    }
}
