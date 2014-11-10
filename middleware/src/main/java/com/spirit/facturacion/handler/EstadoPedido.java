package com.spirit.facturacion.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoPedido {

	PENDIENTE,COMPLETO,INCOMPLETO,ANULADO,COTIZACION;
	
	String nombre = "";
	
	EstadoPedido(){
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}
	
	public static EstadoPedido getEstadoOrdenTrabajoByLetra(String letra) throws GenericBusinessException{
		for ( int i = 0 ; i < EstadoPedido.values().length ; i++  ){
			EstadoPedido estado = EstadoPedido.values()[i];
			if ( estado.getLetra().equals(letra) )
				return estado;
		}
		throw new GenericBusinessException("Estado no considerado para el Pedido !!");
	}
	
	public String getLetra(){
		if ( this == COTIZACION )
			return nombre.substring(2, 3);
		return nombre.substring(0,1);
	}
	
	public String toString() {
		return nombre;
	}
}
