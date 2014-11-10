package com.spirit.nomina.handler;

public enum ModoOperacionRubro {

	CALCULADO,REGISTRADO;
	
	public String getLetra(){
		return this.toString().substring(0, 1);
	}
	
}
