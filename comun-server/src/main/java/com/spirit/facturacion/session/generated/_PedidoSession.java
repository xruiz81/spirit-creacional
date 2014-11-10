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
public abstract class _PedidoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PedidoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoIf addPedido(com.spirit.facturacion.entity.PedidoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PedidoEJB value = new PedidoEJB();
      try {
      value.setId(model.getId());
      value.setOficinaId(model.getOficinaId());
      value.setTipodocumentoId(model.getTipodocumentoId());
      value.setCodigo(model.getCodigo());
      value.setClienteoficinaId(model.getClienteoficinaId());
      value.setTipoidentificacionId(model.getTipoidentificacionId());
      value.setIdentificacion(model.getIdentificacion());
      value.setFormapagoId(model.getFormapagoId());
      value.setMonedaId(model.getMonedaId());
      value.setPuntoimpresionId(model.getPuntoimpresionId());
      value.setOrigendocumentoId(model.getOrigendocumentoId());
      value.setVendedorId(model.getVendedorId());
      value.setBodegaId(model.getBodegaId());
      value.setListaprecioId(model.getListaprecioId());
      value.setFechaPedido(model.getFechaPedido());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setUsuarioId(model.getUsuarioId());
      value.setContacto(model.getContacto());
      value.setDireccion(model.getDireccion());
      value.setTelefono(model.getTelefono());
      value.setTiporeferencia(model.getTiporeferencia());
      value.setReferencia(model.getReferencia());
      value.setDiasvalidez(model.getDiasvalidez());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      value.setPedidoaplId(model.getPedidoaplId());
      value.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      value.setTipopagoId(model.getTipopagoId());
      value.setEquipoId(model.getEquipoId());
      value.setClienteNegociacionId(model.getClienteNegociacionId());
      value.setTipoNegociacion(model.getTipoNegociacion());
      value.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
      value.setPorcentajeDescuentoNegociacion(model.getPorcentajeDescuentoNegociacion());
      value.setAutorizacionSap(model.getAutorizacionSap());
      value.setFechaVencimiento(model.getFechaVencimiento());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en pedido.", e);
			throw new GenericBusinessException(
					"Error al insertar información en pedido.");
      }
     
      return getPedido(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePedido(com.spirit.facturacion.entity.PedidoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PedidoEJB data = new PedidoEJB();
      data.setId(model.getId());
      data.setOficinaId(model.getOficinaId());
      data.setTipodocumentoId(model.getTipodocumentoId());
      data.setCodigo(model.getCodigo());
      data.setClienteoficinaId(model.getClienteoficinaId());
      data.setTipoidentificacionId(model.getTipoidentificacionId());
      data.setIdentificacion(model.getIdentificacion());
      data.setFormapagoId(model.getFormapagoId());
      data.setMonedaId(model.getMonedaId());
      data.setPuntoimpresionId(model.getPuntoimpresionId());
      data.setOrigendocumentoId(model.getOrigendocumentoId());
      data.setVendedorId(model.getVendedorId());
      data.setBodegaId(model.getBodegaId());
      data.setListaprecioId(model.getListaprecioId());
      data.setFechaPedido(model.getFechaPedido());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setUsuarioId(model.getUsuarioId());
      data.setContacto(model.getContacto());
      data.setDireccion(model.getDireccion());
      data.setTelefono(model.getTelefono());
      data.setTiporeferencia(model.getTiporeferencia());
      data.setReferencia(model.getReferencia());
      data.setDiasvalidez(model.getDiasvalidez());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
      data.setPedidoaplId(model.getPedidoaplId());
      data.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      data.setTipopagoId(model.getTipopagoId());
      data.setEquipoId(model.getEquipoId());
      data.setClienteNegociacionId(model.getClienteNegociacionId());
      data.setTipoNegociacion(model.getTipoNegociacion());
      data.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
      data.setPorcentajeDescuentoNegociacion(model.getPorcentajeDescuentoNegociacion());
      data.setAutorizacionSap(model.getAutorizacionSap());
      data.setFechaVencimiento(model.getFechaVencimiento());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en pedido.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en pedido.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePedido(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PedidoEJB data = manager.find(PedidoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en pedido.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en pedido.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoIf getPedido(java.lang.Long id) {
      return (PedidoEJB)queryManagerLocal.find(PedidoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoList() {
	  return queryManagerLocal.singleClassList(PedidoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PedidoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPedidoListSize() {
      Query countQuery = manager.createQuery("select count(*) from PedidoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByClienteoficinaId(java.lang.Long clienteoficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteoficinaId", clienteoficinaId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByTipoidentificacionId(java.lang.Long tipoidentificacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoidentificacionId", tipoidentificacionId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByIdentificacion(java.lang.String identificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("identificacion", identificacion);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByFormapagoId(java.lang.Long formapagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formapagoId", formapagoId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByMonedaId(java.lang.Long monedaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaId", monedaId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByPuntoimpresionId(java.lang.Long puntoimpresionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntoimpresionId", puntoimpresionId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByOrigendocumentoId(java.lang.Long origendocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("origendocumentoId", origendocumentoId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByVendedorId(java.lang.Long vendedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("vendedorId", vendedorId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByBodegaId(java.lang.Long bodegaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bodegaId", bodegaId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByListaprecioId(java.lang.Long listaprecioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("listaprecioId", listaprecioId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByFechaPedido(java.sql.Timestamp fechaPedido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaPedido", fechaPedido);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByContacto(java.lang.String contacto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contacto", contacto);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByDireccion(java.lang.String direccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccion", direccion);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByTelefono(java.lang.String telefono) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefono", telefono);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByTiporeferencia(java.lang.String tiporeferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiporeferencia", tiporeferencia);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByDiasvalidez(java.lang.Integer diasvalidez) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("diasvalidez", diasvalidez);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByPedidoaplId(java.lang.Long pedidoaplId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pedidoaplId", pedidoaplId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionAgencia", porcentajeComisionAgencia);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByTipopagoId(java.lang.Long tipopagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipopagoId", tipopagoId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByEquipoId(java.lang.Long equipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("equipoId", equipoId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByClienteNegociacionId(java.lang.Long clienteNegociacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteNegociacionId", clienteNegociacionId);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByTipoNegociacion(java.lang.String tipoNegociacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoNegociacion", tipoNegociacion);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeNegociacionDirecta", porcentajeNegociacionDirecta);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByPorcentajeDescuentoNegociacion(java.math.BigDecimal porcentajeDescuentoNegociacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoNegociacion", porcentajeDescuentoNegociacion);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByAutorizacionSap(java.lang.String autorizacionSap) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacionSap", autorizacionSap);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaVencimiento", fechaVencimiento);
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PedidoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PedidoEJB.class, aMap);      
    }

/////////////////
}
