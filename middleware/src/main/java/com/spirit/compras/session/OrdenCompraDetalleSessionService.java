package com.spirit.compras.session;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.compras.session.generated._OrdenCompraDetalleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenCompraDetalleSessionService extends _OrdenCompraDetalleSessionService{
	
	Collection findOrdenCompraByQueryByProductoAndByFechas(Map<String,Long> mapaOrdenesCompraDetalle, Timestamp timeIncio, Timestamp timeFin, Long empresaId, String agruparBy) throws com.spirit.exception.GenericBusinessException;
}
