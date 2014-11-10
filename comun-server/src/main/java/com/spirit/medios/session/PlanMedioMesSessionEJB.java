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
import com.spirit.medios.entity.PlanMedioMesEJB;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>PlanMedioMesSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class PlanMedioMesSessionEJB implements PlanMedioMesSessionRemote, PlanMedioMesSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(PlanMedioMesSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new planMedioMes to the database.
    *
    * @param model a data object
    * @return PlanMedioMesIf a data object with the primary key
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioMesIf addPlanMedioMes(com.spirit.medios.entity.PlanMedioMesIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PlanMedioMesEJB value = new PlanMedioMesEJB();
      try {
      value.setId(model.getId());
      value.setPlanMedioId(model.getPlanMedioId());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setValorSubtotal(model.getValorSubtotal());
      value.setValorDescuento(model.getValorDescuento());
      value.setTipo(model.getTipo());
      manager.persist(value);
      manager.flush();
      } catch (Exception e) {
        log.error("Error al insertar información en planMedioMes.", e);
			throw new GenericBusinessException(
					"Error al insertar información en planMedioMes.");
      }
     
      return getPlanMedioMes(value.getPrimaryKey());
   }

   /**
    * Stores the <code>PlanMedioMesIf</code> in the database.
    *
    * @param model the data model to store
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePlanMedioMes(com.spirit.medios.entity.PlanMedioMesIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PlanMedioMesEJB data = new PlanMedioMesEJB();
      data.setId(model.getId());
      data.setPlanMedioId(model.getPlanMedioId());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setValorSubtotal(model.getValorSubtotal());
      data.setValorDescuento(model.getValorDescuento());
      data.setTipo(model.getTipo());
       manager.merge(data);
       manager.flush();
      } catch (Exception e) {
        log.error("Error al actualizar información en planMedioMes.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en planMedioMes.");
      }

   }

   /**
    * Removes a planMedioMes.
    *
    * @param id the unique reference for the planMedioMes
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePlanMedioMes(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PlanMedioMesEJB data = manager.find(PlanMedioMesEJB.class, id);
      manager.remove(data);
      manager.flush();

      } catch (Exception e) {
        log.error("Error al eliminar información en planMedioMes.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en planMedioMes.");
      }

   }

   /**
    * Retrieves a data object from the database by its primary key.
    *
    * @param id the unique reference
    * @return PlanMedioMesIf the data object
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioMesIf getPlanMedioMes(java.lang.Long id) {
      return manager.find(PlanMedioMesEJB.class, id);
   }

   /**
    * Returns a collection of all planMedioMes instances.
    *
    * @return a collection of PlanMedioMesIf objects.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioMesList() {
      String queryString = "from PlanMedioMesEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      return query.getResultList();
   }

   /**
    * Returns a subset of all planMedioMes instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of planMedioMes instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getPlanMedioMesListSize()</code> = last record),
    * any values greater than or equal to the total number of planMedioMes instances will cause
    * the full set to be returned.
    * @return a collection of PlanMedioMesIf objects, of size <code>(endIndex - startIndex)</code>.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioMesList(int startIndex, int endIndex) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      String queryString = "from PlanMedioMesEJB e";
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
    * Obtains the total number of planMedioMes objects in the database.
    *
    * @return an integer value.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPlanMedioMesListSize() {
      Query countQuery = manager.createQuery("select count(*) from PlanMedioMesEJB");
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
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesById(java.lang.Long id) {

      String queryString = "from PlanMedioMesEJB e where e.id = :id ";
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
     * Retrieves a list of data object for the specified planmedioId field.
     *
     * @param planmedioId the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByPlanmedioId(java.lang.Long planmedioId) {

      String queryString = "from PlanMedioMesEJB e where e.planMedioId = :planmedioId ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("planmedioId", planmedioId);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified fechaini field.
     *
     * @param fechaini the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByFechaini(java.sql.Date fechaini) {

      String queryString = "from PlanMedioMesEJB e where e.fechaInicio = :fechaini ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("fechaini", fechaini);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified fechafin field.
     *
     * @param fechafin the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByFechafin(java.sql.Date fechafin) {

      String queryString = "from PlanMedioMesEJB e where e.fechaFin = :fechafin ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("fechafin", fechafin);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified valorTarifa field.
     *
     * @param valorTarifa the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByValorTarifa(java.math.BigDecimal valorTarifa) {

      String queryString = "from PlanMedioMesEJB e where e.valorTarifa = :valorTarifa ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("valorTarifa", valorTarifa);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified valorDescuento field.
     *
     * @param valorDescuento the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByValorDescuento(java.math.BigDecimal valorDescuento) {

      String queryString = "from PlanMedioMesEJB e where e.valorDescuento = :valorDescuento ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("valorDescuento", valorDescuento);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified tipo field.
     *
     * @param tipo the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByTipo(java.lang.String tipo) {

      String queryString = "from PlanMedioMesEJB e where e.tipo = :tipo ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("tipo", tipo);
      return query.getResultList();
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioMesByQuery(Map aMap) {
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "from PlanMedioMesEJB " + objectName + " where "
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
