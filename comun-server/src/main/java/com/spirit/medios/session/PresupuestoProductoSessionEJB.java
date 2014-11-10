package com.spirit.medios.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.PresupuestoProductoEJB;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>PresupuestoProductoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class PresupuestoProductoSessionEJB implements PresupuestoProductoSessionRemote, PresupuestoProductoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(PresupuestoProductoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new presupuestoProducto to the database.
    *
    * @param model a data object
    * @return PresupuestoProductoIf a data object with the primary key
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoProductoIf addPresupuestoProducto(com.spirit.medios.entity.PresupuestoProductoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PresupuestoProductoEJB value = new PresupuestoProductoEJB();
      try {
      value.setId(model.getId());
      value.setProductoClienteId(model.getProductoClienteId());
      value.setPresupuestoId(model.getPresupuestoId());
      manager.persist(value);
      manager.flush();
      } catch (Exception e) {
        log.error("Error al insertar información en presupuestoProducto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en presupuestoProducto.");
      }
     
      return getPresupuestoProducto(value.getPrimaryKey());
   }

   /**
    * Stores the <code>PresupuestoProductoIf</code> in the database.
    *
    * @param model the data model to store
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePresupuestoProducto(com.spirit.medios.entity.PresupuestoProductoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PresupuestoProductoEJB data = new PresupuestoProductoEJB();
      data.setId(model.getId());
      data.setProductoClienteId(model.getProductoClienteId());
      data.setPresupuestoId(model.getPresupuestoId());
       manager.merge(data);
       manager.flush();
      } catch (Exception e) {
        log.error("Error al actualizar información en presupuestoProducto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en presupuestoProducto.");
      }

   }

   /**
    * Removes a presupuestoProducto.
    *
    * @param id the unique reference for the presupuestoProducto
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePresupuestoProducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PresupuestoProductoEJB data = manager.find(PresupuestoProductoEJB.class, id);
      manager.remove(data);
      manager.flush();

      } catch (Exception e) {
        log.error("Error al eliminar información en presupuestoProducto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en presupuestoProducto.");
      }

   }

   /**
    * Retrieves a data object from the database by its primary key.
    *
    * @param id the unique reference
    * @return PresupuestoProductoIf the data object
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoProductoIf getPresupuestoProducto(java.lang.Long id) {
      return manager.find(PresupuestoProductoEJB.class, id);
   }

   /**
    * Returns a collection of all presupuestoProducto instances.
    *
    * @return a collection of PresupuestoProductoIf objects.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoProductoList() {
      String queryString = "from PresupuestoProductoEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      return query.getResultList();
   }

   /**
    * Returns a subset of all presupuestoProducto instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of presupuestoProducto instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getPresupuestoProductoListSize()</code> = last record),
    * any values greater than or equal to the total number of presupuestoProducto instances will cause
    * the full set to be returned.
    * @return a collection of PresupuestoProductoIf objects, of size <code>(endIndex - startIndex)</code>.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoProductoList(int startIndex, int endIndex) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      String queryString = "from PresupuestoProductoEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setFirstResult(startIndex - 1);
      query.setMaxResults(endIndex - startIndex + 1);
      return query.getResultList();
   }

   /**
    * Obtains the total number of presupuestoProducto objects in the database.
    *
    * @return an integer value.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPresupuestoProductoListSize() {
      Query countQuery = manager.createQuery("select count(*) from PresupuestoProductoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }


    /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of PresupuestoProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoProductoById(java.lang.Long id) {

      String queryString = "from PresupuestoProductoEJB e where e.id = :id ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("id", id);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified productoClienteId field.
     *
     * @param productoClienteId the field
     * @return Collection of PresupuestoProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoProductoByProductoClienteId(java.lang.Long productoClienteId) {

      String queryString = "from PresupuestoProductoEJB e where e.productoClienteId = :productoClienteId ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("productoClienteId", productoClienteId);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified presupuestoId field.
     *
     * @param presupuestoId the field
     * @return Collection of PresupuestoProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoProductoByPresupuestoId(java.lang.Long presupuestoId) {

      String queryString = "from PresupuestoProductoEJB e where e.presupuestoId = :presupuestoId ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("presupuestoId", presupuestoId);
      return query.getResultList();
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PresupuestoProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoProductoByQuery(Map aMap) {
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "from PresupuestoProductoEJB " + objectName + " where "
				+ where;
 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();
      
    }

/////////////////
}
