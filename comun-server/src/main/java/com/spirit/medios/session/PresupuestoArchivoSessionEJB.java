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
import com.spirit.medios.entity.PresupuestoArchivoEJB;
import com.spirit.medios.entity.PresupuestoArchivoIf;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>PresupuestoArchivoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class PresupuestoArchivoSessionEJB implements PresupuestoArchivoSessionRemote, PresupuestoArchivoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(PresupuestoArchivoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public PresupuestoArchivoEJB registrarPresupuestoArchivo(PresupuestoArchivoIf modelPresupuestoArchivo, String urlCarpetaSevidor) {
	   PresupuestoArchivoEJB presupuestoArchivo = new PresupuestoArchivoEJB();
		
		int posicionUltimoSlash = -1;
		if(modelPresupuestoArchivo.getUrl() != null) 
			posicionUltimoSlash = modelPresupuestoArchivo.getUrl().lastIndexOf("\\");
		
		String nombreArchivo = "";
		if(posicionUltimoSlash != -1)
			nombreArchivo = modelPresupuestoArchivo.getUrl().substring(posicionUltimoSlash+1);

		presupuestoArchivo.setId(modelPresupuestoArchivo.getId());
		presupuestoArchivo.setPresupuestoId(modelPresupuestoArchivo.getPresupuestoId());
		presupuestoArchivo.setTipoArchivoId(modelPresupuestoArchivo.getTipoArchivoId());
		
		if (!nombreArchivo.equals("")){
			
			int puntoExt = nombreArchivo.lastIndexOf(".");
	    	String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";	    	
	    	presupuestoArchivo.setUrl(urlCarpetaSevidor + strFilename.replaceAll(" ", "_"));
		}
		
		return presupuestoArchivo;
	}

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new presupuestoArchivo to the database.
    *
    * @param model a data object
    * @return PresupuestoArchivoIf a data object with the primary key
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoArchivoIf addPresupuestoArchivo(com.spirit.medios.entity.PresupuestoArchivoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PresupuestoArchivoEJB value = new PresupuestoArchivoEJB();
      try {
      value.setId(model.getId());
      value.setPresupuestoId(model.getPresupuestoId());
      value.setTipoArchivoId(model.getTipoArchivoId());
      value.setUrl(model.getUrl());
      manager.persist(value);
      manager.flush();
      } catch (Exception e) {
        log.error("Error al insertar información en presupuestoArchivo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en presupuestoArchivo.");
      }
     
      return getPresupuestoArchivo(value.getPrimaryKey());
   }

   /**
    * Stores the <code>PresupuestoArchivoIf</code> in the database.
    *
    * @param model the data model to store
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePresupuestoArchivo(com.spirit.medios.entity.PresupuestoArchivoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PresupuestoArchivoEJB data = new PresupuestoArchivoEJB();
      data.setId(model.getId());
      data.setPresupuestoId(model.getPresupuestoId());
      data.setTipoArchivoId(model.getTipoArchivoId());
      data.setUrl(model.getUrl());
       manager.merge(data);
       manager.flush();
      } catch (Exception e) {
        log.error("Error al actualizar información en presupuestoArchivo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en presupuestoArchivo.");
      }

   }

   /**
    * Removes a presupuestoArchivo.
    *
    * @param id the unique reference for the presupuestoArchivo
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePresupuestoArchivo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PresupuestoArchivoEJB data = manager.find(PresupuestoArchivoEJB.class, id);
      manager.remove(data);
      manager.flush();

      } catch (Exception e) {
        log.error("Error al eliminar información en presupuestoArchivo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en presupuestoArchivo.");
      }

   }

   /**
    * Retrieves a data object from the database by its primary key.
    *
    * @param id the unique reference
    * @return PresupuestoArchivoIf the data object
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoArchivoIf getPresupuestoArchivo(java.lang.Long id) {
      return manager.find(PresupuestoArchivoEJB.class, id);
   }

   /**
    * Returns a collection of all presupuestoArchivo instances.
    *
    * @return a collection of PresupuestoArchivoIf objects.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoArchivoList() {
      String queryString = "from PresupuestoArchivoEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      return query.getResultList();
   }

   /**
    * Returns a subset of all presupuestoArchivo instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of presupuestoArchivo instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getPresupuestoArchivoListSize()</code> = last record),
    * any values greater than or equal to the total number of presupuestoArchivo instances will cause
    * the full set to be returned.
    * @return a collection of PresupuestoArchivoIf objects, of size <code>(endIndex - startIndex)</code>.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoArchivoList(int startIndex, int endIndex) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      String queryString = "from PresupuestoArchivoEJB e";
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
    * Obtains the total number of presupuestoArchivo objects in the database.
    *
    * @return an integer value.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPresupuestoArchivoListSize() {
      Query countQuery = manager.createQuery("select count(*) from PresupuestoArchivoEJB");
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
     * @return Collection of PresupuestoArchivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoArchivoById(java.lang.Long id) {

      String queryString = "from PresupuestoArchivoEJB e where e.id = :id ";
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
     * Retrieves a list of data object for the specified presupuestoId field.
     *
     * @param presupuestoId the field
     * @return Collection of PresupuestoArchivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoArchivoByPresupuestoId(java.lang.Long presupuestoId) {

      String queryString = "from PresupuestoArchivoEJB e where e.presupuestoId = :presupuestoId ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("presupuestoId", presupuestoId);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified tipoArchivoId field.
     *
     * @param tipoArchivoId the field
     * @return Collection of PresupuestoArchivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoArchivoByTipoArchivoId(java.lang.Long tipoArchivoId) {

      String queryString = "from PresupuestoArchivoEJB e where e.tipoArchivoId = :tipoArchivoId ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("tipoArchivoId", tipoArchivoId);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified url field.
     *
     * @param url the field
     * @return Collection of PresupuestoArchivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoArchivoByUrl(java.lang.String url) {

      String queryString = "from PresupuestoArchivoEJB e where e.url = :url ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("url", url);
      return query.getResultList();
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PresupuestoArchivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoArchivoByQuery(Map aMap) {
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "from PresupuestoArchivoEJB " + objectName + " where "
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
