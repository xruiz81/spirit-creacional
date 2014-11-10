package com.spirit.cartera.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.cartera.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _AsociacionTransaccionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_AsociacionTransaccionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.AsociacionTransaccionIf addAsociacionTransaccion(com.spirit.cartera.entity.AsociacionTransaccionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      AsociacionTransaccionEJB value = new AsociacionTransaccionEJB();
      try {
      value.setId(model.getId());
      value.setFechaEmision(model.getFechaEmision());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setTablaOrigen(model.getTablaOrigen());
      value.setTipoDocumentoOrigenId(model.getTipoDocumentoOrigenId());
      value.setTransaccionOrigenId(model.getTransaccionOrigenId());
      value.setTablaDestino(model.getTablaDestino());
      value.setTipoDocumentoDestinoId(model.getTipoDocumentoDestinoId());
      value.setTransaccionDestinoId(model.getTransaccionDestinoId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en asociacionTransaccion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en asociacionTransaccion.");
      }
     
      return getAsociacionTransaccion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveAsociacionTransaccion(com.spirit.cartera.entity.AsociacionTransaccionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      AsociacionTransaccionEJB data = new AsociacionTransaccionEJB();
      data.setId(model.getId());
      data.setFechaEmision(model.getFechaEmision());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setTablaOrigen(model.getTablaOrigen());
      data.setTipoDocumentoOrigenId(model.getTipoDocumentoOrigenId());
      data.setTransaccionOrigenId(model.getTransaccionOrigenId());
      data.setTablaDestino(model.getTablaDestino());
      data.setTipoDocumentoDestinoId(model.getTipoDocumentoDestinoId());
      data.setTransaccionDestinoId(model.getTransaccionDestinoId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en asociacionTransaccion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en asociacionTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteAsociacionTransaccion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      AsociacionTransaccionEJB data = manager.find(AsociacionTransaccionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en asociacionTransaccion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en asociacionTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.AsociacionTransaccionIf getAsociacionTransaccion(java.lang.Long id) {
      return (AsociacionTransaccionEJB)queryManagerLocal.find(AsociacionTransaccionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsociacionTransaccionList() {
	  return queryManagerLocal.singleClassList(AsociacionTransaccionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getAsociacionTransaccionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(AsociacionTransaccionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getAsociacionTransaccionListSize() {
      Query countQuery = manager.createQuery("select count(*) from AsociacionTransaccionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionByFechaEmision(java.sql.Timestamp fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionByTablaOrigen(java.lang.String tablaOrigen) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tablaOrigen", tablaOrigen);
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionByTipoDocumentoOrigenId(java.lang.Long tipoDocumentoOrigenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoOrigenId", tipoDocumentoOrigenId);
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionByTransaccionOrigenId(java.lang.Long transaccionOrigenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transaccionOrigenId", transaccionOrigenId);
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionByTablaDestino(java.lang.String tablaDestino) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tablaDestino", tablaDestino);
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionByTipoDocumentoDestinoId(java.lang.Long tipoDocumentoDestinoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoDestinoId", tipoDocumentoDestinoId);
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionByTransaccionDestinoId(java.lang.Long transaccionDestinoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transaccionDestinoId", transaccionDestinoId);
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of AsociacionTransaccionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findAsociacionTransaccionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(AsociacionTransaccionEJB.class, aMap);      
    }

/////////////////
}
