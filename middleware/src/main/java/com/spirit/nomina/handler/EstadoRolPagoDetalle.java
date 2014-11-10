package com.spirit.nomina.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoRolPagoDetalle {
	EMITIDO,PAGADO,AUTORIZADO,
	PROVISIONADO,PROVISIONADO_PAGADO;
	
	String nombre = "";
	
	EstadoRolPagoDetalle(){
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1); 
	}
	
	public static EstadoRolPagoDetalle getEstadoRolPagoDetalleByLetra(String letra) throws GenericBusinessException{
		
		if ( letra.equals(EMITIDO.getLetra()) )
			return EMITIDO;
		else if ( letra.equals(PAGADO.getLetra()) )
			return PAGADO;
		else if ( letra.equals(AUTORIZADO.getLetra()) )
			return AUTORIZADO;
		else if ( letra.equals(PROVISIONADO.getLetra()) )
			return PROVISIONADO;
		else if ( letra.equals(PROVISIONADO_PAGADO.getLetra()) )
			return PROVISIONADO_PAGADO;
		else 
			throw new GenericBusinessException("Letra no corresponde a ningun Estado !!");
		
	}
	
	private static String getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle estado) throws GenericBusinessException{
		if ( estado == EstadoRolPagoDetalle.EMITIDO )
			return estado.toString().substring(0,1);
		else if ( estado == EstadoRolPagoDetalle.PROVISIONADO || estado == EstadoRolPagoDetalle.PAGADO 
				|| estado == EstadoRolPagoDetalle.AUTORIZADO )
			return estado.toString().substring(1,2);
		else if ( estado == PROVISIONADO_PAGADO )
			return "G";
		throw new GenericBusinessException("Estado "+estado+" no considerado en la obtencion de Inicial");
	}
	
	public String getLetra() throws GenericBusinessException{
		return getLetraEstadoRolPagoDetalle(this);
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
}
