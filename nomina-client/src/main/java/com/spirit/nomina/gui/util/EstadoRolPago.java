package com.spirit.nomina.gui.util;

import com.spirit.exception.GenericBusinessException;

public enum EstadoRolPago {
	GENERADO,CERRADO;
	
	/*public static String getLetraEstadoRolPago(EstadoRolPago estado) throws GenericBusinessException{
		if ( estado == GENERADO || estado ==  CERRADO )
			return estado.toString().substring(0,1);
		throw new GenericBusinessException("Estado de Rol "+estado.toString()+"de Pago no considerado !!");
	}*/
	
	public static EstadoRolPago getEstadoRolPagoByLetra(String letra) throws GenericBusinessException{
		if ( letra.equals(GENERADO.getLetra() ) )
			return  GENERADO; 
		else if ( letra.equals(CERRADO.getLetra()) )
			return  CERRADO;
		throw new GenericBusinessException("Letra "+letra+" no considerada para obención de estado !!");
	}
	
	public String getLetra(){
		return this.name().substring(0,1);
	}
	
}
