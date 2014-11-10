package com.spirit.compras.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoOrdenCompra {

	INACTIVA,PENDIENTE,EMITIDA,ORDENADA,ENVIADA,INGRESADA,PREPAGADA,ANULADA;
	
	public static EstadoOrdenCompra getEstadoOrdenCompraByLetra(String letra) throws GenericBusinessException{
		
		for ( EstadoOrdenCompra e : values() ){
			if ( e.getLetra().equals(letra) ){
				return e;
			}
		}
		throw new GenericBusinessException("Letra no corresponde a ningun Estado !!");
	}
	
	public static String getLetraEstadoOrdenCompra(EstadoOrdenCompra estado) throws GenericBusinessException{
		if (estado == INACTIVA || estado == ENVIADA || estado == PENDIENTE || estado == ANULADA)
			return estado.toString().substring(0,1);
		else if ( estado == INGRESADA || estado == ORDENADA)
			return estado.toString().substring(2,3);
		else if ( estado == EMITIDA || estado == PREPAGADA)
			return estado.toString().substring(1,2);
		
		throw new GenericBusinessException("Estado "+estado+" no considerado en la obtencion de Inicial");
	}
	
	public String getLetra() throws GenericBusinessException{
		return getLetraEstadoOrdenCompra(this);
	}
}
