package com.spirit.pos.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.pos.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PuntosTipoProductoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PuntosTipoProductoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.PuntosTipoProductoIf addPuntosTipoProducto(com.spirit.pos.entity.PuntosTipoProductoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PuntosTipoProductoEJB value = new PuntosTipoProductoEJB();
      try {
      value.setId(model.getId());
      value.setTipoProductoId(model.getTipoProductoId());
      value.setTarjetaTipoId(model.getTarjetaTipoId());
      value.setPuntosReferido(model.getPuntosReferido());
      value.setPuntosCompras(model.getPuntosCompras());
      value.setPorcentajeDineroReferido(model.getPorcentajeDineroReferido());
      value.setPorcentajeDineroCompras(model.getPorcentajeDineroCompras());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en puntosTipoProducto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en puntosTipoProducto.");
      }
     
      return getPuntosTipoProducto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePuntosTipoProducto(com.spirit.pos.entity.PuntosTipoProductoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PuntosTipoProductoEJB data = new PuntosTipoProductoEJB();
      data.setId(model.getId());
      data.setTipoProductoId(model.getTipoProductoId());
      data.setTarjetaTipoId(model.getTarjetaTipoId());
      data.setPuntosReferido(model.getPuntosReferido());
      data.setPuntosCompras(model.getPuntosCompras());
      data.setPorcentajeDineroReferido(model.getPorcentajeDineroReferido());
      data.setPorcentajeDineroCompras(model.getPorcentajeDineroCompras());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en puntosTipoProducto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en puntosTipoProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePuntosTipoProducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PuntosTipoProductoEJB data = manager.find(PuntosTipoProductoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en puntosTipoProducto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en puntosTipoProducto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.PuntosTipoProductoIf getPuntosTipoProducto(java.lang.Long id) {
      return (PuntosTipoProductoEJB)queryManagerLocal.find(PuntosTipoProductoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPuntosTipoProductoList() {
	  return queryManagerLocal.singleClassList(PuntosTipoProductoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPuntosTipoProductoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PuntosTipoProductoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPuntosTipoProductoListSize() {
      Query countQuery = manager.createQuery("select count(*) from PuntosTipoProductoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntosTipoProductoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PuntosTipoProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntosTipoProductoByTipoProductoId(java.lang.Long tipoProductoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoProductoId", tipoProductoId);
		return queryManagerLocal.singleClassQueryList(PuntosTipoProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntosTipoProductoByTarjetaTipoId(java.lang.Long tarjetaTipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarjetaTipoId", tarjetaTipoId);
		return queryManagerLocal.singleClassQueryList(PuntosTipoProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntosTipoProductoByPuntosReferido(java.math.BigDecimal puntosReferido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosReferido", puntosReferido);
		return queryManagerLocal.singleClassQueryList(PuntosTipoProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntosTipoProductoByPuntosCompras(java.math.BigDecimal puntosCompras) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosCompras", puntosCompras);
		return queryManagerLocal.singleClassQueryList(PuntosTipoProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntosTipoProductoByPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDineroReferido", porcentajeDineroReferido);
		return queryManagerLocal.singleClassQueryList(PuntosTipoProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntosTipoProductoByPorcentajeDineroCompras(java.math.BigDecimal porcentajeDineroCompras) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDineroCompras", porcentajeDineroCompras);
		return queryManagerLocal.singleClassQueryList(PuntosTipoProductoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PuntosTipoProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntosTipoProductoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PuntosTipoProductoEJB.class, aMap);      
    }

/////////////////
}
