package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class NotaCreditoData implements NotaCreditoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long tipoDocumentoId;

   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.Long operadorNegocioId;

   public java.lang.Long getOperadorNegocioId() {
      return operadorNegocioId;
   }

   public void setOperadorNegocioId(java.lang.Long operadorNegocioId) {
      this.operadorNegocioId = operadorNegocioId;
   }

   private java.lang.Long monedaId;

   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.sql.Timestamp fechaEmision;

   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   private java.sql.Timestamp fechaVencimiento;

   public java.sql.Timestamp getFechaVencimiento() {
      return fechaVencimiento;
   }

   public void setFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
      this.fechaVencimiento = fechaVencimiento;
   }

   private java.sql.Timestamp fechaCaducidad;

   public java.sql.Timestamp getFechaCaducidad() {
      return fechaCaducidad;
   }

   public void setFechaCaducidad(java.sql.Timestamp fechaCaducidad) {
      this.fechaCaducidad = fechaCaducidad;
   }

   private java.lang.String preimpreso;

   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   private java.lang.String autorizacion;

   public java.lang.String getAutorizacion() {
      return autorizacion;
   }

   public void setAutorizacion(java.lang.String autorizacion) {
      this.autorizacion = autorizacion;
   }

   private java.lang.String referencia;

   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.math.BigDecimal iva;

   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   private java.math.BigDecimal ice;

   public java.math.BigDecimal getIce() {
      return ice;
   }

   public void setIce(java.math.BigDecimal ice) {
      this.ice = ice;
   }

   private java.math.BigDecimal otroImpuesto;

   public java.math.BigDecimal getOtroImpuesto() {
      return otroImpuesto;
   }

   public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
      this.otroImpuesto = otroImpuesto;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String tipoCartera;

   public java.lang.String getTipoCartera() {
      return tipoCartera;
   }

   public void setTipoCartera(java.lang.String tipoCartera) {
      this.tipoCartera = tipoCartera;
   }

   private java.lang.Long referenciaId;

   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }
   public NotaCreditoData() {
   }

   public NotaCreditoData(com.spirit.cartera.entity.NotaCreditoIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setCodigo(value.getCodigo());
      setOperadorNegocioId(value.getOperadorNegocioId());
      setMonedaId(value.getMonedaId());
      setUsuarioId(value.getUsuarioId());
      setFechaEmision(value.getFechaEmision());
      setFechaVencimiento(value.getFechaVencimiento());
      setFechaCaducidad(value.getFechaCaducidad());
      setPreimpreso(value.getPreimpreso());
      setAutorizacion(value.getAutorizacion());
      setReferencia(value.getReferencia());
      setValor(value.getValor());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setTipoCartera(value.getTipoCartera());
      setReferenciaId(value.getReferenciaId());
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
		return ToStringer.toString((NotaCreditoIf)this);
	}
}
