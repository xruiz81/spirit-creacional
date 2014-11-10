package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class DocumentoData implements DocumentoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.String abreviado;

   public java.lang.String getAbreviado() {
      return abreviado;
   }

   public void setAbreviado(java.lang.String abreviado) {
      this.abreviado = abreviado;
   }

   private java.lang.Long tipoDocumentoId;

   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   private java.lang.String pideAutorizacion;

   public java.lang.String getPideAutorizacion() {
      return pideAutorizacion;
   }

   public void setPideAutorizacion(java.lang.String pideAutorizacion) {
      this.pideAutorizacion = pideAutorizacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String diferido;

   public java.lang.String getDiferido() {
      return diferido;
   }

   public void setDiferido(java.lang.String diferido) {
      this.diferido = diferido;
   }

   private java.lang.String bonificacion;

   public java.lang.String getBonificacion() {
      return bonificacion;
   }

   public void setBonificacion(java.lang.String bonificacion) {
      this.bonificacion = bonificacion;
   }

   private java.lang.String precioEspecial;

   public java.lang.String getPrecioEspecial() {
      return precioEspecial;
   }

   public void setPrecioEspecial(java.lang.String precioEspecial) {
      this.precioEspecial = precioEspecial;
   }

   private java.lang.String descuentoEspecial;

   public java.lang.String getDescuentoEspecial() {
      return descuentoEspecial;
   }

   public void setDescuentoEspecial(java.lang.String descuentoEspecial) {
      this.descuentoEspecial = descuentoEspecial;
   }

   private java.lang.String multa;

   public java.lang.String getMulta() {
      return multa;
   }

   public void setMulta(java.lang.String multa) {
      this.multa = multa;
   }

   private java.lang.String interes;

   public java.lang.String getInteres() {
      return interes;
   }

   public void setInteres(java.lang.String interes) {
      this.interes = interes;
   }

   private java.lang.String protesto;

   public java.lang.String getProtesto() {
      return protesto;
   }

   public void setProtesto(java.lang.String protesto) {
      this.protesto = protesto;
   }

   private java.lang.String cheque;

   public java.lang.String getCheque() {
      return cheque;
   }

   public void setCheque(java.lang.String cheque) {
      this.cheque = cheque;
   }

   private java.lang.String retencionRenta;

   public java.lang.String getRetencionRenta() {
      return retencionRenta;
   }

   public void setRetencionRenta(java.lang.String retencionRenta) {
      this.retencionRenta = retencionRenta;
   }

   private java.lang.String retencionIva;

   public java.lang.String getRetencionIva() {
      return retencionIva;
   }

   public void setRetencionIva(java.lang.String retencionIva) {
      this.retencionIva = retencionIva;
   }

   private java.lang.String efectivo;

   public java.lang.String getEfectivo() {
      return efectivo;
   }

   public void setEfectivo(java.lang.String efectivo) {
      this.efectivo = efectivo;
   }

   private java.lang.String debitoBancario;

   public java.lang.String getDebitoBancario() {
      return debitoBancario;
   }

   public void setDebitoBancario(java.lang.String debitoBancario) {
      this.debitoBancario = debitoBancario;
   }

   private java.lang.String tarjetaCredito;

   public java.lang.String getTarjetaCredito() {
      return tarjetaCredito;
   }

   public void setTarjetaCredito(java.lang.String tarjetaCredito) {
      this.tarjetaCredito = tarjetaCredito;
   }

   private java.lang.String transaccionElectronica;

   public java.lang.String getTransaccionElectronica() {
      return transaccionElectronica;
   }

   public void setTransaccionElectronica(java.lang.String transaccionElectronica) {
      this.transaccionElectronica = transaccionElectronica;
   }

   private java.lang.String transferenciaBancaria;

   public java.lang.String getTransferenciaBancaria() {
      return transferenciaBancaria;
   }

   public void setTransferenciaBancaria(java.lang.String transferenciaBancaria) {
      this.transferenciaBancaria = transferenciaBancaria;
   }

   private java.lang.String nivelacion;

   public java.lang.String getNivelacion() {
      return nivelacion;
   }

   public void setNivelacion(java.lang.String nivelacion) {
      this.nivelacion = nivelacion;
   }

   private java.lang.String anticipo;

   public java.lang.String getAnticipo() {
      return anticipo;
   }

   public void setAnticipo(java.lang.String anticipo) {
      this.anticipo = anticipo;
   }
   public DocumentoData() {
   }

   public DocumentoData(com.spirit.general.entity.DocumentoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setAbreviado(value.getAbreviado());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setPideAutorizacion(value.getPideAutorizacion());
      setEstado(value.getEstado());
      setDiferido(value.getDiferido());
      setBonificacion(value.getBonificacion());
      setPrecioEspecial(value.getPrecioEspecial());
      setDescuentoEspecial(value.getDescuentoEspecial());
      setMulta(value.getMulta());
      setInteres(value.getInteres());
      setProtesto(value.getProtesto());
      setCheque(value.getCheque());
      setRetencionRenta(value.getRetencionRenta());
      setRetencionIva(value.getRetencionIva());
      setEfectivo(value.getEfectivo());
      setDebitoBancario(value.getDebitoBancario());
      setTarjetaCredito(value.getTarjetaCredito());
      setTransaccionElectronica(value.getTransaccionElectronica());
      setTransferenciaBancaria(value.getTransferenciaBancaria());
      setNivelacion(value.getNivelacion());
      setAnticipo(value.getAnticipo());
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
		return ToStringer.toString((DocumentoIf)this);
	}
}
