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
import com.spirit.medios.entity.TipoBriefEJB;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>TipoBriefSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class TipoBriefSessionEJB implements TipoBriefSessionRemote  {

	@PersistenceContext(unitName="spirit")
	   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(TipoBriefSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   /**
   *
   * Retrieves a list of data object for the specified query Map.
   *
   * //@param Map $field.Name the field
   * @return Collection of EmpresaIf data objects, empty list in case no results were found.
   */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findTipoBriefByQuery(Map aMap, Long idEmpresa) throws GenericBusinessException {
	   String objectName = "e";
	   String where = QueryBuilder.buildWhere(aMap, objectName);
	   String queryString = "from TipoBriefEJB " + objectName + " where " + where + " and e.empresaId = " + idEmpresa;
	   Query query = manager.createQuery(queryString);

	   Set keys = aMap.keySet();
	   Iterator it = keys.iterator();

	   while (it.hasNext()) {
		   String propertyKey = (String) it.next();
		   String property = (String) aMap.get(propertyKey);
		   query.setParameter(propertyKey, property);

	   }

	   return query.getResultList();
   }


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/

   /**
    * Adds a new tipoBrief to the database.
    *
    * @param model a data object
    * @return TipoBriefIf a data object with the primary key
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.TipoBriefIf addTipoBrief(com.spirit.medios.entity.TipoBriefIf model) {
      TipoBriefEJB value = new TipoBriefEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setObligatorio(model.getObligatorio());
         
      } catch (Exception e) {
         log.error("Error while copying properties for creating TipoBriefEJB data.", e);
      }
      manager.persist(value);
      manager.flush();
      return getTipoBrief(value.getPrimaryKey());
   }

   /**
    * Stores the <code>TipoBriefIf</code> in the database.
    *
    * @param model the data model to store
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoBrief(com.spirit.medios.entity.TipoBriefIf model) {
      // We have to create an ejb object:
      TipoBriefEJB data = new TipoBriefEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setObligatorio(model.getObligatorio());
      manager.merge(data);
      manager.flush();
   }

   /**
    * Removes a tipoBrief.
    *
    * @param id the unique reference for the tipoBrief
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoBrief(java.lang.Long id) {
      TipoBriefEJB data = manager.find(TipoBriefEJB.class, id);
      manager.remove(data);
      manager.flush();
   }

   /**
    * Retrieves a data object from the database by its primary key.
    *
    * @param id the unique reference
    * @return TipoBriefIf the data object
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.TipoBriefIf getTipoBrief(java.lang.Long id) {
      return manager.find(TipoBriefEJB.class, id);
   }

   /**
    * Returns a collection of all tipoBrief instances.
    *
    * @return a collection of TipoBriefIf objects.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoBriefList() {
      String queryString = "from TipoBriefEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      return query.getResultList();
   }

   /**
    * Returns a subset of all tipoBrief instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of tipoBrief instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getTipoBriefListSize()</code> = last record),
    * any values greater than or equal to the total number of tipoBrief instances will cause
    * the full set to be returned.
    * @return a collection of TipoBriefIf objects, of size <code>(endIndex - startIndex)</code>.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoBriefList(int startIndex, int endIndex) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      String queryString = "from TipoBriefEJB e";
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
    * Obtains the total number of tipoBrief objects in the database.
    *
    * @return an integer value.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoBriefListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoBriefEJB");
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
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoBriefById(java.lang.Long id) {

      String queryString = "from TipoBriefEJB e where e.id = :id ";
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
     * Retrieves a list of data object for the specified codigo field.
     *
     * @param codigo the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoBriefByCodigo(java.lang.String codigo) {

      String queryString = "from TipoBriefEJB e where e.codigo = :codigo ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("codigo", codigo);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified nombre field.
     *
     * @param nombre the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoBriefByNombre(java.lang.String nombre) {

      String queryString = "from TipoBriefEJB e where e.nombre = :nombre ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("nombre", nombre);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified empresaId field.
     *
     * @param empresaId the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoBriefByEmpresaId(java.lang.Long empresaId) {

      String queryString = "from TipoBriefEJB e where e.empresaId = :empresaId ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("empresaId", empresaId);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified obligatorio field.
     *
     * @param obligatorio the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoBriefByObligatorio(java.lang.String obligatorio) {

      String queryString = "from TipoBriefEJB e where e.obligatorio = :obligatorio ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("obligatorio", obligatorio);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoBriefByQuery(Map aMap) {
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "from TipoBriefEJB " + objectName + " where "
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
	
}
