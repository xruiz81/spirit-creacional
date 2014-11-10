package com.spirit.contabilidad.session;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.PeriodoDetalleEJB;
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoEJB;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.session.generated._PeriodoSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>PeriodoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class PeriodoSessionEJB extends _PeriodoSession implements PeriodoSessionRemote, PeriodoSessionLocal {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   
   @EJB 
   private PeriodoDetalleSessionLocal periodoDetalleLocal; 
   
   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(PeriodoSessionEJB.class);

   @Resource private SessionContext ctx;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void procesarPeriodo(PeriodoIf model,List<PeriodoDetalleIf> modelDetalleList) throws GenericBusinessException {
	   try {
		   PeriodoEJB periodo = registrarPeriodo(model);
		   manager.persist(periodo);

		   for (PeriodoDetalleIf modelDetalle : modelDetalleList) {

			   modelDetalle.setPeriodoId(periodo.getPrimaryKey());
			   PeriodoDetalleEJB periodoDetalle = registrarPeriodoDetalle(modelDetalle);
			   manager.merge(periodoDetalle);
		   }
	   } catch (Exception e) {
		   ctx.setRollbackOnly();
		   log.error("Error al guardar información en PeriodoEJB.", e);
		   throw new GenericBusinessException("Se ha producido un error al guardar el Periodo");
	   }
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void eliminarPeriodo(Long periodoId) throws GenericBusinessException {
	   try {
		   PeriodoEJB data = manager.find(PeriodoEJB.class, periodoId);			
		   Collection<PeriodoDetalleIf> modelDetalleList = periodoDetalleLocal.findPeriodoDetalleByPeriodoId(periodoId);

		   for (PeriodoDetalleIf modelDetalle : modelDetalleList) {				
			   manager.remove(modelDetalle);
		   }			
		   manager.remove(data);
		   manager.flush();

	   } catch (Exception e) {
		   ctx.setRollbackOnly();
		   log.error("Error al eliminar información en PeriodoEJB.", e);
		   throw new GenericBusinessException(
		   "Error al eliminar información en Periodo");
	   }		
   }

   public PeriodoEJB registrarPeriodo(PeriodoIf model) {
	   PeriodoEJB periodo = new PeriodoEJB();

	   periodo.setCodigo(model.getCodigo());
	   periodo.setEmpresaId(model.getEmpresaId());
	   periodo.setFechafin(model.getFechafin());
	   periodo.setFechaini(model.getFechaini());
	   periodo.setId(model.getId());
	   periodo.setStatus(model.getStatus());
	   periodo.setOrden(model.getOrden());

	   return periodo;
   }

   public PeriodoDetalleEJB registrarPeriodoDetalle(PeriodoDetalleIf modelDetalle) {
	   PeriodoDetalleEJB periodoDetalle = new PeriodoDetalleEJB();

	   periodoDetalle.setAnio(modelDetalle.getAnio());
	   periodoDetalle.setId(modelDetalle.getId());
	   periodoDetalle.setMes(modelDetalle.getMes());
	   periodoDetalle.setPeriodoId(modelDetalle.getPeriodoId());
	   periodoDetalle.setStatus(modelDetalle.getStatus());

	   return periodoDetalle;
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findPeriodoByEmpresaIdAndByFechaFin(Long idEmpresa, Date fechaFin){
	   //select * from periodo p where p.EMPRESA_ID = 1 and p.FECHAFIN = TO_Date('2006-12-31', 'YYYY-MM-DD')
	   String queryString = "select distinct p from PeriodoEJB p where p.empresaId = " + idEmpresa + " and p.fechafin = :fechaFin";
	   Query query = manager.createQuery(queryString);
	   query.setParameter("fechaFin",fechaFin);
	   return query.getResultList();
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection<PeriodoIf> findPeriodoByRangoFechas(Long idEmpresa,Date fechaInicio, Date fechaFin) throws GenericBusinessException{
	   //select * from periodo p where p.EMPRESA_ID = 1 and p.FECHAFIN = TO_Date('2006-12-31', 'YYYY-MM-DD')
	   String queryString = "select distinct p from PeriodoEJB p where p.empresaId = " + idEmpresa + " and :fechaInicio >= p.fechaini" +
	   " and :fechaFin <= p.fechafin ";
	   Query query = manager.createQuery(queryString);
	   query.setParameter("fechaInicio",fechaInicio);
	   query.setParameter("fechaFin",fechaFin);
	   return query.getResultList();
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findPeriodoForAsientoAutomatico(Long idEmpresa, Date fechaAsiento) throws GenericBusinessException {
	   String queryString = "select distinct p from PeriodoEJB p where p.empresaId = " + idEmpresa + " and p.fechaini <= :fechaAsiento and p.fechafin >= :fechaAsiento and (p.status = 'A' or p.status = 'P')";
	   Query query = manager.createQuery(queryString);
	   query.setParameter("fechaAsiento",fechaAsiento);
	   return query.getResultList();
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findPeriodoByMesAndAnio(String mes, String anio) throws GenericBusinessException {
	   //select distinct p.* from periodo p where p.ID in (select pd.PERIODO_ID from periodo_detalle pd where pd.MES = '09' and pd.ANIO = '2007')
	   String queryString = "select distinct p from PeriodoEJB p where p.id in (select pd.periodoId from PeriodoDetalleEJB pd where pd.mes = '" + mes + "' and pd.anio = '" + anio + "')";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }

   public boolean periodoActivoOParcial(AsientoIf model) {
	   Date fechaAsiento = new java.sql.Date(model.getFecha().getTime()); 
	   PeriodoIf periodo = getPeriodo(model.getPeriodoId()); 
	   if("P".equals(periodo.getStatus()) || "A".equals(periodo.getStatus()))
		   return true;
	   return false;
   }
   
   public Map mapearPeriodos(Long idEmpresa) throws GenericBusinessException {
		Map periodosMap = new HashMap();
		Iterator periodosIterator = findPeriodoByEmpresaId(idEmpresa).iterator();
		while (periodosIterator.hasNext()) {
			PeriodoIf periodo = (PeriodoIf) periodosIterator.next();
			periodosMap.put(periodo.getId(), periodo);
		}
		return periodosMap;
	}

}
