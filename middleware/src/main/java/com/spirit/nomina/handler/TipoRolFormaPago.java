package com.spirit.nomina.handler;

import com.spirit.exception.GenericBusinessException;

public enum TipoRolFormaPago {

	PERIODO,POR_FECHAS;
	
	String nombre = "";
	
	TipoRolFormaPago() {
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}
	
	public static TipoRolFormaPago getTipoRolPagoByLetra(String letra) throws GenericBusinessException{
		
		for ( int i = 0 ; i < TipoRolFormaPago.values().length ; i++  ){
			TipoRolFormaPago forma = TipoRolFormaPago.values()[i];
			if ( forma.getLetra().equals(letra) )
				return forma;
		}
		throw new GenericBusinessException("Letra no considerada para Forma de Pago !!");
	}
	
	private String getTipoRolLetra() throws GenericBusinessException{
		if ( this == TipoRolFormaPago.PERIODO )
			return "P";
		else if ( this == TipoRolFormaPago.POR_FECHAS ){
			return "F";
		} else 
			throw new GenericBusinessException("Forma de Pago no considerada !!");
	}
	
	public String getLetra() throws GenericBusinessException{
		return getTipoRolLetra();
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
}
