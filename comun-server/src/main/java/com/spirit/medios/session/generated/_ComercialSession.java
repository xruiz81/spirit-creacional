package com.spirit.medios.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ComercialSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ComercialSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ComercialIf addComercial(com.spirit.medios.entity.ComercialIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ComercialEJB value = new ComercialEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setCampanaId(model.getCampanaId());
      value.setDescripcion(model.getDescripcion());
      value.setDerechoprogramaId(model.getDerechoprogramaId());
      value.setVersion(model.getVersion());
      value.setDuracion(model.getDuracion());
      value.setEstado(model.getEstado());
      value.setProductoClienteId(model.getProductoClienteId());
      value.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en comercial.", e);
			throw new GenericBusinessException(
					"Error al insertar información en comercial.");
      }
     
      return getComercial(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveComercial(com.spirit.medios.entity.ComercialIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ComercialEJB data = new ComercialEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setCampanaId(model.getCampanaId());
      data.setDescripcion(model.getDescripcion());
      data.setDerechoprogramaId(model.getDerechoprogramaId());
      data.setVersion(model.getVersion());
      data.setDuracion(model.getDuracion());
      data.setEstado(model.getEstado());
      data.setProductoClienteId(model.getProductoClienteId());
      data.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en comercial.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en comercial.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteComercial(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ComercialEJB data = manager.find(ComercialEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en comercial.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en comercial.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ComercialIf getComercial(java.lang.Long id) {
      return (ComercialEJB)queryManagerLocal.find(ComercialEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getComercialList() {
	  return queryManagerLocal.singleClassList(ComercialEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getComercialList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ComercialEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getComercialListSize() {
      Query countQuery = manager.createQuery("select count(*) from ComercialEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByCampanaId(java.lang.Long campanaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaId", campanaId);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByDerechoprogramaId(java.lang.Long derechoprogramaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("derechoprogramaId", derechoprogramaId);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByVersion(java.lang.String version) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("version", version);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByDuracion(java.lang.String duracion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("duracion", duracion);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByProductoClienteId(java.lang.Long productoClienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoClienteId", productoClienteId);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaProductoVersionId", campanaProductoVersionId);
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ComercialIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findComercialByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ComercialEJB.class, aMap);      
    }

/////////////////
}
