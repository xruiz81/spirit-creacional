package com.spirit.medios.session;

import java.util.Map;

import javax.ejb.*;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._PlanMedioFormaPagoSession;
import com.spirit.medios.session.PlanMedioFormaPagoSessionLocal;
import com.spirit.medios.session.PlanMedioFormaPagoSessionRemote;
import com.spirit.medios.entity.*;
import com.spirit.server.QueryBuilder;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class PlanMedioFormaPagoSessionEJB extends _PlanMedioFormaPagoSession implements PlanMedioFormaPagoSessionRemote,PlanMedioFormaPagoSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long procesarPlanMedioFormaPago(PlanMedioFormaPagoIf model) throws GenericBusinessException {
		
		Long idPlanMedioFormaPago = 0L;
		
		try {
			PlanMedioFormaPagoEJB planMedioFormaPago = registrarPlanMedioFormaPago(model);	
			manager.persist(planMedioFormaPago);
			//addPlanMedioFormaPago(planMedioFormaPago);
			idPlanMedioFormaPago = planMedioFormaPago.getPrimaryKey();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar el Plan Medio Forma de Pago");
		}	
		
		return idPlanMedioFormaPago;
	}
	
	public PlanMedioFormaPagoEJB registrarPlanMedioFormaPago(PlanMedioFormaPagoIf model) {
		PlanMedioFormaPagoEJB planMedioFormaPago = new PlanMedioFormaPagoEJB();
		
		planMedioFormaPago.setId(model.getId());
		planMedioFormaPago.setPedidoId(model.getPedidoId());
		planMedioFormaPago.setPlanMedioId(model.getPlanMedioId());
		planMedioFormaPago.setFechaInicio(model.getFechaInicio());
		planMedioFormaPago.setFechaFin(model.getFechaFin());
		planMedioFormaPago.setTipoFormaPago(model.getTipoFormaPago());
		planMedioFormaPago.setFormaPagoId(model.getFormaPagoId());
		planMedioFormaPago.setEstado(model.getEstado());
		planMedioFormaPago.setFormaPagoCampanaProductoVersionId(model.getFormaPagoCampanaProductoVersionId());
			
		return planMedioFormaPago;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long actualizarPlanMedioFormaPago(PlanMedioFormaPagoIf model) throws GenericBusinessException {
		Long idPlanMedioFormaPago = 0L;
		
		try {
			PlanMedioFormaPagoEJB planMedioFormaPago = registrarPlanMedioFormaPago(model);			
			manager.merge(planMedioFormaPago);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al actualizar el PlanMedioFormaPago ");
		}	
		
		return idPlanMedioFormaPago;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPlanMedioFormaPagoByQueryAndByEstados(Map aMap, String[] estados) {
		
		String objectName = "pmf";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		String queryString = "select distinct pmf from PlanMedioFormaPagoEJB pmf where " + where +"";		
		
		for(int i = 0; i < estados.length; i++){
			if(i == 0 && estados.length == 1){
				queryString += " and pmf.estado = '" + estados[i] + "'";
			}else if (i == 0 && estados.length > 1){
				queryString += " and (pmf.estado = '" + estados[i] + "'";
			}else if (i > 0 && i <= (estados.length -2)){
				queryString += " or pmf.estado = '" + estados[i] + "'";
			}else if (i > 0 && i == (estados.length -1)){
				queryString += " or pmf.estado = '" + estados[i] + "')";
			}
		}
		
		queryString += " order by pmf.id asc";
		
		Query query = manager.createQuery(queryString);
		
		if(aMap.get("planMedioId") != null){
			Long planMedioId = (Long) aMap.get("planMedioId");
			query.setParameter("planMedioId", planMedioId);
			
		}
		if(aMap.get("pedidoId") != null){
			Long pedidoId = (Long) aMap.get("pedidoId");		
			query.setParameter("pedidoId", pedidoId);
			
		}
		
		return query.getResultList();
	}
}
