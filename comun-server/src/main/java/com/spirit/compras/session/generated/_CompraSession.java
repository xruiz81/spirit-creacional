package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.compras.entity.CompraEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CompraSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CompraSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraIf addCompra(com.spirit.compras.entity.CompraIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CompraEJB value = new CompraEJB();
      try {
      value.setId(model.getId());
      value.setOficinaId(model.getOficinaId());
      value.setTipodocumentoId(model.getTipodocumentoId());
      value.setCodigo(model.getCodigo());
      value.setProveedorId(model.getProveedorId());
      value.setMonedaId(model.getMonedaId());
      value.setUsuarioId(model.getUsuarioId());
      value.setFecha(model.getFecha());
      value.setFechaVencimiento(model.getFechaVencimiento());
      value.setPreimpreso(model.getPreimpreso());
      value.setAutorizacion(model.getAutorizacion());
      value.setReferencia(model.getReferencia());
      value.setLocalimportada(model.getLocalimportada());
      value.setValor(model.getValor());
      value.setDescuento(model.getDescuento());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroImpuesto(model.getOtroImpuesto());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      value.setEstadoBpm(model.getEstadoBpm());
      value.setRetencion(model.getRetencion());
      value.setIdSriSustentoTributario(model.getIdSriSustentoTributario());
      value.setFechaCaducidad(model.getFechaCaducidad());
      value.setTipoCompra(model.getTipoCompra());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en compra.", e);
			throw new GenericBusinessException(
					"Error al insertar información en compra.");
      }
     
      return getCompra(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCompra(com.spirit.compras.entity.CompraIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CompraEJB data = new CompraEJB();
      data.setId(model.getId());
      data.setOficinaId(model.getOficinaId());
      data.setTipodocumentoId(model.getTipodocumentoId());
      data.setCodigo(model.getCodigo());
      data.setProveedorId(model.getProveedorId());
      data.setMonedaId(model.getMonedaId());
      data.setUsuarioId(model.getUsuarioId());
      data.setFecha(model.getFecha());
      data.setFechaVencimiento(model.getFechaVencimiento());
      data.setPreimpreso(model.getPreimpreso());
      data.setAutorizacion(model.getAutorizacion());
      data.setReferencia(model.getReferencia());
      data.setLocalimportada(model.getLocalimportada());
      data.setValor(model.getValor());
      data.setDescuento(model.getDescuento());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroImpuesto(model.getOtroImpuesto());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
      data.setEstadoBpm(model.getEstadoBpm());
      data.setRetencion(model.getRetencion());
      data.setIdSriSustentoTributario(model.getIdSriSustentoTributario());
      data.setFechaCaducidad(model.getFechaCaducidad());
      data.setTipoCompra(model.getTipoCompra());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en compra.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en compra.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCompra(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CompraEJB data = manager.find(CompraEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en compra.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en compra.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraIf getCompra(java.lang.Long id) {
      return (CompraEJB)queryManagerLocal.find(CompraEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraList() {
	  return queryManagerLocal.singleClassList(CompraEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CompraEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCompraListSize() {
      Query countQuery = manager.createQuery("select count(*) from CompraEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByProveedorId(java.lang.Long proveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorId", proveedorId);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByMonedaId(java.lang.Long monedaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaId", monedaId);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByFechaVencimiento(java.sql.Date fechaVencimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaVencimiento", fechaVencimiento);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByAutorizacion(java.lang.String autorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacion", autorizacion);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByLocalimportada(java.lang.String localimportada) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("localimportada", localimportada);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByDescuento(java.math.BigDecimal descuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuento", descuento);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroImpuesto", otroImpuesto);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByEstadoBpm(java.lang.String estadoBpm) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estadoBpm", estadoBpm);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByRetencion(java.math.BigDecimal retencion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("retencion", retencion);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByIdSriSustentoTributario(java.lang.Long idSriSustentoTributario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idSriSustentoTributario", idSriSustentoTributario);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByFechaCaducidad(java.sql.Date fechaCaducidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCaducidad", fechaCaducidad);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByTipoCompra(java.lang.String tipoCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoCompra", tipoCompra);
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CompraIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CompraEJB.class, aMap);      
    }

/////////////////
}
