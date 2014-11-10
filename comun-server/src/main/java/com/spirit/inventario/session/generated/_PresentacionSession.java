package com.spirit.inventario.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.inventario.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PresentacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PresentacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.PresentacionIf addPresentacion(com.spirit.inventario.entity.PresentacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PresentacionEJB value = new PresentacionEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setPadreId(model.getPadreId());
      value.setMedidaId(model.getMedidaId());
      value.setEmpresaId(model.getEmpresaId());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en presentacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en presentacion.");
      }
     
      return getPresentacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePresentacion(com.spirit.inventario.entity.PresentacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PresentacionEJB data = new PresentacionEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setPadreId(model.getPadreId());
      data.setMedidaId(model.getMedidaId());
      data.setEmpresaId(model.getEmpresaId());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en presentacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en presentacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePresentacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PresentacionEJB data = manager.find(PresentacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en presentacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en presentacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.PresentacionIf getPresentacion(java.lang.Long id) {
      return (PresentacionEJB)queryManagerLocal.find(PresentacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresentacionList() {
	  return queryManagerLocal.singleClassList(PresentacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresentacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PresentacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPresentacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from PresentacionEJB");
      List countQueryResult = countQuery.getResultList();
      Long countResult = (Long) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresentacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PresentacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresentacionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PresentacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresentacionByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(PresentacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresentacionByPadreId(java.lang.Long padreId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("padreId", padreId);
		return queryManagerLocal.singleClassQueryList(PresentacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresentacionByMedidaId(java.lang.Long medidaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("medidaId", medidaId);
		return queryManagerLocal.singleClassQueryList(PresentacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresentacionByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(PresentacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresentacionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PresentacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PresentacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresentacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PresentacionEJB.class, aMap);      
    }

/////////////////
}
