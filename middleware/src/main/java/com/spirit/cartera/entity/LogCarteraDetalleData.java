package com.spirit.cartera.entity;

import java.io.Serializable;

import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class LogCarteraDetalleData implements LogCarteraDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long logCarteraId;

   public java.lang.Long getLogCarteraId() {
      return logCarteraId;
   }

   public void setLogCarteraId(java.lang.Long logCarteraId) {
      this.logCarteraId = logCarteraId;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.lang.Integer secuencial;

   public java.lang.Integer getSecuencial() {
      return secuencial;
   }

   public void setSecuencial(java.lang.Integer secuencial) {
      this.secuencial = secuencial;
   }

   private java.sql.Date fechaCreacion;

   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.sql.Date fechaCartera;

   public java.sql.Date getFechaCartera() {
      return fechaCartera;
   }

   public void setFechaCartera(java.sql.Date fechaCartera) {
      this.fechaCartera = fechaCartera;
   }

   private java.sql.Date fechaVencimiento;

   public java.sql.Date getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   private java.sql.Date fechaUltimaActualizacion;

   public java.sql.Date getFechaUltimaActualizacion() {
      return fechaUltimaActualizacion;
   }

   public void setFechaUltimaActualizacion(java.sql.Date fechaUltimaActualizacion) {
      this.fechaUltimaActualizacion = fechaUltimaActualizacion;
   }

   private java.lang.Long lineaId;

   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   private java.lang.Long formaPagoId;

   public java.lang.Long getFormaPagoId() {
      return formaPagoId;
   }

   public void setFormaPagoId(java.lang.Long formaPagoId) {
      this.formaPagoId = formaPagoId;
   }

   private java.lang.Long cuentaBancariaId;

   public java.lang.Long getCuentaBancariaId() {
      return cuentaBancariaId;
   }

   public void setCuentaBancariaId(java.lang.Long cuentaBancariaId) {
      this.cuentaBancariaId = cuentaBancariaId;
   }

   private java.lang.String referencia;

   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   private java.lang.String preimpreso;

   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   private java.lang.Long depositoId;

   public java.lang.Long getDepositoId() {
      return depositoId;
   }

   public void setDepositoId(java.lang.Long depositoId) {
      this.depositoId = depositoId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.math.BigDecimal saldo;

   public java.math.BigDecimal getSaldo() {
      return saldo;
   }

   public void setSaldo(java.math.BigDecimal saldo) {
      this.saldo = saldo;
   }

   private java.math.BigDecimal cotizacion;

   public java.math.BigDecimal getCotizacion() {
      return cotizacion;
   }

   public void setCotizacion(java.math.BigDecimal cotizacion) {
      this.cotizacion = cotizacion;
   }

   private java.lang.String cartera;

   public java.lang.String getCartera() {
      return cartera;
   }

   public void setCartera(java.lang.String cartera) {
      this.cartera = cartera;
   }

   private java.lang.String autorizacion;

   public java.lang.String getAutorizacion() {
      return autorizacion;
   }

   public void setAutorizacion(java.lang.String autorizacion) {
      this.autorizacion = autorizacion;
   }

   private java.lang.Long sriSustentoTributarioId;

   public java.lang.Long getSriSustentoTributarioId() {
      return sriSustentoTributarioId;
   }

   public void setSriSustentoTributarioId(java.lang.Long sriSustentoTributarioId) {
      this.sriSustentoTributarioId = sriSustentoTributarioId;
   }

   private java.lang.String log;

   public java.lang.String getLog() {
      return log;
   }

   public void setLog(java.lang.String log) {
      this.log = log;
   }

   private java.lang.String diferido;

   public java.lang.String getDiferido() {
      return diferido;
   }

   public void setDiferido(java.lang.String diferido) {
      this.diferido = diferido;
   }

   private java.math.BigDecimal valorRetencionRenta;

   public java.math.BigDecimal getValorRetencionRenta() {
      return valorRetencionRenta;
   }

   public void setValorRetencionRenta(java.math.BigDecimal valorRetencionRenta) {
      this.valorRetencionRenta = valorRetencionRenta;
   }

   private java.math.BigDecimal valorRetencionIva;

   public java.math.BigDecimal getValorRetencionIva() {
      return valorRetencionIva;
   }

   public void setValorRetencionIva(java.math.BigDecimal valorRetencionIva) {
      this.valorRetencionIva = valorRetencionIva;
   }

   private java.lang.Long debitoBancoId;

   public java.lang.Long getDebitoBancoId() {
      return debitoBancoId;
   }

   public void setDebitoBancoId(java.lang.Long debitoBancoId) {
      this.debitoBancoId = debitoBancoId;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.Long sriClienteRetencionId;

   public java.lang.Long getSriClienteRetencionId() {
      return sriClienteRetencionId;
   }

   public void setSriClienteRetencionId(java.lang.Long sriClienteRetencionId) {
      this.sriClienteRetencionId = sriClienteRetencionId;
   }

   private java.lang.Long chequeBancoId;

   public java.lang.Long getChequeBancoId() {
      return chequeBancoId;
   }

   public void setChequeBancoId(java.lang.Long chequeBancoId) {
      this.chequeBancoId = chequeBancoId;
   }

   private java.lang.Long chequeCuentaBancariaId;

   public java.lang.Long getChequeCuentaBancariaId() {
      return chequeCuentaBancariaId;
   }

   public void setChequeCuentaBancariaId(java.lang.Long chequeCuentaBancariaId) {
      this.chequeCuentaBancariaId = chequeCuentaBancariaId;
   }

   private java.lang.String chequeNumero;

   public java.lang.String getChequeNumero() {
      return chequeNumero;
   }

   public void setChequeNumero(java.lang.String chequeNumero) {
      this.chequeNumero = chequeNumero;
   }

   private java.lang.Long depositoBancoId;

   public java.lang.Long getDepositoBancoId() {
      return depositoBancoId;
   }

   public void setDepositoBancoId(java.lang.Long depositoBancoId) {
      this.depositoBancoId = depositoBancoId;
   }

   private java.lang.Long depositoCuentaBancariaId;

   public java.lang.Long getDepositoCuentaBancariaId() {
      return depositoCuentaBancariaId;
   }

   public void setDepositoCuentaBancariaId(java.lang.Long depositoCuentaBancariaId) {
      this.depositoCuentaBancariaId = depositoCuentaBancariaId;
   }

   private java.lang.String retencionNumero;

   public java.lang.String getRetencionNumero() {
      return retencionNumero;
   }

   public void setRetencionNumero(java.lang.String retencionNumero) {
      this.retencionNumero = retencionNumero;
   }

   private java.lang.String retencionAutorizacion;

   public java.lang.String getRetencionAutorizacion() {
      return retencionAutorizacion;
   }

   public void setRetencionAutorizacion(java.lang.String retencionAutorizacion) {
      this.retencionAutorizacion = retencionAutorizacion;
   }

   private java.lang.Long debitoCuentaBancariaId;

   public java.lang.Long getDebitoCuentaBancariaId() {
      return debitoCuentaBancariaId;
   }

   public void setDebitoCuentaBancariaId(java.lang.Long debitoCuentaBancariaId) {
      this.debitoCuentaBancariaId = debitoCuentaBancariaId;
   }

   private java.lang.String debitoReferencia;

   public java.lang.String getDebitoReferencia() {
      return debitoReferencia;
   }

   public void setDebitoReferencia(java.lang.String debitoReferencia) {
      this.debitoReferencia = debitoReferencia;
   }

   private java.lang.Long transferenciaBancoOrigenId;

   public java.lang.Long getTransferenciaBancoOrigenId() {
      return transferenciaBancoOrigenId;
   }

   public void setTransferenciaBancoOrigenId(java.lang.Long transferenciaBancoOrigenId) {
      this.transferenciaBancoOrigenId = transferenciaBancoOrigenId;
   }

   private java.lang.Long transferenciaCuentaOrigenId;

   public java.lang.Long getTransferenciaCuentaOrigenId() {
      return transferenciaCuentaOrigenId;
   }

   public void setTransferenciaCuentaOrigenId(java.lang.Long transferenciaCuentaOrigenId) {
      this.transferenciaCuentaOrigenId = transferenciaCuentaOrigenId;
   }

   private java.lang.Long transferenciaBancoDestinoId;

   public java.lang.Long getTransferenciaBancoDestinoId() {
      return transferenciaBancoDestinoId;
   }

   public void setTransferenciaBancoDestinoId(java.lang.Long transferenciaBancoDestinoId) {
      this.transferenciaBancoDestinoId = transferenciaBancoDestinoId;
   }

   private java.lang.Long transferenciaCuentaDestinoId;

   public java.lang.Long getTransferenciaCuentaDestinoId() {
      return transferenciaCuentaDestinoId;
   }

   public void setTransferenciaCuentaDestinoId(java.lang.Long transferenciaCuentaDestinoId) {
      this.transferenciaCuentaDestinoId = transferenciaCuentaDestinoId;
   }

   private java.lang.Long tarjetaCreditoBancoId;

   public java.lang.Long getTarjetaCreditoBancoId() {
      return tarjetaCreditoBancoId;
   }

   public void setTarjetaCreditoBancoId(java.lang.Long tarjetaCreditoBancoId) {
      this.tarjetaCreditoBancoId = tarjetaCreditoBancoId;
   }

   private java.lang.Long tarjetaCreditoId;

   public java.lang.Long getTarjetaCreditoId() {
      return tarjetaCreditoId;
   }

   public void setTarjetaCreditoId(java.lang.Long tarjetaCreditoId) {
      this.tarjetaCreditoId = tarjetaCreditoId;
   }

   private java.lang.String voucherReferencia;

   public java.lang.String getVoucherReferencia() {
      return voucherReferencia;
   }

   public void setVoucherReferencia(java.lang.String voucherReferencia) {
      this.voucherReferencia = voucherReferencia;
   }

   private java.lang.String pagoElectronicoReferencia;

   public java.lang.String getPagoElectronicoReferencia() {
      return pagoElectronicoReferencia;
   }

   public void setPagoElectronicoReferencia(java.lang.String pagoElectronicoReferencia) {
      this.pagoElectronicoReferencia = pagoElectronicoReferencia;
   }
   public LogCarteraDetalleData() {
   }

   public LogCarteraDetalleData(com.spirit.cartera.entity.LogCarteraDetalleIf value) {
      setId(value.getId());
      setLogCarteraId(value.getLogCarteraId());
      setDocumentoId(value.getDocumentoId());
      setSecuencial(value.getSecuencial());
      setFechaCreacion(value.getFechaCreacion());
      setFechaCartera(value.getFechaCartera());
      setFechaVencimiento(value.getFechaVencimiento());
      setFechaUltimaActualizacion(value.getFechaUltimaActualizacion());
      setLineaId(value.getLineaId());
      setFormaPagoId(value.getFormaPagoId());
      setCuentaBancariaId(value.getCuentaBancariaId());
      setReferencia(value.getReferencia());
      setPreimpreso(value.getPreimpreso());
      setDepositoId(value.getDepositoId());
      setValor(value.getValor());
      setSaldo(value.getSaldo());
      setCotizacion(value.getCotizacion());
      setCartera(value.getCartera());
      setAutorizacion(value.getAutorizacion());
      setSriSustentoTributarioId(value.getSriSustentoTributarioId());
      setLog(value.getLog());
      setDiferido(value.getDiferido());
      setValorRetencionRenta(value.getValorRetencionRenta());
      setValorRetencionIva(value.getValorRetencionIva());
      setDebitoBancoId(value.getDebitoBancoId());
      setObservacion(value.getObservacion());
      setSriClienteRetencionId(value.getSriClienteRetencionId());
      setChequeBancoId(value.getChequeBancoId());
      setChequeCuentaBancariaId(value.getChequeCuentaBancariaId());
      setChequeNumero(value.getChequeNumero());
      setDepositoBancoId(value.getDepositoBancoId());
      setDepositoCuentaBancariaId(value.getDepositoCuentaBancariaId());
      setRetencionNumero(value.getRetencionNumero());
      setRetencionAutorizacion(value.getRetencionAutorizacion());
      setDebitoCuentaBancariaId(value.getDebitoCuentaBancariaId());
      setDebitoReferencia(value.getDebitoReferencia());
      setTransferenciaBancoOrigenId(value.getTransferenciaBancoOrigenId());
      setTransferenciaCuentaOrigenId(value.getTransferenciaCuentaOrigenId());
      setTransferenciaBancoDestinoId(value.getTransferenciaBancoDestinoId());
      setTransferenciaCuentaDestinoId(value.getTransferenciaCuentaDestinoId());
      setTarjetaCreditoBancoId(value.getTarjetaCreditoBancoId());
      setTarjetaCreditoId(value.getTarjetaCreditoId());
      setVoucherReferencia(value.getVoucherReferencia());
      setPagoElectronicoReferencia(value.getPagoElectronicoReferencia());
   }



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((LogCarteraDetalleIf)this);
	}
}
