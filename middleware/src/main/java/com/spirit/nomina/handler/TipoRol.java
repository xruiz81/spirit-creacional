package com.spirit.nomina.handler;

import com.spirit.nomina.entity.TipoRolIf;

public enum TipoRol {

	QUINCENAL,MENSUAL,
	SALARIO_MINIMO_VITAL,
	TIPO_ROL_PROVISION,
	DECIMO_TERCERO,DECIMO_CUARTO,APORTE_PATRONAL,FONDO_RESERVA,VACACIONES,
	UTILIDADES;
	
	String nombre = "";
	
	TipoRol(){
		String [] palabras = this.name().split("_");
		for ( String palabra : palabras){
			nombre += (palabra + " ");
		}
		nombre = nombre.substring(0, nombre.length()-1);
	}
	
	@Override
	public String toString() {
		return nombre;
	}
		
	public static TipoRol obtenerTipoRol(TipoRolIf tipoRolIf) {
		if ( tipoRolIf.getNombre().contains("QUINCENAL") ){
			return TipoRol.QUINCENAL;
		} else if ( tipoRolIf.getNombre().contains("MENSUAL") ){
			return TipoRol.MENSUAL;
		} else if ( tipoRolIf.getNombre().contains("DECIMO") ){
			if ( tipoRolIf.getNombre().contains("TERCERO") ){
				return TipoRol.DECIMO_TERCERO;
			} else if ( tipoRolIf.getNombre().contains("CUARTO") ){
				return TipoRol.DECIMO_CUARTO;
			}
		} else if ( tipoRolIf.getNombre().contains("APORTE") && tipoRolIf.getNombre().contains("PATRONAL") ){
			return TipoRol.APORTE_PATRONAL;
		} else if ( tipoRolIf.getNombre().contains("FONDO") && tipoRolIf.getNombre().contains("RESERVA") ){
			return TipoRol.FONDO_RESERVA;
		} else if ( tipoRolIf.getNombre().contains("VACACIONES") ){
			return TipoRol.VACACIONES;
		} else if ( tipoRolIf.getNombre().contains("UTILIDAD") ){
			return UTILIDADES;
		}
		return null;
	}
	
}
