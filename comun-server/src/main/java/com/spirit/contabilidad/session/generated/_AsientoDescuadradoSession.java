package com.spirit.contabilidad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.contabilidad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _AsientoDescuadradoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_AsientoDescuadradoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoDescuadradoIf addAsientoDescuadrado(com.spirit.contabilidad.entity.AsientoDescuadradoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      AsientoDescuadradoEJB value = new AsientoDescuadradoEJB();
      try {
      value.setId(model.getId());
      value.setAsientoNumero(model.getAsientoNumero());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en asientoDescuadrado.", e);
			throw new GenericBusinessException(
					"Error al insertar información en asientoDescuadrado.");
      }
     
      return getAsientoDescuadrado(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveAsientoDescuadrado(com.spirit.contabilidad.entity.AsientoDescuadradoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      AsientoDescuadradoEJB data = new AsientoDescuadradoEJB();
      data.setId(model.getId());
      data.setAsientoNumero(model.getAsientoNumero());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en asientoDescuadrado.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en asientoDescuadrado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteAsientoDescuadrado(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      AsientoDescuadradoEJB data = manager.find(AsientoDescuadradoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en asientoDescuadrado.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en asientoDescuadrado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.AsientoDescuadradoIf getAsientoDescuadrado(java.lang.Long id) {
      return (AsientoDescuadradoEJB)queryManagerLocal.find(AsientoDescuadradoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoDescuadradoList() {
	  return queryManagerLocal.singleClassList(AsientoDescuadradoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsientoDescuadradoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(AsientoDescuadradoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getAsientoDescuadradoListSize() {
      Query countQuery = manager.createQuery("select count(*) from AsientoDescuadradoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDescuadradoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(AsientoDescuadradoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDescuadradoByAsientoNumero(java.lang.String asientoNumero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asientoNumero", asientoNumero);
		return queryManagerLocal.singleClassQueryList(AsientoDescuadradoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of AsientoDescuadradoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsientoDescuadradoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(AsientoDescuadradoEJB.class, aMap);      
    }

/////////////////
}
