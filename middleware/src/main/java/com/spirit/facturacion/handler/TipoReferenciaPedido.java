package com.spirit.facturacion.handler;

import com.spirit.exception.GenericBusinessException;

public enum TipoReferenciaPedido {

	NINGUNO,PRESUPUESTO,PLAN_MEDIOS,COMBINADO;

	String nombre = "";

	TipoReferenciaPedido(){
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}

	public static TipoReferenciaPedido getTipoReferenciaPedidoByLetra(String letra) throws GenericBusinessException{
		for ( int i = 0 ; i < TipoReferenciaPedido.values().length ; i++  ){
			TipoReferenciaPedido estado = TipoReferenciaPedido.values()[i];
			if ( estado.getLetra().equals(letra) )
				return estado;
		}
		throw new GenericBusinessException("Estado no considerado para Tipo de Referencia !!");
	}
	
	public String getLetra(){
		if ( this == TipoReferenciaPedido.PLAN_MEDIOS )
			return this.name().substring(8, 9);
		return this.name().substring(0,1);
	}
	
	public String toString() {
		return nombre;
	}

}
