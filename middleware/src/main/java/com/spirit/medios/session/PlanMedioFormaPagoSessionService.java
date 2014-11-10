package com.spirit.medios.session;

import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.PlanMedioFormaPagoEJB;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.session.generated._PlanMedioFormaPagoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanMedioFormaPagoSessionService extends _PlanMedioFormaPagoSessionService{
	public Long procesarPlanMedioFormaPago(PlanMedioFormaPagoIf model) throws GenericBusinessException;
	public PlanMedioFormaPagoEJB registrarPlanMedioFormaPago(PlanMedioFormaPagoIf model);
	public Long actualizarPlanMedioFormaPago(PlanMedioFormaPagoIf model) throws GenericBusinessException;
	public java.util.Collection findPlanMedioFormaPagoByQueryAndByEstados(Map aMap, String[] estados) throws GenericBusinessException;
}
