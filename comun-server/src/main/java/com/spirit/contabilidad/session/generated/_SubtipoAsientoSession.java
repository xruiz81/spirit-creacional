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
public abstract class _SubtipoAsientoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SubtipoAsientoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.SubtipoAsientoIf addSubtipoAsiento(com.spirit.contabilidad.entity.SubtipoAsientoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SubtipoAsientoEJB value = new SubtipoAsientoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setTipoId(model.getTipoId());
      value.setOrden(model.getOrden());
      value.setStatus(model.getStatus());
      value.setTipo(model.getTipo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en subtipoAsiento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en subtipoAsiento.");
      }
     
      return getSubtipoAsiento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSubtipoAsiento(com.spirit.contabilidad.entity.SubtipoAsientoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SubtipoAsientoEJB data = new SubtipoAsientoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setTipoId(model.getTipoId());
      data.setOrden(model.getOrden());
      data.setStatus(model.getStatus());
      data.setTipo(model.getTipo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en subtipoAsiento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en subtipoAsiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSubtipoAsiento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SubtipoAsientoEJB data = manager.find(SubtipoAsientoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en subtipoAsiento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en subtipoAsiento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.SubtipoAsientoIf getSubtipoAsiento(java.lang.Long id) {
      return (SubtipoAsientoEJB)queryManagerLocal.find(SubtipoAsientoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSubtipoAsientoList() {
	  return queryManagerLocal.singleClassList(SubtipoAsientoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSubtipoAsientoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SubtipoAsientoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSubtipoAsientoListSize() {
      Query countQuery = manager.createQuery("select count(*) from SubtipoAsientoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoAsientoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SubtipoAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoAsientoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SubtipoAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoAsientoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(SubtipoAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoAsientoByTipoId(java.lang.Long tipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoId", tipoId);
		return queryManagerLocal.singleClassQueryList(SubtipoAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoAsientoByOrden(java.lang.Long orden) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("orden", orden);
		return queryManagerLocal.singleClassQueryList(SubtipoAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoAsientoByStatus(java.lang.String status) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("status", status);
		return queryManagerLocal.singleClassQueryList(SubtipoAsientoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoAsientoByTipo(java.lang.Long tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(SubtipoAsientoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SubtipoAsientoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoAsientoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SubtipoAsientoEJB.class, aMap);      
    }

/////////////////
}
