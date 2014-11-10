package com.spirit.rrhh.handler;

import com.spirit.exception.GenericBusinessException;

public enum NivelEstudioEnum {

	PRIMARIA,SECUNDARIA,TECNICO,UNIVERSIDAD,POSTGRADO, MAESTRIA,PHD;
	
	public String getLetra(){
		if ( this == PHD ){
			return "H";
		} else if ( this == POSTGRADO ){
			return "G";
		} else {
			return this.toString().substring(0,1);
		}
	}
	
	public static NivelEstudioEnum getNivelEstudioByLetra(String letra) throws GenericBusinessException{
		for ( NivelEstudioEnum nivel : values() ){
			if ( letra.equals(nivel.getLetra()) ){
				return nivel;
			}
		}
		throw new GenericBusinessException("Nivel de estudio no considerado !!");
	}
	
	
}
