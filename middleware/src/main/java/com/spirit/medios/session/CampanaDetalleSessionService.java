package com.spirit.medios.session;

import com.spirit.medios.entity.CampanaDetalleEJB;
import com.spirit.medios.entity.CampanaDetalleIf;
import com.spirit.medios.session.generated._CampanaDetalleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CampanaDetalleSessionService extends _CampanaDetalleSessionService{

	public CampanaDetalleEJB registrarCampanaDetalle(CampanaDetalleIf modelDetalle) throws com.spirit.exception.GenericBusinessException;
}
