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
public abstract class _PresupuestosSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PresupuestosSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestosIf addPresupuestos(com.spirit.medios.entity.PresupuestosIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PresupuestosEJB value = new PresupuestosEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setTipo(model.getTipo());
      value.setClienteOficinaId(model.getClienteOficinaId());
      value.setConcepto(model.getConcepto());
      value.setFechaAprobacion(model.getFechaAprobacion());
      value.setSubtotal(model.getSubtotal());
      value.setDescuento(model.getDescuento());
      value.setComision(model.getComision());
      value.setIva(model.getIva());
      value.setTotal(model.getTotal());
      value.setEstado(model.getEstado());
      value.setPrepago(model.getPrepago());
      value.setRevision(model.getRevision());
      value.setFecha(model.getFecha());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en presupuestos.", e);
			throw new GenericBusinessException(
					"Error al insertar información en presupuestos.");
      }
     
      return getPresupuestos(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePresupuestos(com.spirit.medios.entity.PresupuestosIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PresupuestosEJB data = new PresupuestosEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setTipo(model.getTipo());
      data.setClienteOficinaId(model.getClienteOficinaId());
      data.setConcepto(model.getConcepto());
      data.setFechaAprobacion(model.getFechaAprobacion());
      data.setSubtotal(model.getSubtotal());
      data.setDescuento(model.getDescuento());
      data.setComision(model.getComision());
      data.setIva(model.getIva());
      data.setTotal(model.getTotal());
      data.setEstado(model.getEstado());
      data.setPrepago(model.getPrepago());
      data.setRevision(model.getRevision());
      data.setFecha(model.getFecha());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en presupuestos.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en presupuestos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePresupuestos(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PresupuestosEJB data = manager.find(PresupuestosEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en presupuestos.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en presupuestos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestosIf getPresupuestos(java.lang.Long id) {
      return (PresupuestosEJB)queryManagerLocal.find(PresupuestosEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestosList() {
	  return queryManagerLocal.singleClassList(PresupuestosEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestosList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PresupuestosEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPresupuestosListSize() {
      Query countQuery = manager.createQuery("select count(*) from PresupuestosEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByTipo(java.lang.String tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByClienteOficinaId(java.lang.Long clienteOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteOficinaId", clienteOficinaId);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByConcepto(java.lang.String concepto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("concepto", concepto);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByFechaAprobacion(java.sql.Timestamp fechaAprobacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaAprobacion", fechaAprobacion);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosBySubtotal(java.math.BigDecimal subtotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("subtotal", subtotal);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByDescuento(java.math.BigDecimal descuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuento", descuento);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByComision(java.math.BigDecimal comision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comision", comision);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByTotal(java.math.BigDecimal total) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("total", total);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByPrepago(java.lang.String prepago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("prepago", prepago);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByRevision(java.lang.String revision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("revision", revision);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PresupuestosIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestosByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PresupuestosEJB.class, aMap);      
    }

/////////////////
}
