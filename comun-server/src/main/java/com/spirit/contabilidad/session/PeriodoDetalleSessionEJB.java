package com.spirit.contabilidad.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.session.generated._PeriodoDetalleSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>PeriodoDetalleSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class PeriodoDetalleSessionEJB extends _PeriodoDetalleSession implements PeriodoDetalleSessionRemote, PeriodoDetalleSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(PeriodoDetalleSessionEJB.class);
   private static final String ESTADO_CERRADO = "C";

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
     
   public Collection findPeriodoDetalleByQueryAndEstadoActivoOrParcial(Map aMap) throws com.spirit.exception.GenericBusinessException {
	   String objectName = "pd";
	   String where = QueryBuilder.buildWhere(aMap, objectName);
	   String queryString = "from PeriodoDetalleEJB " + objectName + " where " + where + " and (pd.status = 'A' or pd.status = 'P')";
	   Query query = manager.createQuery(queryString);
	   Set keys = aMap.keySet();
	   Iterator it = keys.iterator();

	   while (it.hasNext()) {
		   String propertyKey = (String) it.next();
		   Object property = aMap.get(propertyKey);
		   query.setParameter(propertyKey, property);
	   }

	   return query.getResultList(); 
	}
   
   public Collection findPeriodoDetalleSiguientesNoInactivoByPeriodoIdAndMesAndAnio(Long idPeriodo, String mes, String anio, boolean periodoMesIncluido) throws com.spirit.exception.GenericBusinessException {
	   //select distinct pd.* from Periodo_Detalle pd where pd.PERIODO_ID = 1741 and pd.mes > '08' and pd.anio >= '2007' and pd.status <> 'I' order by pd.mes, pd.anio
	   String queryString = "select distinct pd from PeriodoDetalleEJB pd where pd.periodoId = " + idPeriodo;
	   if (periodoMesIncluido)
		   queryString += " and pd.mes >= '" + mes + "'";
	   else
		   queryString += " and pd.mes > '" + mes + "'"; 
	   
		queryString += " and pd.anio >= '" + anio + "' and pd.status <> 'I' order by pd.mes, pd.anio";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }

   public Collection findPeriodoDetalleInicialByPeriodoId(Long idPeriodo) throws com.spirit.exception.GenericBusinessException {
	   //SELECT id FROM periodo_detalle pd WHERE pd.MES = (SELECT min(MES) FROM periodo_detalle where periodo_id = 120) and pd.ANIO = (select min(anio) from periodo_detalle where periodo_id=120) 
	   String queryString = "select distinct pd from PeriodoDetalleEJB pd where pd.mes = (select min(mes) from PeriodoDetalleEJB where periodoId = " + idPeriodo + ") and pd.anio = (select min(anio) from PeriodoDetalleEJB where periodoId = " + idPeriodo + ")";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   public boolean periodoDetalleCerrado(String year, String month, Long periodoId) {
	   Map parameterMap = new HashMap();
	   parameterMap.put("anio", year);
	   parameterMap.put("mes", month);
	   parameterMap.put("periodoId", periodoId);
	   Iterator it = findPeriodoDetalleByQuery(parameterMap).iterator();
	   if (it.hasNext()) {
		   PeriodoDetalleIf periodoDetalle = (PeriodoDetalleIf) it.next();
		   if (periodoDetalle.getStatus().equals(ESTADO_CERRADO))
			   return true;
	   }
	   
	   return false;
   }
   
   public Map mapearPeriodosDetallesNoInactivosByPeriodosMap(Map periodosMap) {
	   Map periodosDetallesByPeriodoIdMap = new HashMap();
	   Iterator periodosIterator = periodosMap.keySet().iterator();
	   while (periodosIterator.hasNext()) {
		   Long periodoId = (Long) periodosIterator.next();
		   PeriodoIf periodo = (PeriodoIf) periodosMap.get(periodoId);
		   Vector<PeriodoDetalleIf> periodoDetalleVector = new Vector<PeriodoDetalleIf>();
		   String queryString = "select distinct pd from PeriodoDetalleEJB pd where pd.status <> 'I' and pd.periodoId = " + periodo.getId() + " order by pd.mes, pd.anio";
		   Query query = manager.createQuery(queryString);
		   Iterator resultListIterator = query.getResultList().iterator();
		   while (resultListIterator.hasNext()) {
			   PeriodoDetalleIf periodoDetalle = (PeriodoDetalleIf) resultListIterator.next();
			   if (periodoDetalle.getPeriodoId().compareTo(periodo.getId()) == 0)
				   periodoDetalleVector.add(periodoDetalle);
		   }
		   periodosDetallesByPeriodoIdMap.put(periodo.getId(), periodoDetalleVector);
	   }
	   
	   return periodosDetallesByPeriodoIdMap;
   }
}
