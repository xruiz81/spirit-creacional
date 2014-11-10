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
public abstract class _CarteraDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CarteraDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraDetalleIf addCarteraDetalle(com.spirit.cartera.entity.CarteraDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CarteraDetalleEJB value = new CarteraDetalleEJB();
      try {
      value.setId(model.getId());
      value.setCarteraId(model.getCarteraId());
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
      value.setDiferido(model.getDiferido());
      value.setObservacion(model.getObservacion());
      value.setSriClienteRetencionId(model.getSriClienteRetencionId());
      value.setChequeBancoId(model.getChequeBancoId());
      value.setChequeCuentaBancariaId(model.getChequeCuentaBancariaId());
      value.setChequeNumero(model.getChequeNumero());
      value.setDepositoBancoId(model.getDepositoBancoId());
      value.setDepositoCuentaBancariaId(model.getDepositoCuentaBancariaId());
      value.setRetencionNumero(model.getRetencionNumero());
      value.setRetencionAutorizacion(model.getRetencionAutorizacion());
      value.setValorRetencionRenta(model.getValorRetencionRenta());
      value.setValorRetencionIva(model.getValorRetencionIva());
      value.setDebitoBancoId(model.getDebitoBancoId());
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
        log.error("Error al insertar información en carteraDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en carteraDetalle.");
      }
     
      return getCarteraDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCarteraDetalle(com.spirit.cartera.entity.CarteraDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CarteraDetalleEJB data = new CarteraDetalleEJB();
      data.setId(model.getId());
      data.setCarteraId(model.getCarteraId());
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
      data.setDiferido(model.getDiferido());
      data.setObservacion(model.getObservacion());
      data.setSriClienteRetencionId(model.getSriClienteRetencionId());
      data.setChequeBancoId(model.getChequeBancoId());
      data.setChequeCuentaBancariaId(model.getChequeCuentaBancariaId());
      data.setChequeNumero(model.getChequeNumero());
      data.setDepositoBancoId(model.getDepositoBancoId());
      data.setDepositoCuentaBancariaId(model.getDepositoCuentaBancariaId());
      data.setRetencionNumero(model.getRetencionNumero());
      data.setRetencionAutorizacion(model.getRetencionAutorizacion());
      data.setValorRetencionRenta(model.getValorRetencionRenta());
      data.setValorRetencionIva(model.getValorRetencionIva());
      data.setDebitoBancoId(model.getDebitoBancoId());
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
        log.error("Error al actualizar información en carteraDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en carteraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCarteraDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CarteraDetalleEJB data = manager.find(CarteraDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en carteraDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en carteraDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraDetalleIf getCarteraDetalle(java.lang.Long id) {
      return (CarteraDetalleEJB)queryManagerLocal.find(CarteraDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraDetalleList() {
	  return queryManagerLocal.singleClassList(CarteraDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CarteraDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCarteraDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from CarteraDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByCarteraId(java.lang.Long carteraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraId", carteraId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleBySecuencial(java.lang.Integer secuencial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("secuencial", secuencial);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByFechaCartera(java.sql.Timestamp fechaCartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCartera", fechaCartera);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaVencimiento", fechaVencimiento);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaUltimaActualizacion", fechaUltimaActualizacion);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByLineaId(java.lang.Long lineaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lineaId", lineaId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByFormaPagoId(java.lang.Long formaPagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formaPagoId", formaPagoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByCuentaBancariaId(java.lang.Long cuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaBancariaId", cuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByDepositoId(java.lang.Long depositoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("depositoId", depositoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleBySaldo(java.math.BigDecimal saldo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldo", saldo);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByCotizacion(java.math.BigDecimal cotizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cotizacion", cotizacion);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByCartera(java.lang.String cartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cartera", cartera);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByAutorizacion(java.lang.String autorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacion", autorizacion);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleBySriSustentoTributarioId(java.lang.Long sriSustentoTributarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("sriSustentoTributarioId", sriSustentoTributarioId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByDiferido(java.lang.String diferido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("diferido", diferido);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleBySriClienteRetencionId(java.lang.Long sriClienteRetencionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("sriClienteRetencionId", sriClienteRetencionId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByChequeBancoId(java.lang.Long chequeBancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("chequeBancoId", chequeBancoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByChequeCuentaBancariaId(java.lang.Long chequeCuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("chequeCuentaBancariaId", chequeCuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByChequeNumero(java.lang.String chequeNumero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("chequeNumero", chequeNumero);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByDepositoBancoId(java.lang.Long depositoBancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("depositoBancoId", depositoBancoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByDepositoCuentaBancariaId(java.lang.Long depositoCuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("depositoCuentaBancariaId", depositoCuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByRetencionNumero(java.lang.String retencionNumero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("retencionNumero", retencionNumero);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByRetencionAutorizacion(java.lang.String retencionAutorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("retencionAutorizacion", retencionAutorizacion);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByValorRetencionRenta(java.math.BigDecimal valorRetencionRenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorRetencionRenta", valorRetencionRenta);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByValorRetencionIva(java.math.BigDecimal valorRetencionIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorRetencionIva", valorRetencionIva);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByDebitoBancoId(java.lang.Long debitoBancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debitoBancoId", debitoBancoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByDebitoCuentaBancariaId(java.lang.Long debitoCuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debitoCuentaBancariaId", debitoCuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByDebitoReferencia(java.lang.String debitoReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debitoReferencia", debitoReferencia);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByTransferenciaBancoOrigenId(java.lang.Long transferenciaBancoOrigenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferenciaBancoOrigenId", transferenciaBancoOrigenId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByTransferenciaCuentaOrigenId(java.lang.Long transferenciaCuentaOrigenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferenciaCuentaOrigenId", transferenciaCuentaOrigenId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByTransferenciaBancoDestinoId(java.lang.Long transferenciaBancoDestinoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferenciaBancoDestinoId", transferenciaBancoDestinoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByTransferenciaCuentaDestinoId(java.lang.Long transferenciaCuentaDestinoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferenciaCuentaDestinoId", transferenciaCuentaDestinoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByTarjetaCreditoBancoId(java.lang.Long tarjetaCreditoBancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarjetaCreditoBancoId", tarjetaCreditoBancoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByTarjetaCreditoId(java.lang.Long tarjetaCreditoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarjetaCreditoId", tarjetaCreditoId);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByVoucherReferencia(java.lang.String voucherReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("voucherReferencia", voucherReferencia);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByPagoElectronicoReferencia(java.lang.String pagoElectronicoReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pagoElectronicoReferencia", pagoElectronicoReferencia);
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CarteraDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CarteraDetalleEJB.class, aMap);      
    }

/////////////////
}
