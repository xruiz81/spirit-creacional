package com.spirit.medios.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoOrdenTrabajo {

	PENDIENTE,EN_CURSO,REALIZADO,CANCELADO,SUSPENDIDO,ENTREGADO;
	
	String nombre = "";
	
	EstadoOrdenTrabajo(){
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}
	
	public static EstadoOrdenTrabajo getEstadoOrdenTrabajoByLetra(String letra) throws GenericBusinessException{
		if(letra.equals("T")){
			return ENTREGADO;
		}else{
			for ( int i = 0 ; i < EstadoOrdenTrabajo.values().length ; i++  ){
				EstadoOrdenTrabajo estado = EstadoOrdenTrabajo.values()[i];
				if ( estado.getLetra().equals(letra) ){
					return estado;
				}
			}
		}				
		throw new GenericBusinessException("Estado no considerado para Orden de Trabajo !!");
	}
	
	public String getLetra(){
		if(nombre.equals(ENTREGADO.toString())){
			return nombre.substring(2,3);
		}else{
			return nombre.substring(0,1);
		}
	}
	
	public String toString() {
		return nombre;
	}
}
