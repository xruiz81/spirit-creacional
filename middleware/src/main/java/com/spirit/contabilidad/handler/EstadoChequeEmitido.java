package com.spirit.contabilidad.handler;

import com.spirit.exception.GenericBusinessException;


public enum EstadoChequeEmitido {
	TODOS,EMITIDO,COBRADO,ANULADO;
	
	public String getLetra(){
		return this.name().substring(0,1);
	}
	
	public static EstadoChequeEmitido getEstadoChequeEmitido(String letra) throws GenericBusinessException{
		for ( EstadoChequeEmitido estadoCheque : EstadoChequeEmitido.values() ){
			if ( estadoCheque.getLetra().equals(letra) )
				return estadoCheque;
		}
		throw new GenericBusinessException("Letra de Estado de Cheque no considerada !!");
	}
}
