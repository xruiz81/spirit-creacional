package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PedidoDetalleData implements PedidoDetalleIf, Serializable    {


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

   private java.lang.Long pedidoId;

   public java.lang.Long getPedidoId() {
      return pedidoId;
   }

   public void setPedidoId(java.lang.Long pedidoId) {
      this.pedidoId = pedidoId;
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

   private java.math.BigDecimal cantidadpedida;

   public java.math.BigDecimal getCantidadpedida() {
      return cantidadpedida;
   }

   public void setCantidadpedida(java.math.BigDecimal cantidadpedida) {
      this.cantidadpedida = cantidadpedida;
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

   private java.math.BigDecimal precioreal;

   public java.math.BigDecimal getPrecioreal() {
      return precioreal;
   }

   public void setPrecioreal(java.math.BigDecimal precioreal) {
      this.precioreal = precioreal;
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

   private java.math.BigDecimal otroimpuesto;

   public java.math.BigDecimal getOtroimpuesto() {
      return otroimpuesto;
   }

   public void setOtroimpuesto(java.math.BigDecimal otroimpuesto) {
      this.otroimpuesto = otroimpuesto;
   }

   private java.math.BigDecimal descuentoGlobal;

   public java.math.BigDecimal getDescuentoGlobal() {
      return descuentoGlobal;
   }

   public void setDescuentoGlobal(java.math.BigDecimal descuentoGlobal) {
      this.descuentoGlobal = descuentoGlobal;
   }

   private java.lang.String codigoPersonalizacion;

   public java.lang.String getCodigoPersonalizacion() {
      return codigoPersonalizacion;
   }

   public void setCodigoPersonalizacion(java.lang.String codigoPersonalizacion) {
      this.codigoPersonalizacion = codigoPersonalizacion;
   }

   private java.lang.Long giftcardId;

   public java.lang.Long getGiftcardId() {
      return giftcardId;
   }

   public void setGiftcardId(java.lang.Long giftcardId) {
      this.giftcardId = giftcardId;
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
   public PedidoDetalleData() {
   }

   public PedidoDetalleData(com.spirit.facturacion.entity.PedidoDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setPedidoId(value.getPedidoId());
      setProductoId(value.getProductoId());
      setLoteId(value.getLoteId());
      setDescripcion(value.getDescripcion());
      setMotivodocumentoId(value.getMotivodocumentoId());
      setCantidadpedida(value.getCantidadpedida());
      setCantidad(value.getCantidad());
      setPrecio(value.getPrecio());
      setPrecioreal(value.getPrecioreal());
      setDescuento(value.getDescuento());
      setValor(value.getValor());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroimpuesto(value.getOtroimpuesto());
      setDescuentoGlobal(value.getDescuentoGlobal());
      setCodigoPersonalizacion(value.getCodigoPersonalizacion());
      setGiftcardId(value.getGiftcardId());
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
		return ToStringer.toString((PedidoDetalleIf)this);
	}
}
