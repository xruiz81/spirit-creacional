package com.spirit.medios.session;

import java.util.Collection;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.CampanaProductoEJB;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.CampanaProductoVersionEJB;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._CampanaProductoVersionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CampanaProductoVersionSessionService extends _CampanaProductoVersionSessionService{
	
	public CampanaProductoVersionEJB registrarCampanaProductoVersion(CampanaProductoIf modelCampanaProducto,CampanaProductoVersionIf modelCampanaProductoVersion) throws com.spirit.exception.GenericBusinessException;

	public String getMaximoCodigoVersion(Long clienteId)throws com.spirit.exception.GenericBusinessException;

	public Collection findCampanaProductoVersionByIdCampana(Long idCampana,Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public Collection findCampanaProductoVersionByEmpresaId(Long idEmpresa)throws com.spirit.exception.GenericBusinessException;


}
