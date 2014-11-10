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
public abstract class _FuncionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_FuncionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.FuncionIf addFuncion(com.spirit.seguridad.entity.FuncionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      FuncionEJB value = new FuncionEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setIcon(model.getIcon());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en funcion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en funcion.");
      }
     
      return getFuncion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveFuncion(com.spirit.seguridad.entity.FuncionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      FuncionEJB data = new FuncionEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setIcon(model.getIcon());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en funcion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en funcion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteFuncion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      FuncionEJB data = manager.find(FuncionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en funcion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en funcion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.FuncionIf getFuncion(java.lang.Long id) {
      return (FuncionEJB)queryManagerLocal.find(FuncionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFuncionList() {
	  return queryManagerLocal.singleClassList(FuncionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFuncionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(FuncionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getFuncionListSize() {
      Query countQuery = manager.createQuery("select count(*) from FuncionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(FuncionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(FuncionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(FuncionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionByIcon(java.lang.String icon) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("icon", icon);
		return queryManagerLocal.singleClassQueryList(FuncionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of FuncionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(FuncionEJB.class, aMap);      
    }

/////////////////
}
