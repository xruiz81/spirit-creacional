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
public abstract class _PrensaSeccionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PrensaSeccionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaSeccionIf addPrensaSeccion(com.spirit.medios.entity.PrensaSeccionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PrensaSeccionEJB value = new PrensaSeccionEJB();
      try {
      value.setId(model.getId());
      value.setClienteId(model.getClienteId());
      value.setCodigo(model.getCodigo());
      value.setSeccion(model.getSeccion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en prensaSeccion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en prensaSeccion.");
      }
     
      return getPrensaSeccion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePrensaSeccion(com.spirit.medios.entity.PrensaSeccionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PrensaSeccionEJB data = new PrensaSeccionEJB();
      data.setId(model.getId());
      data.setClienteId(model.getClienteId());
      data.setCodigo(model.getCodigo());
      data.setSeccion(model.getSeccion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en prensaSeccion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en prensaSeccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePrensaSeccion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PrensaSeccionEJB data = manager.find(PrensaSeccionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en prensaSeccion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en prensaSeccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaSeccionIf getPrensaSeccion(java.lang.Long id) {
      return (PrensaSeccionEJB)queryManagerLocal.find(PrensaSeccionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaSeccionList() {
	  return queryManagerLocal.singleClassList(PrensaSeccionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaSeccionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PrensaSeccionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPrensaSeccionListSize() {
      Query countQuery = manager.createQuery("select count(*) from PrensaSeccionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaSeccionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PrensaSeccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaSeccionByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(PrensaSeccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaSeccionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PrensaSeccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaSeccionBySeccion(java.lang.String seccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("seccion", seccion);
		return queryManagerLocal.singleClassQueryList(PrensaSeccionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PrensaSeccionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaSeccionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PrensaSeccionEJB.class, aMap);      
    }

/////////////////
}
