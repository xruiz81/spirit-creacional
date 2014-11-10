package com.spirit.nomina.session;

import java.sql.Date;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoUtilidadIf;
import com.spirit.nomina.session.generated._ContratoUtilidadSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoUtilidadSessionService extends _ContratoUtilidadSessionService{
	
	public Map<String,Object> getInformacionUtilidades(Long empresaId,Date fechaUltimoDiaMesRolPago) 
	throws GenericBusinessException;
	
	public ContratoUtilidadIf buscarContratoUtilidad(Long empresaId,Date fechaRolPago)
	throws GenericBusinessException;
	
	public Integer obtenerAnioUtilidadParametroEmpresa(Long empresaId)
	throws GenericBusinessException;
}
