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
public abstract class _FacturaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_FacturaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.FacturaIf addFactura(com.spirit.facturacion.entity.FacturaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      FacturaEJB value = new FacturaEJB();
      try {
      value.setId(model.getId());
      value.setOficinaId(model.getOficinaId());
      value.setTipodocumentoId(model.getTipodocumentoId());
      value.setNumero(model.getNumero());
      value.setClienteoficinaId(model.getClienteoficinaId());
      value.setTipoidentificacionId(model.getTipoidentificacionId());
      value.setIdentificacion(model.getIdentificacion());
      value.setFormapagoId(model.getFormapagoId());
      value.setMonedaId(model.getMonedaId());
      value.setPuntoImpresionId(model.getPuntoImpresionId());
      value.setOrigendocumentoId(model.getOrigendocumentoId());
      value.setVendedorId(model.getVendedorId());
      value.setBodegaId(model.getBodegaId());
      value.setPedidoId(model.getPedidoId());
      value.setListaPrecioId(model.getListaPrecioId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setFechaFactura(model.getFechaFactura());
      value.setFechaVencimiento(model.getFechaVencimiento());
      value.setUsuarioId(model.getUsuarioId());
      value.setContacto(model.getContacto());
      value.setDireccion(model.getDireccion());
      value.setTelefono(model.getTelefono());
      value.setPreimpresoNumero(model.getPreimpresoNumero());
      value.setValor(model.getValor());
      value.setDescuento(model.getDescuento());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroImpuesto(model.getOtroImpuesto());
      value.setCosto(model.getCosto());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      value.setFacturaaplId(model.getFacturaaplId());
      value.setDescuentoGlobal(model.getDescuentoGlobal());
      value.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      value.setEquipoId(model.getEquipoId());
      value.setClienteNegociacionId(model.getClienteNegociacionId());
      value.setTipoNegociacion(model.getTipoNegociacion());
      value.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
      value.setPorcentajeDescuentoNegociacion(model.getPorcentajeDescuentoNegociacion());
      value.setAutorizacionSap(model.getAutorizacionSap());
      value.setDescuentosVarios(model.getDescuentosVarios());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en factura.", e);
			throw new GenericBusinessException(
					"Error al insertar información en factura.");
      }
     
      return getFactura(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveFactura(com.spirit.facturacion.entity.FacturaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      FacturaEJB data = new FacturaEJB();
      data.setId(model.getId());
      data.setOficinaId(model.getOficinaId());
      data.setTipodocumentoId(model.getTipodocumentoId());
      data.setNumero(model.getNumero());
      data.setClienteoficinaId(model.getClienteoficinaId());
      data.setTipoidentificacionId(model.getTipoidentificacionId());
      data.setIdentificacion(model.getIdentificacion());
      data.setFormapagoId(model.getFormapagoId());
      data.setMonedaId(model.getMonedaId());
      data.setPuntoImpresionId(model.getPuntoImpresionId());
      data.setOrigendocumentoId(model.getOrigendocumentoId());
      data.setVendedorId(model.getVendedorId());
      data.setBodegaId(model.getBodegaId());
      data.setPedidoId(model.getPedidoId());
      data.setListaPrecioId(model.getListaPrecioId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setFechaFactura(model.getFechaFactura());
      data.setFechaVencimiento(model.getFechaVencimiento());
      data.setUsuarioId(model.getUsuarioId());
      data.setContacto(model.getContacto());
      data.setDireccion(model.getDireccion());
      data.setTelefono(model.getTelefono());
      data.setPreimpresoNumero(model.getPreimpresoNumero());
      data.setValor(model.getValor());
      data.setDescuento(model.getDescuento());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroImpuesto(model.getOtroImpuesto());
      data.setCosto(model.getCosto());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
      data.setFacturaaplId(model.getFacturaaplId());
      data.setDescuentoGlobal(model.getDescuentoGlobal());
      data.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      data.setEquipoId(model.getEquipoId());
      data.setClienteNegociacionId(model.getClienteNegociacionId());
      data.setTipoNegociacion(model.getTipoNegociacion());
      data.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
      data.setPorcentajeDescuentoNegociacion(model.getPorcentajeDescuentoNegociacion());
      data.setAutorizacionSap(model.getAutorizacionSap());
      data.setDescuentosVarios(model.getDescuentosVarios());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en factura.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en factura.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteFactura(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      FacturaEJB data = manager.find(FacturaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en factura.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en factura.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.FacturaIf getFactura(java.lang.Long id) {
      return (FacturaEJB)queryManagerLocal.find(FacturaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFacturaList() {
	  return queryManagerLocal.singleClassList(FacturaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFacturaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(FacturaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getFacturaListSize() {
      Query countQuery = manager.createQuery("select count(*) from FacturaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByNumero(java.math.BigDecimal numero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numero", numero);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByClienteoficinaId(java.lang.Long clienteoficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteoficinaId", clienteoficinaId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByTipoidentificacionId(java.lang.Long tipoidentificacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoidentificacionId", tipoidentificacionId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByIdentificacion(java.lang.String identificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("identificacion", identificacion);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByFormapagoId(java.lang.Long formapagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formapagoId", formapagoId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByMonedaId(java.lang.Long monedaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaId", monedaId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByPuntoImpresionId(java.lang.Long puntoImpresionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntoImpresionId", puntoImpresionId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByOrigendocumentoId(java.lang.Long origendocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("origendocumentoId", origendocumentoId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByVendedorId(java.lang.Long vendedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("vendedorId", vendedorId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByBodegaId(java.lang.Long bodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaId", bodegaId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByPedidoId(java.lang.Long pedidoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pedidoId", pedidoId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByListaPrecioId(java.lang.Long listaPrecioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("listaPrecioId", listaPrecioId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByFechaFactura(java.sql.Timestamp fechaFactura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFactura", fechaFactura);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaVencimiento", fechaVencimiento);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByContacto(java.lang.String contacto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contacto", contacto);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByDireccion(java.lang.String direccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccion", direccion);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByTelefono(java.lang.String telefono) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefono", telefono);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByPreimpresoNumero(java.lang.String preimpresoNumero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpresoNumero", preimpresoNumero);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByDescuento(java.math.BigDecimal descuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuento", descuento);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroImpuesto", otroImpuesto);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByCosto(java.math.BigDecimal costo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("costo", costo);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByFacturaaplId(java.lang.Long facturaaplId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("facturaaplId", facturaaplId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByDescuentoGlobal(java.math.BigDecimal descuentoGlobal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoGlobal", descuentoGlobal);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionAgencia", porcentajeComisionAgencia);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByEquipoId(java.lang.Long equipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("equipoId", equipoId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByClienteNegociacionId(java.lang.Long clienteNegociacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteNegociacionId", clienteNegociacionId);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByTipoNegociacion(java.lang.String tipoNegociacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoNegociacion", tipoNegociacion);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeNegociacionDirecta", porcentajeNegociacionDirecta);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByPorcentajeDescuentoNegociacion(java.math.BigDecimal porcentajeDescuentoNegociacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoNegociacion", porcentajeDescuentoNegociacion);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByAutorizacionSap(java.lang.String autorizacionSap) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacionSap", autorizacionSap);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByDescuentosVarios(java.math.BigDecimal descuentosVarios) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentosVarios", descuentosVarios);
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of FacturaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(FacturaEJB.class, aMap);      
    }

/////////////////
}
