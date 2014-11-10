package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CompraDetalleData implements CompraDetalleIf, Serializable    {


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

   private java.lang.Long compraId;

   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.lang.Long cantidad;

   public java.lang.Long getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.lang.Long cantidad) {
      this.cantidad = cantidad;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.math.BigDecimal descuento;

   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
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

   private java.lang.Long idSriAir;

   public java.lang.Long getIdSriAir() {
      return idSriAir;
   }

   public void setIdSriAir(java.lang.Long idSriAir) {
      this.idSriAir = idSriAir;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.Long sriIvaRetencionId;

   public java.lang.Long getSriIvaRetencionId() {
      return sriIvaRetencionId;
   }

   public void setSriIvaRetencionId(java.lang.Long sriIvaRetencionId) {
      this.sriIvaRetencionId = sriIvaRetencionId;
   }

   private java.math.BigDecimal porcentajeDescuentosVarios;

   public java.math.BigDecimal getPorcentajeDescuentosVarios() {
      return porcentajeDescuentosVarios;
   }

   public void setPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) {
      this.porcentajeDescuentosVarios = porcentajeDescuentosVarios;
   }

   private java.math.BigDecimal porcentajeDescuentoEspecial;

   public java.math.BigDecimal getPorcentajeDescuentoEspecial() {
      return porcentajeDescuentoEspecial;
   }

   public void setPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) {
      this.porcentajeDescuentoEspecial = porcentajeDescuentoEspecial;
   }
   public CompraDetalleData() {
   }

   public CompraDetalleData(com.spirit.compras.entity.CompraDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setCompraId(value.getCompraId());
      setProductoId(value.getProductoId());
      setCantidad(value.getCantidad());
      setValor(value.getValor());
      setDescuento(value.getDescuento());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setIdSriAir(value.getIdSriAir());
      setDescripcion(value.getDescripcion());
      setSriIvaRetencionId(value.getSriIvaRetencionId());
      setPorcentajeDescuentosVarios(value.getPorcentajeDescuentosVarios());
      setPorcentajeDescuentoEspecial(value.getPorcentajeDescuentoEspecial());
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
		return ToStringer.toString((CompraDetalleIf)this);
	}
}
