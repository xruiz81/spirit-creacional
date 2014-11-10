package com.spirit.medios.session;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.medios.entity.OverComisionEJB;
import com.spirit.medios.entity.OverComisionIf;
import com.spirit.medios.session.generated._OverComisionSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class OverComisionSessionEJB extends _OverComisionSession implements OverComisionSessionRemote,OverComisionSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public void procesarOverComision(List<OverComisionIf> overColeccion, List<OverComisionIf> overColeccionEliminado) throws com.spirit.exception.GenericBusinessException{
		
		for(OverComisionIf overComision : overColeccion){
			if(overComision.getId() == null){
				OverComisionEJB model = registrarOverComision(overComision);
				manager.persist(model);
			}else{
				OverComisionEJB model = registrarOverComision(overComision);
				manager.merge(model);
			}
		}
		
		for(OverComisionIf overEliminado : overColeccionEliminado){
			if(overEliminado.getId() != null){
				OverComisionEJB model = manager.find(OverComisionEJB.class, overEliminado.getId());
				manager.remove(model);
				manager.flush();
			}
		}
	}
	
	public OverComisionEJB registrarOverComision(OverComisionIf overComision){
		
		OverComisionEJB model = new OverComisionEJB();
		model.setId(overComision.getId());
		model.setProveedorOficinaId(overComision.getProveedorOficinaId());
		model.setProveedorId(overComision.getProveedorId());
		model.setAnio(overComision.getAnio());
		model.setInversionDe(overComision.getInversionDe());
		model.setInversionA(overComision.getInversionA());
		model.setPorcentajeOver(overComision.getPorcentajeOver());
		model.setObjetivo(overComision.getObjetivo());
				
		return model;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findOverComisionByClienteOficinaAndByFechaInicioFechaFin(Long clienteOficinaId, Timestamp fechaInicio, Timestamp fechaFin) {

		//select distinct * from OVER_COMISION oc where oc.cliente_oficina_Id = 65 and oc.fecha_inicial <= '2014-10-08' and oc.fecha_final >= '2014-01-02'
		String queryString = "select distinct oc from OverComisionEJB oc "
				+ "where oc.fechaInicial <= :fechaFin and oc.fechaFinal >= :fechaInicio ";
				  
		if(clienteOficinaId != null && clienteOficinaId.compareTo(0L) == 1){
			queryString += " and oc.clienteOficinaId = " + clienteOficinaId + "";
		}	
				
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		
		return query.getResultList();
	}
}
