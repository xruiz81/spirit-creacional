package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.compras.entity.SolicitudCompraArchivoEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SolicitudCompraArchivoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SolicitudCompraArchivoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.SolicitudCompraArchivoIf addSolicitudCompraArchivo(com.spirit.compras.entity.SolicitudCompraArchivoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SolicitudCompraArchivoEJB value = new SolicitudCompraArchivoEJB();
      try {
      value.setId(model.getId());
      value.setSolicitudCompraId(model.getSolicitudCompraId());
      value.setTipoArchivoId(model.getTipoArchivoId());
      value.setUrl(model.getUrl());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en solicitudCompraArchivo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en solicitudCompraArchivo.");
      }
     
      return getSolicitudCompraArchivo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSolicitudCompraArchivo(com.spirit.compras.entity.SolicitudCompraArchivoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SolicitudCompraArchivoEJB data = new SolicitudCompraArchivoEJB();
      data.setId(model.getId());
      data.setSolicitudCompraId(model.getSolicitudCompraId());
      data.setTipoArchivoId(model.getTipoArchivoId());
      data.setUrl(model.getUrl());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en solicitudCompraArchivo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en solicitudCompraArchivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSolicitudCompraArchivo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SolicitudCompraArchivoEJB data = manager.find(SolicitudCompraArchivoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en solicitudCompraArchivo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en solicitudCompraArchivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.SolicitudCompraArchivoIf getSolicitudCompraArchivo(java.lang.Long id) {
      return (SolicitudCompraArchivoEJB)queryManagerLocal.find(SolicitudCompraArchivoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudCompraArchivoList() {
	  return queryManagerLocal.singleClassList(SolicitudCompraArchivoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSolicitudCompraArchivoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SolicitudCompraArchivoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSolicitudCompraArchivoListSize() {
      Query countQuery = manager.createQuery("select count(*) from SolicitudCompraArchivoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraArchivoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraArchivoBySolicitudCompraId(java.lang.Long solicitudCompraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("solicitudCompraId", solicitudCompraId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraArchivoByTipoArchivoId(java.lang.Long tipoArchivoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoArchivoId", tipoArchivoId);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraArchivoByUrl(java.lang.String url) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("url", url);
		return queryManagerLocal.singleClassQueryList(SolicitudCompraArchivoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SolicitudCompraArchivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSolicitudCompraArchivoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SolicitudCompraArchivoEJB.class, aMap);      
    }

/////////////////
}
