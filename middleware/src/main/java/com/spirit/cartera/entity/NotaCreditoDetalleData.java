package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class NotaCreditoDetalleData implements NotaCreditoDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.lang.Long notaCreditoId;

   public java.lang.Long getNotaCreditoId() {
      return notaCreditoId;
   }

   public void setNotaCreditoId(java.lang.Long notaCreditoId) {
      this.notaCreditoId = notaCreditoId;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
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

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.String tipoReferencia;

   public java.lang.String getTipoReferencia() {
      return tipoReferencia;
   }

   public void setTipoReferencia(java.lang.String tipoReferencia) {
      this.tipoReferencia = tipoReferencia;
   }

   private java.lang.Long referenciaId;

   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   private java.lang.String tipoNota;

   public java.lang.String getTipoNota() {
      return tipoNota;
   }

   public void setTipoNota(java.lang.String tipoNota) {
      this.tipoNota = tipoNota;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String tipoPresupuesto;

   public java.lang.String getTipoPresupuesto() {
      return tipoPresupuesto;
   }

   public void setTipoPresupuesto(java.lang.String tipoPresupuesto) {
      this.tipoPresupuesto = tipoPresupuesto;
   }

   private java.lang.Long presupuestoId;

   public java.lang.Long getPresupuestoId() {
      return presupuestoId;
   }

   public void setPresupuestoId(java.lang.Long presupuestoId) {
      this.presupuestoId = presupuestoId;
   }

   private java.lang.Long ordenId;

   public java.lang.Long getOrdenId() {
      return ordenId;
   }

   public void setOrdenId(java.lang.Long ordenId) {
      this.ordenId = ordenId;
   }
   public NotaCreditoDetalleData() {
   }

   public NotaCreditoDetalleData(com.spirit.cartera.entity.NotaCreditoDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setNotaCreditoId(value.getNotaCreditoId());
      setProductoId(value.getProductoId());
      setCantidad(value.getCantidad());
      setValor(value.getValor());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setDescripcion(value.getDescripcion());
      setTipoReferencia(value.getTipoReferencia());
      setReferenciaId(value.getReferenciaId());
      setTipoNota(value.getTipoNota());
      setObservacion(value.getObservacion());
      setTipoPresupuesto(value.getTipoPresupuesto());
      setPresupuestoId(value.getPresupuestoId());
      setOrdenId(value.getOrdenId());
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
		return ToStringer.toString((NotaCreditoDetalleIf)this);
	}
}
