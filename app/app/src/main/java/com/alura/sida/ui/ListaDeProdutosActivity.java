package com.alura.sida.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alura.sida.R;

public class ListaDeProdutosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


    }
}
