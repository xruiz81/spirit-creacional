package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ChequeEmitidoData implements ChequeEmitidoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.sql.Date fechaEmision;

   public java.sql.Date getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Date fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   private java.lang.Long cuentaBancariaId;

   public java.lang.Long getCuentaBancariaId() {
      return cuentaBancariaId;
   }

   public void setCuentaBancariaId(java.lang.Long cuentaBancariaId) {
      this.cuentaBancariaId = cuentaBancariaId;
   }

   private java.lang.String numero;

   public java.lang.String getNumero() {
      return numero;
   }

   public void setNumero(java.lang.String numero) {
      this.numero = numero;
   }

   private java.lang.String detalle;

   public java.lang.String getDetalle() {
      return detalle;
   }

   public void setDetalle(java.lang.String detalle) {
      this.detalle = detalle;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long tipoDocumentoId;

   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   private java.lang.Long transaccionId;

   public java.lang.Long getTransaccionId() {
      return transaccionId;
   }

   public void setTransaccionId(java.lang.Long transaccionId) {
      this.transaccionId = transaccionId;
   }

   private java.lang.String beneficiario;

   public java.lang.String getBeneficiario() {
      return beneficiario;
   }

   public void setBeneficiario(java.lang.String beneficiario) {
      this.beneficiario = beneficiario;
   }

   private java.sql.Date fechaCobro;

   public java.sql.Date getFechaCobro() {
      return fechaCobro;
   }

   public void setFechaCobro(java.sql.Date fechaCobro) {
      this.fechaCobro = fechaCobro;
   }

   private java.lang.String origen;

   public java.lang.String getOrigen() {
      return origen;
   }

   public void setOrigen(java.lang.String origen) {
      this.origen = origen;
   }
   public ChequeEmitidoData() {
   }

   public ChequeEmitidoData(com.spirit.contabilidad.entity.ChequeEmitidoIf value) {
      setId(value.getId());
      setFechaEmision(value.getFechaEmision());
      setCuentaBancariaId(value.getCuentaBancariaId());
      setNumero(value.getNumero());
      setDetalle(value.getDetalle());
      setValor(value.getValor());
      setEstado(value.getEstado());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setTransaccionId(value.getTransaccionId());
      setBeneficiario(value.getBeneficiario());
      setFechaCobro(value.getFechaCobro());
      setOrigen(value.getOrigen());
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
		return ToStringer.toString((ChequeEmitidoIf)this);
	}
}
