package com.spirit.medios.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoPresupuesto {

	COTIZADO,PENDIENTE,APROBADO,CANCELADO,FACTURADO,PREPAGADO;
	
	String nombre = "";
	
	EstadoPresupuesto(){
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}
	
	public static EstadoPresupuesto getEstadoPresupuestoByLetra(String letra) throws GenericBusinessException{
		for ( int i = 0 ; i < EstadoPresupuesto.values().length ; i++  ){
			EstadoPresupuesto estado = EstadoPresupuesto.values()[i];
			if ( estado.getLetra().equals(letra) )
				return estado;
		}
		throw new GenericBusinessException("Estado no considerado para Presupuesto !!");
	}
	
	public String getLetra(){
		if ( this == COTIZADO )
			return this.name().substring(2, 3);
		
		else if ( this == PREPAGADO )
			return this.name().substring(1, 2);
		
		return this.name().substring(0, 1); 
	}
	
	public String toString() {
		return nombre;
	}
	
}
