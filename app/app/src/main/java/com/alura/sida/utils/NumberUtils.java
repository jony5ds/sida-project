package com.alura.sida.utils;

import java.text.DecimalFormat;

public class NumberUtils {
    public static String  formatarDecimal(float valor)
    {
        String resultado;
        DecimalFormat formato = new DecimalFormat("#.00");
        return resultado = formato.format(valor);
    }

    public static float dinheiroParaFloat(String valor)
    {
        try {

            String resultado = valor.replace("R$","");

            resultado = resultado.replaceAll(",",".")
                    .replaceAll("\\s", "");

            int simbolos = 0;

            for(int i = 0; i < resultado.length(); i++) {
                if(resultado.charAt(i) == '.') simbolos++;
            }

            if (simbolos > 1)
            {
                resultado = resultado.replaceFirst("\\.","");
            }

            Float floatObject = new Float(resultado);
            return floatObject.floatValue();
        }
        catch (Exception e)
        {
            return 0f;
        }

    }
}
