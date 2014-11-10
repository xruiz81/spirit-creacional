package com.spirit.seguridad.handler;

import com.spirit.exception.GenericBusinessException;

public enum TipoUsuarioTimeTracker {

	SUPER,POR_EQUIPO,EJECUTIVO,JEFE;
	
	String nombre = "";
	
	TipoUsuarioTimeTracker() {
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}
	
	public static TipoUsuarioTimeTracker getTipoUsuarioByLetra(String letra) throws GenericBusinessException{
		if ( letra == null )
			return null;
			
		for ( TipoUsuarioTimeTracker tutt : values() ){
			if ( tutt.getLetra().equals(letra) )
				return tutt;
		}
		throw new GenericBusinessException("Letra para Tipo de Usuario de TimeTracker no considerada !!");
	}
	
	public String getLetra(){
		return this.name().substring(0, 1);
	}
	
	public String toString() {
		return nombre;
	}
	
}
