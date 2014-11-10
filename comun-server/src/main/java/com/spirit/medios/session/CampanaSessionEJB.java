package com.spirit.medios.session;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.CampanaArchivoIf;
import com.spirit.medios.entity.CampanaBriefIf;
import com.spirit.medios.entity.CampanaDetalleIf;
import com.spirit.medios.entity.CampanaEJB;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.CampanaProductoData;
import com.spirit.medios.entity.CampanaProductoEJB;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.CampanaProductoVersionData;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._CampanaSession;
import com.spirit.server.QueryBuilder;
import com.spirit.util.FindQuery;

/**
 * The <code>CampanaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class CampanaSessionEJB extends _CampanaSession implements CampanaSessionRemote  {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	//ADD 19 SEPTIEMBRE
	@EJB 
	private CampanaProductoVersionSessionLocal campanaProductoVersionLocal; 
	//END 19 SEPTIEMBRE

	@EJB 
	private CampanaProductoSessionLocal campanaProductoLocal; 

	@EJB 
	private CampanaDetalleSessionLocal campanaDetalleLocal; 

	@EJB 
	private CampanaBriefSessionLocal campanaBriefLocal; 

	@EJB 
	private CampanaArchivoSessionLocal campanaArchivoLocal;

	private DecimalFormat formatoSerial = new DecimalFormat("00000");	

	@Resource private SessionContext ctx;

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getCampanaList(int startIndex, int endIndex, Map aMap, Long idEmpresa){
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}

		String objectName = "e";
		String cadenaQuery = "select distinct e from CampanaEJB " + objectName + ", ClienteEJB c, TipoClienteEJB tc where c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and ";
		String orden = "order by e.codigo desc";
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

	public int getCampanaListSize(Map aMap, Long idEmpresa) {
		String objectName = "e";
		String cadenaQuery = "select distinct count(*) from CampanaEJB " + objectName + ", ClienteEJB c, TipoClienteEJB tc where " + objectName + ".clienteId=c.id  and  c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and ";
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCampanaByEmpresaId (Long idEmpresa){
		String objectName = "e";
		String cadenaQuery = "select distinct e from CampanaEJB e, ClienteEJB cl, TipoClienteEJB tc where e.clienteId = cl.id and cl.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by e.codigo desc";
		Query query = manager.createQuery(cadenaQuery);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCampanaByQueryAndByCorporacionId (Map aMap, Long idCorporacion, Long idEmpresa){
		String objectName = "e";
		String cadenaQuery = "select distinct e from CampanaEJB e, ClienteEJB cl, TipoClienteEJB tc where e.clienteId = cl.id and cl.corporacionId = " + idCorporacion + " and cl.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and ";
		String orden = "order by e.codigo desc";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int findCampanaByQueryAndByCorporacionIdSize (Map aMap, Long idCorporacion, Long idEmpresa){
		String objectName = "e";
		String cadenaQuery = "select distinct count(*) from CampanaEJB e, ClienteEJB cl, TipoClienteEJB tc where e.clienteId = cl.id and cl.corporacionId = " + idCorporacion + " and cl.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and ";
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCampanaByQueryAndByResponsableOrdenTrabajoAndByFechaInicioAndFechaFin(Map aMap, Long idResponsableOrdenTrabajo, Date fechaInicio, Date fechaFin) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "";
		if(fechaInicio != null && fechaFin != null){
			queryString = "select distinct e from CampanaEJB " + objectName + ", OrdenTrabajoEJB ot where " + where + " and e.id = ot.campanaId and ot.empleadoId = " + idResponsableOrdenTrabajo + " and e.fechaini between :fechaInicio and :fechaFin";
		}else{
			queryString = "select distinct e from CampanaEJB " + objectName + ", OrdenTrabajoEJB ot where " + where + " and e.id = ot.campanaId and ot.empleadoId = " + idResponsableOrdenTrabajo;
		}
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		if(fechaInicio != null && fechaFin != null){
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFin",fechaFin);
		}		
		return query.getResultList();     
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCampanaByResponsableOrdenTrabajoAndByFechaInicioAndFechaFin(Long idResponsableOrdenTrabajo, Date fechaInicio, Date fechaFin) {
		String queryString = "";
		if(fechaInicio != null && fechaFin != null){
			queryString = "select distinct e from CampanaEJB e,OrdenTrabajoEJB ot where e.id = ot.campanaId and ot.empleadoId = " + idResponsableOrdenTrabajo + " and e.fechaini between :fechaInicio and :fechaFin";
		}else{
			queryString = "select distinct e from CampanaEJB e,OrdenTrabajoEJB ot where e.id = ot.campanaId and ot.empleadoId = " + idResponsableOrdenTrabajo;
		}
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		if(fechaInicio != null && fechaFin != null){
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFin",fechaFin);
		}		
		return query.getResultList();  
	}
	
	//ADD 19 SEPTIEMBRE
	public CampanaIf procesarCampana(CampanaIf model,List<CampanaDetalleIf> modelDetalleList,Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> modelProductoVersionesMap) 
	throws GenericBusinessException {
		try {
			String codigo = getMaximoCodigoCampana(model.getCodigo());
			int codigoCampana = 1;
			
			//ADD 19 SEPTIEMBRE
			String codigoV = "";
			int codigoVersion = 1;
			//END 19 SEPTIEMBRE
			
			if (!codigo.equals("[null]")) {
				codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
				codigoCampana = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
			}
			model.setCodigo(model.getCodigo() + formatoSerial.format(codigoCampana));
			
			
			
			
			CampanaIf campana = registrarCampana(model);
			manager.persist(campana);

			for (CampanaDetalleIf modelDetalle : modelDetalleList) {

				modelDetalle.setCampanaId(campana.getPrimaryKey());
				CampanaDetalleIf campanaDetalle = campanaDetalleLocal.registrarCampanaDetalle(modelDetalle);
				manager.merge(campanaDetalle);
			}

			//COMENTED 19 SEPTIEMBRE
			/*for (ProductoClienteIf modelProducto : modelProductoList) {

				CampanaProductoData modelCampanaProducto = new CampanaProductoData();
				modelCampanaProducto.setCampanaId(campana.getPrimaryKey());
				CampanaProductoIf campanaProducto = campanaProductoLocal.registrarCampanaProducto(modelCampanaProducto, modelProducto);
				manager.merge(campanaProducto);
			}*/
			
			for (ProductoClienteIf modelProducto : modelProductoVersionesMap.keySet()){
				CampanaProductoData modelCampanaProducto = new CampanaProductoData();
				modelCampanaProducto.setCampanaId(campana.getPrimaryKey());
				CampanaProductoIf campanaProducto = campanaProductoLocal.registrarCampanaProducto(modelCampanaProducto, modelProducto);
				//manager.merge(campanaProducto);
				manager.persist(campanaProducto);
				
				//ADD 19 SEPTIEMBRE
				if (modelProductoVersionesMap.get(modelProducto) != null && modelProductoVersionesMap.get(modelProducto).size() > 0){
					for (CampanaProductoVersionIf modelCampanaProductoVersion : modelProductoVersionesMap.get(modelProducto)){
										
						if(modelCampanaProductoVersion.getCodigo() == null){
							codigoV = campanaProductoVersionLocal.getMaximoCodigoVersion(model.getClienteId());
																
							if(codigoV.equals("")){
								codigoV = "00001";
							}else{
								codigoV = formatoSerial.format(Integer.valueOf(codigoV) + 1); 
							}
							modelCampanaProductoVersion.setCodigo(codigoV);
							
						}else{
							modelCampanaProductoVersion.setCodigo(modelCampanaProductoVersion.getCodigo());
						}	
										
						CampanaProductoVersionData modelCampanaProductoVersionData = (CampanaProductoVersionData) modelCampanaProductoVersion;
						modelCampanaProductoVersionData.setCampanaProductoId(campanaProducto.getPrimaryKey());
						CampanaProductoVersionIf campanaProductoVersion = campanaProductoVersionLocal.registrarCampanaProductoVersion(campanaProducto,modelCampanaProductoVersionData);
						manager.merge(campanaProductoVersion);
					}
				}
			}
			

			/*for (CampanaBriefIf modelBrief : modelBriefList) {

				modelBrief.setCampanaId(campana.getPrimaryKey());
				CampanaBriefIf campanaBrief = campanaBriefLocal.registrarCampanaBrief(modelBrief);
				manager.merge(campanaBrief);
			}*/

			/*for (CampanaArchivoIf modelArchivo : modelArchivoList) {

				modelArchivo.setCampanaId(campana.getPrimaryKey());
				CampanaArchivoIf campanaArchivo = campanaArchivoLocal.registrarCampanaArchivo(modelArchivo);
				manager.merge(campanaArchivo);
			}*/

			/*for (File archivo: archivosList){
				FileSaver.save(archivo);
			}*/
			return campana;
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al insertar información en Campana-CampanaDetalle");
		}
	}
	//END 19 SEPTIEMBRE

	public CampanaIf procesarCampana(CampanaIf model,List<CampanaDetalleIf> modelDetalleList,List<ProductoClienteIf> modelProductoList) 
	throws GenericBusinessException {
		try {
			String codigo = getMaximoCodigoCampana(model.getCodigo());
			int codigoCampana = 1;
			if (!codigo.equals("[null]")) {
				codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
				codigoCampana = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
			}
			model.setCodigo(model.getCodigo() + formatoSerial.format(codigoCampana));
			CampanaIf campana = registrarCampana(model);
			manager.persist(campana);

			for (CampanaDetalleIf modelDetalle : modelDetalleList) {

				modelDetalle.setCampanaId(campana.getPrimaryKey());
				CampanaDetalleIf campanaDetalle = campanaDetalleLocal.registrarCampanaDetalle(modelDetalle);
				manager.merge(campanaDetalle);
			}

			for (ProductoClienteIf modelProducto : modelProductoList) {

				CampanaProductoData modelCampanaProducto = new CampanaProductoData();
				modelCampanaProducto.setCampanaId(campana.getPrimaryKey());
				CampanaProductoIf campanaProducto = campanaProductoLocal.registrarCampanaProducto(modelCampanaProducto, modelProducto);
				manager.merge(campanaProducto);
			}

			/*for (CampanaBriefIf modelBrief : modelBriefList) {

				modelBrief.setCampanaId(campana.getPrimaryKey());
				CampanaBriefIf campanaBrief = campanaBriefLocal.registrarCampanaBrief(modelBrief);
				manager.merge(campanaBrief);
			}*/

			/*for (CampanaArchivoIf modelArchivo : modelArchivoList) {

				modelArchivo.setCampanaId(campana.getPrimaryKey());
				CampanaArchivoIf campanaArchivo = campanaArchivoLocal.registrarCampanaArchivo(modelArchivo);
				manager.merge(campanaArchivo);
			}*/

			/*for (File archivo: archivosList){
				FileSaver.save(archivo);
			}*/
			return campana;
		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al insertar información en Campana-CampanaDetalle");
		}
	}

	private String getMaximoCodigoCampana(String codigoParcialCampana) {
		String queryString = "select max(codigo) from CampanaEJB c where c.codigo like '" + codigoParcialCampana + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}
	
	//ADD 19 SEPTIEMBRE
	public String getMaximoCodigoVersion(String codigoParcialVersion) {
		String queryString = "select max(codigo) from CampanaProductoVersionEJB c where c.codigo like '" + codigoParcialVersion + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}
	//END 19 SEPTIEMBRE
	
	//ADD 28 SEPTIEMBRE
	public CampanaIf actualizarCampana(CampanaIf model, List<CampanaDetalleIf> modelDetalleList, Map<ProductoClienteIf,
									   ArrayList<CampanaProductoVersionIf>> modelProductoVersionesMap,
									   Vector<ProductoClienteIf> productoClienteIfEliminadosVector,  
									   Vector<CampanaProductoVersionIf> campanaProductoVersionIfEliminadosVector, 
									   List<CampanaDetalleIf> detallesCampanaEliminadosList) throws GenericBusinessException {
		try{
			CampanaIf campana = registrarCampana(model);			
			manager.merge(campana);
			
			//ADD 28 SEPTIEMBRE
			String codigoV = "";

			for (CampanaDetalleIf modelDetalle : modelDetalleList) {
				modelDetalle.setCampanaId(campana.getPrimaryKey());
				CampanaDetalleIf campanaDetalle = campanaDetalleLocal.registrarCampanaDetalle(modelDetalle);
				manager.merge(campanaDetalle);
			}

			for (CampanaDetalleIf modelDetallesEliminados : detallesCampanaEliminadosList) {
				CampanaDetalleIf data = campanaDetalleLocal.getCampanaDetalle(modelDetallesEliminados.getId());
				manager.remove(data);
			}

			//COMENTED 28 SEPTIEMBRE
			/*Collection<CampanaProductoIf> modelProductosEliminadosList = campanaProductoLocal.findCampanaProductoByCampanaId(campana.getPrimaryKey());

			for (CampanaProductoIf modelProducto : modelProductosEliminadosList) {
				CampanaProductoIf data = campanaProductoLocal.getCampanaProducto(modelProducto.getId());
				manager.remove(data);
			}
			
			for (ProductoClienteIf modelProducto : modelProductoList) {

				CampanaProductoData modelCampanaProducto = new CampanaProductoData();
				modelCampanaProducto.setCampanaId(campana.getPrimaryKey());
				CampanaProductoIf campanaProducto = campanaProductoLocal.registrarCampanaProducto(modelCampanaProducto, modelProducto);
				manager.persist(campanaProducto);
			}	
			*/	
			
			//ADD 28 SEPTIEMBRE
			for (CampanaProductoVersionIf modelCampanaProductoVersionEliminados : campanaProductoVersionIfEliminadosVector) {
				CampanaProductoVersionIf data = campanaProductoVersionLocal.getCampanaProductoVersion(modelCampanaProductoVersionEliminados.getId());
				manager.remove(data);
			}
			
			//x campana y por producto Cliente
			for(ProductoClienteIf productoClienteIf : productoClienteIfEliminadosVector){
				Collection<CampanaProductoIf> modelCampanaProductosEliminadosList = campanaProductoLocal.findCampanaProductosByCampanaIdAndProductoClienteId(campana.getPrimaryKey(), productoClienteIf.getId());
				for (CampanaProductoIf modelCampanaProductoEliminado : modelCampanaProductosEliminadosList) {
					CampanaProductoIf data = campanaProductoLocal.getCampanaProducto(modelCampanaProductoEliminado.getId());
					manager.remove(data);
				}
			}
			//END 28 SEPTIEMBRE

			//AYUDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
			/*for (ProductoClienteIf modelProducto : modelProductoList) {

				CampanaProductoData modelCampanaProducto = new CampanaProductoData();
				modelCampanaProducto.setCampanaId(campana.getPrimaryKey());
				CampanaProductoIf campanaProducto = campanaProductoLocal.registrarCampanaProducto(modelCampanaProducto, modelProducto);
				manager.persist(campanaProducto);
			}	
			
			if(comercial.getId() == null){
				ComercialEJB model = registrarComercial(comercial, clienteId, true);
				manager.persist(model);
			}else{
				ComercialEJB model = registrarComercial(comercial, clienteId, false);
				manager.merge(model);
			}*/
			//AYUDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
			
			
			//ADD 28 SEPTIEMBRE
			for (ProductoClienteIf modelProducto : modelProductoVersionesMap.keySet()){
				CampanaProductoIf modelCampanaProducto;
				Collection<CampanaProductoIf> modelCampanaProductoColl = campanaProductoLocal.findCampanaProductosByCampanaIdAndProductoClienteId(campana.getPrimaryKey(), modelProducto.getId());
				CampanaProductoEJB campanaProducto;
				
				if (modelCampanaProductoColl != null && !modelCampanaProductoColl.isEmpty() ){
					modelCampanaProducto = (CampanaProductoIf) modelCampanaProductoColl.iterator().next();
				}else{
					modelCampanaProducto = new CampanaProductoData(); 
				}
				
				if(modelCampanaProducto.getId() == null){
					modelCampanaProducto.setCampanaId(campana.getPrimaryKey());
					campanaProducto = campanaProductoLocal.registrarCampanaProducto(modelCampanaProducto, modelProducto);
					manager.persist(campanaProducto);
				}else{
					campanaProducto = campanaProductoLocal.registrarCampanaProducto(modelCampanaProducto, modelProducto);
					campanaProducto.setId(modelCampanaProducto.getId());
					manager.merge(campanaProducto);
				}
								
				//ADD 19 SEPTIEMBRE
				if (modelProductoVersionesMap.get(modelProducto) != null && modelProductoVersionesMap.get(modelProducto).size() > 0){
					for (CampanaProductoVersionIf modelCampanaProductoVersion : modelProductoVersionesMap.get(modelProducto)){
										
						if(modelCampanaProductoVersion.getCodigo() == null){
							codigoV = campanaProductoVersionLocal.getMaximoCodigoVersion(model.getClienteId());
																
							if(codigoV.equals("")){
								codigoV = "00001";
							}else{
								codigoV = formatoSerial.format(Integer.valueOf(codigoV) + 1); 
							}
							modelCampanaProductoVersion.setCodigo(codigoV);
							
						}else{
							modelCampanaProductoVersion.setCodigo(modelCampanaProductoVersion.getCodigo());
						}	
										
						CampanaProductoVersionIf modelCampanaProductoVersionData = (CampanaProductoVersionIf) modelCampanaProductoVersion;
						CampanaProductoVersionIf campanaProductoVersion; //ADD 29 SEPTIEMBRE
						//COMENTED 29 SEPTIEMBRE
						//modelCampanaProductoVersionData.setCampanaProductoId(campanaProducto.getPrimaryKey());
						//CampanaProductoVersionIf campanaProductoVersion = campanaProductoVersionLocal.registrarCampanaProductoVersion(campanaProducto,modelCampanaProductoVersionData);
						//manager.merge(campanaProductoVersion); //COMENTED 29 SEPTIEMBRE
						
						//ADD 29 SEPTIEMBRE
						if(modelCampanaProductoVersionData.getId() == null){
							modelCampanaProductoVersionData.setCampanaProductoId(campanaProducto.getPrimaryKey());
							campanaProductoVersion = campanaProductoVersionLocal.registrarCampanaProductoVersion(campanaProducto,modelCampanaProductoVersionData);
							manager.persist(campanaProductoVersion);
						}else{
							modelCampanaProductoVersionData.setCampanaProductoId(campanaProducto.getPrimaryKey());
							campanaProductoVersion = campanaProductoVersionLocal.registrarCampanaProductoVersion(campanaProducto,modelCampanaProductoVersionData);
							campanaProductoVersion.setId(modelCampanaProductoVersionData.getId());
							manager.merge(campanaProductoVersion);
						}
						//END 29 SEPTIEMBRE
					}
				}
			}
			
			
			

			/*for (CampanaBriefIf modelBrief : modelBriefList) {

				modelBrief.setCampanaId(campana.getPrimaryKey());
				CampanaBriefIf campanaBrief = campanaBriefLocal.registrarCampanaBrief(modelBrief,urlc);
				manager.merge(campanaBrief);
			}

			for (CampanaBriefIf modelBriefEliminados : briefsEliminadosList) {
				CampanaBriefIf data = campanaBriefLocal.getCampanaBrief(modelBriefEliminados.getId());
				manager.remove(data);
			}*/

			/*for (CampanaArchivoIf modelArchivo : modelArchivoList) {

				modelArchivo.setCampanaId(campana.getPrimaryKey());
				CampanaArchivoIf campanaArchivo = campanaArchivoLocal.registrarCampanaArchivo(modelArchivo);
				manager.merge(campanaArchivo);
			}

			for (CampanaArchivoIf modelArchivoEliminados : archivosEliminadosList) {
				CampanaArchivoIf data = campanaArchivoLocal.getCampanaArchivo(modelArchivoEliminados.getId());
				manager.remove(data);
			}*/

			/*for (File archivo: archivosList){
				FileSaver.save(archivo);
			}*/
			return campana;
		} catch(Exception e){
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar información en Campana-CampanaDetalle");
		}
	}
	//END 28 SEPTIEMBRE
	
	public CampanaIf actualizarCampana(CampanaIf model, List<CampanaDetalleIf> modelDetalleList, List<ProductoClienteIf> modelProductoList, List<CampanaDetalleIf> detallesCampanaEliminadosList) throws GenericBusinessException {
		try{
			CampanaIf campana = registrarCampana(model);			
			manager.merge(campana);

			for (CampanaDetalleIf modelDetalle : modelDetalleList) {
				modelDetalle.setCampanaId(campana.getPrimaryKey());
				CampanaDetalleIf campanaDetalle = campanaDetalleLocal.registrarCampanaDetalle(modelDetalle);
				manager.merge(campanaDetalle);
			}

			for (CampanaDetalleIf modelDetallesEliminados : detallesCampanaEliminadosList) {
				CampanaDetalleIf data = campanaDetalleLocal.getCampanaDetalle(modelDetallesEliminados.getId());
				manager.remove(data);
			}

			Collection<CampanaProductoIf> modelProductosEliminadosList = campanaProductoLocal.findCampanaProductoByCampanaId(campana.getPrimaryKey());

			for (CampanaProductoIf modelProducto : modelProductosEliminadosList) {
				CampanaProductoIf data = campanaProductoLocal.getCampanaProducto(modelProducto.getId());
				manager.remove(data);
			}	

			for (ProductoClienteIf modelProducto : modelProductoList) {

				CampanaProductoData modelCampanaProducto = new CampanaProductoData();
				modelCampanaProducto.setCampanaId(campana.getPrimaryKey());
				CampanaProductoIf campanaProducto = campanaProductoLocal.registrarCampanaProducto(modelCampanaProducto, modelProducto);
				manager.persist(campanaProducto);
			}		

			/*for (CampanaBriefIf modelBrief : modelBriefList) {

				modelBrief.setCampanaId(campana.getPrimaryKey());
				CampanaBriefIf campanaBrief = campanaBriefLocal.registrarCampanaBrief(modelBrief,urlc);
				manager.merge(campanaBrief);
			}

			for (CampanaBriefIf modelBriefEliminados : briefsEliminadosList) {
				CampanaBriefIf data = campanaBriefLocal.getCampanaBrief(modelBriefEliminados.getId());
				manager.remove(data);
			}*/

			/*for (CampanaArchivoIf modelArchivo : modelArchivoList) {

				modelArchivo.setCampanaId(campana.getPrimaryKey());
				CampanaArchivoIf campanaArchivo = campanaArchivoLocal.registrarCampanaArchivo(modelArchivo);
				manager.merge(campanaArchivo);
			}

			for (CampanaArchivoIf modelArchivoEliminados : archivosEliminadosList) {
				CampanaArchivoIf data = campanaArchivoLocal.getCampanaArchivo(modelArchivoEliminados.getId());
				manager.remove(data);
			}*/

			/*for (File archivo: archivosList){
				FileSaver.save(archivo);
			}*/
			return campana;
		} catch(Exception e){
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar información en Campana-CampanaDetalle");
		}
	}

	public void actualizarArchivosCampana(CampanaIf model,List<CampanaArchivoIf> modelArchivoList, List<CampanaArchivoIf> archivosEliminadosList,String urlCarpetaSevidor) 
	throws GenericBusinessException {
		try{
			CampanaIf campana = registrarCampana(model);
			manager.merge(campana);

			for (CampanaArchivoIf modelArchivoEliminados : archivosEliminadosList) {
				CampanaArchivoIf data = campanaArchivoLocal.getCampanaArchivo(modelArchivoEliminados.getId());
				manager.remove(data);
			}

			for (CampanaArchivoIf modelArchivo : modelArchivoList) {

				modelArchivo.setCampanaId(campana.getPrimaryKey());
				CampanaArchivoIf campanaArchivo = campanaArchivoLocal.registrarCampanaArchivo(modelArchivo,urlCarpetaSevidor);
				manager.merge(campanaArchivo);
			}

		} catch(Exception e){
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar información en Campana-CampanaDetalle");
		}
	}

	public void actualizarArchivosBrief(CampanaIf model,List<CampanaBriefIf> modelBriefList, List<CampanaBriefIf> briefsEliminadosList,String urlCarpetaSevidor) 
	throws GenericBusinessException {
		try{
			CampanaIf campana = registrarCampana(model);
			manager.merge(campana);

			for (CampanaBriefIf modelBriefEliminados : briefsEliminadosList) {
				CampanaBriefIf data = campanaBriefLocal.getCampanaBrief(modelBriefEliminados.getId());
				manager.remove(data);
			}

			for (CampanaBriefIf modelBrief : modelBriefList) {

				modelBrief.setCampanaId(campana.getPrimaryKey());
				CampanaBriefIf campanaBrief = campanaBriefLocal.registrarCampanaBrief(modelBrief,urlCarpetaSevidor);
				manager.merge(campanaBrief);
			}
		} catch(Exception e){
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar información en Campana-CampanaDetalle");
		}
	}

	public void eliminarCampana(Long campanaId) throws GenericBusinessException {
		try {
			CampanaEJB data = manager.find(CampanaEJB.class, campanaId);
			eliminarReferencias(campanaId);
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al eliminar información en Campana");
		}
	}

	/**
	 * @param model
	 * @return
	 */
	private CampanaEJB registrarCampana(CampanaIf model) {
		CampanaEJB campana = new CampanaEJB();

		campana.setId(model.getId());
		campana.setCodigo(model.getCodigo());
		campana.setNombre(model.getNombre());
		campana.setClienteId(model.getClienteId());
		campana.setFechaini(model.getFechaini());
		campana.setEstado(model.getEstado());
		campana.setPublicoObjetivo(model.getPublicoObjetivo());
		campana.setObservacion(model.getObservacion());
		campana.setUsuarioCreacionId(model.getUsuarioCreacionId());
		campana.setFechaCreacion(model.getFechaCreacion());
		campana.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
		campana.setFechaActualizacion(model.getFechaActualizacion());

		return campana;
	}

	private void eliminarReferencias(Long campanaId) throws GenericBusinessException {
		Collection<CampanaProductoIf> modelProductoList = campanaProductoLocal.findCampanaProductoByCampanaId(campanaId);
		Collection<CampanaDetalleIf> modelDetalleList = campanaDetalleLocal.findCampanaDetalleByCampanaId(campanaId);
		Collection<CampanaBriefIf> modelBriefList = campanaBriefLocal.findCampanaBriefByCampanaId(campanaId);
		Collection<CampanaArchivoIf> modelArchivoList = campanaArchivoLocal.findCampanaArchivoByCampanaId(campanaId);

		for (CampanaProductoIf modelProducto : modelProductoList) {
			
			Collection<CampanaProductoVersionIf> modelProductoVersionList = campanaProductoVersionLocal.findCampanaProductoVersionByCampanaProductoId(modelProducto.getId());
			
			for (CampanaProductoVersionIf modelProductoVersion : modelProductoVersionList) {
				
				manager.remove(modelProductoVersion);
			}			
			
			manager.remove(modelProducto);
		}

		for (CampanaDetalleIf modelDetalle : modelDetalleList) {
			manager.remove(modelDetalle);
		}

		for (CampanaBriefIf modelBrief : modelBriefList) {			
			manager.remove(modelBrief);
		}

		for (CampanaArchivoIf modelArchivo : modelArchivoList) {			
			manager.remove(modelArchivo);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCampanaByClienteIdAndByFechaInicioAndFechaFin(java.lang.Long clienteId, Date fechaInicio, Date fechaFin) {
		String queryString = "";
		if(fechaInicio != null && fechaFin != null){
			queryString = "from CampanaEJB e where e.clienteId = :clienteId and e.fechaini between :fechaInicio and :fechaFin";
		}else{
			queryString = "from CampanaEJB e where e.clienteId = :clienteId ";
		}
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("clienteId", clienteId);
		if(fechaInicio != null && fechaFin != null){
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFin",fechaFin);
		}		
		return query.getResultList();      
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCampanaByEstadoAndByFechaInicioAndFechaFin(java.lang.String estado, Date fechaInicio, Date fechaFin) {
		String queryString = "";
		if(fechaInicio != null && fechaFin != null){
			queryString = "from CampanaEJB e where e.estado = :estado and e.fechaini between :fechaInicio and :fechaFin";
		}else{
			queryString = "from CampanaEJB e where e.estado = :estado ";
		}
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("estado", estado);
		if(fechaInicio != null && fechaFin != null){
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFin",fechaFin);
		}		
		return query.getResultList();  
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCampanaByQueryAndByFechaInicioAndFechaFin(Map aMap, Date fechaInicio, Date fechaFin) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "";
		if(fechaInicio != null && fechaFin != null){
			queryString = "from CampanaEJB " + objectName + " where " + where + " and e.fechaini between :fechaInicio and :fechaFin";
		}else{
			queryString = "from CampanaEJB " + objectName + " where " + where;
		}
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		if(fechaInicio != null && fechaFin != null){
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFin",fechaFin);
		}		
		return query.getResultList();  
	}
	
	public java.util.Collection findCampanaByPlanMedioId(Long planMedioId) throws GenericBusinessException {
		//SELECT DISTINCT C.* FROM PLAN_MEDIO PM, ORDEN_TRABAJO_DETALLE ODT, ORDEN_TRABAJO OT, CAMPANA C
	    //WHERE PM.`ORDEN_TRABAJO_DETALLE_ID` = ODT.`ID` AND ODT.`ORDEN_ID` = OT.`ID` AND OT.`CAMPANA_ID` = C.`ID` AND PM.`ID` = 1402
		String queryString = 	"select distinct c from PlanMedioEJB pm, OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, CampanaEJB c " +
								"where pm.ordenTrabajoDetalleId = otd.id and otd.ordenId = ot.id and ot.campanaId = c.id and pm.id = :planMedioId";
		Query query = manager.createQuery(queryString);
		query.setParameter("planMedioId", planMedioId);
		return query.getResultList();
	}
}
