package com.spirit.cartera.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CARTERA_DETALLE")
@Entity
public class CarteraDetalleEJB implements Serializable, CarteraDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long carteraId;
   private java.lang.Long documentoId;
   private java.lang.Integer secuencial;
   private java.sql.Date fechaCreacion;
   private java.sql.Date fechaCartera;
   private java.sql.Date fechaVencimiento;
   private java.sql.Date fechaUltimaActualizacion;
   private java.lang.Long lineaId;
   private java.lang.Long formaPagoId;
   private java.lang.Long cuentaBancariaId;
   private java.lang.String referencia;
   private java.lang.String preimpreso;
   private java.lang.Long depositoId;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal saldo;
   private java.math.BigDecimal cotizacion;
   private java.lang.String cartera;
   private java.lang.String autorizacion;
   private java.lang.Long sriSustentoTributarioId;
   private java.lang.String diferido;
   private java.lang.String observacion;
   private java.lang.Long sriClienteRetencionId;
   private java.lang.Long chequeBancoId;
   private java.lang.Long chequeCuentaBancariaId;
   private java.lang.String chequeNumero;
   private java.lang.Long depositoBancoId;
   private java.lang.Long depositoCuentaBancariaId;
   private java.lang.String retencionNumero;
   private java.lang.String retencionAutorizacion;
   private java.math.BigDecimal valorRetencionRenta;
   private java.math.BigDecimal valorRetencionIva;
   private java.lang.Long debitoBancoId;
   private java.lang.Long debitoCuentaBancariaId;
   private java.lang.String debitoReferencia;
   private java.lang.Long transferenciaBancoOrigenId;
   private java.lang.Long transferenciaCuentaOrigenId;
   private java.lang.Long transferenciaBancoDestinoId;
   private java.lang.Long transferenciaCuentaDestinoId;
   private java.lang.Long tarjetaCreditoBancoId;
   private java.lang.Long tarjetaCreditoId;
   private java.lang.String voucherReferencia;
   private java.lang.String pagoElectronicoReferencia;

   public CarteraDetalleEJB() {
   }

   public CarteraDetalleEJB(com.spirit.cartera.entity.CarteraDetalleIf value) {
      setId(value.getId());
      setCarteraId(value.getCarteraId());
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
      setDiferido(value.getDiferido());
      setObservacion(value.getObservacion());
      setSriClienteRetencionId(value.getSriClienteRetencionId());
      setChequeBancoId(value.getChequeBancoId());
      setChequeCuentaBancariaId(value.getChequeCuentaBancariaId());
      setChequeNumero(value.getChequeNumero());
      setDepositoBancoId(value.getDepositoBancoId());
      setDepositoCuentaBancariaId(value.getDepositoCuentaBancariaId());
      setRetencionNumero(value.getRetencionNumero());
      setRetencionAutorizacion(value.getRetencionAutorizacion());
      setValorRetencionRenta(value.getValorRetencionRenta());
      setValorRetencionIva(value.getValorRetencionIva());
      setDebitoBancoId(value.getDebitoBancoId());
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

   public java.lang.Long create(com.spirit.cartera.entity.CarteraDetalleIf value) {
      setId(value.getId());
      setCarteraId(value.getCarteraId());
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
      setDiferido(value.getDiferido());
      setObservacion(value.getObservacion());
      setSriClienteRetencionId(value.getSriClienteRetencionId());
      setChequeBancoId(value.getChequeBancoId());
      setChequeCuentaBancariaId(value.getChequeCuentaBancariaId());
      setChequeNumero(value.getChequeNumero());
      setDepositoBancoId(value.getDepositoBancoId());
      setDepositoCuentaBancariaId(value.getDepositoCuentaBancariaId());
      setRetencionNumero(value.getRetencionNumero());
      setRetencionAutorizacion(value.getRetencionAutorizacion());
      setValorRetencionRenta(value.getValorRetencionRenta());
      setValorRetencionIva(value.getValorRetencionIva());
      setDebitoBancoId(value.getDebitoBancoId());
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
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "CARTERA_ID")
   public java.lang.Long getCarteraId() {
      return carteraId;
   }

   public void setCarteraId(java.lang.Long carteraId) {
      this.carteraId = carteraId;
   }

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "SECUENCIAL")
   public java.lang.Integer getSecuencial() {
      return secuencial;
   }

   public void setSecuencial(java.lang.Integer secuencial) {
      this.secuencial = secuencial;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "FECHA_CARTERA")
   public java.sql.Date getFechaCartera() {
      return fechaCartera;
   }

   public void setFechaCartera(java.sql.Date fechaCartera) {
      this.fechaCartera = fechaCartera;
   }

   @Column(name = "FECHA_VENCIMIENTO")
   public java.sql.Date getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   @Column(name = "FECHA_ULTIMA_ACTUALIZACION")
   public java.sql.Date getFechaUltimaActualizacion() {
      return fechaUltimaActualizacion;
   }

   public void setFechaUltimaActualizacion(java.sql.Date fechaUltimaActualizacion) {
      this.fechaUltimaActualizacion = fechaUltimaActualizacion;
   }

   @Column(name = "LINEA_ID")
   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   @Column(name = "FORMA_PAGO_ID")
   public java.lang.Long getFormaPagoId() {
      return formaPagoId;
   }

   public void setFormaPagoId(java.lang.Long formaPagoId) {
      this.formaPagoId = formaPagoId;
   }

   @Column(name = "CUENTA_BANCARIA_ID")
   public java.lang.Long getCuentaBancariaId() {
      return cuentaBancariaId;
   }

   public void setCuentaBancariaId(java.lang.Long cuentaBancariaId) {
      this.cuentaBancariaId = cuentaBancariaId;
   }

   @Column(name = "REFERENCIA")
   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   @Column(name = "PREIMPRESO")
   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   @Column(name = "DEPOSITO_ID")
   public java.lang.Long getDepositoId() {
      return depositoId;
   }

   public void setDepositoId(java.lang.Long depositoId) {
      this.depositoId = depositoId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "SALDO")
   public java.math.BigDecimal getSaldo() {
      return saldo;
   }

   public void setSaldo(java.math.BigDecimal saldo) {
      this.saldo = saldo;
   }

   @Column(name = "COTIZACION")
   public java.math.BigDecimal getCotizacion() {
      return cotizacion;
   }

   public void setCotizacion(java.math.BigDecimal cotizacion) {
      this.cotizacion = cotizacion;
   }

   @Column(name = "CARTERA")
   public java.lang.String getCartera() {
      return cartera;
   }

   public void setCartera(java.lang.String cartera) {
      this.cartera = cartera;
   }

   @Column(name = "AUTORIZACION")
   public java.lang.String getAutorizacion() {
      return autorizacion;
   }

   public void setAutorizacion(java.lang.String autorizacion) {
      this.autorizacion = autorizacion;
   }

   @Column(name = "SRI_SUSTENTO_TRIBUTARIO_ID")
   public java.lang.Long getSriSustentoTributarioId() {
      return sriSustentoTributarioId;
   }

   public void setSriSustentoTributarioId(java.lang.Long sriSustentoTributarioId) {
      this.sriSustentoTributarioId = sriSustentoTributarioId;
   }

   @Column(name = "DIFERIDO")
   public java.lang.String getDiferido() {
      return diferido;
   }

   public void setDiferido(java.lang.String diferido) {
      this.diferido = diferido;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "SRI_CLIENTE_RETENCION_ID")
   public java.lang.Long getSriClienteRetencionId() {
      return sriClienteRetencionId;
   }

   public void setSriClienteRetencionId(java.lang.Long sriClienteRetencionId) {
      this.sriClienteRetencionId = sriClienteRetencionId;
   }

   @Column(name = "CHEQUE_BANCO_ID")
   public java.lang.Long getChequeBancoId() {
      return chequeBancoId;
   }

   public void setChequeBancoId(java.lang.Long chequeBancoId) {
      this.chequeBancoId = chequeBancoId;
   }

   @Column(name = "CHEQUE_CUENTA_BANCARIA_ID")
   public java.lang.Long getChequeCuentaBancariaId() {
      return chequeCuentaBancariaId;
   }

   public void setChequeCuentaBancariaId(java.lang.Long chequeCuentaBancariaId) {
      this.chequeCuentaBancariaId = chequeCuentaBancariaId;
   }

   @Column(name = "CHEQUE_NUMERO")
   public java.lang.String getChequeNumero() {
      return chequeNumero;
   }

   public void setChequeNumero(java.lang.String chequeNumero) {
      this.chequeNumero = chequeNumero;
   }

   @Column(name = "DEPOSITO_BANCO_ID")
   public java.lang.Long getDepositoBancoId() {
      return depositoBancoId;
   }

   public void setDepositoBancoId(java.lang.Long depositoBancoId) {
      this.depositoBancoId = depositoBancoId;
   }

   @Column(name = "DEPOSITO_CUENTA_BANCARIA_ID")
   public java.lang.Long getDepositoCuentaBancariaId() {
      return depositoCuentaBancariaId;
   }

   public void setDepositoCuentaBancariaId(java.lang.Long depositoCuentaBancariaId) {
      this.depositoCuentaBancariaId = depositoCuentaBancariaId;
   }

   @Column(name = "RETENCION_NUMERO")
   public java.lang.String getRetencionNumero() {
      return retencionNumero;
   }

   public void setRetencionNumero(java.lang.String retencionNumero) {
      this.retencionNumero = retencionNumero;
   }

   @Column(name = "RETENCION_AUTORIZACION")
   public java.lang.String getRetencionAutorizacion() {
      return retencionAutorizacion;
   }

   public void setRetencionAutorizacion(java.lang.String retencionAutorizacion) {
      this.retencionAutorizacion = retencionAutorizacion;
   }

   @Column(name = "VALOR_RETENCION_RENTA")
   public java.math.BigDecimal getValorRetencionRenta() {
      return valorRetencionRenta;
   }

   public void setValorRetencionRenta(java.math.BigDecimal valorRetencionRenta) {
      this.valorRetencionRenta = valorRetencionRenta;
   }

   @Column(name = "VALOR_RETENCION_IVA")
   public java.math.BigDecimal getValorRetencionIva() {
      return valorRetencionIva;
   }

   public void setValorRetencionIva(java.math.BigDecimal valorRetencionIva) {
      this.valorRetencionIva = valorRetencionIva;
   }

   @Column(name = "DEBITO_BANCO_ID")
   public java.lang.Long getDebitoBancoId() {
      return debitoBancoId;
   }

   public void setDebitoBancoId(java.lang.Long debitoBancoId) {
      this.debitoBancoId = debitoBancoId;
   }

   @Column(name = "DEBITO_CUENTA_BANCARIA_ID")
   public java.lang.Long getDebitoCuentaBancariaId() {
      return debitoCuentaBancariaId;
   }

   public void setDebitoCuentaBancariaId(java.lang.Long debitoCuentaBancariaId) {
      this.debitoCuentaBancariaId = debitoCuentaBancariaId;
   }

   @Column(name = "DEBITO_REFERENCIA")
   public java.lang.String getDebitoReferencia() {
      return debitoReferencia;
   }

   public void setDebitoReferencia(java.lang.String debitoReferencia) {
      this.debitoReferencia = debitoReferencia;
   }

   @Column(name = "TRANSFERENCIA_BANCO_ORIGEN_ID")
   public java.lang.Long getTransferenciaBancoOrigenId() {
      return transferenciaBancoOrigenId;
   }

   public void setTransferenciaBancoOrigenId(java.lang.Long transferenciaBancoOrigenId) {
      this.transferenciaBancoOrigenId = transferenciaBancoOrigenId;
   }

   @Column(name = "TRANSFERENCIA_CUENTA_ORIGEN_ID")
   public java.lang.Long getTransferenciaCuentaOrigenId() {
      return transferenciaCuentaOrigenId;
   }

   public void setTransferenciaCuentaOrigenId(java.lang.Long transferenciaCuentaOrigenId) {
      this.transferenciaCuentaOrigenId = transferenciaCuentaOrigenId;
   }

   @Column(name = "TRANSFERENCIA_BANCO_DESTINO_ID")
   public java.lang.Long getTransferenciaBancoDestinoId() {
      return transferenciaBancoDestinoId;
   }

   public void setTransferenciaBancoDestinoId(java.lang.Long transferenciaBancoDestinoId) {
      this.transferenciaBancoDestinoId = transferenciaBancoDestinoId;
   }

   @Column(name = "TRANSFERENCIA_CUENTA_DESTINO_ID")
   public java.lang.Long getTransferenciaCuentaDestinoId() {
      return transferenciaCuentaDestinoId;
   }

   public void setTransferenciaCuentaDestinoId(java.lang.Long transferenciaCuentaDestinoId) {
      this.transferenciaCuentaDestinoId = transferenciaCuentaDestinoId;
   }

   @Column(name = "TARJETA_CREDITO_BANCO_ID")
   public java.lang.Long getTarjetaCreditoBancoId() {
      return tarjetaCreditoBancoId;
   }

   public void setTarjetaCreditoBancoId(java.lang.Long tarjetaCreditoBancoId) {
      this.tarjetaCreditoBancoId = tarjetaCreditoBancoId;
   }

   @Column(name = "TARJETA_CREDITO_ID")
   public java.lang.Long getTarjetaCreditoId() {
      return tarjetaCreditoId;
   }

   public void setTarjetaCreditoId(java.lang.Long tarjetaCreditoId) {
      this.tarjetaCreditoId = tarjetaCreditoId;
   }

   @Column(name = "VOUCHER_REFERENCIA")
   public java.lang.String getVoucherReferencia() {
      return voucherReferencia;
   }

   public void setVoucherReferencia(java.lang.String voucherReferencia) {
      this.voucherReferencia = voucherReferencia;
   }

   @Column(name = "PAGO_ELECTRONICO_REFERENCIA")
   public java.lang.String getPagoElectronicoReferencia() {
      return pagoElectronicoReferencia;
   }

   public void setPagoElectronicoReferencia(java.lang.String pagoElectronicoReferencia) {
      this.pagoElectronicoReferencia = pagoElectronicoReferencia;
   }
	public String toString() {
		return ToStringer.toString((CarteraDetalleIf)this);
	}
}
