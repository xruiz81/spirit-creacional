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
public abstract class _BodegaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_BodegaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.BodegaIf addBodega(com.spirit.inventario.entity.BodegaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      BodegaEJB value = new BodegaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setOficinaId(model.getOficinaId());
      value.setFuncionBodegaId(model.getFuncionBodegaId());
      value.setTipoBodegaId(model.getTipoBodegaId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en bodega.", e);
			throw new GenericBusinessException(
					"Error al insertar información en bodega.");
      }
     
      return getBodega(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveBodega(com.spirit.inventario.entity.BodegaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      BodegaEJB data = new BodegaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setOficinaId(model.getOficinaId());
      data.setFuncionBodegaId(model.getFuncionBodegaId());
      data.setTipoBodegaId(model.getTipoBodegaId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en bodega.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en bodega.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteBodega(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      BodegaEJB data = manager.find(BodegaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en bodega.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en bodega.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.BodegaIf getBodega(java.lang.Long id) {
      return (BodegaEJB)queryManagerLocal.find(BodegaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getBodegaList() {
	  return queryManagerLocal.singleClassList(BodegaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getBodegaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(BodegaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getBodegaListSize() {
      Query countQuery = manager.createQuery("select count(*) from BodegaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBodegaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(BodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBodegaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(BodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBodegaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(BodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBodegaByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(BodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBodegaByFuncionBodegaId(java.lang.Long funcionBodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("funcionBodegaId", funcionBodegaId);
		return queryManagerLocal.singleClassQueryList(BodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBodegaByTipoBodegaId(java.lang.Long tipoBodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoBodegaId", tipoBodegaId);
		return queryManagerLocal.singleClassQueryList(BodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBodegaByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(BodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBodegaByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(BodegaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of BodegaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBodegaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(BodegaEJB.class, aMap);      
    }

/////////////////
}
