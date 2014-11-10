package com.spirit.util;

public class ValidarIdentificacion {
    
    private static final String RUC_SUFFIX = "001";
    
    public static boolean esNumeroIdentificacionValido(String tipoIdentificacion, String numeroIdentificacion) {
    	
    	if (tipoIdentificacion.equals("CE"))
    		return esNumeroCedulaValido(numeroIdentificacion);
    	else if (tipoIdentificacion.equals("RU"))
    		return esNumeroRucValido(numeroIdentificacion);
    	
    	return false;
    }
    
    private static boolean esNumeroCedulaValido(String numeroCedula) {
        boolean esNumeroCedulaValido = false;
        int digitoIndice = 0;
        int suma = 0;
        int contador = 0;
        int digitoVerificador;
        
        if (numeroCedula != null && !numeroCedula.trim().equals("")) {
        	String cedula = numeroCedula;
        	
        	if (ValidarNumero.esNumeroValido(cedula,10,10,0)) {
            	int provincia = Integer.parseInt(cedula.substring(0, 2));
            	
                if (esProvincia(provincia)) {
                	String digito;
                    int resultado;

                    while (digitoIndice < 9) {
                    	digitoIndice = 2 * contador + 1;
                        digito = cedula.substring(digitoIndice - 1, digitoIndice);
                        resultado = Integer.parseInt(digito) * 2;
                        
                        if (resultado >= 10)
                            resultado = 1 + resultado % 10;
                       
                        suma += resultado;
                        contador++;
                    }
                    
                    digitoIndice = 1;
                    contador = 1;
                    
                    while (digitoIndice < 8) {
                    	digitoIndice = 2 * contador;
                        digito = cedula.substring(digitoIndice - 1, digitoIndice);
                        suma += Integer.parseInt(digito);
                        contador++;
                    }

                    digitoVerificador = ((Math.round(suma / 10) + 1) * 10) - suma;
                    
                    if (digitoVerificador == 10)
                        digitoVerificador = 0;
                    
                    if (digitoVerificador == Integer.parseInt(cedula.substring(9, 10)))
                        esNumeroCedulaValido = true;
                }
            }
        }
        
        return esNumeroCedulaValido;
    }
    
    private static boolean esProvincia(int provincia) {
    	return (provincia >= 1 && provincia <= 23);
    }
        
    private static boolean esNumeroRucValido(String numeroRuc) {
        boolean esNumeroRucValido = false;
        String parteCedula = "";
        String parteVec3 = "";
        String sufijoRuc = "";
        
        if (numeroRuc != null && !numeroRuc.trim().equals("")) {
        	sufijoRuc = numeroRuc.substring(10, numeroRuc.length());
            
        	if (sufijoRuc.equals(RUC_SUFFIX)) {
                if (ValidarNumero.esNumeroValido(numeroRuc, 13, 13, 0)) {
                	parteCedula = numeroRuc.trim().substring(0, 10);
                    
                    if (Integer.parseInt(parteCedula) != 0) {
                        parteVec3 = numeroRuc.substring(2, 3);
                        
                        int vec3 = Integer.parseInt(parteVec3);
                        
                        if (vec3 >= 0 && vec3 <= 5)
                        	esNumeroRucValido = esNumeroCedulaValido(parteCedula);
                        else {
                            if (vec3 == 6)
                            	esNumeroRucValido = validarTercero6(parteCedula);
                            else if (vec3 == 9)
                                esNumeroRucValido = validarTercero9(parteCedula);
                        }
                    }
                }
            }
        }
        
        return esNumeroRucValido;
    }
    
    private static boolean validarTercero6(String rucSeis) {
        boolean esValido = false;
        int uno = Integer.parseInt(rucSeis.substring(0, 1));
        int dos = Integer.parseInt(rucSeis.substring(1, 2));
        int tres = Integer.parseInt(rucSeis.substring(2, 3));
        int cuatro = Integer.parseInt(rucSeis.substring(3, 4));
        int cinco = Integer.parseInt(rucSeis.substring(4, 5));
        int seis = Integer.parseInt(rucSeis.substring(5, 6));
        int siete = Integer.parseInt(rucSeis.substring(6, 7));
        int ocho = Integer.parseInt(rucSeis.substring(7, 8));
        int nueve = Integer.parseInt(rucSeis.substring(8, 9));
        
        int suma = uno * 3 + dos * 2 + tres * 7 + cuatro * 6 + cinco * 5 + seis * 4 + siete * 3 + ocho * 2;
        
        while (suma > 11)
            suma -= 11;
        
        if ((11 - suma) == nueve)
        	esValido = true;
        
        return esValido;
    }
    
    private static boolean validarTercero9(String rucNueve) {
        boolean esValido = false;
        int uno = Integer.parseInt(rucNueve.substring(0, 1));
        int dos = Integer.parseInt(rucNueve.substring(1, 2));
        int tres = Integer.parseInt(rucNueve.substring(2, 3));
        int cuatro = Integer.parseInt(rucNueve.substring(3, 4));
        int cinco = Integer.parseInt(rucNueve.substring(4, 5));
        int seis = Integer.parseInt(rucNueve.substring(5, 6));
        int siete = Integer.parseInt(rucNueve.substring(6, 7));
        int ocho = Integer.parseInt(rucNueve.substring(7, 8));
        int nueve = Integer.parseInt(rucNueve.substring(8, 9));
        int diez = Integer.parseInt(rucNueve.substring(9, 10));
        
        int suma = uno * 4 + dos * 3 + tres * 2 + cuatro * 7 + cinco * 6 + seis * 5 + siete * 4 + ocho * 3 + nueve * 2;
        
        while (suma > 11)
            suma -= 11;
        
        if ((11 - suma) == diez)
        	esValido = true;

        return esValido;
    }
}
