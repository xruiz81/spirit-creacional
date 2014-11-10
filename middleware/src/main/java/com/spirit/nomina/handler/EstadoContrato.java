package com.spirit.nomina.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoContrato {

	ACTIVO,INACTIVO,TERMINADO;
	
	
	private static String getEstadoContrato(EstadoContrato estado) throws GenericBusinessException{
		return estado.toString().substring(0,1);
		
	}
	
	public static EstadoContrato getEstadoContratoByLetra(String letra) throws GenericBusinessException{
		EstadoContrato[] ecs = values();
		for ( EstadoContrato ec : ecs ){
			if ( letra.equals(ec.toString().substring(0,1)))
				return ec;
		}
		throw new GenericBusinessException("Letra no considerada en la obtención del Estado !!");
	}
	
	public String getLetra() throws GenericBusinessException{
		return getEstadoContrato(this);
	}
	
	
}
