package com.spirit.medios.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.ReunionProductoEJB;
import com.spirit.medios.entity.ReunionProductoIf;
import com.spirit.medios.session.generated._ReunionProductoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionProductoSessionService extends _ReunionProductoSessionService{

	public ReunionProductoEJB registrarReunionProductoCliente(ReunionProductoIf modelReunionProducto, ProductoClienteIf modelProductoCliente) throws GenericBusinessException;
	public ReunionProductoEJB registrarReunionProductoProspectoCliente(ReunionProductoIf modelReunionProducto, String modelProductoProspectoCliente) throws GenericBusinessException;
}
