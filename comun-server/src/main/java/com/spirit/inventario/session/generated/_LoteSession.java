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
public abstract class _LoteSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_LoteSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.LoteIf addLote(com.spirit.inventario.entity.LoteIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      LoteEJB value = new LoteEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setProductoId(model.getProductoId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setFechaElaboracion(model.getFechaElaboracion());
      value.setFechaVencimiento(model.getFechaVencimiento());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en lote.", e);
			throw new GenericBusinessException(
					"Error al insertar información en lote.");
      }
     
      return getLote(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveLote(com.spirit.inventario.entity.LoteIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      LoteEJB data = new LoteEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setProductoId(model.getProductoId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setFechaElaboracion(model.getFechaElaboracion());
      data.setFechaVencimiento(model.getFechaVencimiento());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en lote.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en lote.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteLote(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      LoteEJB data = manager.find(LoteEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en lote.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en lote.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.LoteIf getLote(java.lang.Long id) {
      return (LoteEJB)queryManagerLocal.find(LoteEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLoteList() {
	  return queryManagerLocal.singleClassList(LoteEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLoteList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(LoteEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getLoteListSize() {
      Query countQuery = manager.createQuery("select count(*) from LoteEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLoteById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(LoteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLoteByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(LoteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLoteByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(LoteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLoteByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(LoteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLoteByFechaElaboracion(java.sql.Timestamp fechaElaboracion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaElaboracion", fechaElaboracion);
		return queryManagerLocal.singleClassQueryList(LoteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLoteByFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaVencimiento", fechaVencimiento);
		return queryManagerLocal.singleClassQueryList(LoteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLoteByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(LoteEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of LoteIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLoteByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(LoteEJB.class, aMap);      
    }

/////////////////
}
