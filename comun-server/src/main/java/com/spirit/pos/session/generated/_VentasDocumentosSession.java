package com.spirit.pos.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.pos.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _VentasDocumentosSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_VentasDocumentosSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.VentasDocumentosIf addVentasDocumentos(com.spirit.pos.entity.VentasDocumentosIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      VentasDocumentosEJB value = new VentasDocumentosEJB();
      try {
      value.setId(model.getId());
      value.setVentastrackId(model.getVentastrackId());
      value.setTablaNombre(model.getTablaNombre());
      value.setTablaId(model.getTablaId());
      value.setRevisado(model.getRevisado());
      value.setNumDoc(model.getNumDoc());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
    	  e.printStackTrace();
        log.error("Error al insertar información en ventasDocumentos.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ventasDocumentos.");
      }
     
      return getVentasDocumentos(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveVentasDocumentos(com.spirit.pos.entity.VentasDocumentosIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      VentasDocumentosEJB data = new VentasDocumentosEJB();
      data.setId(model.getId());
      data.setVentastrackId(model.getVentastrackId());
      data.setTablaNombre(model.getTablaNombre());
      data.setTablaId(model.getTablaId());
      data.setRevisado(model.getRevisado());
      data.setNumDoc(model.getNumDoc());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ventasDocumentos.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ventasDocumentos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteVentasDocumentos(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      VentasDocumentosEJB data = manager.find(VentasDocumentosEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ventasDocumentos.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ventasDocumentos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.VentasDocumentosIf getVentasDocumentos(java.lang.Long id) {
      return (VentasDocumentosEJB)queryManagerLocal.find(VentasDocumentosEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getVentasDocumentosList() {
	  return queryManagerLocal.singleClassList(VentasDocumentosEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getVentasDocumentosList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(VentasDocumentosEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getVentasDocumentosListSize() {
      Query countQuery = manager.createQuery("select count(*) from VentasDocumentosEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasDocumentosById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(VentasDocumentosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasDocumentosByVentastrackId(java.lang.Long ventastrackId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ventastrackId", ventastrackId);
		return queryManagerLocal.singleClassQueryList(VentasDocumentosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasDocumentosByTablaNombre(java.lang.String tablaNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tablaNombre", tablaNombre);
		return queryManagerLocal.singleClassQueryList(VentasDocumentosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasDocumentosByTablaId(java.lang.Long tablaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tablaId", tablaId);
		return queryManagerLocal.singleClassQueryList(VentasDocumentosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasDocumentosByRevisado(java.lang.String revisado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("revisado", revisado);
		return queryManagerLocal.singleClassQueryList(VentasDocumentosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasDocumentosByNumDoc(java.lang.String numDoc) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numDoc", numDoc);
		return queryManagerLocal.singleClassQueryList(VentasDocumentosEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of VentasDocumentosIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasDocumentosByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(VentasDocumentosEJB.class, aMap);      
    }

/////////////////
}
