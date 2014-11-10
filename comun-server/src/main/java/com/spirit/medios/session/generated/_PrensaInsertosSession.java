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
public abstract class _PrensaInsertosSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PrensaInsertosSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaInsertosIf addPrensaInsertos(com.spirit.medios.entity.PrensaInsertosIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PrensaInsertosEJB value = new PrensaInsertosEJB();
      try {
      value.setId(model.getId());
      value.setClienteId(model.getClienteId());
      value.setCodigo(model.getCodigo());
      value.setMaxPaginas(model.getMaxPaginas());
      value.setTarifa(model.getTarifa());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en prensaInsertos.", e);
			throw new GenericBusinessException(
					"Error al insertar información en prensaInsertos.");
      }
     
      return getPrensaInsertos(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePrensaInsertos(com.spirit.medios.entity.PrensaInsertosIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PrensaInsertosEJB data = new PrensaInsertosEJB();
      data.setId(model.getId());
      data.setClienteId(model.getClienteId());
      data.setCodigo(model.getCodigo());
      data.setMaxPaginas(model.getMaxPaginas());
      data.setTarifa(model.getTarifa());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en prensaInsertos.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en prensaInsertos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePrensaInsertos(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PrensaInsertosEJB data = manager.find(PrensaInsertosEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en prensaInsertos.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en prensaInsertos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaInsertosIf getPrensaInsertos(java.lang.Long id) {
      return (PrensaInsertosEJB)queryManagerLocal.find(PrensaInsertosEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaInsertosList() {
	  return queryManagerLocal.singleClassList(PrensaInsertosEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaInsertosList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PrensaInsertosEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPrensaInsertosListSize() {
      Query countQuery = manager.createQuery("select count(*) from PrensaInsertosEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaInsertosById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PrensaInsertosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaInsertosByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(PrensaInsertosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaInsertosByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PrensaInsertosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaInsertosByMaxPaginas(java.math.BigDecimal maxPaginas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("maxPaginas", maxPaginas);
		return queryManagerLocal.singleClassQueryList(PrensaInsertosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaInsertosByTarifa(java.math.BigDecimal tarifa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarifa", tarifa);
		return queryManagerLocal.singleClassQueryList(PrensaInsertosEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PrensaInsertosIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaInsertosByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PrensaInsertosEJB.class, aMap);      
    }

/////////////////
}
