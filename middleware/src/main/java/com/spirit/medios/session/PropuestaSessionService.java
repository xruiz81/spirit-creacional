package com.spirit.medios.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.PropuestaDetalleIf;
import com.spirit.medios.entity.PropuestaIf;
import com.spirit.medios.session.generated._PropuestaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PropuestaSessionService extends _PropuestaSessionService{

	Collection findPropuestaByQuery(int startIndex,int endIndex,Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int findPropuestaByQuerySize(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	Collection findPropuestaByQueryAndByIdCliente(int startIndex,int endIndex,Map aMap, Long idCliente, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int findPropuestaByQueryAndByIdClienteSize(Map aMap, Long idCliente, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	Collection findPropuestaByEmpresaId(Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public void procesarPropuesta(PropuestaIf model,List<PropuestaDetalleIf> modelDetalleList) throws GenericBusinessException;
	public void actualizarPropuesta(PropuestaIf model,List<PropuestaDetalleIf> modelDetalleList) throws GenericBusinessException;
	public void eliminarPropuesta(Long propuestaId) throws GenericBusinessException;
}
