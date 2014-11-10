package com.spirit.medios.session;

import java.util.ArrayList;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.PlanMedioFacturacionEJB;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.session.generated._PlanMedioFacturacionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanMedioFacturacionSessionService extends _PlanMedioFacturacionSessionService{
	public Long procesarPlanMedioFacturacion(PlanMedioFacturacionIf PlanMedioFacturacion) throws GenericBusinessException;
	public void procesarPlanMedioFacturacionList(ArrayList<PlanMedioFacturacionIf> listaPlanMedioFacturacionEscogido) throws GenericBusinessException;
	public PlanMedioFacturacionEJB registrarPlanMedioFacturacion(PlanMedioFacturacionIf model);
	public Long actualizarPlanMedioFacturacion(PlanMedioFacturacionIf model) throws GenericBusinessException;
	public void actualizarPlanMedioFacturacionList(ArrayList<PlanMedioFacturacionIf> listaPlanMedioFacturacionEscogido) throws GenericBusinessException;
	public java.util.Collection findPlanMedioFacturacionByQueryAndByEstados(Map aMap, String[] estados) throws GenericBusinessException;
}
