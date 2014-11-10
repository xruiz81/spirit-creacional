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
public abstract class _CuentaEntidadSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CuentaEntidadSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.CuentaEntidadIf addCuentaEntidad(com.spirit.contabilidad.entity.CuentaEntidadIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CuentaEntidadEJB value = new CuentaEntidadEJB();
      try {
      value.setId(model.getId());
      value.setEntidadId(model.getEntidadId());
      value.setTipoEntidad(model.getTipoEntidad());
      value.setNemonico(model.getNemonico());
      value.setCuentaId(model.getCuentaId());
      value.setOficinaId(model.getOficinaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cuentaEntidad.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cuentaEntidad.");
      }
     
      return getCuentaEntidad(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCuentaEntidad(com.spirit.contabilidad.entity.CuentaEntidadIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CuentaEntidadEJB data = new CuentaEntidadEJB();
      data.setId(model.getId());
      data.setEntidadId(model.getEntidadId());
      data.setTipoEntidad(model.getTipoEntidad());
      data.setNemonico(model.getNemonico());
      data.setCuentaId(model.getCuentaId());
      data.setOficinaId(model.getOficinaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cuentaEntidad.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cuentaEntidad.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCuentaEntidad(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CuentaEntidadEJB data = manager.find(CuentaEntidadEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cuentaEntidad.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cuentaEntidad.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.CuentaEntidadIf getCuentaEntidad(java.lang.Long id) {
      return (CuentaEntidadEJB)queryManagerLocal.find(CuentaEntidadEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentaEntidadList() {
	  return queryManagerLocal.singleClassList(CuentaEntidadEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentaEntidadList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CuentaEntidadEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCuentaEntidadListSize() {
      Query countQuery = manager.createQuery("select count(*) from CuentaEntidadEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaEntidadById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CuentaEntidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaEntidadByEntidadId(java.lang.Long entidadId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("entidadId", entidadId);
		return queryManagerLocal.singleClassQueryList(CuentaEntidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaEntidadByTipoEntidad(java.lang.String tipoEntidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoEntidad", tipoEntidad);
		return queryManagerLocal.singleClassQueryList(CuentaEntidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaEntidadByNemonico(java.lang.String nemonico) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nemonico", nemonico);
		return queryManagerLocal.singleClassQueryList(CuentaEntidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaEntidadByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(CuentaEntidadEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaEntidadByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(CuentaEntidadEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CuentaEntidadIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaEntidadByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CuentaEntidadEJB.class, aMap);      
    }

/////////////////
}
