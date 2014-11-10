package com.spirit.rrhh.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.rrhh.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _EmpleadoIdiomasSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmpleadoIdiomasSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoIdiomasIf addEmpleadoIdiomas(com.spirit.rrhh.entity.EmpleadoIdiomasIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmpleadoIdiomasEJB value = new EmpleadoIdiomasEJB();
      try {
      value.setId(model.getId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setIdiomaId(model.getIdiomaId());
      value.setHabla(model.getHabla());
      value.setComprende(model.getComprende());
      value.setLee(model.getLee());
      value.setEscribe(model.getEscribe());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en empleadoIdiomas.", e);
			throw new GenericBusinessException(
					"Error al insertar información en empleadoIdiomas.");
      }
     
      return getEmpleadoIdiomas(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmpleadoIdiomas(com.spirit.rrhh.entity.EmpleadoIdiomasIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmpleadoIdiomasEJB data = new EmpleadoIdiomasEJB();
      data.setId(model.getId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setIdiomaId(model.getIdiomaId());
      data.setHabla(model.getHabla());
      data.setComprende(model.getComprende());
      data.setLee(model.getLee());
      data.setEscribe(model.getEscribe());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en empleadoIdiomas.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en empleadoIdiomas.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmpleadoIdiomas(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmpleadoIdiomasEJB data = manager.find(EmpleadoIdiomasEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en empleadoIdiomas.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en empleadoIdiomas.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoIdiomasIf getEmpleadoIdiomas(java.lang.Long id) {
      return (EmpleadoIdiomasEJB)queryManagerLocal.find(EmpleadoIdiomasEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoIdiomasList() {
	  return queryManagerLocal.singleClassList(EmpleadoIdiomasEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoIdiomasList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmpleadoIdiomasEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmpleadoIdiomasListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmpleadoIdiomasEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoIdiomasById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmpleadoIdiomasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoIdiomasByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoIdiomasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoIdiomasByIdiomaId(java.lang.Long idiomaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idiomaId", idiomaId);
		return queryManagerLocal.singleClassQueryList(EmpleadoIdiomasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoIdiomasByHabla(java.lang.String habla) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("habla", habla);
		return queryManagerLocal.singleClassQueryList(EmpleadoIdiomasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoIdiomasByComprende(java.lang.String comprende) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comprende", comprende);
		return queryManagerLocal.singleClassQueryList(EmpleadoIdiomasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoIdiomasByLee(java.lang.String lee) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lee", lee);
		return queryManagerLocal.singleClassQueryList(EmpleadoIdiomasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoIdiomasByEscribe(java.lang.String escribe) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("escribe", escribe);
		return queryManagerLocal.singleClassQueryList(EmpleadoIdiomasEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmpleadoIdiomasIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoIdiomasByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmpleadoIdiomasEJB.class, aMap);      
    }

/////////////////
}
