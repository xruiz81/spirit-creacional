package com.spirit.nomina.handler;

import com.spirit.exception.GenericBusinessException;

public enum EstadoRubroEventual {
	EMITIDO,AUTORIZADO,AUTORIZADO_PARCIAL,PAGADO;

	String nombre = "";
	
	EstadoRubroEventual() {
		String[] palabras = this.name().split("_");
		for ( String s : palabras )
			nombre += (s+" ");
		nombre = nombre.substring(0,nombre.length()-1);
	}
	
	private static String getLetraEstadoRubroEventual(EstadoRubroEventual estado) throws GenericBusinessException{
		if ( estado == EstadoRubroEventual.EMITIDO )
			return estado.toString().substring(0,1);
		else if (estado == EstadoRubroEventual.AUTORIZADO_PARCIAL){
			return "R";
		}else if ( estado == EstadoRubroEventual.PAGADO 
				|| estado == EstadoRubroEventual.AUTORIZADO )
			return estado.toString().substring(1,2);
		throw new GenericBusinessException("Estado no considerado en la obtencion de letra !!");
	}
	
	public static EstadoRubroEventual getRubroEventualByLetra(String letra) throws GenericBusinessException{
		if ( letra.equals(getLetraEstadoRubroEventual(EMITIDO)) )
			return EMITIDO;
		else if ( letra.equals(getLetraEstadoRubroEventual(AUTORIZADO)) )
			return AUTORIZADO;
		else if ( letra.equals(getLetraEstadoRubroEventual(PAGADO)) )
			return PAGADO;
		else if ( letra.equals(getLetraEstadoRubroEventual(AUTORIZADO_PARCIAL)) )
			return AUTORIZADO_PARCIAL; 
		throw new GenericBusinessException("Letra no considerada en la obtención del Estado !!");
	}
	
	public String getLetra() throws GenericBusinessException{
		return getLetraEstadoRubroEventual(this);
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
