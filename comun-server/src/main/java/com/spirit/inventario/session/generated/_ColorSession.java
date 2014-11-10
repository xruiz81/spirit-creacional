package com.spirit.inventario.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.inventario.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ColorSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ColorSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.ColorIf addColor(com.spirit.inventario.entity.ColorIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ColorEJB value = new ColorEJB();
      try {
      value.setId(model.getId());
      value.setNombre(model.getNombre());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en color.", e);
			throw new GenericBusinessException(
					"Error al insertar información en color.");
      }
     
      return getColor(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveColor(com.spirit.inventario.entity.ColorIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ColorEJB data = new ColorEJB();
      data.setId(model.getId());
      data.setNombre(model.getNombre());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en color.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en color.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteColor(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ColorEJB data = manager.find(ColorEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en color.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en color.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.ColorIf getColor(java.lang.Long id) {
      return (ColorEJB)queryManagerLocal.find(ColorEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getColorList() {
	  return queryManagerLocal.singleClassList(ColorEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getColorList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ColorEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getColorListSize() {
      Query countQuery = manager.createQuery("select count(*) from ColorEJB");
      List countQueryResult = countQuery.getResultList();
      Long countResult = (Long) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findColorById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ColorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findColorByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(ColorEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ColorIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findColorByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ColorEJB.class, aMap);      
    }

/////////////////
}
