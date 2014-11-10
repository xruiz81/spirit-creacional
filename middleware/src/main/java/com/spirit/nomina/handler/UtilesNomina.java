package com.spirit.nomina.handler;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoRolIf;

public class UtilesNomina {

	
	public static OperacionNomina getIngresoEgreso(TipoRolIf tipoRolIf,RubroIf rubroIf) throws GenericBusinessException{
		if ( rubroIf == null )
			return OperacionNomina.EGRESO;
		
		String tipoRubro = rubroIf.getTipoRubro();
		if ( TipoRubro.ADELANTO.getLetra().equals( tipoRubro ) ){
			if ( tipoRolIf.getNombre().contains("MENSUAL") ||
				 tipoRolIf.getNombre().contains("QUINCENAL") )
				return OperacionNomina.INGRESO;
			return OperacionNomina.EGRESO;
		}
		
		if ( TipoRubro.QUINCENA.getLetra().equals(tipoRubro) ){ //ANTICIPO A LA QUINCENA - QUINCENA
			if ( tipoRolIf.getNombre().contains("MENSUAL") )
				return OperacionNomina.EGRESO;
			else if ( tipoRolIf.getNombre().contains("QUINCENAL") )
				return OperacionNomina.INGRESO;
		}
		  
		if ( TipoRubro.SUELDO.getLetra().equals(tipoRubro) || //SUELDO
			 TipoRubro.BENEFICIO.getLetra().equals(tipoRubro) ){ //BENEFICIO
			return OperacionNomina.INGRESO;
		}
		
		if ( TipoRubro.COMISION.getLetra().equals(tipoRubro) ){
			return OperacionNomina.INGRESO;
		}
		
		return OperacionNomina.EGRESO;
	
	}
	
}
