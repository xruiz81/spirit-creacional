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
public abstract class _ClienteIndicadorSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ClienteIndicadorSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteIndicadorIf addClienteIndicador(com.spirit.crm.entity.ClienteIndicadorIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ClienteIndicadorEJB value = new ClienteIndicadorEJB();
      try {
      value.setId(model.getId());
      value.setClienteOficinaId(model.getClienteOficinaId());
      value.setTipoindicadorId(model.getTipoindicadorId());
      value.setValor(model.getValor());
      value.setCodigo(model.getCodigo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en clienteIndicador.", e);
			throw new GenericBusinessException(
					"Error al insertar información en clienteIndicador.");
      }
     
      return getClienteIndicador(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveClienteIndicador(com.spirit.crm.entity.ClienteIndicadorIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ClienteIndicadorEJB data = new ClienteIndicadorEJB();
      data.setId(model.getId());
      data.setClienteOficinaId(model.getClienteOficinaId());
      data.setTipoindicadorId(model.getTipoindicadorId());
      data.setValor(model.getValor());
      data.setCodigo(model.getCodigo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en clienteIndicador.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en clienteIndicador.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteClienteIndicador(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ClienteIndicadorEJB data = manager.find(ClienteIndicadorEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en clienteIndicador.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en clienteIndicador.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteIndicadorIf getClienteIndicador(java.lang.Long id) {
      return (ClienteIndicadorEJB)queryManagerLocal.find(ClienteIndicadorEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteIndicadorList() {
	  return queryManagerLocal.singleClassList(ClienteIndicadorEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteIndicadorList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ClienteIndicadorEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getClienteIndicadorListSize() {
      Query countQuery = manager.createQuery("select count(*) from ClienteIndicadorEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteIndicadorById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ClienteIndicadorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteIndicadorByClienteOficinaId(java.lang.Long clienteOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteOficinaId", clienteOficinaId);
		return queryManagerLocal.singleClassQueryList(ClienteIndicadorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteIndicadorByTipoindicadorId(java.lang.Long tipoindicadorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoindicadorId", tipoindicadorId);
		return queryManagerLocal.singleClassQueryList(ClienteIndicadorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteIndicadorByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(ClienteIndicadorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteIndicadorByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ClienteIndicadorEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ClienteIndicadorIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteIndicadorByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ClienteIndicadorEJB.class, aMap);      
    }

/////////////////
}
