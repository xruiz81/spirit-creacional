package com.spirit.general.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.general.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CruceDocumentoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CruceDocumentoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.CruceDocumentoIf addCruceDocumento(com.spirit.general.entity.CruceDocumentoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CruceDocumentoEJB value = new CruceDocumentoEJB();
      try {
      value.setId(model.getId());
      value.setDocumentoId(model.getDocumentoId());
      value.setDocumentoaplId(model.getDocumentoaplId());
      value.setValidoAlGuardar(model.getValidoAlGuardar());
      value.setValidoAlActualizar(model.getValidoAlActualizar());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cruceDocumento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cruceDocumento.");
      }
     
      return getCruceDocumento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCruceDocumento(com.spirit.general.entity.CruceDocumentoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CruceDocumentoEJB data = new CruceDocumentoEJB();
      data.setId(model.getId());
      data.setDocumentoId(model.getDocumentoId());
      data.setDocumentoaplId(model.getDocumentoaplId());
      data.setValidoAlGuardar(model.getValidoAlGuardar());
      data.setValidoAlActualizar(model.getValidoAlActualizar());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cruceDocumento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cruceDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCruceDocumento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CruceDocumentoEJB data = manager.find(CruceDocumentoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cruceDocumento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cruceDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.CruceDocumentoIf getCruceDocumento(java.lang.Long id) {
      return (CruceDocumentoEJB)queryManagerLocal.find(CruceDocumentoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCruceDocumentoList() {
	  return queryManagerLocal.singleClassList(CruceDocumentoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCruceDocumentoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CruceDocumentoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCruceDocumentoListSize() {
      Query countQuery = manager.createQuery("select count(*) from CruceDocumentoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCruceDocumentoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CruceDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCruceDocumentoByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(CruceDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCruceDocumentoByDocumentoaplId(java.lang.Long documentoaplId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoaplId", documentoaplId);
		return queryManagerLocal.singleClassQueryList(CruceDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCruceDocumentoByValidoAlGuardar(java.lang.String validoAlGuardar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("validoAlGuardar", validoAlGuardar);
		return queryManagerLocal.singleClassQueryList(CruceDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCruceDocumentoByValidoAlActualizar(java.lang.String validoAlActualizar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("validoAlActualizar", validoAlActualizar);
		return queryManagerLocal.singleClassQueryList(CruceDocumentoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CruceDocumentoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCruceDocumentoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CruceDocumentoEJB.class, aMap);      
    }

/////////////////
}
