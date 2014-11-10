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
public abstract class _CuentasPorPagarSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CuentasPorPagarSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CuentasPorPagarIf addCuentasPorPagar(com.spirit.cartera.entity.CuentasPorPagarIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CuentasPorPagarEJB value = new CuentasPorPagarEJB();
      try {
      value.setId(model.getId());
      value.setCarteraId(model.getCarteraId());
      value.setCodigo(model.getCodigo());
      value.setPreimpreso(model.getPreimpreso());
      value.setFechaEmision(model.getFechaEmision());
      value.setComentario(model.getComentario());
      value.setTipodocumentoId(model.getTipodocumentoId());
      value.setReferenciaId(model.getReferenciaId());
      value.setValor(model.getValor());
      value.setSaldo(model.getSaldo());
      value.setCompraId(model.getCompraId());
      value.setFechaCompra(model.getFechaCompra());
      value.setOficinaId(model.getOficinaId());
      value.setObservacion(model.getObservacion());
      value.setProveedorId(model.getProveedorId());
      value.setRazonSocial(model.getRazonSocial());
      value.setIdentificacion(model.getIdentificacion());
      value.setProveedorOficinaId(model.getProveedorOficinaId());
      value.setTipoProveedorId(model.getTipoProveedorId());
      value.setCodigoTipoProveedor(model.getCodigoTipoProveedor());
      value.setTipoProveedor(model.getTipoProveedor());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cuentasPorPagar.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cuentasPorPagar.");
      }
     
      return getCuentasPorPagar(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCuentasPorPagar(com.spirit.cartera.entity.CuentasPorPagarIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CuentasPorPagarEJB data = new CuentasPorPagarEJB();
      data.setId(model.getId());
      data.setCarteraId(model.getCarteraId());
      data.setCodigo(model.getCodigo());
      data.setPreimpreso(model.getPreimpreso());
      data.setFechaEmision(model.getFechaEmision());
      data.setComentario(model.getComentario());
      data.setTipodocumentoId(model.getTipodocumentoId());
      data.setReferenciaId(model.getReferenciaId());
      data.setValor(model.getValor());
      data.setSaldo(model.getSaldo());
      data.setCompraId(model.getCompraId());
      data.setFechaCompra(model.getFechaCompra());
      data.setOficinaId(model.getOficinaId());
      data.setObservacion(model.getObservacion());
      data.setProveedorId(model.getProveedorId());
      data.setRazonSocial(model.getRazonSocial());
      data.setIdentificacion(model.getIdentificacion());
      data.setProveedorOficinaId(model.getProveedorOficinaId());
      data.setTipoProveedorId(model.getTipoProveedorId());
      data.setCodigoTipoProveedor(model.getCodigoTipoProveedor());
      data.setTipoProveedor(model.getTipoProveedor());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cuentasPorPagar.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cuentasPorPagar.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCuentasPorPagar(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CuentasPorPagarEJB data = manager.find(CuentasPorPagarEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cuentasPorPagar.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cuentasPorPagar.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CuentasPorPagarIf getCuentasPorPagar(java.lang.Long id) {
      return (CuentasPorPagarEJB)queryManagerLocal.find(CuentasPorPagarEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentasPorPagarList() {
	  return queryManagerLocal.singleClassList(CuentasPorPagarEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentasPorPagarList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CuentasPorPagarEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCuentasPorPagarListSize() {
      Query countQuery = manager.createQuery("select count(*) from CuentasPorPagarEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByCarteraId(java.lang.Long carteraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraId", carteraId);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByFechaEmision(java.sql.Timestamp fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByComentario(java.lang.String comentario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comentario", comentario);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByReferenciaId(java.lang.Long referenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaId", referenciaId);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarBySaldo(java.math.BigDecimal saldo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldo", saldo);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByCompraId(java.lang.Long compraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraId", compraId);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByFechaCompra(java.sql.Timestamp fechaCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCompra", fechaCompra);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByProveedorId(java.lang.Long proveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorId", proveedorId);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByRazonSocial(java.lang.String razonSocial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("razonSocial", razonSocial);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByIdentificacion(java.lang.String identificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("identificacion", identificacion);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByProveedorOficinaId(java.lang.Long proveedorOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorOficinaId", proveedorOficinaId);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByTipoProveedorId(java.lang.Long tipoProveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoProveedorId", tipoProveedorId);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByCodigoTipoProveedor(java.lang.String codigoTipoProveedor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoTipoProveedor", codigoTipoProveedor);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByTipoProveedor(java.lang.String tipoProveedor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoProveedor", tipoProveedor);
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CuentasPorPagarIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorPagarByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CuentasPorPagarEJB.class, aMap);      
    }

/////////////////
}
