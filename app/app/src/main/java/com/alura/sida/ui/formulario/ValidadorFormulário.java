package com.alura.sida.ui.formulario;

public class ValidadorFormulário {

    private final String produtoNome;
    private String produtoQuantidade;

    public ValidadorFormulário(String produtoNome, String quantidade) {
        this.produtoNome = produtoNome;
        this.produtoQuantidade = quantidade;
    }

    public String validar() {
        String mensagem = "";

        if (produtoNome.isEmpty())
        {
            mensagem = "Preencha o nome do produto";
        }

        if (produtoQuantidade.isEmpty())
            mensagem = "Preencha a quantidade do produto";

        return mensagem;
    }

}
