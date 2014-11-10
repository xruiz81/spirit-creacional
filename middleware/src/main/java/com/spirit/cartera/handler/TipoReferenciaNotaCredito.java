package com.spirit.cartera.handler;

import com.spirit.exception.GenericBusinessException;

public enum TipoReferenciaNotaCredito {

	NINGUNO,FACTURA,COMPRA;

	String nombre = "";

	TipoReferenciaNotaCredito(){
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}

	public static TipoReferenciaNotaCredito getTipoReferenciaPedidoByLetra(String letra) throws GenericBusinessException{
		for ( int i = 0 ; i < TipoReferenciaNotaCredito.values().length ; i++  ){
			TipoReferenciaNotaCredito estado = TipoReferenciaNotaCredito.values()[i];
			if ( estado.getLetra().equals(letra) )
				return estado;
		}
		throw new GenericBusinessException("Estado no considerado para Tipo de Referencia !!");
	}
	
	public String getLetra(){
		return this.name().substring(0,1);
	}
	
	public String toString() {
		return nombre;
	}

}