package com.spirit.util;

public class ValidarNumero {
	/**
     * Devuelve true si el String sigue la plantilla diseñada con minE mínimo de
     * enteros, maxE máximo de enteros y maxD como máximo de decimales
     * @param unNumero el número a verificar si es válido
     * @param minE el número mínimo de dígitos enteros
     * @param maxE el número máximo de dígitos enteros
     * @param maxD el número máximo de dígitos fraccionarios
     * @return true en caso de ser un n&uacute;mero válido, caso contrario retorna false.
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
