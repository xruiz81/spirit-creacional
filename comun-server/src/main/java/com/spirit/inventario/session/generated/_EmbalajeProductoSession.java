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
public abstract class _EmbalajeProductoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmbalajeProductoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.EmbalajeProductoIf addEmbalajeProducto(com.spirit.inventario.entity.EmbalajeProductoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmbalajeProductoEJB value = new EmbalajeProductoEJB();
      try {
      value.setId(model.getId());
      value.setProductoId(model.getProductoId());
      value.setEmbalajeId(model.getEmbalajeId());
      value.setCantidad(model.getCantidad());
      value.setAreaCubica(model.getAreaCubica());
      value.setTipoManejo(model.getTipoManejo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en embalajeProducto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en embalajeProducto.");
      }
     
      return getEmbalajeProducto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmbalajeProducto(com.spirit.inventario.entity.EmbalajeProductoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmbalajeProductoEJB data = new EmbalajeProductoEJB();
      data.setId(model.getId());
      data.setProductoId(model.getProductoId());
      data.setEmbalajeId(model.getEmbalajeId());
      data.setCantidad(model.getCantidad());
      data.setAreaCubica(model.getAreaCubica());
      data.setTipoManejo(model.getTipoManejo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en embalajeProducto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en embalajeProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmbalajeProducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmbalajeProductoEJB data = manager.find(EmbalajeProductoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en embalajeProducto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en embalajeProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.EmbalajeProductoIf getEmbalajeProducto(java.lang.Long id) {
      return (EmbalajeProductoEJB)queryManagerLocal.find(EmbalajeProductoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmbalajeProductoList() {
	  return queryManagerLocal.singleClassList(EmbalajeProductoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmbalajeProductoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmbalajeProductoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmbalajeProductoListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmbalajeProductoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmbalajeProductoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmbalajeProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmbalajeProductoByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(EmbalajeProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmbalajeProductoByEmbalajeId(java.lang.Long embalajeId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("embalajeId", embalajeId);
		return queryManagerLocal.singleClassQueryList(EmbalajeProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmbalajeProductoByCantidad(java.math.BigDecimal cantidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantidad", cantidad);
		return queryManagerLocal.singleClassQueryList(EmbalajeProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmbalajeProductoByAreaCubica(java.math.BigDecimal areaCubica) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("areaCubica", areaCubica);
		return queryManagerLocal.singleClassQueryList(EmbalajeProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmbalajeProductoByTipoManejo(java.lang.String tipoManejo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoManejo", tipoManejo);
		return queryManagerLocal.singleClassQueryList(EmbalajeProductoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmbalajeProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmbalajeProductoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmbalajeProductoEJB.class, aMap);      
    }

/////////////////
}
