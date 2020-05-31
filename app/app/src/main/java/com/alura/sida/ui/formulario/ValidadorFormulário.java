package com.alura.sida.ui.formulario;

public class ValidadorFormulário {

    private final String produtoNome;
    private final String produtoPreco;
    private final String produtoMarca;
    private final String produtoPeso;

    public ValidadorFormulário(String produtoNome,
                               String produtoPreco,
                               String produtoMarca,
                               String produtoPeso) {
        this.produtoNome = produtoNome;
        this.produtoPreco = produtoPreco;
        this.produtoMarca = produtoMarca;
        this.produtoPeso = produtoPeso;
    }

    public String validar() {
        String mensagem = "";

        if (produtoNome.isEmpty())
        {
            mensagem = "Preencha o nome do produto";
        }
         else if (produtoMarca.isEmpty())
        {
             mensagem = "Preencha a marca do produto";
        }
         else if (produtoPreco.isEmpty())
        {
            mensagem = "Preencha o preço do produto";
        }
        return mensagem;
    }
}
