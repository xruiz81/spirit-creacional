package com.spirit.sri.dimm;

import java.security.GeneralSecurityException;

public enum TipoDeclaracion {

	REOC, ANEXO_TRANSACCIONAL;
	
	
	public String getInicialesParaNombreArchivo() throws GeneralSecurityException{
		if ( this == REOC ){
			return "REOC";
		} else if (this == ANEXO_TRANSACCIONAL) {
			return "AT";
		} else 
			throw new GeneralSecurityException("Tipo de Declaración no considerado !!");
	}
	
}
