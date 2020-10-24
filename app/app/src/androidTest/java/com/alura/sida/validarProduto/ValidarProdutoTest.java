package com.alura.sida.validarProduto;

import com.alura.sida.model.ProdutoObj;

import java.util.ArrayList;
import java.util.List;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


 public class ValidarProdutoTest {

     private ProdutoObj produtoObj

    public static List<ProdutoObj> getInstance2produtos()
    {
        List<ProdutoObj> todosProdutos = new ArrayList<>();
        todosProdutos.add(new ProdutoObj("Arroz","Tio João",15f,"5", 2));
        todosProdutos.add(new ProdutoObj("Feijão","Carreteiro",8f,"5", 5));

        return todosProdutos;
    }
}
