package com.spirit.nomina.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoRolPago {
	GENERADO,CERRADO;
	
	public static String getLetraEstadoRolPago( EstadoRolPago estado ) throws GenericBusinessException{
		if ( estado == EstadoRolPago.GENERADO || estado == EstadoRolPago.GENERADO ){
			return estado.toString().substring(0,1);
		}
		throw new GenericBusinessException("Estado de Rol de Pago no considerado !!");
	}
}
