package com.spirit.nomina.handler;

import com.spirit.exception.GenericBusinessException;

public enum TipoRubro {

	ADELANTO,	//L
	
	BENEFICIO,	//B
	DESCUENTO,	//D
	SUELDO,		//S
	QUINCENA,	//Q
	
	ANTICIPO,	//A
	EVENTUAL,	//E
	EVENTUAL_BENEFICIOS_SOCIALES,	//O
	PROVISION,	//P
	
	COMISION,	//C
	UTILIDAD;	//U
	
	//OTROS_INGRESOS;	//I
	
	String nombre = "";
	
	public String getLetra() throws GenericBusinessException{
		if ( this == ADELANTO )
			return "L";
		else if (this == EVENTUAL_BENEFICIOS_SOCIALES)
			return "O";
		/*else if (this == OTROS_INGRESOS)
			return "I";*/
		else 
			return this.name().substring(0, 1);
	}
	
	public static TipoRubro getTipoRubroByLetra(String letra) throws GenericBusinessException{
		TipoRubro trs[] = values();
		for (TipoRubro tr : trs){
			if ( letra.equals(tr.getLetra()) )
				return tr;
		}
		throw new GenericBusinessException("Letra no registrada en Tipo de Rubro !!");
	}
	
	public static TipoRubro getTipoRubroByNombre(String nombre) throws GenericBusinessException{
		TipoRubro trs[] = values();
		for (TipoRubro tr : trs){
			if ( tr.toString().equals(nombre) )
				return tr;
		}
		throw new GenericBusinessException("Nombre no registrado en Tipo de Rubro !!");
	}
	
	public String toString() {
		if ("".equals(nombre)){
			String[] palabras = this.name().split("_");
			for ( String palabra : palabras ){
				nombre = nombre + palabra + " ";
			}
			nombre = nombre.substring(0, nombre.length()-1);
		}
		return nombre;
	}
	
}
