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
public abstract class _PrensaUbicacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PrensaUbicacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaUbicacionIf addPrensaUbicacion(com.spirit.medios.entity.PrensaUbicacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PrensaUbicacionEJB value = new PrensaUbicacionEJB();
      try {
      value.setId(model.getId());
      value.setClienteId(model.getClienteId());
      value.setCodigo(model.getCodigo());
      value.setUbicacion(model.getUbicacion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en prensaUbicacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en prensaUbicacion.");
      }
     
      return getPrensaUbicacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePrensaUbicacion(com.spirit.medios.entity.PrensaUbicacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PrensaUbicacionEJB data = new PrensaUbicacionEJB();
      data.setId(model.getId());
      data.setClienteId(model.getClienteId());
      data.setCodigo(model.getCodigo());
      data.setUbicacion(model.getUbicacion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en prensaUbicacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en prensaUbicacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePrensaUbicacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PrensaUbicacionEJB data = manager.find(PrensaUbicacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en prensaUbicacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en prensaUbicacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaUbicacionIf getPrensaUbicacion(java.lang.Long id) {
      return (PrensaUbicacionEJB)queryManagerLocal.find(PrensaUbicacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaUbicacionList() {
	  return queryManagerLocal.singleClassList(PrensaUbicacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaUbicacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PrensaUbicacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPrensaUbicacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from PrensaUbicacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaUbicacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PrensaUbicacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaUbicacionByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(PrensaUbicacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaUbicacionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PrensaUbicacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaUbicacionByUbicacion(java.lang.String ubicacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ubicacion", ubicacion);
		return queryManagerLocal.singleClassQueryList(PrensaUbicacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PrensaUbicacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaUbicacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PrensaUbicacionEJB.class, aMap);      
    }

/////////////////
}
