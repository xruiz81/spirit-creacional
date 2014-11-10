package com.spirit.compras.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ORDEN_COMPRA_DETALLE")
@Entity
public class OrdenCompraDetalleEJB implements Serializable, OrdenCompraDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long documentoId;
   private java.lang.Long productoId;
   private java.lang.Long ordencompraId;
   private java.lang.Long cantidad;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal descuentoAgenciaCompra;
   private java.math.BigDecimal iva;
   private java.math.BigDecimal ice;
   private java.math.BigDecimal otroImpuesto;
   private java.lang.String descripcion;
   private java.math.BigDecimal porcentajeNegociacionDirecta;
   private java.math.BigDecimal porcentajeComisionPura;
   private java.math.BigDecimal porcentajeDescuentosVariosCompra;
   private java.math.BigDecimal porcentajeDescuentoEspecial;
   private java.sql.Timestamp fechaPublicacion;

   public OrdenCompraDetalleEJB() {
   }

   public OrdenCompraDetalleEJB(com.spirit.compras.entity.OrdenCompraDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setProductoId(value.getProductoId());
      setOrdencompraId(value.getOrdencompraId());
      setCantidad(value.getCantidad());
      setValor(value.getValor());
      setDescuentoAgenciaCompra(value.getDescuentoAgenciaCompra());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setDescripcion(value.getDescripcion());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeComisionPura(value.getPorcentajeComisionPura());
      setPorcentajeDescuentosVariosCompra(value.getPorcentajeDescuentosVariosCompra());
      setPorcentajeDescuentoEspecial(value.getPorcentajeDescuentoEspecial());
      setFechaPublicacion(value.getFechaPublicacion());
   }

   public java.lang.Long create(com.spirit.compras.entity.OrdenCompraDetalleIf value) {
      setId(value.getId());
      setDocumentoId(value.getDocumentoId());
      setProductoId(value.getProductoId());
      setOrdencompraId(value.getOrdencompraId());
      setCantidad(value.getCantidad());
      setValor(value.getValor());
      setDescuentoAgenciaCompra(value.getDescuentoAgenciaCompra());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setDescripcion(value.getDescripcion());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeComisionPura(value.getPorcentajeComisionPura());
      setPorcentajeDescuentosVariosCompra(value.getPorcentajeDescuentosVariosCompra());
      setPorcentajeDescuentoEspecial(value.getPorcentajeDescuentoEspecial());
      setFechaPublicacion(value.getFechaPublicacion());
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

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "ORDENCOMPRA_ID")
   public java.lang.Long getOrdencompraId() {
      return ordencompraId;
   }

   public void setOrdencompraId(java.lang.Long ordencompraId) {
      this.ordencompraId = ordencompraId;
   }

   @Column(name = "CANTIDAD")
   public java.lang.Long getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.lang.Long cantidad) {
      this.cantidad = cantidad;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "DESCUENTO_AGENCIA_COMPRA")
   public java.math.BigDecimal getDescuentoAgenciaCompra() {
      return descuentoAgenciaCompra;
   }

   public void setDescuentoAgenciaCompra(java.math.BigDecimal descuentoAgenciaCompra) {
      this.descuentoAgenciaCompra = descuentoAgenciaCompra;
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

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "PORCENTAJE_NEGOCIACION_DIRECTA")
   public java.math.BigDecimal getPorcentajeNegociacionDirecta() {
      return porcentajeNegociacionDirecta;
   }

   public void setPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) {
      this.porcentajeNegociacionDirecta = porcentajeNegociacionDirecta;
   }

   @Column(name = "PORCENTAJE_COMISION_PURA")
   public java.math.BigDecimal getPorcentajeComisionPura() {
      return porcentajeComisionPura;
   }

   public void setPorcentajeComisionPura(java.math.BigDecimal porcentajeComisionPura) {
      this.porcentajeComisionPura = porcentajeComisionPura;
   }

   @Column(name = "PORCENTAJE_DESCUENTOS_VARIOS_COMPRA")
   public java.math.BigDecimal getPorcentajeDescuentosVariosCompra() {
      return porcentajeDescuentosVariosCompra;
   }

   public void setPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) {
      this.porcentajeDescuentosVariosCompra = porcentajeDescuentosVariosCompra;
   }

   @Column(name = "PORCENTAJE_DESCUENTO_ESPECIAL")
   public java.math.BigDecimal getPorcentajeDescuentoEspecial() {
      return porcentajeDescuentoEspecial;
   }

   public void setPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) {
      this.porcentajeDescuentoEspecial = porcentajeDescuentoEspecial;
   }

   @Column(name = "FECHA_PUBLICACION")
   public java.sql.Timestamp getFechaPublicacion() {
      return fechaPublicacion;
   }

   public void setFechaPublicacion(java.sql.Timestamp fechaPublicacion) {
      this.fechaPublicacion = fechaPublicacion;
   }
	public String toString() {
		return ToStringer.toString((OrdenCompraDetalleIf)this);
	}
}
