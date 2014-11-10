package com.spirit.facturacion.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.facturacion.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PrecioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PrecioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PrecioIf addPrecio(com.spirit.facturacion.entity.PrecioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PrecioEJB value = new PrecioEJB();
      try {
      value.setId(model.getId());
      value.setListaprecioId(model.getListaprecioId());
      value.setProductoId(model.getProductoId());
      value.setPrecio(model.getPrecio());
      value.setCambiarPrecio(model.getCambiarPrecio());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en precio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en precio.");
      }
     
      return getPrecio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePrecio(com.spirit.facturacion.entity.PrecioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PrecioEJB data = new PrecioEJB();
      data.setId(model.getId());
      data.setListaprecioId(model.getListaprecioId());
      data.setProductoId(model.getProductoId());
      data.setPrecio(model.getPrecio());
      data.setCambiarPrecio(model.getCambiarPrecio());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en precio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en precio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePrecio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PrecioEJB data = manager.find(PrecioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en precio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en precio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PrecioIf getPrecio(java.lang.Long id) {
      return (PrecioEJB)queryManagerLocal.find(PrecioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrecioList() {
	  return queryManagerLocal.singleClassList(PrecioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrecioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PrecioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPrecioListSize() {
      Query countQuery = manager.createQuery("select count(*) from PrecioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioByListaprecioId(java.lang.Long listaprecioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("listaprecioId", listaprecioId);
		return queryManagerLocal.singleClassQueryList(PrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(PrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioByPrecio(java.math.BigDecimal precio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precio", precio);
		return queryManagerLocal.singleClassQueryList(PrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioByCambiarPrecio(java.lang.String cambiarPrecio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cambiarPrecio", cambiarPrecio);
		return queryManagerLocal.singleClassQueryList(PrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PrecioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PrecioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrecioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PrecioEJB.class, aMap);      
    }

/////////////////
}
