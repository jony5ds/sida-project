package com.alura.sida.mock;

import com.alura.sida.model.ProdutoObj;

import java.util.ArrayList;
import java.util.List;


public class ProdutoMock {

    public static List<ProdutoObj> getInstance0produtos() {
        List<ProdutoObj> todosProdutos = new ArrayList<>();
        todosProdutos.add(new ProdutoObj());
        todosProdutos.add(new ProdutoObj());
        return todosProdutos;
    }

    public static List<ProdutoObj> getInstance2produtos() {
        List<ProdutoObj> todosProdutos = new ArrayList<>();
        todosProdutos.add(new ProdutoObj("Arroz", "Tio João", 10f, "5", 2));
        todosProdutos.add(new ProdutoObj("Feijão", "Carreteiro", 5f, "5", 2));
        return todosProdutos;
    }

    public static List<ProdutoObj> getInstance1produto3quantidades() {
        List<ProdutoObj> todosProdutos = new ArrayList<>();
        todosProdutos.add(new ProdutoObj("Macarrão", "Piraquê", 20f, "5", 3));
        return todosProdutos;
    }

    public static List<ProdutoObj> getInstance1produto3quantidadesNegativas() {
        List<ProdutoObj> todosProdutos = new ArrayList<>();
        todosProdutos.add(new ProdutoObj("Biscoito", "Traquinas", 20f, "5", -3));
        return todosProdutos;
    }

}
