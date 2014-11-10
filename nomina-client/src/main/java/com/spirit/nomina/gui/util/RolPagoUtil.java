package com.spirit.nomina.gui.util;

import java.util.Collection;
import java.util.Set;

import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.handler.NominaParametros;

public class RolPagoUtil {

	
	public static void verificarRubroFondoReserva(Collection<Long> contratosIdCollection) throws GenericBusinessException{
		
		Set<String> setParametros = NominaUtil.getParametrosSet(true, NominaParametros.BASE_CODIGO_FONDO_RESERVA_PARAMETRO_EMPRESA);
		Collection<ContratoIf> resultado = SessionServiceLocator.getContratoSessionService()
			.getContratosSinFondoReserva(contratosIdCollection,setParametros);
		for ( ContratoIf c : resultado ){
			System.out.println("COD: "+c.getCodigo());
		}
				
	}


}
