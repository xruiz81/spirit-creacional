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

@Table(name = "FACTURA_DETALLE")
@Entity
public class FacturaDetalleEJB implements Serializable, FacturaDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long documentoId;
   private java.lang.Long facturaId;
   private java.lang.Long productoId;
   private java.lang.Long loteId;
   private java.lang.String descripcion;
   private java.lang.Long motivodocumentoId;
   private java.math.BigDecimal cantidad;
   private java.math.BigDecimal precio;
   private java.math.BigDecimal precioReal;
   private java.math.BigDecimal descuento;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal ice;
   private java.math.BigDecimal otroImpuesto;
   private java.math.BigDecimal costo;
   private java.lang.Long lineaId;
   private java.math.BigDecimal cantidadDevuelta;
   private java.math.BigDecimal descuentoGlobal;
   private java.lang.Long idSriClienteRetencion;
   private java.math.BigDecimal porcentajeDescuentosVarios;
   private java.lang.String comprasReembolsoAsociadas;

   public FacturaDetalleEJB() {
   }

   public FacturaDetalleEJB(com.spirit.facturacion.entity.FacturaDetalleIf value) {
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

   public java.lang.Long create(com.spirit.facturacion.entity.FacturaDetalleIf value) {
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

   @Column(name = "FACTURA_ID")
   public java.lang.Long getFacturaId() {
      return facturaId;
   }

   public void setFacturaId(java.lang.Long facturaId) {
      this.facturaId = facturaId;
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

   @Column(name = "PRECIO_REAL")
   public java.math.BigDecimal getPrecioReal() {
      return precioReal;
   }

   public void setPrecioReal(java.math.BigDecimal precioReal) {
      this.precioReal = precioReal;
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

   @Column(name = "OTRO_IMPUESTO")
   public java.math.BigDecimal getOtroImpuesto() {
      return otroImpuesto;
   }

   public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
      this.otroImpuesto = otroImpuesto;
   }

   @Column(name = "COSTO")
   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   @Column(name = "LINEA_ID")
   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   @Column(name = "CANTIDAD_DEVUELTA")
   public java.math.BigDecimal getCantidadDevuelta() {
      return cantidadDevuelta;
   }

   public void setCantidadDevuelta(java.math.BigDecimal cantidadDevuelta) {
      this.cantidadDevuelta = cantidadDevuelta;
   }

   @Column(name = "DESCUENTO_GLOBAL")
   public java.math.BigDecimal getDescuentoGlobal() {
      return descuentoGlobal;
   }

   public void setDescuentoGlobal(java.math.BigDecimal descuentoGlobal) {
      this.descuentoGlobal = descuentoGlobal;
   }

   @Column(name = "ID_SRI_CLIENTE_RETENCION")
   public java.lang.Long getIdSriClienteRetencion() {
      return idSriClienteRetencion;
   }

   public void setIdSriClienteRetencion(java.lang.Long idSriClienteRetencion) {
      this.idSriClienteRetencion = idSriClienteRetencion;
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
		return ToStringer.toString((FacturaDetalleIf)this);
	}
}
