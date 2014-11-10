package com.spirit.nomina.session;

import java.sql.Date;
import java.util.Collection;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoGastoDeducibleIf;
import com.spirit.nomina.session.generated._ContratoGastoDeducibleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoGastoDeducibleSessionService extends _ContratoGastoDeducibleSessionService{
	
	public void actualizarContratoGastoDeducible(Collection<ContratoGastoDeducibleIf> contratoGastosDeduciblesRemovidos,
			Collection<ContratoGastoDeducibleIf> contratoGastoDeducibleColleccion) throws GenericBusinessException;
		
	
	
	//Crear metodo para busqueda de gastos deducible por contrato por rango de fecha
	public Collection<ContratoGastoDeducibleIf> findContratoGastoDeducibleByFechas(Long contratoId,Date fecha);
	
}
