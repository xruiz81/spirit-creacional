package com.spirit.cartera.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.cartera.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ClienteCondicionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ClienteCondicionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.ClienteCondicionIf addClienteCondicion(com.spirit.cartera.entity.ClienteCondicionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ClienteCondicionEJB value = new ClienteCondicionEJB();
      try {
      value.setId(model.getId());
      value.setClienteId(model.getClienteId());
      value.setSubtipoordenId(model.getSubtipoordenId());
      value.setFormapagoId(model.getFormapagoId());
      value.setObservaciones(model.getObservaciones());
      value.setFechaini(model.getFechaini());
      value.setFechafin(model.getFechafin());
      value.setCodigo(model.getCodigo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en clienteCondicion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en clienteCondicion.");
      }
     
      return getClienteCondicion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveClienteCondicion(com.spirit.cartera.entity.ClienteCondicionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ClienteCondicionEJB data = new ClienteCondicionEJB();
      data.setId(model.getId());
      data.setClienteId(model.getClienteId());
      data.setSubtipoordenId(model.getSubtipoordenId());
      data.setFormapagoId(model.getFormapagoId());
      data.setObservaciones(model.getObservaciones());
      data.setFechaini(model.getFechaini());
      data.setFechafin(model.getFechafin());
      data.setCodigo(model.getCodigo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en clienteCondicion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en clienteCondicion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteClienteCondicion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ClienteCondicionEJB data = manager.find(ClienteCondicionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en clienteCondicion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en clienteCondicion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.ClienteCondicionIf getClienteCondicion(java.lang.Long id) {
      return (ClienteCondicionEJB)queryManagerLocal.find(ClienteCondicionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteCondicionList() {
	  return queryManagerLocal.singleClassList(ClienteCondicionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteCondicionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ClienteCondicionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getClienteCondicionListSize() {
      Query countQuery = manager.createQuery("select count(*) from ClienteCondicionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteCondicionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ClienteCondicionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteCondicionByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(ClienteCondicionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteCondicionBySubtipoordenId(java.lang.Long subtipoordenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("subtipoordenId", subtipoordenId);
		return queryManagerLocal.singleClassQueryList(ClienteCondicionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteCondicionByFormapagoId(java.lang.Long formapagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formapagoId", formapagoId);
		return queryManagerLocal.singleClassQueryList(ClienteCondicionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteCondicionByObservaciones(java.lang.String observaciones) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observaciones", observaciones);
		return queryManagerLocal.singleClassQueryList(ClienteCondicionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteCondicionByFechaini(java.sql.Date fechaini) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaini", fechaini);
		return queryManagerLocal.singleClassQueryList(ClienteCondicionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteCondicionByFechafin(java.sql.Date fechafin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechafin", fechafin);
		return queryManagerLocal.singleClassQueryList(ClienteCondicionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteCondicionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ClienteCondicionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ClienteCondicionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteCondicionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ClienteCondicionEJB.class, aMap);      
    }

/////////////////
}
