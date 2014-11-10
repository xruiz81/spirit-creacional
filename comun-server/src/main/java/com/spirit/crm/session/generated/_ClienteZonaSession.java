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
public abstract class _ClienteZonaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ClienteZonaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteZonaIf addClienteZona(com.spirit.crm.entity.ClienteZonaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ClienteZonaEJB value = new ClienteZonaEJB();
      try {
      value.setId(model.getId());
      value.setClienteId(model.getClienteId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en clienteZona.", e);
			throw new GenericBusinessException(
					"Error al insertar información en clienteZona.");
      }
     
      return getClienteZona(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveClienteZona(com.spirit.crm.entity.ClienteZonaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ClienteZonaEJB data = new ClienteZonaEJB();
      data.setId(model.getId());
      data.setClienteId(model.getClienteId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en clienteZona.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en clienteZona.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteClienteZona(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ClienteZonaEJB data = manager.find(ClienteZonaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en clienteZona.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en clienteZona.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteZonaIf getClienteZona(java.lang.Long id) {
      return (ClienteZonaEJB)queryManagerLocal.find(ClienteZonaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteZonaList() {
	  return queryManagerLocal.singleClassList(ClienteZonaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteZonaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ClienteZonaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getClienteZonaListSize() {
      Query countQuery = manager.createQuery("select count(*) from ClienteZonaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteZonaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ClienteZonaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteZonaByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(ClienteZonaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteZonaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ClienteZonaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteZonaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(ClienteZonaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ClienteZonaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteZonaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ClienteZonaEJB.class, aMap);      
    }

/////////////////
}
