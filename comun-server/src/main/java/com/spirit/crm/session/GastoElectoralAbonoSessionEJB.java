package com.spirit.crm.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

import com.spirit.crm.entity.GastoElectoralAbonoEJB;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>GastoElectoralAbonoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class GastoElectoralAbonoSessionEJB implements GastoElectoralAbonoSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(GastoElectoralAbonoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   public Collection findGastoElectoralIngresoByQueryByFechaInicioAndByFechaFin(Map aMap, Date fechaInicio, Date fechaFin) throws com.spirit.exception.GenericBusinessException{
       String objectName = "ge";
       String where = "";
       if(!aMap.isEmpty()){
    	   where = QueryBuilder.buildWhere(aMap, objectName);
       }
       String queryString = "";
       if(fechaInicio != null && fechaFin != null){
    	   queryString = "select ge from GastoElectoralAbonoEJB " + objectName + " where " + where + " and ge.fecha between :fechaInicio and :fechaFin";
       }else{
    	   queryString = "select ge from GastoElectoralAbonoEJB " + objectName + " where " + where + "";
       }
       String orderByPart = " order by ge.campana asc, ge.fecha asc";
 	   queryString += orderByPart;
 	   Query query = manager.createQuery(queryString);
 	   if(fechaInicio != null && fechaFin != null){
 		   query.setParameter("fechaInicio",fechaInicio);
 		   query.setParameter("fechaFin",fechaFin);
 	   } 	   
 	   
 	   Set keys = aMap.keySet();
 	   Iterator it = keys.iterator();

 	   while (it.hasNext()) {
 		   String propertyKey = (String) it.next();
 		   Object property = aMap.get(propertyKey);
 		   query.setParameter(propertyKey, property);
 	   }
 	   
 	   return query.getResultList();
   }

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new gastoElectoralAbono to the database.
    *
    * @param model a data object
    * @return GastoElectoralAbonoIf a data object with the primary key
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.GastoElectoralAbonoIf addGastoElectoralAbono(com.spirit.crm.entity.GastoElectoralAbonoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      GastoElectoralAbonoEJB value = new GastoElectoralAbonoEJB();
      try {
      value.setId(model.getId());
      value.setFecha(model.getFecha());
      value.setEntregadoPor(model.getEntregadoPor());
      value.setValor(model.getValor());
      value.setCampana(model.getCampana());
      manager.persist(value);
      manager.flush();
      } catch (Exception e) {
        log.error("Error al insertar información en gastoElectoralAbono.", e);
			throw new GenericBusinessException(
					"Error al insertar información en gastoElectoralAbono.");
      }
     
      return getGastoElectoralAbono(value.getPrimaryKey());
   }

   /**
    * Stores the <code>GastoElectoralAbonoIf</code> in the database.
    *
    * @param model the data model to store
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveGastoElectoralAbono(com.spirit.crm.entity.GastoElectoralAbonoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      GastoElectoralAbonoEJB data = new GastoElectoralAbonoEJB();
      data.setId(model.getId());
      data.setFecha(model.getFecha());
      data.setEntregadoPor(model.getEntregadoPor());
      data.setValor(model.getValor());
      data.setCampana(model.getCampana());
       manager.merge(data);
       manager.flush();
      } catch (Exception e) {
        log.error("Error al actualizar información en gastoElectoralAbono.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en gastoElectoralAbono.");
      }

   }

   /**
    * Removes a gastoElectoralAbono.
    *
    * @param id the unique reference for the gastoElectoralAbono
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteGastoElectoralAbono(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      GastoElectoralAbonoEJB data = manager.find(GastoElectoralAbonoEJB.class, id);
      manager.remove(data);
      manager.flush();

      } catch (Exception e) {
        log.error("Error al eliminar información en gastoElectoralAbono.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en gastoElectoralAbono.");
      }

   }

   /**
    * Retrieves a data object from the database by its primary key.
    *
    * @param id the unique reference
    * @return GastoElectoralAbonoIf the data object
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.GastoElectoralAbonoIf getGastoElectoralAbono(java.lang.Long id) {
      return manager.find(GastoElectoralAbonoEJB.class, id);
   }

   /**
    * Returns a collection of all gastoElectoralAbono instances.
    *
    * @return a collection of GastoElectoralAbonoIf objects.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGastoElectoralAbonoList() {
      String queryString = "from GastoElectoralAbonoEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      return query.getResultList();
   }

   /**
    * Returns a subset of all gastoElectoralAbono instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of gastoElectoralAbono instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getGastoElectoralAbonoListSize()</code> = last record),
    * any values greater than or equal to the total number of gastoElectoralAbono instances will cause
    * the full set to be returned.
    * @return a collection of GastoElectoralAbonoIf objects, of size <code>(endIndex - startIndex)</code>.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGastoElectoralAbonoList(int startIndex, int endIndex) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      String queryString = "from GastoElectoralAbonoEJB e";
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
    * Obtains the total number of gastoElectoralAbono objects in the database.
    *
    * @return an integer value.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getGastoElectoralAbonoListSize() {
      Query countQuery = manager.createQuery("select count(*) from GastoElectoralAbonoEJB");
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
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralAbonoById(java.lang.Long id) {

      String queryString = "from GastoElectoralAbonoEJB e where e.id = :id ";
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
     * Retrieves a list of data object for the specified fecha field.
     *
     * @param fecha the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralAbonoByFecha(java.sql.Date fecha) {

      String queryString = "from GastoElectoralAbonoEJB e where e.fecha = :fecha ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("fecha", fecha);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified entregadoPor field.
     *
     * @param entregadoPor the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralAbonoByEntregadoPor(java.lang.String entregadoPor) {

      String queryString = "from GastoElectoralAbonoEJB e where e.entregadoPor = :entregadoPor ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("entregadoPor", entregadoPor);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified valor field.
     *
     * @param valor the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralAbonoByValor(java.math.BigDecimal valor) {

      String queryString = "from GastoElectoralAbonoEJB e where e.valor = :valor ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("valor", valor);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified campana field.
     *
     * @param campana the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralAbonoByCampana(java.lang.String campana) {

      String queryString = "from GastoElectoralAbonoEJB e where e.campana = :campana ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("campana", campana);
      return query.getResultList();
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralAbonoByQuery(Map aMap) {
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "from GastoElectoralAbonoEJB " + objectName + " where "
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
