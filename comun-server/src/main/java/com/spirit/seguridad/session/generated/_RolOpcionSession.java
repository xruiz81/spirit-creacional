package com.spirit.seguridad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.seguridad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _RolOpcionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_RolOpcionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.RolOpcionIf addRolOpcion(com.spirit.seguridad.entity.RolOpcionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      RolOpcionEJB value = new RolOpcionEJB();
      try {
      value.setId(model.getId());
      value.setRolId(model.getRolId());
      value.setMenuId(model.getMenuId());
      value.setNinguno(model.getNinguno());
      value.setGrabarActualizar(model.getGrabarActualizar());
      value.setBorrar(model.getBorrar());
      value.setConsultar(model.getConsultar());
      value.setAutorizar(model.getAutorizar());
      value.setImprimir(model.getImprimir());
      value.setGenerarGrafico(model.getGenerarGrafico());
      value.setDuplicar(model.getDuplicar());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en rolOpcion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en rolOpcion.");
      }
     
      return getRolOpcion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveRolOpcion(com.spirit.seguridad.entity.RolOpcionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      RolOpcionEJB data = new RolOpcionEJB();
      data.setId(model.getId());
      data.setRolId(model.getRolId());
      data.setMenuId(model.getMenuId());
      data.setNinguno(model.getNinguno());
      data.setGrabarActualizar(model.getGrabarActualizar());
      data.setBorrar(model.getBorrar());
      data.setConsultar(model.getConsultar());
      data.setAutorizar(model.getAutorizar());
      data.setImprimir(model.getImprimir());
      data.setGenerarGrafico(model.getGenerarGrafico());
      data.setDuplicar(model.getDuplicar());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en rolOpcion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en rolOpcion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteRolOpcion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      RolOpcionEJB data = manager.find(RolOpcionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en rolOpcion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en rolOpcion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.RolOpcionIf getRolOpcion(java.lang.Long id) {
      return (RolOpcionEJB)queryManagerLocal.find(RolOpcionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolOpcionList() {
	  return queryManagerLocal.singleClassList(RolOpcionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolOpcionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(RolOpcionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getRolOpcionListSize() {
      Query countQuery = manager.createQuery("select count(*) from RolOpcionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByRolId(java.lang.Long rolId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rolId", rolId);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByMenuId(java.lang.Long menuId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("menuId", menuId);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByNinguno(java.lang.String ninguno) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ninguno", ninguno);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByGrabarActualizar(java.lang.String grabarActualizar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("grabarActualizar", grabarActualizar);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByBorrar(java.lang.String borrar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("borrar", borrar);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByConsultar(java.lang.String consultar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("consultar", consultar);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByAutorizar(java.lang.String autorizar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizar", autorizar);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByImprimir(java.lang.String imprimir) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("imprimir", imprimir);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByGenerarGrafico(java.lang.String generarGrafico) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("generarGrafico", generarGrafico);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByDuplicar(java.lang.String duplicar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("duplicar", duplicar);
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of RolOpcionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolOpcionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(RolOpcionEJB.class, aMap);      
    }

/////////////////
}
