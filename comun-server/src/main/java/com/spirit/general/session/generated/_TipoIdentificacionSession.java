package com.spirit.general.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.general.entity.*;
import com.spirit.general.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _TipoIdentificacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoIdentificacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoIdentificacionIf addTipoIdentificacion(com.spirit.general.entity.TipoIdentificacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoIdentificacionEJB value = new TipoIdentificacionEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setCodigoSri(model.getCodigoSri());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoIdentificacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoIdentificacion.");
      }
     
      return getTipoIdentificacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoIdentificacion(com.spirit.general.entity.TipoIdentificacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoIdentificacionEJB data = new TipoIdentificacionEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setCodigoSri(model.getCodigoSri());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoIdentificacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoIdentificacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoIdentificacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoIdentificacionEJB data = manager.find(TipoIdentificacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoIdentificacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoIdentificacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoOrdenIf addTipoOrden(com.spirit.general.entity.TipoOrdenIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoOrdenEJB value = new TipoOrdenEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setTipo(model.getTipo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoOrden.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoOrden.");
      }
     
      return getTipoOrden(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoOrden(com.spirit.general.entity.TipoOrdenIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoOrdenEJB data = new TipoOrdenEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setTipo(model.getTipo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoOrden.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoOrden.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoOrden(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoOrdenEJB data = manager.find(TipoOrdenEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoOrden.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoOrden.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoIdentificacionIf getTipoIdentificacion(java.lang.Long id) {
      return (TipoIdentificacionEJB)queryManagerLocal.find(TipoIdentificacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoIdentificacionList() {
	  return queryManagerLocal.singleClassList(TipoIdentificacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoIdentificacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoIdentificacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoIdentificacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoIdentificacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoOrdenIf getTipoOrden(java.lang.Long id) {
      return (TipoOrdenEJB)queryManagerLocal.find(TipoOrdenEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoOrdenList() {
	  return queryManagerLocal.singleClassList(TipoOrdenEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoOrdenList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoOrdenEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoOrdenListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoOrdenEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIdentificacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoIdentificacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIdentificacionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoIdentificacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIdentificacionByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoIdentificacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIdentificacionByCodigoSri(java.lang.String codigoSri) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoSri", codigoSri);
		return queryManagerLocal.singleClassQueryList(TipoIdentificacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoIdentificacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIdentificacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoIdentificacionEJB.class, aMap);      
    }

/////////////////


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoOrdenById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoOrdenEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoOrdenByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoOrdenEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoOrdenByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoOrdenEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoOrdenByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TipoOrdenEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoOrdenByTipo(java.lang.String tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(TipoOrdenEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoOrdenIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoOrdenByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoOrdenEJB.class, aMap);      
    }

/////////////////
}
