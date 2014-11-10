package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class FacturaDetalleData implements FacturaDetalleIf, Serializable    {


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

   private java.lang.Long facturaId;

   public java.lang.Long getFacturaId() {
      return facturaId;
   }

   public void setFacturaId(java.lang.Long facturaId) {
      this.facturaId = facturaId;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.lang.Long loteId;

   public java.lang.Long getLoteId() {
      return loteId;
   }

   public void setLoteId(java.lang.Long loteId) {
      this.loteId = loteId;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.Long motivodocumentoId;

   public java.lang.Long getMotivodocumentoId() {
      return motivodocumentoId;
   }

   public void setMotivodocumentoId(java.lang.Long motivodocumentoId) {
      this.motivodocumentoId = motivodocumentoId;
   }

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   private java.math.BigDecimal precio;

   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }

   private java.math.BigDecimal precioReal;

   public java.math.BigDecimal getPrecioReal() {
      return precioReal;
   }

   public void setPrecioReal(java.math.BigDecimal precioReal) {
      this.precioReal = precioReal;
   }

   private java.math.BigDecimal descuento;

   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
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

   private java.math.BigDecimal costo;

   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   private java.lang.Long lineaId;

   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   private java.math.BigDecimal cantidadDevuelta;

   public java.math.BigDecimal getCantidadDevuelta() {
      return cantidadDevuelta;
   }

   public void setCantidadDevuelta(java.math.BigDecimal cantidadDevuelta) {
      this.cantidadDevuelta = cantidadDevuelta;
   }

   private java.math.BigDecimal descuentoGlobal;

   public java.math.BigDecimal getDescuentoGlobal() {
      return descuentoGlobal;
   }

   public void setDescuentoGlobal(java.math.BigDecimal descuentoGlobal) {
      this.descuentoGlobal = descuentoGlobal;
   }

   private java.lang.Long idSriClienteRetencion;

   public java.lang.Long getIdSriClienteRetencion() {
      return idSriClienteRetencion;
   }

   public void setIdSriClienteRetencion(java.lang.Long idSriClienteRetencion) {
      this.idSriClienteRetencion = idSriClienteRetencion;
   }

   private java.math.BigDecimal porcentajeDescuentosVarios;

   public java.math.BigDecimal getPorcentajeDescuentosVarios() {
      return porcentajeDescuentosVarios;
   }

   public void setPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) {
      this.porcentajeDescuentosVarios = porcentajeDescuentosVarios;
   }

   private java.lang.String comprasReembolsoAsociadas;

   public java.lang.String getComprasReembolsoAsociadas() {
      return comprasReembolsoAsociadas;
   }

   public void setComprasReembolsoAsociadas(java.lang.String comprasReembolsoAsociadas) {
      this.comprasReembolsoAsociadas = comprasReembolsoAsociadas;
   }
   public FacturaDetalleData() {
   }

   public FacturaDetalleData(com.spirit.facturacion.entity.FacturaDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setFacturaId(value.getFacturaId());
      setProductoId(value.getProductoId());
      setLoteId(value.getLoteId());
      setDescripcion(value.getDescripcion());
      setMotivodocumentoId(value.getMotivodocumentoId());
      setCantidad(value.getCantidad());
      setPrecio(value.getPrecio());
      setPrecioReal(value.getPrecioReal());
      setDescuento(value.getDescuento());
      setValor(value.getValor());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setCosto(value.getCosto());
      setLineaId(value.getLineaId());
      setCantidadDevuelta(value.getCantidadDevuelta());
      setDescuentoGlobal(value.getDescuentoGlobal());
      setIdSriClienteRetencion(value.getIdSriClienteRetencion());
      setPorcentajeDescuentosVarios(value.getPorcentajeDescuentosVarios());
      setComprasReembolsoAsociadas(value.getComprasReembolsoAsociadas());
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
		return ToStringer.toString((FacturaDetalleIf)this);
	}
}
