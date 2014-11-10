package com.spirit.compras.session;

import java.sql.Date;
import java.util.Collection;

import com.spirit.compras.entity.LogCompraRetencionIf;
import com.spirit.compras.session.generated._LogCompraRetencionSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LogCompraRetencionSessionService extends _LogCompraRetencionSessionService{
	
	public Collection<LogCompraRetencionIf> getLogCompraRetencionByDocumento_Cliente_FechaInicio_FechaFin(
			String codigoDocumento, Long clienteId, Date fechaInicio,
			Date fechaFin, java.lang.Long idEmpresa)
			throws GenericBusinessException ;
	
}
