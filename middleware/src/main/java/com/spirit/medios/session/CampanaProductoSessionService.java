package com.spirit.medios.session;

import java.util.Collection;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.CampanaProductoEJB;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._CampanaProductoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CampanaProductoSessionService extends _CampanaProductoSessionService{

	java.util.Collection findProductosCampanaByCampanaId(java.lang.Long idCampana) throws com.spirit.exception.GenericBusinessException;
	public CampanaProductoEJB registrarCampanaProducto(CampanaProductoIf modelCampanaProducto, ProductoClienteIf modelProducto) throws com.spirit.exception.GenericBusinessException;
	
	public java.util.Collection findCampanaProductosByCampanaIdAndProductoClienteId(java.lang.Long idCampana,java.lang.Long idProductoCliente) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findCampanaProductoByEmpresaId(Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
}
