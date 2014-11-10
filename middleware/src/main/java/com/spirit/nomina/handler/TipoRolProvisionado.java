package com.spirit.nomina.handler;

import com.spirit.exception.GenericBusinessException;

public enum TipoRolProvisionado {

	SI,NO;
	
	public static TipoRolProvisionado getTipoRolProvisionado(String letra) throws GenericBusinessException{
		for ( TipoRolProvisionado trp : values() ){
			if ( trp.getLetra().equals(letra) )
				return trp;
		}
		throw new GenericBusinessException("Letra para Rol Provisionado no considerada !!");
	}
	
	public String getLetra(){
		return this.name().substring(0, 1);
	}
	
	public String toString() {
		return this.name().toString();
	}
}
