package com.spirit.medios.session;

import java.util.Collection;

import com.spirit.medios.session.generated._PlanMedioDetalleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanMedioDetalleSessionService extends _PlanMedioDetalleSessionService{

	Collection findPlanMedioDetalleByPlanMedioMesIdAndByProveedorId(java.lang.Long idPlanMedioMes, java.lang.Long idProveedor) throws com.spirit.exception.GenericBusinessException;
}
