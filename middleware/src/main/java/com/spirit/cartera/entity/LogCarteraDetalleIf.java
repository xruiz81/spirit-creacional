package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LogCarteraDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getLogCarteraId();

   void setLogCarteraId(java.lang.Long logCarteraId);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Integer getSecuencial();

   void setSecuencial(java.lang.Integer secuencial);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.sql.Date getFechaCartera();

   void setFechaCartera(java.sql.Date fechaCartera);

   java.sql.Date getFechaVencimiento();

   void setFechaVencimiento(java.sql.Date fechaVencimiento);

   java.sql.Date getFechaUltimaActualizacion();

   void setFechaUltimaActualizacion(java.sql.Date fechaUltimaActualizacion);

   java.lang.Long getLineaId();

   void setLineaId(java.lang.Long lineaId);

   java.lang.Long getFormaPagoId();

   void setFormaPagoId(java.lang.Long formaPagoId);

   java.lang.Long getCuentaBancariaId();

   void setCuentaBancariaId(java.lang.Long cuentaBancariaId);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);

   java.lang.String getPreimpreso();

   void setPreimpreso(java.lang.String preimpreso);

   java.lang.Long getDepositoId();

   void setDepositoId(java.lang.Long depositoId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getSaldo();

   void setSaldo(java.math.BigDecimal saldo);

   java.math.BigDecimal getCotizacion();

   void setCotizacion(java.math.BigDecimal cotizacion);

   java.lang.String getCartera();

   void setCartera(java.lang.String cartera);

   java.lang.String getAutorizacion();

   void setAutorizacion(java.lang.String autorizacion);

   java.lang.Long getSriSustentoTributarioId();

   void setSriSustentoTributarioId(java.lang.Long sriSustentoTributarioId);

   java.lang.String getLog();

   void setLog(java.lang.String log);

   java.lang.String getDiferido();

   void setDiferido(java.lang.String diferido);

   java.math.BigDecimal getValorRetencionRenta();

   void setValorRetencionRenta(java.math.BigDecimal valorRetencionRenta);

   java.math.BigDecimal getValorRetencionIva();

   void setValorRetencionIva(java.math.BigDecimal valorRetencionIva);

   java.lang.Long getDebitoBancoId();

   void setDebitoBancoId(java.lang.Long debitoBancoId);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.Long getSriClienteRetencionId();

   void setSriClienteRetencionId(java.lang.Long sriClienteRetencionId);

   java.lang.Long getChequeBancoId();

   void setChequeBancoId(java.lang.Long chequeBancoId);

   java.lang.Long getChequeCuentaBancariaId();

   void setChequeCuentaBancariaId(java.lang.Long chequeCuentaBancariaId);

   java.lang.String getChequeNumero();

   void setChequeNumero(java.lang.String chequeNumero);

   java.lang.Long getDepositoBancoId();

   void setDepositoBancoId(java.lang.Long depositoBancoId);

   java.lang.Long getDepositoCuentaBancariaId();

   void setDepositoCuentaBancariaId(java.lang.Long depositoCuentaBancariaId);

   java.lang.String getRetencionNumero();

   void setRetencionNumero(java.lang.String retencionNumero);

   java.lang.String getRetencionAutorizacion();

   void setRetencionAutorizacion(java.lang.String retencionAutorizacion);

   java.lang.Long getDebitoCuentaBancariaId();

   void setDebitoCuentaBancariaId(java.lang.Long debitoCuentaBancariaId);

   java.lang.String getDebitoReferencia();

   void setDebitoReferencia(java.lang.String debitoReferencia);

   java.lang.Long getTransferenciaBancoOrigenId();

   void setTransferenciaBancoOrigenId(java.lang.Long transferenciaBancoOrigenId);

   java.lang.Long getTransferenciaCuentaOrigenId();

   void setTransferenciaCuentaOrigenId(java.lang.Long transferenciaCuentaOrigenId);

   java.lang.Long getTransferenciaBancoDestinoId();

   void setTransferenciaBancoDestinoId(java.lang.Long transferenciaBancoDestinoId);

   java.lang.Long getTransferenciaCuentaDestinoId();

   void setTransferenciaCuentaDestinoId(java.lang.Long transferenciaCuentaDestinoId);

   java.lang.Long getTarjetaCreditoBancoId();

   void setTarjetaCreditoBancoId(java.lang.Long tarjetaCreditoBancoId);

   java.lang.Long getTarjetaCreditoId();

   void setTarjetaCreditoId(java.lang.Long tarjetaCreditoId);

   java.lang.String getVoucherReferencia();

   void setVoucherReferencia(java.lang.String voucherReferencia);

   java.lang.String getPagoElectronicoReferencia();

   void setPagoElectronicoReferencia(java.lang.String pagoElectronicoReferencia);


}
