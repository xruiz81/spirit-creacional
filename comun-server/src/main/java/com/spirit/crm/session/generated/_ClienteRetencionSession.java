package com.spirit.crm.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.crm.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ClienteRetencionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ClienteRetencionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteRetencionIf addClienteRetencion(com.spirit.crm.entity.ClienteRetencionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ClienteRetencionEJB value = new ClienteRetencionEJB();
      try {
      value.setId(model.getId());
      value.setClienteId(model.getClienteId());
      value.setSriAirId(model.getSriAirId());
      value.setSriIvaRetencionId(model.getSriIvaRetencionId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en clienteRetencion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en clienteRetencion.");
      }
     
      return getClienteRetencion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveClienteRetencion(com.spirit.crm.entity.ClienteRetencionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ClienteRetencionEJB data = new ClienteRetencionEJB();
      data.setId(model.getId());
      data.setClienteId(model.getClienteId());
      data.setSriAirId(model.getSriAirId());
      data.setSriIvaRetencionId(model.getSriIvaRetencionId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en clienteRetencion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en clienteRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteClienteRetencion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ClienteRetencionEJB data = manager.find(ClienteRetencionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en clienteRetencion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en clienteRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteRetencionIf getClienteRetencion(java.lang.Long id) {
      return (ClienteRetencionEJB)queryManagerLocal.find(ClienteRetencionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteRetencionList() {
	  return queryManagerLocal.singleClassList(ClienteRetencionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteRetencionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ClienteRetencionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getClienteRetencionListSize() {
      Query countQuery = manager.createQuery("select count(*) from ClienteRetencionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteRetencionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ClienteRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteRetencionByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(ClienteRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteRetencionBySriAirId(java.lang.Long sriAirId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("sriAirId", sriAirId);
		return queryManagerLocal.singleClassQueryList(ClienteRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteRetencionBySriIvaRetencionId(java.lang.Long sriIvaRetencionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("sriIvaRetencionId", sriIvaRetencionId);
		return queryManagerLocal.singleClassQueryList(ClienteRetencionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ClienteRetencionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteRetencionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ClienteRetencionEJB.class, aMap);      
    }

/////////////////
}
