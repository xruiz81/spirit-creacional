package com.spirit.util;

public class ValidarNumero {
	/**
     * Devuelve true si el String sigue la plantilla dise�ada con minE m�nimo de
     * enteros, maxE m�ximo de enteros y maxD como m�ximo de decimales
     * @param unNumero el n�mero a verificar si es v�lido
     * @param minE el n�mero m�nimo de d�gitos enteros
     * @param maxE el n�mero m�ximo de d�gitos enteros
     * @param maxD el n�mero m�ximo de d�gitos fraccionarios
     * @return true en caso de ser un n&uacute;mero v�lido, caso contrario retorna false.
     */
    public static boolean esNumeroValido(String unNumero, int minE, int maxE, int maxD) {
        String regex = new String("[0-9]{%,#}([.]{1}[0-9]{0,&})?");
        String minEnteros = String.valueOf(minE);
        String maxEnteros = String.valueOf(maxE);
        String decimales = String.valueOf(maxD);
        regex = regex.replaceAll("%", minEnteros);
        regex = regex.replaceAll("#", maxEnteros);
        regex = regex.replaceAll("&", decimales);
        return (unNumero.matches(regex));
    }
}
