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
public abstract class _OrdenMedioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OrdenMedioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenMedioIf addOrdenMedio(com.spirit.medios.entity.OrdenMedioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OrdenMedioEJB value = new OrdenMedioEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setClienteOficinaId(model.getClienteOficinaId());
      value.setPlanMedioId(model.getPlanMedioId());
      value.setProveedorId(model.getProveedorId());
      value.setUsuarioId(model.getUsuarioId());
      value.setOficinaId(model.getOficinaId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setEstado(model.getEstado());
      value.setValorTotal(model.getValorTotal());
      value.setProductoProveedorId(model.getProductoProveedorId());
      value.setFechaOrden(model.getFechaOrden());
      value.setValorDescuento(model.getValorDescuento());
      value.setValorIva(model.getValorIva());
      value.setValorSubtotal(model.getValorSubtotal());
      value.setPorcentajeDescuentoVenta(model.getPorcentajeDescuentoVenta());
      value.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      value.setValorDescuentoVenta(model.getValorDescuentoVenta());
      value.setValorComisionAgencia(model.getValorComisionAgencia());
      value.setPorcentajeCanje(model.getPorcentajeCanje());
      value.setProductoClienteId(model.getProductoClienteId());
      value.setOrdenMedioTipo(model.getOrdenMedioTipo());
      value.setObservacion(model.getObservacion());
      value.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
      value.setPorcentajeNegociacionComision(model.getPorcentajeNegociacionComision());
      value.setComisionPura(model.getComisionPura());
      value.setPorcentajeBonificacionCompra(model.getPorcentajeBonificacionCompra());
      value.setPorcentajeBonificacionVenta(model.getPorcentajeBonificacionVenta());
      value.setNumeroOrdenAgrupacion(model.getNumeroOrdenAgrupacion());
      value.setRevision(model.getRevision());
      value.setPorcentajeComisionAdicional(model.getPorcentajeComisionAdicional());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ordenMedio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ordenMedio.");
      }
     
      return getOrdenMedio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOrdenMedio(com.spirit.medios.entity.OrdenMedioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OrdenMedioEJB data = new OrdenMedioEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setClienteOficinaId(model.getClienteOficinaId());
      data.setPlanMedioId(model.getPlanMedioId());
      data.setProveedorId(model.getProveedorId());
      data.setUsuarioId(model.getUsuarioId());
      data.setOficinaId(model.getOficinaId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setEstado(model.getEstado());
      data.setValorTotal(model.getValorTotal());
      data.setProductoProveedorId(model.getProductoProveedorId());
      data.setFechaOrden(model.getFechaOrden());
      data.setValorDescuento(model.getValorDescuento());
      data.setValorIva(model.getValorIva());
      data.setValorSubtotal(model.getValorSubtotal());
      data.setPorcentajeDescuentoVenta(model.getPorcentajeDescuentoVenta());
      data.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      data.setValorDescuentoVenta(model.getValorDescuentoVenta());
      data.setValorComisionAgencia(model.getValorComisionAgencia());
      data.setPorcentajeCanje(model.getPorcentajeCanje());
      data.setProductoClienteId(model.getProductoClienteId());
      data.setOrdenMedioTipo(model.getOrdenMedioTipo());
      data.setObservacion(model.getObservacion());
      data.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
      data.setPorcentajeNegociacionComision(model.getPorcentajeNegociacionComision());
      data.setComisionPura(model.getComisionPura());
      data.setPorcentajeBonificacionCompra(model.getPorcentajeBonificacionCompra());
      data.setPorcentajeBonificacionVenta(model.getPorcentajeBonificacionVenta());
      data.setNumeroOrdenAgrupacion(model.getNumeroOrdenAgrupacion());
      data.setRevision(model.getRevision());
      data.setPorcentajeComisionAdicional(model.getPorcentajeComisionAdicional());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ordenMedio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ordenMedio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOrdenMedio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OrdenMedioEJB data = manager.find(OrdenMedioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ordenMedio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ordenMedio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenMedioIf getOrdenMedio(java.lang.Long id) {
      return (OrdenMedioEJB)queryManagerLocal.find(OrdenMedioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenMedioList() {
	  return queryManagerLocal.singleClassList(OrdenMedioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenMedioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OrdenMedioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOrdenMedioListSize() {
      Query countQuery = manager.createQuery("select count(*) from OrdenMedioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByClienteOficinaId(java.lang.Long clienteOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteOficinaId", clienteOficinaId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByPlanMedioId(java.lang.Long planMedioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioId", planMedioId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByProveedorId(java.lang.Long proveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorId", proveedorId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByValorTotal(java.math.BigDecimal valorTotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTotal", valorTotal);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByProductoProveedorId(java.lang.Long productoProveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoProveedorId", productoProveedorId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByFechaOrden(java.sql.Timestamp fechaOrden) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaOrden", fechaOrden);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByValorDescuento(java.math.BigDecimal valorDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuento", valorDescuento);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByValorIva(java.math.BigDecimal valorIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorIva", valorIva);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByValorSubtotal(java.math.BigDecimal valorSubtotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorSubtotal", valorSubtotal);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoVenta", porcentajeDescuentoVenta);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionAgencia", porcentajeComisionAgencia);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuentoVenta", valorDescuentoVenta);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorComisionAgencia", valorComisionAgencia);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByPorcentajeCanje(java.math.BigDecimal porcentajeCanje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeCanje", porcentajeCanje);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByProductoClienteId(java.lang.Long productoClienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoClienteId", productoClienteId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByOrdenMedioTipo(java.lang.String ordenMedioTipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenMedioTipo", ordenMedioTipo);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaProductoVersionId", campanaProductoVersionId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByPorcentajeNegociacionComision(java.math.BigDecimal porcentajeNegociacionComision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeNegociacionComision", porcentajeNegociacionComision);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByComisionPura(java.lang.String comisionPura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comisionPura", comisionPura);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByPorcentajeBonificacionCompra(java.math.BigDecimal porcentajeBonificacionCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeBonificacionCompra", porcentajeBonificacionCompra);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByPorcentajeBonificacionVenta(java.math.BigDecimal porcentajeBonificacionVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeBonificacionVenta", porcentajeBonificacionVenta);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numeroOrdenAgrupacion", numeroOrdenAgrupacion);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByRevision(java.lang.String revision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("revision", revision);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionAdicional", porcentajeComisionAdicional);
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OrdenMedioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OrdenMedioEJB.class, aMap);      
    }

/////////////////
}
