package com.spirit.cartera.session;

import java.sql.Date;
import java.util.Collection;

import com.spirit.cartera.entity.LogCarteraIf;
import com.spirit.cartera.session.generated._LogCarteraSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LogCarteraSessionService extends _LogCarteraSessionService{
	
	public Collection<LogCarteraIf> getLogCarteraByDocumento_Cliente_FechaInicio_FechaFin(
			String codigoDocumento, Long clienteId, Date fechaInicio,
			Date fechaFin, java.lang.Long idEmpresa)
			throws GenericBusinessException;
	
}
