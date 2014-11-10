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
import com.spirit.pos.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _VentasTrackSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_VentasTrackSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.VentasTrackIf addVentasTrack(com.spirit.pos.entity.VentasTrackIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      VentasTrackEJB value = new VentasTrackEJB();
      try {
      value.setId(model.getId());
      value.setValorTotal(model.getValorTotal());
      value.setCajasesionId(model.getCajasesionId());
      value.setFechaVenta(model.getFechaVenta());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ventasTrack.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ventasTrack.");
      }
     
      return getVentasTrack(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveVentasTrack(com.spirit.pos.entity.VentasTrackIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      VentasTrackEJB data = new VentasTrackEJB();
      data.setId(model.getId());
      data.setValorTotal(model.getValorTotal());
      data.setCajasesionId(model.getCajasesionId());
      data.setFechaVenta(model.getFechaVenta());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ventasTrack.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ventasTrack.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteVentasTrack(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      VentasTrackEJB data = manager.find(VentasTrackEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ventasTrack.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ventasTrack.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.CajasesionMovimientosIf addCajasesionMovimientos(com.spirit.pos.entity.CajasesionMovimientosIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CajasesionMovimientosEJB value = new CajasesionMovimientosEJB();
      try {
      value.setId(model.getId());
      value.setCajasesionId(model.getCajasesionId());
      value.setValor(model.getValor());
      value.setTipomovimiento(model.getTipomovimiento());
      value.setCuentaId(model.getCuentaId());
      value.setCajadestinoId(model.getCajadestinoId());
      value.setObservacion(model.getObservacion());
      value.setFecha(model.getFecha());
      value.setRevisado(model.getRevisado());
      value.setNumDoc(model.getNumDoc());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cajasesionMovimientos.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cajasesionMovimientos.");
      }
     
      return getCajasesionMovimientos(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCajasesionMovimientos(com.spirit.pos.entity.CajasesionMovimientosIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CajasesionMovimientosEJB data = new CajasesionMovimientosEJB();
      data.setId(model.getId());
      data.setCajasesionId(model.getCajasesionId());
      data.setValor(model.getValor());
      data.setTipomovimiento(model.getTipomovimiento());
      data.setCuentaId(model.getCuentaId());
      data.setCajadestinoId(model.getCajadestinoId());
      data.setObservacion(model.getObservacion());
      data.setFecha(model.getFecha());
      data.setRevisado(model.getRevisado());
      data.setNumDoc(model.getNumDoc());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cajasesionMovimientos.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cajasesionMovimientos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCajasesionMovimientos(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CajasesionMovimientosEJB data = manager.find(CajasesionMovimientosEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cajasesionMovimientos.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cajasesionMovimientos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.VentasTrackIf getVentasTrack(java.lang.Long id) {
      return (VentasTrackEJB)queryManagerLocal.find(VentasTrackEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getVentasTrackList() {
	  return queryManagerLocal.singleClassList(VentasTrackEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getVentasTrackList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(VentasTrackEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getVentasTrackListSize() {
      Query countQuery = manager.createQuery("select count(*) from VentasTrackEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.CajasesionMovimientosIf getCajasesionMovimientos(java.lang.Long id) {
      return (CajasesionMovimientosEJB)queryManagerLocal.find(CajasesionMovimientosEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCajasesionMovimientosList() {
	  return queryManagerLocal.singleClassList(CajasesionMovimientosEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCajasesionMovimientosList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CajasesionMovimientosEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCajasesionMovimientosListSize() {
      Query countQuery = manager.createQuery("select count(*) from CajasesionMovimientosEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasTrackById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(VentasTrackEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasTrackByValorTotal(java.math.BigDecimal valorTotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTotal", valorTotal);
		return queryManagerLocal.singleClassQueryList(VentasTrackEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasTrackByCajasesionId(java.lang.Long cajasesionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cajasesionId", cajasesionId);
		return queryManagerLocal.singleClassQueryList(VentasTrackEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasTrackByFechaVenta(java.sql.Timestamp fechaVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaVenta", fechaVenta);
		return queryManagerLocal.singleClassQueryList(VentasTrackEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of VentasTrackIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasTrackByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(VentasTrackEJB.class, aMap);      
    }

/////////////////


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByCajasesionId(java.lang.Long cajasesionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cajasesionId", cajasesionId);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByTipomovimiento(java.lang.String tipomovimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipomovimiento", tipomovimiento);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByCajadestinoId(java.lang.Long cajadestinoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cajadestinoId", cajadestinoId);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByRevisado(java.lang.String revisado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("revisado", revisado);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByNumDoc(java.lang.String numDoc) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numDoc", numDoc);
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CajasesionMovimientosIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajasesionMovimientosByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CajasesionMovimientosEJB.class, aMap);      
    }

/////////////////
}
