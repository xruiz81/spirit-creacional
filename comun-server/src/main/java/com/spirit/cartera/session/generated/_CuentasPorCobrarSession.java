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
public abstract class _CuentasPorCobrarSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CuentasPorCobrarSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CuentasPorCobrarIf addCuentasPorCobrar(com.spirit.cartera.entity.CuentasPorCobrarIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CuentasPorCobrarEJB value = new CuentasPorCobrarEJB();
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
      value.setFacturaId(model.getFacturaId());
      value.setFechaFactura(model.getFechaFactura());
      value.setOficinaId(model.getOficinaId());
      value.setObservacion(model.getObservacion());
      value.setClienteId(model.getClienteId());
      value.setRazonSocial(model.getRazonSocial());
      value.setIdentificacion(model.getIdentificacion());
      value.setClienteOficinaId(model.getClienteOficinaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cuentasPorCobrar.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cuentasPorCobrar.");
      }
     
      return getCuentasPorCobrar(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCuentasPorCobrar(com.spirit.cartera.entity.CuentasPorCobrarIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CuentasPorCobrarEJB data = new CuentasPorCobrarEJB();
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
      data.setFacturaId(model.getFacturaId());
      data.setFechaFactura(model.getFechaFactura());
      data.setOficinaId(model.getOficinaId());
      data.setObservacion(model.getObservacion());
      data.setClienteId(model.getClienteId());
      data.setRazonSocial(model.getRazonSocial());
      data.setIdentificacion(model.getIdentificacion());
      data.setClienteOficinaId(model.getClienteOficinaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cuentasPorCobrar.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cuentasPorCobrar.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCuentasPorCobrar(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CuentasPorCobrarEJB data = manager.find(CuentasPorCobrarEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cuentasPorCobrar.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cuentasPorCobrar.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CuentasPorCobrarIf getCuentasPorCobrar(java.lang.Long id) {
      return (CuentasPorCobrarEJB)queryManagerLocal.find(CuentasPorCobrarEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentasPorCobrarList() {
	  return queryManagerLocal.singleClassList(CuentasPorCobrarEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentasPorCobrarList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CuentasPorCobrarEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCuentasPorCobrarListSize() {
      Query countQuery = manager.createQuery("select count(*) from CuentasPorCobrarEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByCarteraId(java.lang.Long carteraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraId", carteraId);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByFechaEmision(java.sql.Timestamp fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByComentario(java.lang.String comentario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comentario", comentario);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByReferenciaId(java.lang.Long referenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaId", referenciaId);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarBySaldo(java.math.BigDecimal saldo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldo", saldo);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByFacturaId(java.lang.Long facturaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("facturaId", facturaId);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByFechaFactura(java.sql.Timestamp fechaFactura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFactura", fechaFactura);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByRazonSocial(java.lang.String razonSocial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("razonSocial", razonSocial);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByIdentificacion(java.lang.String identificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("identificacion", identificacion);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByClienteOficinaId(java.lang.Long clienteOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteOficinaId", clienteOficinaId);
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CuentasPorCobrarIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentasPorCobrarByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CuentasPorCobrarEJB.class, aMap);      
    }

/////////////////
}
