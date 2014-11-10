package com.spirit.cartera.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoCarteraPago {

	EMITIDO,APROBADO,PAGADO,ANULADO;
	
	String nombre = "";
	
	EstadoCarteraPago(){
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}
	
	public static EstadoCarteraPago getEstadoCarteraPagoByLetra(String letra) throws GenericBusinessException{
		for ( int i = 0 ; i < EstadoCarteraPago.values().length ; i++  ){
			EstadoCarteraPago estado = EstadoCarteraPago.values()[i];
			if ( estado.getLetra().equals(letra) )
				return estado;
		}
		throw new GenericBusinessException("Estado no considerado para Presupuesto !!");
	}
	
	public String getLetra(){
		if ( this == ANULADO )
			return this.name().substring(1, 2);
		
		return this.name().substring(0, 1); 
	}
	
	public String toString() {
		return nombre;
	}	
}
