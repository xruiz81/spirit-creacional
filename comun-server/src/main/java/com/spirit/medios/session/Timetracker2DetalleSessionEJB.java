package com.spirit.medios.session;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.medios.entity.Timetracker2DetalleEJB;
import com.spirit.medios.entity.Timetracker2DetalleIf;
import com.spirit.medios.entity.Timetracker2EJB;
import com.spirit.medios.entity.Timetracker2EmpleadoEJB;
import com.spirit.medios.entity.Timetracker2EmpleadoIf;
import com.spirit.medios.entity.Timetracker2If;
import com.spirit.medios.session.generated._Timetracker2DetalleSession;
import com.spirit.server.QueryBuilder;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class Timetracker2DetalleSessionEJB extends _Timetracker2DetalleSession implements Timetracker2DetalleSessionRemote,Timetracker2DetalleSessionLocal  {

	@EJB private UtilitariosSessionLocal utilitariosLocal;
	
	
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection findTimetracker2DetalleByQueryByFechaInicioAndByFechaFin(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, Long empleadoId, boolean resetearFechas) throws GenericBusinessException {
		
		String objectName = "td";
		
		Long clienteOficinaId = 0L;
		if(aMap.get("timetracker2ClienteOficinaId") != null){
			clienteOficinaId = (Long)aMap.get("timetracker2ClienteOficinaId");
			aMap.remove("timetracker2ClienteOficinaId");
		}
		
		String where = "";
		if (!aMap.isEmpty()) {
			where = QueryBuilder.buildWhere(aMap, objectName) + " and";
		}

		String queryString = "select td from Timetracker2DetalleEJB td, Timetracker2EmpleadoEJB te where " + where + " td.timetracker2EmpleadoId = te.id and td.fecha >= :fechaInicio and td.fecha <= :fechaFin";
		
		if(clienteOficinaId.compareTo(0L) == 1){
			queryString += " and td.clienteOficinaId = " + clienteOficinaId + "";
		}
		
		if(empleadoId.compareTo(0L) == 1){
			queryString += " and te.empleadoId = " + empleadoId + "";
		}
		
		String orderByPart = " order by td.fecha asc";
		queryString += orderByPart;
		
		Query query = manager.createQuery(queryString);
		
		if(resetearFechas){
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
		}		
		
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		/*if(clienteOficinaId.compareTo(0L) == 1){
			query.setParameter("clienteOficinaId", clienteOficinaId);
		}*/

		return query.getResultList();
	}
	
	public Collection findTimetracker2DetalleByQueryByFechaInicioAndByFechaFin2(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, Long empleadoId, boolean resetearFechas) throws GenericBusinessException {
		
		String objectName = "td";
		
		Long clienteOficinaId = 0L;
		if(aMap.get("timetracker2ClienteOficinaId") != null){
			clienteOficinaId = (Long)aMap.get("timetracker2ClienteOficinaId");
			aMap.remove("timetracker2ClienteOficinaId");
		}
		
		Long oficinaId = 0L;
		if(aMap.get("oficinaId") != null){
			oficinaId = (Long)aMap.get("oficinaId");
			aMap.remove("oficinaId");
		}
		
		Long departamentoId = 0L;
		if(aMap.get("departamentoId") != null){
			departamentoId = (Long)aMap.get("departamentoId");
			aMap.remove("departamentoId");
		}
		
		String where = "";
		if (!aMap.isEmpty()) {
			where = QueryBuilder.buildWhere(aMap, objectName) + " and";
		}

		String queryString = "select distinct td, te, ta, e from Timetracker2DetalleEJB td, Timetracker2EmpleadoEJB te, Timetracker2EJB ta, EmpleadoEJB e where " + where + " td.timetracker2EmpleadoId = te.id and te.timetracker2Id = ta.id and te.empleadoId = e.id and td.fecha >= :fechaInicio and td.fecha <= :fechaFin";
		
		if(clienteOficinaId.compareTo(0L) == 1){
			queryString += " and td.clienteOficinaId = " + clienteOficinaId + "";
		}
		
		if(oficinaId.compareTo(0L) == 1){
			queryString += " and e.oficinaId = " + oficinaId + "";
		}
		
		if(departamentoId.compareTo(0L) == 1){
			queryString += " and e.departamentoId = " + departamentoId + "";
		}
		
		if(empleadoId.compareTo(0L) == 1){
			queryString += " and te.empleadoId = " + empleadoId + "";
		}
		
		//importante este orden para el reporte timetracker consolidado
		String orderByPart = " order by e.nombres, e.apellidos asc";
		queryString += orderByPart;
		
		Query query = manager.createQuery(queryString);
		
		if(resetearFechas){
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
		}		
		
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		return query.getResultList();
	}
	
	public void procesarTimetracker2DetalleColeccion(Vector<Timetracker2DetalleIf> detalles) throws com.spirit.exception.GenericBusinessException{
				
		for(Timetracker2DetalleIf detalle : detalles){
			
			boolean detalleExiste = false;
			//si es de un cliente particular debo ver si existen registros sin cliente a los cuales haya 
			//que actualizar porque coinciden con timetracker2empleadoId y con la fecha.
			//if(detalle.getClienteOficinaId() != null){
				Map aMap = new HashMap();
				aMap.put("timetracker2EmpleadoId", detalle.getTimetracker2EmpleadoId());
				aMap.put("fecha", detalle.getFecha());
				Collection detallesSinCliente = findTimetracker2DetalleByQuery(aMap);
				Iterator detallesSinClienteIt = detallesSinCliente.iterator();
				while(detallesSinClienteIt.hasNext()){
					Timetracker2DetalleIf detalleSinCliente = (Timetracker2DetalleIf)detallesSinClienteIt.next();
					if(detalle.getClienteOficinaId() != null && detalleSinCliente.getClienteOficinaId() == null){
						detalle.setId(detalleSinCliente.getId());
						Timetracker2DetalleEJB model = registrarTimetracker2Detalle(detalle);
						manager.merge(model);
						detalleExiste = true;
					}else if(detalle.getClienteOficinaId() == null && detalleSinCliente.getClienteOficinaId() != null){
						detalle.setId(detalleSinCliente.getId());
						detalle.setClienteOficinaId(detalleSinCliente.getClienteOficinaId());
						Timetracker2DetalleEJB model = registrarTimetracker2Detalle(detalle);
						manager.merge(model);
						detalleExiste = true;
					}
				}				
			//}						
			
			if(!detalleExiste){
				Timetracker2DetalleEJB model = registrarTimetracker2Detalle(detalle);
				if(detalle.getId() == null){
					manager.persist(model);
				}else{
					manager.merge(model);
				}
			}			
		}		
	}
	
	public Timetracker2DetalleEJB registrarTimetracker2Detalle(Timetracker2DetalleIf detalle){
		Timetracker2DetalleEJB model = new Timetracker2DetalleEJB();
			 		
 		model.setEstado(detalle.getEstado());
 		model.setClienteOficinaId(detalle.getClienteOficinaId());
 		model.setFecha(detalle.getFecha());
 		model.setId(detalle.getId());
 		model.setPrimaryKey(detalle.getPrimaryKey());
 		model.setTiempo(detalle.getTiempo());
 		model.setTimetracker2EmpleadoId(detalle.getTimetracker2EmpleadoId());
 		model.setTiempoDesignado(detalle.getTiempoDesignado());
 		
		return model;
	}
}
