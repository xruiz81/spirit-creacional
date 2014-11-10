package com.spirit.medios.handler;

import com.spirit.exception.GenericBusinessException;

public enum FrecuenciaHerramientasMedios {

	DIARIO,SEMANAL,QUINCENAL,MENSUAL,BIMENSUAL,TRIMESTRAL,SEMESTRAL,ANUAL;
	
	String nombre = "";
	
	FrecuenciaHerramientasMedios(){
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}
	
	public static FrecuenciaHerramientasMedios getFrecuenciaHerramientasMediosByLetra(String letra) throws GenericBusinessException{
		for ( int i = 0 ; i < FrecuenciaHerramientasMedios.values().length ; i++  ){
			FrecuenciaHerramientasMedios frecuencia = FrecuenciaHerramientasMedios.values()[i];
			if ( frecuencia.getLetra().equals(letra) )
				return frecuencia;
		}
		throw new GenericBusinessException("Estado no considerado para Presupuesto !!");
	}
	
	public String getLetra(){
		if ( this == SEMESTRAL )
			return this.name().substring(6, 7);
		
		return this.name().substring(0, 1); 
	}
	
	public String toString() {
		return nombre;
	}	
}
