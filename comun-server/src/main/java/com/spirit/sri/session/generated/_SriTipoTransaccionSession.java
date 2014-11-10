package com.spirit.sri.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.sri.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SriTipoTransaccionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriTipoTransaccionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriTipoTransaccionIf addSriTipoTransaccion(com.spirit.sri.entity.SriTipoTransaccionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriTipoTransaccionEJB value = new SriTipoTransaccionEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriTipoTransaccion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriTipoTransaccion.");
      }
     
      return getSriTipoTransaccion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriTipoTransaccion(com.spirit.sri.entity.SriTipoTransaccionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriTipoTransaccionEJB data = new SriTipoTransaccionEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriTipoTransaccion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriTipoTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriTipoTransaccion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriTipoTransaccionEJB data = manager.find(SriTipoTransaccionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriTipoTransaccion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriTipoTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriTipoTransaccionIf getSriTipoTransaccion(java.lang.Long id) {
      return (SriTipoTransaccionEJB)queryManagerLocal.find(SriTipoTransaccionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriTipoTransaccionList() {
	  return queryManagerLocal.singleClassList(SriTipoTransaccionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriTipoTransaccionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriTipoTransaccionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriTipoTransaccionListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriTipoTransaccionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriTipoTransaccionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriTipoTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriTipoTransaccionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriTipoTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriTipoTransaccionByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(SriTipoTransaccionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriTipoTransaccionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriTipoTransaccionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriTipoTransaccionEJB.class, aMap);      
    }

/////////////////
}
