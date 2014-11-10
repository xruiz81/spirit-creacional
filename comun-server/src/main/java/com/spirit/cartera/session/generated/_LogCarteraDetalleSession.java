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
public abstract class _LogCarteraDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_LogCarteraDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.LogCarteraDetalleIf addLogCarteraDetalle(com.spirit.cartera.entity.LogCarteraDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      LogCarteraDetalleEJB value = new LogCarteraDetalleEJB();
      try {
      value.setId(model.getId());
      value.setLogCarteraId(model.getLogCarteraId());
      value.setDocumentoId(model.getDocumentoId());
      value.setSecuencial(model.getSecuencial());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setFechaCartera(model.getFechaCartera());
      value.setFechaVencimiento(model.getFechaVencimiento());
      value.setFechaUltimaActualizacion(model.getFechaUltimaActualizacion());
      value.setLineaId(model.getLineaId());
      value.setFormaPagoId(model.getFormaPagoId());
      value.setCuentaBancariaId(model.getCuentaBancariaId());
      value.setReferencia(model.getReferencia());
      value.setPreimpreso(model.getPreimpreso());
      value.setDepositoId(model.getDepositoId());
      value.setValor(model.getValor());
      value.setSaldo(model.getSaldo());
      value.setCotizacion(model.getCotizacion());
      value.setCartera(model.getCartera());
      value.setAutorizacion(model.getAutorizacion());
      value.setSriSustentoTributarioId(model.getSriSustentoTributarioId());
      value.setLog(model.getLog());
      value.setDiferido(model.getDiferido());
      value.setValorRetencionRenta(model.getValorRetencionRenta());
      value.setValorRetencionIva(model.getValorRetencionIva());
      value.setDebitoBancoId(model.getDebitoBancoId());
      value.setObservacion(model.getObservacion());
      value.setSriClienteRetencionId(model.getSriClienteRetencionId());
      value.setChequeBancoId(model.getChequeBancoId());
      value.setChequeCuentaBancariaId(model.getChequeCuentaBancariaId());
      value.setChequeNumero(model.getChequeNumero());
      value.setDepositoBancoId(model.getDepositoBancoId());
      value.setDepositoCuentaBancariaId(model.getDepositoCuentaBancariaId());
      value.setRetencionNumero(model.getRetencionNumero());
      value.setRetencionAutorizacion(model.getRetencionAutorizacion());
      value.setDebitoCuentaBancariaId(model.getDebitoCuentaBancariaId());
      value.setDebitoReferencia(model.getDebitoReferencia());
      value.setTransferenciaBancoOrigenId(model.getTransferenciaBancoOrigenId());
      value.setTransferenciaCuentaOrigenId(model.getTransferenciaCuentaOrigenId());
      value.setTransferenciaBancoDestinoId(model.getTransferenciaBancoDestinoId());
      value.setTransferenciaCuentaDestinoId(model.getTransferenciaCuentaDestinoId());
      value.setTarjetaCreditoBancoId(model.getTarjetaCreditoBancoId());
      value.setTarjetaCreditoId(model.getTarjetaCreditoId());
      value.setVoucherReferencia(model.getVoucherReferencia());
      value.setPagoElectronicoReferencia(model.getPagoElectronicoReferencia());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en logCarteraDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en logCarteraDetalle.");
      }
     
      return getLogCarteraDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveLogCarteraDetalle(com.spirit.cartera.entity.LogCarteraDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      LogCarteraDetalleEJB data = new LogCarteraDetalleEJB();
      data.setId(model.getId());
      data.setLogCarteraId(model.getLogCarteraId());
      data.setDocumentoId(model.getDocumentoId());
      data.setSecuencial(model.getSecuencial());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setFechaCartera(model.getFechaCartera());
      data.setFechaVencimiento(model.getFechaVencimiento());
      data.setFechaUltimaActualizacion(model.getFechaUltimaActualizacion());
      data.setLineaId(model.getLineaId());
      data.setFormaPagoId(model.getFormaPagoId());
      data.setCuentaBancariaId(model.getCuentaBancariaId());
      data.setReferencia(model.getReferencia());
      data.setPreimpreso(model.getPreimpreso());
      data.setDepositoId(model.getDepositoId());
      data.setValor(model.getValor());
      data.setSaldo(model.getSaldo());
      data.setCotizacion(model.getCotizacion());
      data.setCartera(model.getCartera());
      data.setAutorizacion(model.getAutorizacion());
      data.setSriSustentoTributarioId(model.getSriSustentoTributarioId());
      data.setLog(model.getLog());
      data.setDiferido(model.getDiferido());
      data.setValorRetencionRenta(model.getValorRetencionRenta());
      data.setValorRetencionIva(model.getValorRetencionIva());
      data.setDebitoBancoId(model.getDebitoBancoId());
      data.setObservacion(model.getObservacion());
      data.setSriClienteRetencionId(model.getSriClienteRetencionId());
      data.setChequeBancoId(model.getChequeBancoId());
      data.setChequeCuentaBancariaId(model.getChequeCuentaBancariaId());
      data.setChequeNumero(model.getChequeNumero());
      data.setDepositoBancoId(model.getDepositoBancoId());
      data.setDepositoCuentaBancariaId(model.getDepositoCuentaBancariaId());
      data.setRetencionNumero(model.getRetencionNumero());
      data.setRetencionAutorizacion(model.getRetencionAutorizacion());
      data.setDebitoCuentaBancariaId(model.getDebitoCuentaBancariaId());
      data.setDebitoReferencia(model.getDebitoReferencia());
      data.setTransferenciaBancoOrigenId(model.getTransferenciaBancoOrigenId());
      data.setTransferenciaCuentaOrigenId(model.getTransferenciaCuentaOrigenId());
      data.setTransferenciaBancoDestinoId(model.getTransferenciaBancoDestinoId());
      data.setTransferenciaCuentaDestinoId(model.getTransferenciaCuentaDestinoId());
      data.setTarjetaCreditoBancoId(model.getTarjetaCreditoBancoId());
      data.setTarjetaCreditoId(model.getTarjetaCreditoId());
      data.setVoucherReferencia(model.getVoucherReferencia());
      data.setPagoElectronicoReferencia(model.getPagoElectronicoReferencia());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en logCarteraDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en logCarteraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteLogCarteraDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      LogCarteraDetalleEJB data = manager.find(LogCarteraDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en logCarteraDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en logCarteraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.LogCarteraDetalleIf getLogCarteraDetalle(java.lang.Long id) {
      return (LogCarteraDetalleEJB)queryManagerLocal.find(LogCarteraDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogCarteraDetalleList() {
	  return queryManagerLocal.singleClassList(LogCarteraDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogCarteraDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(LogCarteraDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getLogCarteraDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from LogCarteraDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByLogCarteraId(java.lang.Long logCarteraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("logCarteraId", logCarteraId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleBySecuencial(java.lang.Integer secuencial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("secuencial", secuencial);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByFechaCartera(java.sql.Timestamp fechaCartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCartera", fechaCartera);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaVencimiento", fechaVencimiento);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaUltimaActualizacion", fechaUltimaActualizacion);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByLineaId(java.lang.Long lineaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lineaId", lineaId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByFormaPagoId(java.lang.Long formaPagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formaPagoId", formaPagoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByCuentaBancariaId(java.lang.Long cuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaBancariaId", cuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByDepositoId(java.lang.Long depositoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("depositoId", depositoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleBySaldo(java.math.BigDecimal saldo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldo", saldo);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByCotizacion(java.math.BigDecimal cotizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cotizacion", cotizacion);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByCartera(java.lang.String cartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cartera", cartera);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByAutorizacion(java.lang.String autorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacion", autorizacion);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleBySriSustentoTributarioId(java.lang.Long sriSustentoTributarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("sriSustentoTributarioId", sriSustentoTributarioId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByLog(java.lang.String log) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("log", log);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByDiferido(java.lang.String diferido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("diferido", diferido);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByValorRetencionRenta(java.math.BigDecimal valorRetencionRenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorRetencionRenta", valorRetencionRenta);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByValorRetencionIva(java.math.BigDecimal valorRetencionIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorRetencionIva", valorRetencionIva);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByDebitoBancoId(java.lang.Long debitoBancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debitoBancoId", debitoBancoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleBySriClienteRetencionId(java.lang.Long sriClienteRetencionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("sriClienteRetencionId", sriClienteRetencionId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByChequeBancoId(java.lang.Long chequeBancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("chequeBancoId", chequeBancoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByChequeCuentaBancariaId(java.lang.Long chequeCuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("chequeCuentaBancariaId", chequeCuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByChequeNumero(java.lang.String chequeNumero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("chequeNumero", chequeNumero);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByDepositoBancoId(java.lang.Long depositoBancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("depositoBancoId", depositoBancoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByDepositoCuentaBancariaId(java.lang.Long depositoCuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("depositoCuentaBancariaId", depositoCuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByRetencionNumero(java.lang.String retencionNumero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("retencionNumero", retencionNumero);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByRetencionAutorizacion(java.lang.String retencionAutorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("retencionAutorizacion", retencionAutorizacion);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByDebitoCuentaBancariaId(java.lang.Long debitoCuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debitoCuentaBancariaId", debitoCuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByDebitoReferencia(java.lang.String debitoReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debitoReferencia", debitoReferencia);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByTransferenciaBancoOrigenId(java.lang.Long transferenciaBancoOrigenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferenciaBancoOrigenId", transferenciaBancoOrigenId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByTransferenciaCuentaOrigenId(java.lang.Long transferenciaCuentaOrigenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferenciaCuentaOrigenId", transferenciaCuentaOrigenId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByTransferenciaBancoDestinoId(java.lang.Long transferenciaBancoDestinoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferenciaBancoDestinoId", transferenciaBancoDestinoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByTransferenciaCuentaDestinoId(java.lang.Long transferenciaCuentaDestinoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferenciaCuentaDestinoId", transferenciaCuentaDestinoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByTarjetaCreditoBancoId(java.lang.Long tarjetaCreditoBancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarjetaCreditoBancoId", tarjetaCreditoBancoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByTarjetaCreditoId(java.lang.Long tarjetaCreditoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarjetaCreditoId", tarjetaCreditoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByVoucherReferencia(java.lang.String voucherReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("voucherReferencia", voucherReferencia);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByPagoElectronicoReferencia(java.lang.String pagoElectronicoReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pagoElectronicoReferencia", pagoElectronicoReferencia);
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of LogCarteraDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(LogCarteraDetalleEJB.class, aMap);      
    }

/////////////////
}
