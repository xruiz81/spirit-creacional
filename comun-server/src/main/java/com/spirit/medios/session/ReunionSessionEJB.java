package com.spirit.medios.session;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.ReunionArchivoIf;
import com.spirit.medios.entity.ReunionAsistenteData;
import com.spirit.medios.entity.ReunionAsistenteIf;
import com.spirit.medios.entity.ReunionCompromisoIf;
import com.spirit.medios.entity.ReunionEJB;
import com.spirit.medios.entity.ReunionIf;
import com.spirit.medios.entity.ReunionProductoData;
import com.spirit.medios.entity.ReunionProductoIf;
import com.spirit.medios.session.generated._ReunionSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ReunionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class ReunionSessionEJB extends _ReunionSession implements ReunionSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

	@EJB 
	private ReunionProductoSessionLocal reunionProductoLocal; 
	   
	@EJB 
	private ReunionAsistenteSessionLocal reunionAsistenteLocal; 
	
	@EJB 
	private ReunionArchivoSessionLocal reunionArchivoLocal; 
	   
	@EJB 
	private ReunionCompromisoSessionLocal reunionCompromisoLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ReunionSessionEJB.class);

@Resource private SessionContext ctx;
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findReunionByQuery(int startIndex,int endIndex,Map aMap, Long idEmpresa) {
	   if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "select distinct e from ReunionEJB " + objectName + ", OficinaEJB o where ";
 	queryString += (!where.equals(" ")?where+" and ":"");
 	queryString += "e.oficinaId = o.id and o.empresaId = " + idEmpresa;
 	queryString += " order by e.fecha desc, e.id desc";
 	Query query = manager.createQuery(queryString);

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
   public int findReunionByQuerySize(Map aMap, Long idEmpresa) {
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "select distinct count(*) from ReunionEJB " + objectName + ", OficinaEJB o where ";
 	queryString += (!where.equals(" ")?where+" and ":"");
 	queryString += "e.oficinaId = o.id and o.empresaId = " + idEmpresa;
 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
    }
   
	public ReunionIf procesarReunion(ReunionIf model, List modelProductoList, List<EmpleadoIf> modelAsistenteAgenciaList, List modelAsistenteClienteList, List<ReunionCompromisoIf> modelCompromisoList, boolean esCliente,  List<File> archivosList,List<String> archivosNombreList) throws GenericBusinessException, java.io.NotSerializableException {
		try {
			 
			ReunionIf reunion = registrarReunion(model);
			manager.persist(reunion);

			if (esCliente) {
				List<ProductoClienteIf> modelProductoClienteList = modelProductoList;
				
				for (ProductoClienteIf modelProducto : modelProductoClienteList) {

					ReunionProductoData modelReunionProducto = new ReunionProductoData();
					modelReunionProducto.setReunionId(reunion.getPrimaryKey());
					ReunionProductoIf reunionProductoCliente = reunionProductoLocal.registrarReunionProductoCliente(modelReunionProducto, modelProducto);
					manager.merge(reunionProductoCliente);
				}
			} else {
				List<String> modelProductoProspectoClienteList = modelProductoList;
				for (String modelProducto : modelProductoProspectoClienteList) {

					ReunionProductoData modelReunionProducto = new ReunionProductoData();
					modelReunionProducto.setReunionId(reunion.getPrimaryKey());
					ReunionProductoIf reunionProductoCliente = reunionProductoLocal.registrarReunionProductoProspectoCliente(modelReunionProducto, modelProducto);
					manager.merge(reunionProductoCliente);
				}				
			} 
			
			for (EmpleadoIf modelEmpleado : modelAsistenteAgenciaList) {

				ReunionAsistenteData modelAsistenteAgencia = new ReunionAsistenteData();
				modelAsistenteAgencia.setReunionId(reunion.getPrimaryKey());
				ReunionAsistenteIf reunionAsistenteAgencia = reunionAsistenteLocal.registrarReunionAsistenteAgencia(modelAsistenteAgencia, modelEmpleado);
				manager.merge(reunionAsistenteAgencia);
			}
			
			if (esCliente) {
				List<ClienteContactoIf> modelClienteContactoList = modelAsistenteClienteList;
				
				for (ClienteContactoIf modelClienteContacto : modelClienteContactoList) {

					ReunionAsistenteData modelAsistenteCliente = new ReunionAsistenteData();
					modelAsistenteCliente.setReunionId(reunion.getPrimaryKey());
					ReunionAsistenteIf reunionAsistenteCliente = reunionAsistenteLocal.registrarReunionAsistenteCliente(modelAsistenteCliente, modelClienteContacto);
					manager.merge(reunionAsistenteCliente);
				}
			} else {
				List<String> modelProspectoClienteList = modelAsistenteClienteList;
				
				for (String modelProspectoCliente : modelProspectoClienteList) {

					ReunionAsistenteData modelAsistenteProspectoCliente = new ReunionAsistenteData();
					modelAsistenteProspectoCliente.setReunionId(reunion.getPrimaryKey());
					ReunionAsistenteIf reunionAsistenteProspectoCliente = reunionAsistenteLocal.registrarReunionAsistenteProspectoCliente(modelAsistenteProspectoCliente,modelProspectoCliente);
					manager.merge(reunionAsistenteProspectoCliente);
				}
			}

			for (ReunionCompromisoIf modelCompromiso : modelCompromisoList) {

				modelCompromiso.setReunionId(reunion.getPrimaryKey());
				ReunionCompromisoIf reunionCompromiso = reunionCompromisoLocal.registrarReunionCompromiso(modelCompromiso);
				manager.merge(reunionCompromiso);
			}
			
			return reunion;

		} catch (Exception e) {
			ctx.setRollbackOnly();
			//e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en Reunion");
		}
	}
	
	public ReunionIf actualizarReunion(ReunionIf model, List modelProductoList, List<EmpleadoIf> modelAsistenteAgenciaList, List modelAsistenteClienteList, List<ReunionCompromisoIf> modelCompromisoList, boolean esCliente, List<File> archivosList, List<ReunionCompromisoIf> compromisosEliminadosList) throws GenericBusinessException {
		try{
			ReunionIf reunion = registrarReunion(model);			
			manager.merge(reunion);
			
			Collection<ReunionProductoIf> modelProductosEliminadosList = reunionProductoLocal.findReunionProductoByReunionId(reunion.getPrimaryKey());
			for (ReunionProductoIf modelProductoEliminado : modelProductosEliminadosList) {
				manager.remove(modelProductoEliminado);
			}
			
			if (esCliente) {
				List<ProductoClienteIf> modelProductoClienteList = modelProductoList;
				
				for (ProductoClienteIf modelProducto : modelProductoClienteList) {
	
					ReunionProductoData modelReunionProducto = new ReunionProductoData();
					modelReunionProducto.setReunionId(reunion.getPrimaryKey());
					ReunionProductoIf reunionProductoCliente = reunionProductoLocal.registrarReunionProductoCliente(modelReunionProducto, modelProducto);
					manager.merge(reunionProductoCliente);
				}
			} else {
				List<String> modelProductoProspectoClienteList = modelProductoList;
				for (String modelProducto : modelProductoProspectoClienteList) {
	
					ReunionProductoData modelReunionProducto = new ReunionProductoData();
					modelReunionProducto.setReunionId(reunion.getPrimaryKey());
					ReunionProductoIf reunionProductoProspectoCliente = reunionProductoLocal.registrarReunionProductoProspectoCliente(modelReunionProducto, modelProducto);
					manager.merge(reunionProductoProspectoCliente);
				}				
			}
			
			Collection<ReunionAsistenteIf> modelAsistenteEliminadoList = reunionAsistenteLocal.findReunionAsistenteByReunionId(reunion.getPrimaryKey());
			for (ReunionAsistenteIf modelAsistenteEliminado : modelAsistenteEliminadoList) {
				manager.remove(modelAsistenteEliminado);
			}
	
			for (EmpleadoIf modelEmpleado : modelAsistenteAgenciaList) {
	
				ReunionAsistenteData modelAsistenteAgencia = new ReunionAsistenteData();
				modelAsistenteAgencia.setReunionId(reunion.getPrimaryKey());
				ReunionAsistenteIf reunionAsistenteAgencia = reunionAsistenteLocal.registrarReunionAsistenteAgencia(modelAsistenteAgencia, modelEmpleado);
				manager.merge(reunionAsistenteAgencia);
			}
			
			if (esCliente) {
				List<ClienteContactoIf> modelClienteContactoList = modelAsistenteClienteList;
				
				for (ClienteContactoIf modelClienteContacto : modelClienteContactoList) {
	
					ReunionAsistenteData modelAsistenteCliente = new ReunionAsistenteData();
					modelAsistenteCliente.setReunionId(reunion.getPrimaryKey());
					ReunionAsistenteIf reunionAsistenteCliente = reunionAsistenteLocal.registrarReunionAsistenteCliente(modelAsistenteCliente, modelClienteContacto);
					manager.merge(reunionAsistenteCliente);
				}
			} else {
				List<String> modelProspectoClienteList = modelAsistenteClienteList;
				
				for (String modelProspectoCliente : modelProspectoClienteList) {
	
					ReunionAsistenteData modelAsistenteProspectoCliente = new ReunionAsistenteData();
					modelAsistenteProspectoCliente.setReunionId(reunion.getPrimaryKey());
					ReunionAsistenteIf reunionAsistenteProspectoCliente = reunionAsistenteLocal.registrarReunionAsistenteProspectoCliente(modelAsistenteProspectoCliente,modelProspectoCliente);
					manager.merge(reunionAsistenteProspectoCliente);
				}
	
			}
			
			for (ReunionCompromisoIf modelCompromiso : modelCompromisoList) {
				modelCompromiso.setReunionId(reunion.getPrimaryKey());
				ReunionCompromisoIf reunionCompromiso = reunionCompromisoLocal.registrarReunionCompromiso(modelCompromiso);
				manager.merge(reunionCompromiso);
			}
			
			for (ReunionCompromisoIf modelCompromisoEliminado : compromisosEliminadosList) {
				ReunionCompromisoIf data = reunionCompromisoLocal.getReunionCompromiso(modelCompromisoEliminado.getId());
				manager.remove(data);
			}
			manager.flush();
			return reunion;
		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar información en Reunion");
		}
	}
	
	public void actualizarArchivosReunion(ReunionIf model, List<ReunionArchivoIf> modelArchivoList, List<ReunionArchivoIf> archivosEliminadosList,String urlCarpetaSevidor) throws GenericBusinessException {
		try{
			ReunionIf reunion = registrarReunion(model);
			
			manager.merge(reunion);
			
			for (ReunionArchivoIf modelArchivoEliminado : archivosEliminadosList) {
				ReunionArchivoIf data = reunionArchivoLocal.getReunionArchivo(modelArchivoEliminado.getId());
				manager.remove(data);
			}
			
			for (ReunionArchivoIf modelArchivo : modelArchivoList) {
				modelArchivo.setReunionId(reunion.getPrimaryKey());
				ReunionArchivoIf reunionArchivo = reunionArchivoLocal.registrarReunionArchivo(modelArchivo,urlCarpetaSevidor);
				manager.merge(reunionArchivo);
			}
			
			manager.flush();
		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al actualizar información en Reunion");
		}
	}

	public void eliminarReunion(Long reunionId) throws GenericBusinessException {
		try {
			ReunionEJB data = manager.find(ReunionEJB.class, reunionId);
			eliminarReferencias(reunionId);
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en ReunionEJB.", e);
			throw new GenericBusinessException("Error al eliminar información en Reunion");
		}
	}
	
	/**
	 * @param model
	 * @return
	 */
	private ReunionEJB registrarReunion(ReunionIf model) {
		ReunionEJB reunion = new ReunionEJB();
		
		reunion.setId(model.getId());
		reunion.setOficinaId(model.getOficinaId());
		reunion.setClienteId(model.getClienteId());
		reunion.setProspectocliente(model.getProspectocliente());
		reunion.setFecha(model.getFecha());
		reunion.setFechaEnvio(model.getFechaEnvio());
		reunion.setNumEnviados(model.getNumEnviados());
		reunion.setDescripcion(model.getDescripcion());
		reunion.setEstado(model.getEstado());
		reunion.setUsuarioCreacionId(model.getUsuarioCreacionId());
		reunion.setFechaCreacion(model.getFechaCreacion());
		reunion.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
		reunion.setFechaActualizacion(model.getFechaActualizacion());
		reunion.setEjecutivoId(model.getEjecutivoId());
		reunion.setLugarReunion(model.getLugarReunion());
		reunion.setConCopia(model.getConCopia());
		
		return reunion;
	}
		
	private void eliminarReferencias(Long reunionId) throws GenericBusinessException {
		Collection<ReunionProductoIf> modelProductoList = reunionProductoLocal.findReunionProductoByReunionId(reunionId);
		Collection<ReunionAsistenteIf> modelAsistenteList = reunionAsistenteLocal.findReunionAsistenteByReunionId(reunionId);
		Collection<ReunionArchivoIf> modelArchivoList = reunionArchivoLocal.findReunionArchivoByReunionId(reunionId);
		Collection<ReunionCompromisoIf> modelCompromisoList = reunionCompromisoLocal.findReunionCompromisoByReunionId(reunionId);

		for (ReunionProductoIf modelProducto : modelProductoList) {
			manager.remove(modelProducto);
		}

		for (ReunionAsistenteIf modelAsistente : modelAsistenteList) {
			manager.remove(modelAsistente);
		}

		for (ReunionArchivoIf modelArchivo : modelArchivoList) {	
			manager.remove(modelArchivo);
		}

		for (ReunionCompromisoIf modelCompromiso : modelCompromisoList) {
			manager.remove(modelCompromiso);
		}
	}
	
}
