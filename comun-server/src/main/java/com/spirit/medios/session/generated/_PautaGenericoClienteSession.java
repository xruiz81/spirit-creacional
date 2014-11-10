package com.spirit.medios.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PautaGenericoClienteSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PautaGenericoClienteSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PautaGenericoClienteIf addPautaGenericoCliente(com.spirit.medios.entity.PautaGenericoClienteIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PautaGenericoClienteEJB value = new PautaGenericoClienteEJB();
      try {
      value.setId(model.getId());
      value.setClienteId(model.getClienteId());
      value.setTipoProductoId(model.getTipoProductoId());
      value.setGenericoId(model.getGenericoId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en pautaGenericoCliente.", e);
			throw new GenericBusinessException(
					"Error al insertar información en pautaGenericoCliente.");
      }
     
      return getPautaGenericoCliente(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePautaGenericoCliente(com.spirit.medios.entity.PautaGenericoClienteIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PautaGenericoClienteEJB data = new PautaGenericoClienteEJB();
      data.setId(model.getId());
      data.setClienteId(model.getClienteId());
      data.setTipoProductoId(model.getTipoProductoId());
      data.setGenericoId(model.getGenericoId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en pautaGenericoCliente.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en pautaGenericoCliente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePautaGenericoCliente(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PautaGenericoClienteEJB data = manager.find(PautaGenericoClienteEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en pautaGenericoCliente.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en pautaGenericoCliente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PautaGenericoClienteIf getPautaGenericoCliente(java.lang.Long id) {
      return (PautaGenericoClienteEJB)queryManagerLocal.find(PautaGenericoClienteEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPautaGenericoClienteList() {
	  return queryManagerLocal.singleClassList(PautaGenericoClienteEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPautaGenericoClienteList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PautaGenericoClienteEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPautaGenericoClienteListSize() {
      Query countQuery = manager.createQuery("select count(*) from PautaGenericoClienteEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPautaGenericoClienteById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PautaGenericoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPautaGenericoClienteByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(PautaGenericoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPautaGenericoClienteByTipoProductoId(java.lang.Long tipoProductoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoProductoId", tipoProductoId);
		return queryManagerLocal.singleClassQueryList(PautaGenericoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPautaGenericoClienteByGenericoId(java.lang.Long genericoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("genericoId", genericoId);
		return queryManagerLocal.singleClassQueryList(PautaGenericoClienteEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PautaGenericoClienteIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPautaGenericoClienteByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PautaGenericoClienteEJB.class, aMap);      
    }

/////////////////
}
