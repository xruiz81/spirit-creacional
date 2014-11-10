package com.spirit.medios.session;

import java.util.Collection;
import java.util.List;

import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.session.generated._ComercialSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ComercialSessionService extends _ComercialSessionService{

	   Collection findComercialByClienteIdAndByEmpresaId(Long idCliente,Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	   Collection findComercialByEmpresaIdByEstadoActivoAndByClienteId(Long idEmpresa, Long idCliente) throws com.spirit.exception.GenericBusinessException;
	   public void procesarComercialColeccion(List<ComercialIf> comercialColeccion, List<ComercialIf> comercialEliminadoColeccion, Long clienteId) throws com.spirit.exception.GenericBusinessException;
	   //ADD 7 SEPTIEMBRE
	   public Collection findComercialByProductoClienteIdAndVersion(Long idEmpresa, Long idProductoCliente, String version) throws com.spirit.exception.GenericBusinessException;
}
