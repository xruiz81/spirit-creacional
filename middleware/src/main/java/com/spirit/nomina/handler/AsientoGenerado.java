package com.spirit.nomina.handler;

import com.spirit.exception.GenericBusinessException;

public enum AsientoGenerado {

	SI, NO;
	
	private static String getAsientoGenerado(AsientoGenerado estado) throws GenericBusinessException{
		return estado.toString().substring(0,1);
		
	}
	
	public static AsientoGenerado getAsientoGeneradoByLetra(String letra) throws GenericBusinessException{
		AsientoGenerado[] ecs = values();
		for ( AsientoGenerado ec : ecs ){
			if ( letra.equals(ec.toString().substring(0,1)) );
			return ec;
		}
		throw new GenericBusinessException("Letra no considerada en la obtención del Asiento Generado !!");
	}
	
	public String getLetra() throws GenericBusinessException{
		return getAsientoGenerado(this);
	}
	
}
