package com.spirit.facturacion.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PEDIDO_DETALLE")
@Entity
public class PedidoDetalleEJB implements Serializable, PedidoDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long documentoId;
   private java.lang.Long pedidoId;
   private java.lang.Long productoId;
   private java.lang.Long loteId;
   private java.lang.String descripcion;
   private java.lang.Long motivodocumentoId;
   private java.math.BigDecimal cantidadpedida;
   private java.math.BigDecimal cantidad;
   private java.math.BigDecimal precio;
   private java.math.BigDecimal precioreal;
   private java.math.BigDecimal descuento;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal ice;
   private java.math.BigDecimal otroimpuesto;
   private java.math.BigDecimal descuentoGlobal;
   private java.lang.String codigoPersonalizacion;
   private java.lang.Long giftcardId;
   private java.math.BigDecimal porcentajeDescuentosVarios;
   private java.lang.String comprasReembolsoAsociadas;

   public PedidoDetalleEJB() {
   }

   public PedidoDetalleEJB(com.spirit.facturacion.entity.PedidoDetalleIf value) {
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

   public java.lang.Long create(com.spirit.facturacion.entity.PedidoDetalleIf value) {
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

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "PEDIDO_ID")
   public java.lang.Long getPedidoId() {
      return pedidoId;
   }

   public void setPedidoId(java.lang.Long pedidoId) {
      this.pedidoId = pedidoId;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "LOTE_ID")
   public java.lang.Long getLoteId() {
      return loteId;
   }

   public void setLoteId(java.lang.Long loteId) {
      this.loteId = loteId;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "MOTIVODOCUMENTO_ID")
   public java.lang.Long getMotivodocumentoId() {
      return motivodocumentoId;
   }

   public void setMotivodocumentoId(java.lang.Long motivodocumentoId) {
      this.motivodocumentoId = motivodocumentoId;
   }

   @Column(name = "CANTIDADPEDIDA")
   public java.math.BigDecimal getCantidadpedida() {
      return cantidadpedida;
   }

   public void setCantidadpedida(java.math.BigDecimal cantidadpedida) {
      this.cantidadpedida = cantidadpedida;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   @Column(name = "PRECIO")
   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }

   @Column(name = "PRECIOREAL")
   public java.math.BigDecimal getPrecioreal() {
      return precioreal;
   }

   public void setPrecioreal(java.math.BigDecimal precioreal) {
      this.precioreal = precioreal;
   }

   @Column(name = "DESCUENTO")
   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "IVA")
   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   @Column(name = "ICE")
   public java.math.BigDecimal getIce() {
      return ice;
   }

   public void setIce(java.math.BigDecimal ice) {
      this.ice = ice;
   }

   @Column(name = "OTROIMPUESTO")
   public java.math.BigDecimal getOtroimpuesto() {
      return otroimpuesto;
   }

   public void setOtroimpuesto(java.math.BigDecimal otroimpuesto) {
      this.otroimpuesto = otroimpuesto;
   }

   @Column(name = "DESCUENTO_GLOBAL")
   public java.math.BigDecimal getDescuentoGlobal() {
      return descuentoGlobal;
   }

   public void setDescuentoGlobal(java.math.BigDecimal descuentoGlobal) {
      this.descuentoGlobal = descuentoGlobal;
   }

   @Column(name = "CODIGO_PERSONALIZACION")
   public java.lang.String getCodigoPersonalizacion() {
      return codigoPersonalizacion;
   }

   public void setCodigoPersonalizacion(java.lang.String codigoPersonalizacion) {
      this.codigoPersonalizacion = codigoPersonalizacion;
   }

   @Column(name = "GIFTCARD_ID")
   public java.lang.Long getGiftcardId() {
      return giftcardId;
   }

   public void setGiftcardId(java.lang.Long giftcardId) {
      this.giftcardId = giftcardId;
   }

   @Column(name = "PORCENTAJE_DESCUENTOS_VARIOS")
   public java.math.BigDecimal getPorcentajeDescuentosVarios() {
      return porcentajeDescuentosVarios;
   }

   public void setPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) {
      this.porcentajeDescuentosVarios = porcentajeDescuentosVarios;
   }

   @Column(name = "COMPRAS_REEMBOLSO_ASOCIADAS")
   public java.lang.String getComprasReembolsoAsociadas() {
      return comprasReembolsoAsociadas;
   }

   public void setComprasReembolsoAsociadas(java.lang.String comprasReembolsoAsociadas) {
      this.comprasReembolsoAsociadas = comprasReembolsoAsociadas;
   }
	public String toString() {
		return ToStringer.toString((PedidoDetalleIf)this);
	}
}
