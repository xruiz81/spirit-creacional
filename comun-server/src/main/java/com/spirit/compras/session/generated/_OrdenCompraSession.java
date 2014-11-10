package com.spirit.compras.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.compras.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _OrdenCompraSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OrdenCompraSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.OrdenCompraIf addOrdenCompra(com.spirit.compras.entity.OrdenCompraIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OrdenCompraEJB value = new OrdenCompraEJB();
      try {
      value.setId(model.getId());
      value.setOficinaId(model.getOficinaId());
      value.setTipodocumentoId(model.getTipodocumentoId());
      value.setCodigo(model.getCodigo());
      value.setProveedorId(model.getProveedorId());
      value.setMonedaId(model.getMonedaId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setUsuarioId(model.getUsuarioId());
      value.setBodegaId(model.getBodegaId());
      value.setLocalimportada(model.getLocalimportada());
      value.setFecha(model.getFecha());
      value.setFechaRecepcion(model.getFechaRecepcion());
      value.setFechaVencimiento(model.getFechaVencimiento());
      value.setEstado(model.getEstado());
      value.setObservacion(model.getObservacion());
      value.setValor(model.getValor());
      value.setDescuentoAgenciaCompra(model.getDescuentoAgenciaCompra());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroImpuesto(model.getOtroImpuesto());
      value.setSolicitudcompraId(model.getSolicitudcompraId());
      value.setEstadoBpm(model.getEstadoBpm());
      value.setPorcentajeDescuentosVariosCompra(model.getPorcentajeDescuentosVariosCompra());
      value.setPorcentajeDescuentosVariosVenta(model.getPorcentajeDescuentosVariosVenta());
      value.setPorcentajeDescuentoEspecial(model.getPorcentajeDescuentoEspecial());
      value.setRevision(model.getRevision());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ordenCompra.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ordenCompra.");
      }
     
      return getOrdenCompra(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOrdenCompra(com.spirit.compras.entity.OrdenCompraIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OrdenCompraEJB data = new OrdenCompraEJB();
      data.setId(model.getId());
      data.setOficinaId(model.getOficinaId());
      data.setTipodocumentoId(model.getTipodocumentoId());
      data.setCodigo(model.getCodigo());
      data.setProveedorId(model.getProveedorId());
      data.setMonedaId(model.getMonedaId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setUsuarioId(model.getUsuarioId());
      data.setBodegaId(model.getBodegaId());
      data.setLocalimportada(model.getLocalimportada());
      data.setFecha(model.getFecha());
      data.setFechaRecepcion(model.getFechaRecepcion());
      data.setFechaVencimiento(model.getFechaVencimiento());
      data.setEstado(model.getEstado());
      data.setObservacion(model.getObservacion());
      data.setValor(model.getValor());
      data.setDescuentoAgenciaCompra(model.getDescuentoAgenciaCompra());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroImpuesto(model.getOtroImpuesto());
      data.setSolicitudcompraId(model.getSolicitudcompraId());
      data.setEstadoBpm(model.getEstadoBpm());
      data.setPorcentajeDescuentosVariosCompra(model.getPorcentajeDescuentosVariosCompra());
      data.setPorcentajeDescuentosVariosVenta(model.getPorcentajeDescuentosVariosVenta());
      data.setPorcentajeDescuentoEspecial(model.getPorcentajeDescuentoEspecial());
      data.setRevision(model.getRevision());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ordenCompra.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ordenCompra.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOrdenCompra(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OrdenCompraEJB data = manager.find(OrdenCompraEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ordenCompra.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ordenCompra.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.OrdenCompraIf getOrdenCompra(java.lang.Long id) {
      return (OrdenCompraEJB)queryManagerLocal.find(OrdenCompraEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenCompraList() {
	  return queryManagerLocal.singleClassList(OrdenCompraEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenCompraList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OrdenCompraEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOrdenCompraListSize() {
      Query countQuery = manager.createQuery("select count(*) from OrdenCompraEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByProveedorId(java.lang.Long proveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorId", proveedorId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByMonedaId(java.lang.Long monedaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaId", monedaId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByBodegaId(java.lang.Long bodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaId", bodegaId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByLocalimportada(java.lang.String localimportada) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("localimportada", localimportada);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByFechaRecepcion(java.sql.Date fechaRecepcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaRecepcion", fechaRecepcion);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByFechaVencimiento(java.sql.Date fechaVencimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaVencimiento", fechaVencimiento);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByDescuentoAgenciaCompra(java.math.BigDecimal descuentoAgenciaCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoAgenciaCompra", descuentoAgenciaCompra);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroImpuesto", otroImpuesto);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraBySolicitudcompraId(java.lang.Long solicitudcompraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("solicitudcompraId", solicitudcompraId);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByEstadoBpm(java.lang.String estadoBpm) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estadoBpm", estadoBpm);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentosVariosCompra", porcentajeDescuentosVariosCompra);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentosVariosVenta", porcentajeDescuentosVariosVenta);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoEspecial", porcentajeDescuentoEspecial);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByRevision(java.lang.String revision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("revision", revision);
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OrdenCompraIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenCompraByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OrdenCompraEJB.class, aMap);      
    }

/////////////////
}
