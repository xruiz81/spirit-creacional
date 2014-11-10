package com.spirit.contabilidad.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoPeriodo {

	INACTIVO, ACTIVO, CERRADO, PARCIAL ;
	
	public static EstadoPeriodo getEstadoPeriodoByLetra(String letra) throws GenericBusinessException{
		for ( EstadoPeriodo periodo : values() ){
			if ( periodo.getLetra().equals(letra) ){
				return periodo;
			}
		}
		throw new GenericBusinessException("Letra de Periodo no considerado !!");
	}
	
	public String getLetra(){
		return this.toString().substring(0,1);
	}
	
}
