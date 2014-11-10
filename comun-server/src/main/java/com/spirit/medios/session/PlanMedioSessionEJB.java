package com.spirit.medios.session;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.MapaComercialEJB;
import com.spirit.medios.entity.MapaComercialIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioDetalleMapaIf;
import com.spirit.medios.entity.OrdenMedioEJB;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioDetalleEJB;
import com.spirit.medios.entity.PlanMedioDetalleIf;
import com.spirit.medios.entity.PlanMedioEJB;
import com.spirit.medios.entity.PlanMedioFacturacionData;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PlanMedioMesEJB;
import com.spirit.medios.entity.PlanMedioMesIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._PlanMedioSession;
import com.spirit.util.FindQuery;

/**
 * The <code>PlanMedioSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class PlanMedioSessionEJB extends _PlanMedioSession implements PlanMedioSessionRemote, PlanMedioSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   @EJB 
   private PlanMedioDetalleSessionLocal planMedioDetalleLocal;
   
   @EJB 
   private MapaComercialSessionLocal mapaComercialLocal;
   
   @EJB 
   private ProductoClienteSessionLocal productoClienteLocal;
   
   @EJB 
   private PlanMedioMesSessionLocal planMedioMesLocal;
   
   @EJB 
   private CampanaProductoVersionSessionLocal campanaProductoVersionLocal;
   
   @EJB 
   private CampanaProductoSessionLocal campanaProductoLocal;
   
   @EJB 
   private OrdenMedioDetalleSessionLocal ordenMedioDetalleLocal;
   
   @EJB 
   private OrdenMedioDetalleMapaSessionLocal ordenMedioDetalleMapaLocal;
   
   @EJB 
   private ClienteOficinaSessionLocal clienteOficinaLocal;
   
   @EJB 
   private UtilitariosSessionLocal utilitariosLocal;
   
   private DecimalFormat formatoSerial = new DecimalFormat("00000");
   
   private static final String ORDEN_MEDIO_TIPO_CANAL = "C";
   
   @Resource private SessionContext ctx; 

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

     @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findPlanMedioByQuery(int startIndex,int endIndex,Map aMap, Long idEmpresa) {
	    if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
  	
	    String objectName = "e";
		String cadenaQuery = "select distinct e from PlanMedioEJB " + objectName + ", GrupoObjetivoEJB go where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + " and ";
		String orden = "order by e.codigo desc, e.id desc";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();	
	}
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int findPlanMedioByQuerySize(Map aMap, Long idEmpresa) {
	   
	    String objectName = "e";
		String cadenaQuery = "select distinct count(*) from PlanMedioEJB " + objectName + ", GrupoObjetivoEJB go where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + " and ";
		String orden = "";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
   }
   // * */
   
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public Collection findPlanMedioByFechas(String fechaInicio, String fechaFinal, Long idEmpresa){
	 	  String queryString = "select distinct e from PlanMedioEJB e, GrupoObjetivoEJB go where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + " and e.fechaInicio <= :fechaFinal and e.fechaFin >= :fechaInicio";
	   
	   	  // Add a an order by on all primary keys to assure reproducable results.
	      String orderByPart = "";
	      orderByPart += " order by e.id";
	      queryString += orderByPart;
	      Query query = manager.createQuery(queryString);
	      query.setParameter("fechaFinal",fechaFinal);
	      query.setParameter("fechaInicio",fechaInicio);
	      return query.getResultList();
	}
   	
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public Collection findPlanMedioByCodigoAndEstadosAprobadoPedido(String codigo, Long idEmpresa){
	 	  String queryString = "select distinct e from PlanMedioEJB e, GrupoObjetivoEJB go where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + " " +
	 	  		"and e.codigo = '" + codigo + "' and (e.estado = 'A' or e.estado = 'D' or e.estado = 'F')";
	 	  String orderByPart = "";
	      orderByPart += " order by e.id";
	      queryString += orderByPart;
	      Query query = manager.createQuery(queryString);
	     return query.getResultList();
	}
	
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	  public java.util.Collection findPlanMedioByOrdenTrabajoId(java.lang.Long idOrden){
		String objectName = "m";
		String queryString = "select distinct m from PlanMedioEJB " + objectName + ", OrdenTrabajoDetalleEJB otd where  m.ordenTrabajoDetalleId = otd.id and otd.ordenId = " + idOrden + " order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	  }
   	
   	//ADD 4 JULIO
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public Collection findPlanMedioByIdPlanMedioHermanoAndEstadosExceptoIdPlanMedioVersionHistorica(Long idPlanMedioHistorico, Long idPlaMedioHermano, String... estados){
   		String objectName = "e";
		
		String cadenaQuery = "select distinct e from PlanMedioEJB " + objectName.trim();// +  COMENTED 4 JULIO
		 					// " where e.planMedioHermanoId = " + idPlaMedioHermano + " and (";	
		
		String cadenaQueryWhere = "";
		
		if (idPlaMedioHermano != null){
			cadenaQueryWhere = " where e.planMedioHermanoId = " + idPlaMedioHermano + " and " +
		 					   " e.id <> "+ idPlanMedioHistorico +" and (";
		}else{				
			cadenaQueryWhere = " where e.id = " + idPlanMedioHistorico + " and (";// +
							 //  " e.id <> "+ idPlanMedioHistorico +" and (";
		}
							
		cadenaQuery = cadenaQuery + cadenaQueryWhere;
		
							for ( String estado : estados ){
								cadenaQuery += (" e.estado <> '"+estado+"' and");
							}//para quitarle el ultimo or
							cadenaQuery = cadenaQuery.substring(0,cadenaQuery.length()-3);
							cadenaQuery += " ) ";
							
		String orden = " order by e.codigo desc";
		System.out.println("DATA>>>>>>>>>>>>>>>>>>>"+cadenaQuery);
		Query query = manager.createQuery(cadenaQuery + orden);
		return query.getResultList();
   	}
   	//END 4 JULIO
   	
  //ADD 1 JULIO aki obtengo a los hermanos si es version obtengo todos los hermanos excepto el id del plan que tendra nueva version
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public Collection findPlanMedioByIdPlanMedioHermanoNuevaVersion(Long lessIdPlanMedio,Long idPlaMedioHermano){
   		   		
   		//ADD 20 JUNIO
   		//String tipoPlanMedio = planMedioTipo;
   		String cadenaEstado  = "";
   		//END 20 JUNIO
   		
	    String objectName = "e";
			
		String cadenaQuery = "select distinct e from PlanMedioEJB " + objectName.trim() + 
			 				 " where e.planMedioHermanoId = " + idPlaMedioHermano; 		 
		
				cadenaEstado   = " and e.id <> "+ lessIdPlanMedio + " and (e.estado = '" + "N" +"' or e.estado = '" + "P" +"') ";
			    		
		cadenaQuery = cadenaQuery + cadenaEstado;
		
		String orden = " order by e.codigo desc";
		//Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);
		System.out.println("DATA>>>>>>>>>>>>>>>>>>>"+cadenaQuery);
		Query query = manager.createQuery(cadenaQuery + orden);

		return query.getResultList();
	}
   	//END 1 JULIO
   	
   	
  //ADD 1 JULIO aki obtengo a los hermanos si es version obtengo todos los hermanos excepto el id del plan que tendra nueva version
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public Collection findPlanMedioByIdPlanMedioHermanoNuevoMes(Long idPlaMedioHermano){
   		   	
   		String cadenaEstado  = "";
   		String objectName = "e";
			
		String cadenaQuery = "select distinct e from PlanMedioEJB " + objectName.trim() + 
			 				 " where e.planMedioHermanoId = " + idPlaMedioHermano; 		 
						   			 
		cadenaEstado   = " and (e.estado <> '" + "E" +"' and e.estado <> '" + "H" +"') ";
			    		
		cadenaQuery = cadenaQuery + cadenaEstado;
		
		String orden = " order by e.codigo desc";
		//Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);
		System.out.println("DATA>>>>>>>>>>>>>>>>>>>"+cadenaQuery);
		Query query = manager.createQuery(cadenaQuery + orden);

		
		return query.getResultList();
	}
   	//END 1 JULIO
   	
	
   	//ADD 17 JUNIO
 	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public Collection findPlanMedioOriginalByQueryAndByIdClienteOficinaAndIdCampana(int startIndex,int endIndex,Long idClienteOficina, Long idCampana ,Long idEmpresa, String planMedioTipo){
   		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
   		
   		//ADD 20 JUNIO
   		String tipoPlanMedio = planMedioTipo;
   		String cadenaEstado  = "";
   		//END 20 JUNIO
   		
	    String objectName = "e";
		/*String cadenaQuery = "select distinct e from PlanMedioEJB " + objectName.trim() + ",OrdenTrabajoDetalleEJB otd,OrdenTrabajoEJB ot," +
		 					 "CampanaEJB ca, ClienteOficinaEJB co,GrupoObjetivoEJB go " +
		 					 "where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + 
		 					 " and e.ordenTrabajoDetalleId = otd.id and otd.ordenId = ot.id " +
		 					 " and ot.campanaId = ca.id and ca.id = " + idCampana +
		 					 " and ot.clienteoficinaId = co.id and co.id = " + idClienteOficina +
		 					 " and e.estado = '" + estado.trim()+ "' ";//+ estado.trim()+ "";*/
		
		String cadenaQuery = "select distinct e from PlanMedioEJB " + objectName.trim() + ",OrdenTrabajoDetalleEJB otd,OrdenTrabajoEJB ot," +
			 				 " CampanaEJB ca, ClienteOficinaEJB co,GrupoObjetivoEJB go " +
			 				 " where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + 
			 				 " and e.ordenTrabajoDetalleId = otd.id and otd.ordenId = ot.id " +
			 				 " and ot.campanaId = ca.id and ca.id = " + idCampana +
			 				 " and ot.clienteoficinaId = co.id and co.id = " + idClienteOficina;
			 
		if (tipoPlanMedio.compareTo("V") == 0){//Cuando el Plan de Medio Tipo es NUEVA VERSION
		//cadenaEstado   = " and (e.estado = '" + "N" +"') or (e.estado = '" + "P" +"') ";
			cadenaEstado   = " and (e.estado = '" + "N" +"' or e.estado = '" + "P" +"') ";
		}else if(tipoPlanMedio.compareTo("M") == 0){//Cuando el Plan de Medio Tipo es NUEVO MES
			//COMENTED 23 JUNIO
			// cadenaEstado  = " and (e.estado <> '" + "E" +"' and e.estado <> '" + "H" +"') ";
			cadenaEstado   = " and (e.estado <> '" + "E" +"' and e.estado <> '" + "H" +"') "+
				 			 " and e.planMedioHermanoId = "+ null +" "	;
			
		}						 
			    		
		cadenaQuery = cadenaQuery + cadenaEstado;
		
		String orden = " order by e.codigo desc";
		//Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);
		System.out.println("DATA>>>>>>>>>>>>>>>>>>>"+cadenaQuery);
		Query query = manager.createQuery(cadenaQuery + orden);

		//COMMENTED 17 JUNIO
		/*Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}*/
		
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
   	//END 17 JUNIO
   	
   	//ADD 17 JUNIO
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public int findPlanMedioOriginalByQueryAndByIdClienteAndIdCampanaSize(Long idClienteOficina, Long idCampana, Long idEmpresa, String planMedioTipo){//, String vom){
   		
   		//ADD 20 JUNIO
   		String tipoPlanMedio = planMedioTipo;
   		String cadenaEstado  = "";
   		//END 20 JUNIO
   		String objectName = "e";
		/*String cadenaQuery = "select distinct count(*) from PlanMedioEJB " + objectName.trim() + ",OrdenTrabajoDetalleEJB otd,OrdenTrabajoEJB ot," +
							 "CampanaEJB ca, ClienteOficinaEJB co,GrupoObjetivoEJB go " +
							 "where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + 
							 " and e.ordenTrabajoDetalleId = otd.id and otd.ordenId = ot.id " +
							 " and ot.campanaId = ca.id and ca.id = " + idCampana +
							 " and ot.clienteoficinaId = co.id and co.id = " + idClienteOficina +
							 " and e.estado = '" + estado.trim()+ "' ";*/
   		
   		String cadenaQuery = "select distinct count(*) from PlanMedioEJB " + objectName.trim() + ",OrdenTrabajoDetalleEJB otd,OrdenTrabajoEJB ot," +
   							 " CampanaEJB ca, ClienteOficinaEJB co,GrupoObjetivoEJB go " +
   							 " where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + 
   							 " and e.ordenTrabajoDetalleId = otd.id and otd.ordenId = ot.id " +
   							 " and ot.campanaId = ca.id and ca.id = " + idCampana +
   							 " and ot.clienteoficinaId = co.id and co.id = " + idClienteOficina;
   							 
   		if (tipoPlanMedio.compareTo("V") == 0){//Cuando el Plan de Medio Tipo es NUEVA VERSION
   			cadenaEstado   = " and (e.estado = '" + "N" +"' or e.estado = '" + "P" +"') ";
   		}else if(tipoPlanMedio.compareTo("M") == 0){//Cuando el Plan de Medio Tipo es NUEVO MES
   			//COMENTED 23 JUNIO
   			//cadenaEstado   = " and (e.estado <> '" + "E" +"' and e.estado <> '" + "H" +"') ";
   			cadenaEstado   = " and (e.estado <> '" + "E" +"' and e.estado <> '" + "H" +"') "+
   							 " and e.planMedioHermanoId = "+ null +" "	;
   			
   		}						 
   							    		
   		cadenaQuery = cadenaQuery + cadenaEstado;
	
		String orden = " order by e.codigo desc";
		System.out.println("SIZE >>>>>>>>>>>>>>" + cadenaQuery);
		Query query = manager.createQuery(cadenaQuery + orden);

		//COMENTED 17 JUNIO
		/*Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}*/
			
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
   	} 
   	//END 17 JUNIO 	
   	
   	//MODIFIED 24 JUNIO se agrego la variable estado que lo coge como una lista
   	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public int findPlanMedioByQueryAndByIdClienteSize(Map aMap,Long idClienteOficina,Long idEmpresa,String... estados){
   		
   		String objectName = "e";
		String cadenaQuery = "select distinct count(*) from PlanMedioEJB " + objectName + 
							 ",OrdenTrabajoDetalleEJB otd,OrdenTrabajoEJB ot,ClienteOficinaEJB co," +
							 "GrupoObjetivoEJB go where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + 
							 " and e.ordenTrabajoDetalleId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = co.id " +
							 " and co.id = " + idClienteOficina + "";
		
		if(!estados[0].equals("") && estados.length > 0){
			cadenaQuery += " and (";
			for ( String estado : estados ){
				cadenaQuery += (" e.estado = '"+estado+"' or");
			}
			//para quitarle el ultimo or
			cadenaQuery = cadenaQuery.substring(0,cadenaQuery.length()-3);
			cadenaQuery += " ) and ";
		}else{
			cadenaQuery += " and ";
		}
		
		//si no tiene esto se cae
		if(aMap.get("codigo") == null){
			aMap.put("codigo", "%");
		}
							
		String orden = "order by e.codigo desc";	
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);		

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
			
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
   	
 	//MODIFIED 24 JUNIO
 	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public Collection findPlanMedioByQueryAndByIdClienteOficina(int startIndex,int endIndex,Map aMap,Long idClienteOficina ,Long idEmpresa,String... estados){
   		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
   		
	    String objectName = "e";
		String cadenaQuery = "select distinct e from PlanMedioEJB " + objectName + 
							 ",OrdenTrabajoDetalleEJB otd,OrdenTrabajoEJB ot," +
							 "ClienteOficinaEJB co,GrupoObjetivoEJB go " +
							 "where e.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + 
							 " and e.ordenTrabajoDetalleId = otd.id and otd.ordenId = ot.id and " +
							 "ot.clienteoficinaId = co.id and co.id = " + idClienteOficina + "";		

		if(!estados[0].equals("") && estados.length > 0){
			cadenaQuery += " and (";
			for ( String estado : estados ){
				cadenaQuery += (" e.estado = '"+estado+"' or");
			}//para quitarle el ultimo or
			cadenaQuery = cadenaQuery.substring(0,cadenaQuery.length()-3);
			cadenaQuery += " ) and ";
		}else{
			cadenaQuery += " and ";
		}
		
		//si no tiene esto se cae
		if(aMap.get("codigo") == null){
			aMap.put("codigo", "%");
		}
		
		String orden = "order by e.codigo desc, e.id desc";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
 	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public int findPlanMedioByQueryByProveedorIdByEmpresaIdAndByEstadosSize(Map aMap, Long idProveedor, Long idEmpresa, String... estados){
   		
		String objectName = "pm";
   		String cadenaQuery = "select distinct count(*) from PlanMedioEJB pm, PlanMedioMesEJB pmm, PlanMedioDetalleEJB pmd, GrupoObjetivoEJB go "
   								+ "where pm.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + " and pm.id = pmm.planMedioId and pmm.id = pmd.planMedioMesId "
   								+ "and pmd.proveedorId = " + idProveedor + "";
		
		if(!estados[0].equals("") && estados.length > 0){
			cadenaQuery += " and (";
			for ( String estado : estados ){
				cadenaQuery += (" pm.estado = '"+estado+"' or");
			}
			//para quitarle el ultimo or
			cadenaQuery = cadenaQuery.substring(0,cadenaQuery.length()-3);
			cadenaQuery += " ) and ";
		}else{
			cadenaQuery += " and ";
		}
		
		//si no tiene esto se cae
		if(aMap.get("codigo") == null){
			aMap.put("codigo", "%");
		}
							
		String orden = "order by pm.codigo desc";	
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);		

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
			
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public Collection findPlanMedioByQueryByProveedorIdByEmpresaIdAndByEstados(int startIndex,int endIndex, Map aMap, Long idProveedor, Long idEmpresa, String... estados){
   		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
   		
	    String objectName = "pm";
	    String cadenaQuery = "select distinct pm from PlanMedioEJB pm, PlanMedioMesEJB pmm, PlanMedioDetalleEJB pmd, GrupoObjetivoEJB go "
					+ "where pm.grupoObjetivoId = go.id and go.empresaId = " + idEmpresa + " and pm.id = pmm.planMedioId and pmm.id = pmd.planMedioMesId "
					+ "and pmd.proveedorId = " + idProveedor + "";

		if(!estados[0].equals("") && estados.length > 0){
			cadenaQuery += " and (";
			for ( String estado : estados ){
				cadenaQuery += (" pm.estado = '"+estado+"' or");
			}//para quitarle el ultimo or
			cadenaQuery = cadenaQuery.substring(0,cadenaQuery.length()-3);
			cadenaQuery += " ) and ";
		}else{
			cadenaQuery += " and ";
		}
		
		//si no tiene esto se cae
		if(aMap.get("codigo") == null){
			aMap.put("codigo", "%");
		}
		
		String orden = "order by pm.codigo desc, pm.id desc";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
   	
   	//ADD 24 JUNIO
 	@TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public Collection findPlanMedioByIdPlanMedioHermano(Long idPlanMedioHermano)throws GenericBusinessException {
 		Query query = null;
 		
 		try{
 			String cadenaQuery = "select distinct pm from PlanMedioEJB pm " + 
			 " where pm.planMedioHermanoId = " + idPlanMedioHermano + 
			 " and (pm.estado = '" + "N" +"' or pm.estado = '" + "P" +"') ";
						    				
 			String orden = " order by pm.codigo desc";
 			String queryString = cadenaQuery + orden;
 			query = manager.createQuery(queryString);
 			List lista = query.getResultList();

 		} catch(Exception e){
			e.printStackTrace();
		} 		
 		return query.getResultList();
	}
   	//END 24 JUNIO
   	
   	
   	//ADD 24 JUNIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarVersionPlanMedioHermano(Long planMedioOrigenId,Long idPlanMedioNuevaVersion) throws GenericBusinessException {
		try{			
			Collection<PlanMedioIf>  listaResultados = findPlanMedioByIdPlanMedioHermano(planMedioOrigenId);
				
			for (PlanMedioIf plan : listaResultados){
				plan.setPlanMedioHermanoId(idPlanMedioNuevaVersion);
				manager.merge(plan);
			}
			
		} catch(Exception e){
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar el Plan Medio Hermano");
		}
	}
	//END 24 JUNIO   	
   	
	//ADD 23 JUNIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarPlanMedioEstado(Long planMedioId,String estado) throws GenericBusinessException {
		try{
			
			PlanMedioEJB data = manager.find(PlanMedioEJB.class, planMedioId);
			data.setEstado(estado);
			manager.merge(data);			
			
		} catch(Exception e){
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar el estado de la OrdenMedio");
		}
	}
	//END ADD 23 JUNIO	
	  	
   	//ADD 22 JUNIO
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public PlanMedioIf procesarPlanMedioXTipo(PlanMedioIf model, Vector<PlanMedioMesIf> planMedioMesVector, Collection<PlanMedioDetalleIf> detallesPlantilla, Map<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComercialesPlantilla,PlanMedioIf planMedioOriginal)
    	throws GenericBusinessException{
    		   try{
    			   //AKI GIOMAYRA 22 JUNIO COMO EN LA ORDEN MEDIO
    			   //ADD 23 JUNIO
    			   if (model.getPlanMedioTipo().compareTo("V") == 0){ //NUEVA VERSION
    				   this.actualizarPlanMedioEstado(planMedioOriginal.getId(),"H"); //HISTORICO
    				   model.setPlanMedioOrigenId(planMedioOriginal.getId());
    				   //BUSCAR SI TIENE HERMANOS 23 JUNIO
    			   }else  if(model.getPlanMedioTipo().compareTo("M") == 0){ //NUEVO MES
    				   model.setPlanMedioHermanoId(planMedioOriginal.getId());
    			   }
    			   //END 23 JUNIO    			   
    			   
    			   if (model.getPlanMedioTipo().compareTo("V") != 0){//NUEVA VERSION //ADD 14 JULIO
	    			   String codigo = getMaximoCodigo(model.getCodigo());
	    			   int codigoPlan = 1;
	    			   if (!codigo.equals("[null]")) {
	    				   codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
	    				   codigoPlan = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
	    			   }
	    			   model.setCodigo(model.getCodigo() + formatoSerial.format(codigoPlan));
    			   }else{//ADD 14 JULIO
    				   model.setCodigo(model.getCodigo());
    			   }//END 14 JULIO
    			       			     
    			   //MODIFIED 14 JULIO
    			  /* String codigo = getMaximoCodigo(model.getCodigo());
    			   int codigoPlan = 1;
    			   if (!codigo.equals("[null]")) {
    				   codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
    				   codigoPlan = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
    			   }
    			   model.setCodigo(model.getCodigo() + formatoSerial.format(codigoPlan));*/
    			   //MODIFIED 14 JULIO
    			      			    			   
    			   //END ADD 23 JUNIO
    				
    			   PlanMedioEJB planMedio = registrarPlanMedio(model);
    			   manager.persist(planMedio);
    			   
    			   //ADD 24 JUNIO
    			   if (model.getPlanMedioTipo().compareTo("V") == 0){
    				   actualizarVersionPlanMedioHermano(planMedioOriginal.getId(),planMedio.getId());
    			   }
    			   //END 24 JUNIO
    			   
    			   for (PlanMedioMesIf modelPlanMedioMes : planMedioMesVector) {
    				   modelPlanMedioMes.setPlanMedioId(planMedio.getPrimaryKey());
    				   PlanMedioMesEJB planMedioMes = registrarPlanMedioMes(modelPlanMedioMes);
    				   manager.persist(planMedioMes);
    				   
    				   Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet().iterator();
    				   while(planMedioDetalleIt.hasNext()){
    					   PlanMedioDetalleIf modelPlanMedioDetalle = (PlanMedioDetalleIf)planMedioDetalleIt.next();
    					   modelPlanMedioDetalle.setPlanMedioMesId(planMedioMes.getPrimaryKey());
    					   PlanMedioDetalleEJB planMedioDetalle = registrarPlanMedioDetalle(modelPlanMedioDetalle);
    					   manager.persist(planMedioDetalle);
    					   				   
    					   Collection<MapaComercialIf> mapaComercialesPlantilla = mapasComercialesPlantilla.get(modelPlanMedioDetalle);
    					   Iterator mapaComercialesPlantillaIt = mapaComercialesPlantilla.iterator();
    					   while(mapaComercialesPlantillaIt.hasNext()){
    						   MapaComercialIf modelMapaComercial = (MapaComercialIf) mapaComercialesPlantillaIt.next();
    						   
    						   modelMapaComercial.setPlanMedioDetalleId(planMedioDetalle.getPrimaryKey());
    						   MapaComercialEJB mapaComercial = registrarMapaComercial(modelMapaComercial);
    						   manager.persist(mapaComercial);
    					   }
    				   }
    				}		   
    			   
    			   return planMedio;
    			   
    		   } catch(Exception e) {
    			   e.printStackTrace();
    			   ctx.setRollbackOnly();
    			   throw new GenericBusinessException("Error al guardar informacion en Plan de Medio");
    		   }
    }
   	//END 22 JUNIO
 
   	//mapasComercialesPlantilla->Plan de Medio Detalle con sus respectivos Mapas Comerciales
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public PlanMedioIf procesarPlanMedio(PlanMedioIf model, Vector<PlanMedioMesIf> planMedioMesVector, Collection<PlanMedioDetalleIf> detallesPlantilla, Map<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComercialesPlantilla) 
    throws GenericBusinessException{
	   try{
		   String codigo = getMaximoCodigo(model.getCodigo());
		   int codigoPlan = 1;
		   if (!codigo.equals("[null]")) {
			   codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
			   codigoPlan = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
		   }
		   model.setCodigo(model.getCodigo() + formatoSerial.format(codigoPlan));
			
		   PlanMedioEJB planMedio = registrarPlanMedio(model);
		   manager.persist(planMedio);
		   
		   for (PlanMedioMesIf modelPlanMedioMes : planMedioMesVector) {
			   modelPlanMedioMes.setPlanMedioId(planMedio.getPrimaryKey());
			   PlanMedioMesEJB planMedioMes = registrarPlanMedioMes(modelPlanMedioMes);
			   manager.persist(planMedioMes);
			   
			   Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet().iterator();
			   while(planMedioDetalleIt.hasNext()){
				   PlanMedioDetalleIf modelPlanMedioDetalle = (PlanMedioDetalleIf)planMedioDetalleIt.next();
				   modelPlanMedioDetalle.setPlanMedioMesId(planMedioMes.getPrimaryKey());
				   PlanMedioDetalleEJB planMedioDetalle = registrarPlanMedioDetalle(modelPlanMedioDetalle);
				   manager.persist(planMedioDetalle);
				   				   
				   Collection<MapaComercialIf> mapaComercialesPlantilla = mapasComercialesPlantilla.get(modelPlanMedioDetalle);
				   Iterator mapaComercialesPlantillaIt = mapaComercialesPlantilla.iterator();
				   while(mapaComercialesPlantillaIt.hasNext()){
					   MapaComercialIf modelMapaComercial = (MapaComercialIf) mapaComercialesPlantillaIt.next();
					   
					   modelMapaComercial.setPlanMedioDetalleId(planMedioDetalle.getPrimaryKey());
					   MapaComercialEJB mapaComercial = registrarMapaComercial(modelMapaComercial);
					   manager.persist(mapaComercial);
				   }
			   }
			}		   
		   
		   return planMedio;
		   
	   } catch(Exception e) {
		   e.printStackTrace();
		   ctx.setRollbackOnly();
		   throw new GenericBusinessException("Error al guardar informacion en Plan de Medio");
	   }
   	}
    
    private String getMaximoCodigo(String codigoParcial) {
		/*String queryString = "select max(codigo) from PlanMedioEJB pm where pm.codigo like '" + codigoParcial + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();*/
		
		String queryString = "select max(codigo) from PresupuestoEJB p where p.codigo like '" + codigoParcial + "%'";
		Query query = manager.createQuery(queryString);
		String codigoPresupuesto = query.getResultList().toString();
		
		String queryString2 = "select max(codigo) from PlanMedioEJB pm where pm.codigo like '" + codigoParcial + "%'";
		Query query2 = manager.createQuery(queryString2);
		String codigoPlanMedio = query2.getResultList().toString();
		
		String codigo = codigoPresupuesto;
		if(codigoPlanMedio.compareTo(codigoPresupuesto) >= 1){
			codigo = codigoPlanMedio;
		}
		
		return codigo;
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public void actualizarPlanMedio(PlanMedioIf model, Vector<PlanMedioMesIf> planMedioMesVector, Vector<PlanMedioMesIf> planMedioMesRemovidoVector, Collection<PlanMedioDetalleIf> detallesPlantilla, Map<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComercialesPlantilla, boolean nuevoPlan)
    throws GenericBusinessException{
    	try{
	    	PlanMedioEJB planMedio = registrarPlanMedio(model);//retorna un planMedioEJB con los datos de model
			manager.merge(planMedio);
			
			for (PlanMedioMesIf modelPlanMedioMes : planMedioMesVector) {
				modelPlanMedioMes.setPlanMedioId(planMedio.getPrimaryKey());
				PlanMedioMesEJB planMedioMes = registrarPlanMedioMes(modelPlanMedioMes);//retorna un planMedioMesEJB
				
				if(planMedioMes.getId() != null){
					manager.merge(planMedioMes);
					//Si al actualizar se cargo un plan nuevo debo eliminar los registros del plan anterior.
					if(nuevoPlan){
						Collection<PlanMedioDetalleIf> detallesPlantillasEliminadas = planMedioDetalleLocal.findPlanMedioDetalleByPlanMedioMesId(planMedioMes.getId());
						Iterator detallesPlantillasEliminadasIt = detallesPlantillasEliminadas.iterator();
						while(detallesPlantillasEliminadasIt.hasNext()){
							PlanMedioDetalleIf modelPlanMedioDetalleEliminado = (PlanMedioDetalleIf)detallesPlantillasEliminadasIt.next();
							Collection<MapaComercialIf> mapaComercialesEliminados = mapaComercialLocal.findMapaComercialByPlanMedioDetalleId(modelPlanMedioDetalleEliminado.getId());
							Iterator mapaComercialesEliminadosIt = mapaComercialesEliminados.iterator();
							while(mapaComercialesEliminadosIt.hasNext()){
								MapaComercialIf modelMapaComercialEliminado = (MapaComercialIf) mapaComercialesEliminadosIt.next();
								MapaComercialEJB dataMapa = manager.find(MapaComercialEJB.class, modelMapaComercialEliminado.getId());
								manager.remove(dataMapa);
							}
							PlanMedioDetalleEJB dataDetalle = manager.find(PlanMedioDetalleEJB.class, modelPlanMedioDetalleEliminado.getId());
							manager.remove(dataDetalle);
						}
					}
				}
				else
					manager.persist(planMedioMes);

				//Iterator detallesPlantillaIt = detallesPlantilla.iterator();
				Iterator detallesPlantillaIt = mapasComercialesPlantilla.keySet().iterator();
				while(detallesPlantillaIt.hasNext()){
					PlanMedioDetalleIf modelPlanMedioDetalle = (PlanMedioDetalleIf)detallesPlantillaIt.next();

					modelPlanMedioDetalle.setPlanMedioMesId(planMedioMes.getPrimaryKey());
					//me devuelve a modelPlanMedioDetalle como un PlanMedioDetalleEJB
					PlanMedioDetalleEJB planMedioDetalle = registrarPlanMedioDetalle(modelPlanMedioDetalle);
					if(planMedioDetalle.getId() != null)
						manager.merge(planMedioDetalle);
					else
						manager.persist(planMedioDetalle);

					Collection<MapaComercialIf> mapaComercialesPlantilla = mapasComercialesPlantilla.get(modelPlanMedioDetalle);
					//aqui se cae
					Iterator mapaComercialesPlantillaIt = mapaComercialesPlantilla.iterator();
					while(mapaComercialesPlantillaIt.hasNext()){
						MapaComercialIf modelMapaComercial = (MapaComercialIf) mapaComercialesPlantillaIt.next();

						modelMapaComercial.setPlanMedioDetalleId(planMedioDetalle.getPrimaryKey());
						MapaComercialEJB mapaComercial = registrarMapaComercial(modelMapaComercial);
						if(mapaComercial.getId() != null)
							manager.merge(mapaComercial);
						else
							manager.persist(mapaComercial);

					}
				}
			}
			
			/*for (PlanMedioMesIf modelPlanMedioMes : planMedioMesVector) {
				modelPlanMedioMes.setPlanMedioId(planMedio.getPrimaryKey());
				PlanMedioMesEJB planMedioMes = registrarPlanMedioMes(modelPlanMedioMes);
				manager.merge(planMedioMes);
			}*/
			
			for (PlanMedioMesIf modelPlanMedioMesRemovido : planMedioMesRemovidoVector) {
				if(modelPlanMedioMesRemovido.getId()!=null){
					PlanMedioMesEJB data = manager.find(PlanMedioMesEJB.class, modelPlanMedioMesRemovido.getId());
					manager.remove(data);
				}
			}
    	} catch(Exception e) {
    		e.printStackTrace();
    		ctx.setRollbackOnly();
    		throw new GenericBusinessException("Error al actualizar informacion en Plan de Medio");
 	   }
   	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
   	public void eliminarPlanMedio(PlanMedioIf model) throws GenericBusinessException{
    	try{
    		Collection<OrdenMedioIf> ordenesMedio = findOrdenMedioByPlanMedioId(model.getId());
    		Iterator<OrdenMedioIf> ordenesMedioIt = ordenesMedio.iterator();
    		while(ordenesMedioIt.hasNext()){
    			OrdenMedioIf ordenMedio = ordenesMedioIt.next();
    			//seteo el estado anulado a la orden ya que el plan quedo con estado historico (H).
    			ordenMedio.setEstado("A");
    			manager.merge(ordenMedio);
    		}
    		
	    	PlanMedioEJB planMedio = registrarPlanMedio(model);
			manager.merge(planMedio);
			
    	} catch(Exception e) {
    		e.printStackTrace();
    		ctx.setRollbackOnly();
    		throw new GenericBusinessException("Error al eliminar informacion en Plan de Medio");
 	   }
   	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
  	public PlanMedioEJB registrarPlanMedio(PlanMedioIf model) throws GenericBusinessException{
   		PlanMedioEJB planMedio = new PlanMedioEJB();
		
   		planMedio.setId(model.getId());
   		planMedio.setCiudad1(model.getCiudad1());
   		planMedio.setCiudad2(model.getCiudad2());
   		planMedio.setCiudad3(model.getCiudad3());
   		planMedio.setCodigo(model.getCodigo());
   		planMedio.setConcepto(model.getConcepto());
   		planMedio.setEstado(model.getEstado());
   		planMedio.setFechaCreacion(model.getFechaCreacion());
   		planMedio.setFechaFin(model.getFechaFin());
   		planMedio.setFechaInicio(model.getFechaInicio());
   		planMedio.setGrupoObjetivoId(model.getGrupoObjetivoId());
   		planMedio.setModificacion(model.getModificacion());
   		planMedio.setOrdenTrabajoDetalleId(model.getOrdenTrabajoDetalleId());
   		planMedio.setPlanMedioOrigenId(model.getPlanMedioOrigenId());
   		planMedio.setTipoProveedorId(model.getTipoProveedorId());
   		planMedio.setUsuarioId(model.getUsuarioId());
   		planMedio.setValorDescuento(model.getValorDescuento() != null ? utilitariosLocal.redondeoValor(model.getValorDescuento()) : null);
   		planMedio.setValorSubtotal(model.getValorSubtotal() != null ? utilitariosLocal.redondeoValor(model.getValorSubtotal()) : null);
   		planMedio.setValorTotal(model.getValorTotal() != null ? utilitariosLocal.redondeoValor(model.getValorTotal()) : null);
   		planMedio.setCobertura(model.getCobertura());
   		planMedio.setConsideraciones(model.getConsideraciones());
   		planMedio.setFechaActualizacion(model.getFechaActualizacion());
   		planMedio.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
   		planMedio.setValorIva(model.getValorIva());
   		planMedio.setPorcentajeDescuento(model.getPorcentajeDescuento());
   		planMedio.setValorDescuentoVenta(model.getValorDescuentoVenta());
   		planMedio.setValorComisionAgencia(model.getValorComisionAgencia());
   		planMedio.setOrdenMedioTipo(model.getOrdenMedioTipo());
   		planMedio.setPlanMedioOrigenId(model.getPlanMedioOrigenId());
   		planMedio.setPlanMedioHermanoId(model.getPlanMedioHermanoId());
   		planMedio.setPlanMedioTipo(model.getPlanMedioTipo());
   		planMedio.setAutorizacionSap(model.getAutorizacionSap());
   		planMedio.setFechaAprobacion(model.getFechaAprobacion());
   		planMedio.setRevision(model.getRevision());
   		planMedio.setPrepago(model.getPrepago());
   	
		return planMedio;
	}
  	
  	private PlanMedioMesEJB registrarPlanMedioMes(PlanMedioMesIf model){
  		PlanMedioMesEJB planMedioMes = new PlanMedioMesEJB();
		
  		planMedioMes.setId(model.getId());
  		planMedioMes.setFechaFin(model.getFechaFin());
  		planMedioMes.setFechaInicio(model.getFechaInicio());
  		planMedioMes.setPlanMedioId(model.getPlanMedioId());
  		planMedioMes.setTipo(model.getTipo());
  		planMedioMes.setValorDescuento(model.getValorDescuento() != null ? utilitariosLocal.redondeoValor(model.getValorDescuento()) : null);
  		planMedioMes.setValorSubtotal(model.getValorSubtotal() != null ? utilitariosLocal.redondeoValor(model.getValorSubtotal()) : null);
  		planMedioMes.setValorDescuentoVenta(model.getValorDescuentoVenta() != null ? utilitariosLocal.redondeoValor(model.getValorDescuentoVenta()) : null);
  		planMedioMes.setValorComisionAgencia(model.getValorComisionAgencia() != null ? utilitariosLocal.redondeoValor(model.getValorComisionAgencia()) : null);
   		
		return planMedioMes;
	}
  	
  	private PlanMedioDetalleEJB	registrarPlanMedioDetalle(PlanMedioDetalleIf modelPlanMedioDetalle){
  		PlanMedioDetalleEJB planMedioDetalle = new PlanMedioDetalleEJB();
  		
  		planMedioDetalle.setId(modelPlanMedioDetalle.getId());
  		planMedioDetalle.setAudiencia(modelPlanMedioDetalle.getAudiencia());
  		planMedioDetalle.setColor(modelPlanMedioDetalle.getColor());
  		planMedioDetalle.setComercial(modelPlanMedioDetalle.getComercial());
  		planMedioDetalle.setComercialId(modelPlanMedioDetalle.getComercialId());
  		planMedioDetalle.setProductoClienteId(modelPlanMedioDetalle.getProductoClienteId());
  		planMedioDetalle.setCampanaProductoVersionId(modelPlanMedioDetalle.getCampanaProductoVersionId());
  		planMedioDetalle.setPauta(modelPlanMedioDetalle.getPauta());
  		planMedioDetalle.setAuspicioDescripcion(modelPlanMedioDetalle.getAuspicioDescripcion());
  		planMedioDetalle.setGeneroProgramaId(modelPlanMedioDetalle.getGeneroProgramaId());
  		planMedioDetalle.setHoraInicio(modelPlanMedioDetalle.getHoraInicio());
  		planMedioDetalle.setPlanMedioMesId(modelPlanMedioDetalle.getPlanMedioMesId());
  		planMedioDetalle.setPrograma(modelPlanMedioDetalle.getPrograma());
  		planMedioDetalle.setProveedorId(modelPlanMedioDetalle.getProveedorId());
  		planMedioDetalle.setRaiting1(modelPlanMedioDetalle.getRaiting1());
  		planMedioDetalle.setRaiting2(modelPlanMedioDetalle.getRaiting2());
  		planMedioDetalle.setRaitingPonderado(modelPlanMedioDetalle.getRaitingPonderado());
  		planMedioDetalle.setSeccion(modelPlanMedioDetalle.getSeccion());
  		planMedioDetalle.setTamanio(modelPlanMedioDetalle.getTamanio());
  		planMedioDetalle.setTotalCunias(modelPlanMedioDetalle.getTotalCunias());
  		planMedioDetalle.setUbicacion(modelPlanMedioDetalle.getUbicacion());
  		planMedioDetalle.setValorTarifa(modelPlanMedioDetalle.getValorTarifa());
  		planMedioDetalle.setValorTotal(modelPlanMedioDetalle.getValorTotal());
  		planMedioDetalle.setValorDescuento(modelPlanMedioDetalle.getValorDescuento());
  		planMedioDetalle.setProductoProveedorId(modelPlanMedioDetalle.getProductoProveedorId());
  		planMedioDetalle.setValorIva(modelPlanMedioDetalle.getValorIva());
  		planMedioDetalle.setValorSubtotal(modelPlanMedioDetalle.getValorSubtotal());
  		planMedioDetalle.setPorcentajeDescuento(modelPlanMedioDetalle.getPorcentajeDescuento());
  		planMedioDetalle.setPorcentajeDescuentoVenta(modelPlanMedioDetalle.getPorcentajeDescuentoVenta());
  		planMedioDetalle.setPorcentajeComisionAgencia(modelPlanMedioDetalle.getPorcentajeComisionAgencia());
  		planMedioDetalle.setValorDescuentoVenta(modelPlanMedioDetalle.getValorDescuentoVenta());
  		planMedioDetalle.setValorComisionAgencia(modelPlanMedioDetalle.getValorComisionAgencia());
  		planMedioDetalle.setPorcentajeBonificacionVenta(modelPlanMedioDetalle.getPorcentajeBonificacionVenta());
  		planMedioDetalle.setPorcentajeBonificacionCompra(modelPlanMedioDetalle.getPorcentajeBonificacionCompra());
  		planMedioDetalle.setNumeroOrdenAgrupacion(modelPlanMedioDetalle.getNumeroOrdenAgrupacion());
  		planMedioDetalle.setVersion(modelPlanMedioDetalle.getVersion());
  		if(modelPlanMedioDetalle.getNegociacionComision() != null){
  			planMedioDetalle.setNegociacionComision(modelPlanMedioDetalle.getNegociacionComision());
  		}else{
  			planMedioDetalle.setNegociacionComision("N");
  		}  		
  		  		
		return planMedioDetalle;
  	}  	
  	
  	private MapaComercialEJB registrarMapaComercial(MapaComercialIf modelMapaComercial){
  		MapaComercialEJB mapaComercial = new MapaComercialEJB();
  		
  		mapaComercial.setId(modelMapaComercial.getId());
  		mapaComercial.setFecha(modelMapaComercial.getFecha());
  		mapaComercial.setFrecuencia(modelMapaComercial.getFrecuencia());
  		mapaComercial.setPlanMedioDetalleId(modelMapaComercial.getPlanMedioDetalleId());  		
  		  		
		return mapaComercial;
  	}
  	
  	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByPlanMedioId(java.lang.Long planMedioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioId", planMedioId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }
  	
  	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPlanMedioByCodigoAndByEstados(String codigo, String... estados) {
		String queryString = "SELECT distinct pm FROM PlanMedioEJB pm ";
		queryString += "where pm.codigo = '" + codigo + "'";
		queryString += " and (";
		for ( String estado : estados ){
			queryString += ("pm.estado = '" + estado + "' or ");
		}
		queryString = queryString.substring(0,queryString.length()-3);
		queryString += " )";		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
  	
  	@TransactionAttribute(TransactionAttributeType.REQUIRED)
  	public java.util.Collection findPlanMedioByPedido(Map queryMap) throws com.spirit.exception.GenericBusinessException {
  		//SELECT DISTINCT PM.CODIGO, PC.NOMBRE FROM PEDIDO P, PLAN_MEDIO PM, PLAN_MEDIO_FACTURACION PMF, PRODUCTO_CLIENTE PC, TIPO_DOCUMENTO TD WHERE P.`REFERENCIA` = PM.`CODIGO` AND PM.ID = PMF.`PLAN_MEDIO_ID` AND PMF.`PRODUCTO_CLIENTE_ID` = PC.`ID` AND P.`TIPODOCUMENTO_ID` = TD.`ID` AND TD.`EMPRESA_ID` = 1 AND P.`ID` = 16643
  		String queryString = "SELECT distinct pm, pc FROM PedidoEJB p, PlanMedioEJB pm, PlanMedioFacturacionEJB pmf, ProductoClienteEJB pc, TipoDocumentoEJB td where p.referencia = pm.codigo and pm.id = pmf.planMedioId and pmf.productoClienteId = pc.id and p.tipodocumentoId = td.id and td.empresaId = :empresaId and p.id = :pedidoId";
		Query query = manager.createQuery(queryString);
		Long empresaId = (Long) queryMap.get("enterpriseId");
		Long pedidoId = (Long) queryMap.get("orderId");
		query.setParameter("empresaId", empresaId);
		query.setParameter("pedidoId", pedidoId);
		return query.getResultList();
  	}
  	
  	// ADD 6 SEPTIEMBRE
	/*
	 * Productos Clientes del Plan de Medio con su respectivas versiones,
	 * ordenes de medio, ordenes de medio con sus respectivas ordenes medio
	 * detalle, ordenes medio detalle con sus respectivas ordenes medio detalle
	 * mapa Del PlanMedioMes se obtiene la lista de PlanesMedioDetalle para
	 * obtener la lista de Productos cliente cada producto de esa lista es
	 * comparado con el producto de cada OrdenMedioDetalle de la lista de
	 * Ordenes de Medio para formar el Mapa de Comerciales con sus respectivas
	 * ordenes de medio totales con sus OrdenMedioDetalle y
	 * OrdenMedioDetalleMapa
	 * 
	 * Se agrego codigo para que el sistema soporte Ordenes de Medio agrupadas x
	 * Canal, de esta manera se enlista a los productos de las Ordenes, con las
	 * Ordenes de Medio que tienen en sus Ordenes de Medio Detalle a un producto
	 * en particular y de esta Ordenes de Medio Detalle se agregan las Ordenes
	 * Medio Detalle Mapa
	 */
  	
  	@TransactionAttribute(TransactionAttributeType.REQUIRED)
  	public java.util.Map<ProductoClienteIf,Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>> cargarMapaProductosClienteVersionesDetalle(PlanMedioIf planMedioIf) {
		// mapa de productos con sus respectivas OrdenMedio estas con sus
		// OrdenMedioDetalle y estas con sus respectivas OrdenesMedioDetalleMapa
		Map<ProductoClienteIf,Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>> mapaProductosClienteVersionesOrdenMedioDetalleMapa = new LinkedHashMap<ProductoClienteIf, Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>>();
			
		try{
			Map<Long,ArrayList<CampanaProductoVersionIf>> mapaIdProductosClienteListVersion = new LinkedHashMap<Long, ArrayList<CampanaProductoVersionIf>>();
			ArrayList<CampanaProductoVersionIf> listVersiones;
				
			// lista de productos clientes del plan de medios
			Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> mapaProductosClienteListVersion = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
			ArrayList<CampanaProductoVersionIf> listaVersionesProductosCliente;
			
			// Se obtiene el Plan de Medio Mes del Plan de Medio a ser Pedido o
			// Facturado
			PlanMedioMesIf planMedioMesIf = (PlanMedioMesIf)planMedioMesLocal.findPlanMedioMesByPlanmedioId(planMedioIf.getId()).iterator().next();
			// se obitene las listas de Planes Medio Detalles del Plan Medio Mes
			
			Collection listaPlanMedioDetalle = planMedioDetalleLocal.findPlanMedioDetalleByPlanMedioMesId(planMedioMesIf.getId());
			Iterator listaPlanMedioDetalleIt = listaPlanMedioDetalle.iterator();
			// de cada Plan Medio Detalle se obtiene el Comercial y se lo agrega
			// a la lista de Comerciales
			
			Map campanaProductoVersionMap = new HashMap();
			while(listaPlanMedioDetalleIt.hasNext()){
				PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf)listaPlanMedioDetalleIt.next();
				CampanaProductoVersionIf campanaProductoVersion = campanaProductoVersionLocal.getCampanaProductoVersion(planMedioDetalleIf.getCampanaProductoVersionId());
				campanaProductoVersionMap.put(planMedioDetalleIf.getCampanaProductoVersionId(), campanaProductoVersion);
			}
			Map<Long,ProductoClienteIf> campanaProductoProductoClienteMap = new HashMap<Long,ProductoClienteIf>();
			Iterator campanaProductoVersionMapIt = campanaProductoVersionMap.keySet().iterator();
			while(campanaProductoVersionMapIt.hasNext()){
				Long campanaProductoVersionId = (Long)campanaProductoVersionMapIt.next();
				CampanaProductoVersionIf campanaProductoVersion = (CampanaProductoVersionIf)campanaProductoVersionMap.get(campanaProductoVersionId);
				CampanaProductoIf campanaProducto = campanaProductoLocal.getCampanaProducto(campanaProductoVersion.getCampanaProductoId());
				ProductoClienteIf productoCliente = productoClienteLocal.getProductoCliente(campanaProducto.getProductoClienteId());
				campanaProductoProductoClienteMap.put(campanaProducto.getId(), productoCliente);
			}
			Iterator campanaProductoProductoClienteMapIt = campanaProductoProductoClienteMap.keySet().iterator();
			while(campanaProductoProductoClienteMapIt.hasNext()){
				Long campanaProductoId = (Long)campanaProductoProductoClienteMapIt.next();
				ArrayList<CampanaProductoVersionIf> campanaProductoVersiones = (ArrayList<CampanaProductoVersionIf> ) campanaProductoVersionLocal.findCampanaProductoVersionByCampanaProductoId(campanaProductoId);
				ProductoClienteIf productoCliente = campanaProductoProductoClienteMap.get(campanaProductoId);
				if(mapaProductosClienteListVersion.get(productoCliente) == null){
					mapaProductosClienteListVersion.put(productoCliente, campanaProductoVersiones);
				}else{
					campanaProductoVersiones.addAll(mapaProductosClienteListVersion.get(productoCliente));
					mapaProductosClienteListVersion.put(productoCliente, campanaProductoVersiones);
				}
			}
			// Se obtiene todas las Ordenes de Medio del PlanMedio a ser Pedido
			// o Facturado
			Collection listaOrdenMedio = findOrdenMedioByPlanMedioId(planMedioIf.getId());
			for(ProductoClienteIf proc : mapaProductosClienteListVersion.keySet()){ 
				long idProductoCliente = proc.getId();
				Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>> mapVersionOrdenesMedioDetallesMapaList = new LinkedHashMap<Long, Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>();
				for (CampanaProductoVersionIf version : mapaProductosClienteListVersion.get(proc)){
				
					ArrayList<OrdenMedioIf> listaOrdenMedioTmp = new ArrayList<OrdenMedioIf>(); 
					Iterator listaOrdenMedioIt = listaOrdenMedio.iterator();
					// Se instancia el Mapa de las Ordenes de Medio del
					// ProductoCliente con sus OrdenMedioDetalle y con sus
					// OrdenMedioDetalleMapa
					Map <OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
					// Se recorre la lista de Ordenes de Medio
					while(listaOrdenMedioIt.hasNext()){
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf)listaOrdenMedioIt.next();
						// agregar la orden de medio o no
						boolean agregar = false;
						// Se instancia el mapa de las Ordenes Medio Detalle con
						// sus respectivas Ordenes Medio Detalle Mapa
						Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
						// Se obtiene una colleccion de OrdenMedioDetalle de la
						// OrdenMedio
						Collection<OrdenMedioDetalleIf> listaOrdenMedioDetalleIf = ordenMedioDetalleLocal.findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenMedioDetalleIt = listaOrdenMedioDetalleIf.iterator();
						// PARA ORDENES DE MEDIO X PRODUCTO
						if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) !=0 ){ 
							if (ordenMedioIf.getProductoClienteId().compareTo(proc.getId())==0){
								while(ordenMedioDetalleIt.hasNext()){
									OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
									Collection<OrdenMedioDetalleMapaIf> listaOrdenMedioDetalleMapaIf = ordenMedioDetalleMapaLocal.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
									Iterator ordenMedioDetalleMapaIt = listaOrdenMedioDetalleMapaIf.iterator();
									
									// Se instancia el arraylist de las Ordenes
									// Medio Detalle Mapa
									ArrayList<OrdenMedioDetalleMapaIf> ordenesMedioDetalleMapaTotales = new ArrayList<OrdenMedioDetalleMapaIf>();		
									
									if(ordenMedioDetalleIf.getCampanaProductoVersionId().compareTo(version.getId()) == 0){
										while(ordenMedioDetalleMapaIt.hasNext()){
											OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
											ordenesMedioDetalleMapaTotales.add(ordenMedioDetalleMapaIf);
										}
										// agrega la ordenMedio com con su
										// respectiva lista de Ordenes de Medio
										// Detalle Totales
										ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,ordenesMedioDetalleMapaTotales);
										agregar = true;
									}
								}
								if (agregar){
									// agrega la ordenMedio com con su
									// respectiva lista de Ordenes de Medio
									// Detalle Totales
									ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales);
								}
							}							
							
						}else{
						// PARA ORDENES DE MEDIO X CANAL O PROVEEDOR
							while(ordenMedioDetalleIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
								
								if (ordenMedioDetalleIf.getProductoClienteId().compareTo(proc.getId()) == 0 && ordenMedioDetalleIf.getCampanaProductoVersionId().compareTo(version.getId()) == 0	){
									
									Collection<OrdenMedioDetalleMapaIf> listaOrdenMedioDetalleMapaIf = ordenMedioDetalleMapaLocal.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenMedioDetalleIf.getId()); 
									Iterator ordenMedioDetalleMapaIt = listaOrdenMedioDetalleMapaIf.iterator();
									// Se instancia el arraylist de las Ordenes
									// Medio Detalle Mapa
									ArrayList<OrdenMedioDetalleMapaIf> ordenesMedioDetalleMapaTotales = new ArrayList<OrdenMedioDetalleMapaIf>();	
									while(ordenMedioDetalleMapaIt.hasNext()){
										// Se obtiene la siguiente
										// OrdenMedioDetalle
										OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
										ordenesMedioDetalleMapaTotales.add(ordenMedioDetalleMapaIf);
									}
									// agrega la ordenMedio com con su
									// respectiva lista de Ordenes de Medio
									// Detalle Totales
									ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,ordenesMedioDetalleMapaTotales);
									agregar = true;
								}
							}
							if (agregar){
								// ESTAS ORDENES DE MEDIO NO TIENEN LOS VALORES
								// SUBTOTALES VERDADEROS XQ CORRESPONDEN A TODOS
								// LOS DATOS Y LO Q SE QUIERE ES POR PRODUCTO 24
								// MAYO GIOMY!!!!
								// agrega la ordenMedio com con su respectiva
								// lista de Ordenes de Medio Detalle Totales
								ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales);
							}
							
						}
					}
					mapVersionOrdenesMedioDetallesMapaList.put(version.getId(), ordenesMedioTotales);
					
				}
				// agrega el comercial com con su respectiva lista de Ordenes de
				// Medio Totales
				mapaProductosClienteVersionesOrdenMedioDetalleMapa.put(proc,mapVersionOrdenesMedioDetallesMapaList);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// mapa que contiene a todos los comerciales con sus respectivas ordenes
		// de Medio Totales
		return mapaProductosClienteVersionesOrdenMedioDetalleMapa;
	}
  	
  	/*
	 * Llena la listas de PlanMedioFacturacion Total que deberian ser generadas
	 * por un Plan Medio con los campos de Comercial, Ordenes de Medio Orden
	 * Medio Detalle, Orden Medio DetalleMapa
	 */
  	@TransactionAttribute(TransactionAttributeType.REQUIRED)
  	public ArrayList<PlanMedioFacturacionIf> getPlanMedioFacturacionTotal(PlanMedioIf planMedioFacturado, boolean esFacturacionCompleta){
		// lista de Planes de MedioFacturacion
		ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion = new ArrayList<PlanMedioFacturacionIf>(); 
		try{
			ArrayList<ClienteOficinaIf> listaProveedores = new ArrayList<ClienteOficinaIf>();
			// Lista de todas las Ordenes de Medio del Plan de Medio a ser
			// Pedido o Facturado
			Collection listaOrdenMedioIf = findOrdenMedioByPlanMedioId(planMedioFacturado.getId());
	
			Iterator ordenMedioTmpIt = listaOrdenMedioIf.iterator();
			// Se recorre la listaOrdenMedio para llenar la lista de Proveedores
			while(ordenMedioTmpIt.hasNext()){
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioTmpIt.next();
				// se obtiene al cliente proveedor de la orden de Medio
				ClienteOficinaIf proveedor = clienteOficinaLocal.getClienteOficina(ordenMedioIf.getProveedorId());
				int tmp = 0;	
				// for que llena la lista de proveedores
				for(ClienteOficinaIf clienteOficinaIf2:listaProveedores){
					if(clienteOficinaIf2.getId().compareTo(proveedor.getId())!=0){
						tmp++;
					}
				}
				// agrega a los proveedores de las ordenes de medio que no se
				// encuentran en la lista de proveedores
				if(tmp==listaProveedores.size()){
					listaProveedores.add(proveedor);
				}
			}
			
			Map<ProductoClienteIf,Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>> mapaProductosClienteVersionOrdenMedioDetalleMapa = cargarMapaProductosClienteVersionesDetalle(planMedioFacturado);
			Iterator mapaProductosClienteVersionOrdenMedioDetalleMapaIt1 = mapaProductosClienteVersionOrdenMedioDetalleMapa.keySet().iterator();
			
			// Llena la listas de Planes de Medio de Facturacion con o sin canje
			// con los
			// Producto Cliente Version con sus respectivas Ordenes de Medio
			// hasta el DetalleMapa
			
			// START WHILE DE PRODUCTOS CLIENTE VERSIONES
			while(mapaProductosClienteVersionOrdenMedioDetalleMapaIt1.hasNext()){
				ProductoClienteIf productoClienteIf = (ProductoClienteIf)mapaProductosClienteVersionOrdenMedioDetalleMapaIt1.next(); // ADD
																																		// 8
																																		// SEPTIEMBRE
				
				Map mapaVersionOrdenesMedioTotal = mapaProductosClienteVersionOrdenMedioDetalleMapa.get(productoClienteIf);
				Iterator mapaVersionOrdenesMedioTotalIt = mapaVersionOrdenesMedioTotal.keySet().iterator();
				while(mapaVersionOrdenesMedioTotalIt.hasNext()){
					Long productoClienteVersionId = (Long) mapaVersionOrdenesMedioTotalIt.next();
					
					Map mapaOrdenesMedioTotal = (Map) mapaVersionOrdenesMedioTotal.get(productoClienteVersionId);
					Iterator ordenMedioIt = mapaOrdenesMedioTotal.keySet().iterator();
					
					// START WHILE DE ORDENES DE MEDIO
					while(ordenMedioIt.hasNext()){
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioIt.next();
						// mapa de Orden Medio Detalle con Ordenes Medio Detalle
						// Mapa
						Map mapaOrdenesMedioDetalleTotal = (Map)mapaOrdenesMedioTotal.get(ordenMedioIf);
						Iterator ordenMedioDetalleIt = mapaOrdenesMedioDetalleTotal.keySet().iterator();
						// START WHILE DE ORDENES DE MEDIO DETALLE
						while(ordenMedioDetalleIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
							// ArrayList de las Ordenes de Medio Detalle Mapa
							// del Comercial
							ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
							// START FOR ORDEN MEDIO DETALLE DATA
							for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
									
								if(ordenMedioIf.getPorcentajeCanje().compareTo(new BigDecimal(100))==0){
									BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje(); 
									PlanMedioFacturacionIf planMedioFacturacionIfC = crearPlanMedioFacturacionIf(planMedioFacturado.getFechaInicio(),planMedioFacturado.getFechaFin(),ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,true,porcentajeCanje);
									listPlanMedioFacturacion.add(planMedioFacturacionIfC);
								}else if(esFacturacionCompleta || (ordenMedioIf.getPorcentajeCanje().compareTo(new BigDecimal(0))==0 && ordenMedioIf.getComisionPura().equals("N"))){
									BigDecimal porcentaje = new BigDecimal(0); 
									PlanMedioFacturacionIf planMedioFacturacionIfNC = crearPlanMedioFacturacionIf(planMedioFacturado.getFechaInicio(),planMedioFacturado.getFechaFin(),ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,false,porcentaje);
									listPlanMedioFacturacion.add(planMedioFacturacionIfNC);
								}else{
									BigDecimal porcentajeCien = new BigDecimal(100); 
									BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje(); 
									// porcentaje que no es canjeado
									BigDecimal porcentaje = porcentajeCien.subtract(porcentajeCanje);
									PlanMedioFacturacionIf planMedioFacturacionIfNC = crearPlanMedioFacturacionIf(planMedioFacturado.getFechaInicio(),planMedioFacturado.getFechaFin(),ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,false,porcentaje);
									PlanMedioFacturacionIf planMedioFacturacionIfC = crearPlanMedioFacturacionIf(planMedioFacturado.getFechaInicio(),planMedioFacturado.getFechaFin(),ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,true,porcentajeCanje);
									listPlanMedioFacturacion.add(planMedioFacturacionIfNC);
									listPlanMedioFacturacion.add(planMedioFacturacionIfC);
								}
							}
						}
					}
				} 
			}
			return listPlanMedioFacturacion;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listPlanMedioFacturacion;
	}
	
	/*
	 * crea el Plan de Medio de Facturacion con los datos del Producto Cliente,
	 * Version del Producto, la OrdenMedio, el Comercial, la Orden MedioDetalle
	 * y OrdenMedioDetallaMapa del Plan de Medio a ser Pedido o Facturado
	 */
  	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private PlanMedioFacturacionData crearPlanMedioFacturacionIf(Date fechaInicio,Date fechaFin, OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf, OrdenMedioDetalleIf ordenMedioDetalleIf, OrdenMedioIf ordenMedioIf,ProductoClienteIf productoClienteIf,Long versionId,boolean canje, BigDecimal porcentaje){
		PlanMedioFacturacionData planMedioFacturacionData = new PlanMedioFacturacionData();
		planMedioFacturacionData.setOrdenMedioDetalleMapaId(ordenMedioDetalleMapaIf.getId());
		planMedioFacturacionData.setOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
		planMedioFacturacionData.setOrdenMedioId(ordenMedioIf.getId());
		planMedioFacturacionData.setPlanMedioId(ordenMedioIf.getPlanMedioId());
		planMedioFacturacionData.setProductoClienteId(productoClienteIf.getId());
		planMedioFacturacionData.setCampanaProductoVersionId(versionId);
		planMedioFacturacionData.setFechaInicio(new Timestamp(fechaInicio.getTime()));
		planMedioFacturacionData.setFechaFin(new Timestamp(fechaFin.getTime()));
		
		if(canje){
			planMedioFacturacionData.setCanje("S");// S = si ; N = no
		}else{
			planMedioFacturacionData.setCanje("N");// S = si ; N = no
		}
		
		planMedioFacturacionData.setProveedorId(ordenMedioIf.getProveedorId());
		planMedioFacturacionData.setPorcentajeCanje(porcentaje);
		planMedioFacturacionData.setEstado("-");// F=facturado, P=pedido,
		
		return planMedioFacturacionData;
	}
	
  	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>  getOrdenesMedioSegunPeriodoAFacturar(ArrayList<OrdenMedioIf> listaOrdenMedio,ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion,Date periodoFechaInicio, Date periodoFechaFin ) throws GenericBusinessException{
		ArrayList<OrdenMedioIf> listaOrdenMedioSegunPeriodo = new ArrayList<OrdenMedioIf>();
		Map <OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
		
		// listaOrdenMedio Lista de Ordenes de Medio x Cliente Proveedor
		for(OrdenMedioIf ordenMedioIf: listaOrdenMedio){
			// mapa de OrdenesMedioDetalle con sus respectivas listas de
			// OrdenesMedioDetalleMapa
			Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
			Collection<OrdenMedioDetalleIf>ordenMedioDetalleColl = ordenMedioDetalleLocal.findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
			Iterator ordenMedioDetalleIt = ordenMedioDetalleColl.iterator();
			
			while(ordenMedioDetalleIt.hasNext()){
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
				ArrayList<OrdenMedioDetalleMapaIf>ordenMedioDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
				
				Collection<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaColl = ordenMedioDetalleMapaLocal.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
				Iterator ordenMedioDetalleMapaIt = ordenMedioDetalleMapaColl.iterator();
				while(ordenMedioDetalleMapaIt.hasNext()){
					OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
					boolean agregar = false;
					for (PlanMedioFacturacionIf planMedioFacturacionIf: listPlanMedioFacturacion ){
						if (planMedioFacturacionIf.getOrdenMedioId().compareTo(ordenMedioIf.getId()) == 0 
							&& planMedioFacturacionIf.getOrdenMedioDetalleId().compareTo(ordenMedioDetalleIf.getId()) == 0 
							&& planMedioFacturacionIf.getOrdenMedioDetalleMapaId().compareTo(ordenMedioDetalleMapaIf.getId()) == 0){
							agregar = true;
						}
					}
					
					// DIFERENTE DEL DETALLE MAPA DE LISTA DE PLAN MEDIO
					// FACTURACION
					if (agregar){
						Date fechaOrdenMedioDetalleMapa = ordenMedioDetalleMapaIf.getFecha();
						Calendar calendar = GregorianCalendar.getInstance();
						calendar.setTime(fechaOrdenMedioDetalleMapa);
						int dia  = calendar.get(Calendar.DATE);
						int mes  = calendar.get(Calendar.MONTH);
						int anio = calendar.get(Calendar.YEAR);
						
						Calendar calendarFechaInicio =  GregorianCalendar.getInstance();
						calendarFechaInicio.setTime(periodoFechaInicio);
						Calendar calendarFechaFin =  GregorianCalendar.getInstance();
						calendarFechaFin.setTime(periodoFechaFin);
						
						Calendar calendarTemp = GregorianCalendar.getInstance();
						// Setea los datos del periodoFechaInicio pa que si
						// concuerdan
						// no haya conflictos con los datos locales y solo se
						// riga al dia, mes y anio
						calendarTemp.setTime(periodoFechaInicio);
						calendarTemp.set(Calendar.DATE, dia);
						calendarTemp.set(Calendar.MONTH, mes);
						calendarTemp.set(Calendar.YEAR, anio);
					
						if ( calendarTemp.compareTo(calendarFechaInicio) >= 0 && calendarTemp.compareTo(calendarFechaFin) <= 0){
							ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf);
						}
					}
				}
				ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,ordenMedioDetalleMapaList);//
			}				
			ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales);//
		}
		// System.out.println(ordenesMedioTotales.size());
		return ordenesMedioTotales;
	}
}
