package com.spirit.medios.session;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.PlanMedioFacturacionEJB;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.session.generated._PlanMedioFacturacionSession;
import com.spirit.server.QueryBuilder;


/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class PlanMedioFacturacionSessionEJB extends _PlanMedioFacturacionSession implements PlanMedioFacturacionSessionRemote,PlanMedioFacturacionSessionLocal  {
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@Resource private SessionContext ctx;
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long procesarPlanMedioFacturacion(PlanMedioFacturacionIf model) throws GenericBusinessException {
		
		Long idPlanMedioFacturacion = 0L;
		
		try {
			PlanMedioFacturacionEJB planMedioFacturacion = registrarPlanMedioFacturacion(model);			
			addPlanMedioFacturacion(planMedioFacturacion);
			idPlanMedioFacturacion = planMedioFacturacion.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar el Plan Medio Facturacion");
		}	
		
		return idPlanMedioFacturacion;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarPlanMedioFacturacionList(ArrayList<PlanMedioFacturacionIf> listaPlanMedioFacturacionEscogido) throws GenericBusinessException {
		
		for(PlanMedioFacturacionIf planMedioFacturacionIf : listaPlanMedioFacturacionEscogido){
			procesarPlanMedioFacturacion(planMedioFacturacionIf);
		}	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long actualizarPlanMedioFacturacion(PlanMedioFacturacionIf model) throws GenericBusinessException {
		Long idPlanMedioFacturacion = 0L;
		
		try {
			PlanMedioFacturacionEJB planMedioFacturacion = registrarPlanMedioFacturacion(model);			
			manager.merge(planMedioFacturacion);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar el Pedido");
		}	
		
		return idPlanMedioFacturacion;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarPlanMedioFacturacionList(ArrayList<PlanMedioFacturacionIf> listaPlanMedioFacturacionEscogido) throws GenericBusinessException {
		
		for(PlanMedioFacturacionIf planMedioFacturacionIf : listaPlanMedioFacturacionEscogido){
			actualizarPlanMedioFacturacion(planMedioFacturacionIf);
		}
	}	
	
	public PlanMedioFacturacionEJB registrarPlanMedioFacturacion(PlanMedioFacturacionIf model) {
		PlanMedioFacturacionEJB planMedioFacturacion = new PlanMedioFacturacionEJB();
		
		planMedioFacturacion.setId(model.getId());
		//planMedioFacturacion.setComercialId(model.getComercialId());
		//ADD 12 MAYO
		planMedioFacturacion.setProductoClienteId(model.getProductoClienteId());
		//ADD 9 SEPTIEMBRE
		planMedioFacturacion.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
		
		planMedioFacturacion.setFechaInicio(model.getFechaInicio());
		planMedioFacturacion.setFechaFin(model.getFechaFin());
		planMedioFacturacion.setPedidoId(model.getPedidoId());
		planMedioFacturacion.setPlanMedioId(model.getPlanMedioId());
		planMedioFacturacion.setCanje(model.getCanje());
		planMedioFacturacion.setProveedorId(model.getProveedorId());
		planMedioFacturacion.setEstado(model.getEstado());
		planMedioFacturacion.setPorcentajeCanje(model.getPorcentajeCanje());
		planMedioFacturacion.setOrdenMedioId(model.getOrdenMedioId());
		//ADD
		planMedioFacturacion.setOrdenMedioDetalleId(model.getOrdenMedioDetalleId());
		planMedioFacturacion.setOrdenMedioDetalleMapaId(model.getOrdenMedioDetalleMapaId());
		
		
		return planMedioFacturacion;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPlanMedioFacturacionByQueryAndByEstados(Map aMap, String[] estados) {
		
		String objectName = "pmf";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		String queryString = "select distinct pmf from PlanMedioFacturacionEJB pmf where " + where +"";		
		
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
