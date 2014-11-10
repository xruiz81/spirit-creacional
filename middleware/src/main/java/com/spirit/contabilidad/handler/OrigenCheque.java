package com.spirit.contabilidad.handler;

import com.spirit.exception.GenericBusinessException;

public enum OrigenCheque {
	ASIENTO,CARTERA,NOMINA;

	public static OrigenCheque getOrigenChequeByLetra(String letra ) throws GenericBusinessException{
		for ( OrigenCheque oc : OrigenCheque.values() ){
			if ( oc.getLetra().equals(letra) )
				return oc;
		}
		throw new GenericBusinessException("Letra "+letra+" no corresponde a ningun Origen de Cheque !!");
	}
	
	public String getLetra(){
		return this.name().substring(0,1);
	}
}
