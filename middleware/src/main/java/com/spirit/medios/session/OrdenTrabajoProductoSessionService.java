package com.spirit.medios.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._OrdenTrabajoProductoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenTrabajoProductoSessionService extends _OrdenTrabajoProductoSessionService{

	public java.util.Collection findProductosClienteOrdenByOrdenTrabajoId(java.lang.Long idOrdenTrabajo) throws GenericBusinessException;
}
