package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PRESUPUESTO_DETALLE")
@Entity
public class PresupuestoDetalleEJB implements Serializable, PresupuestoDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long presupuestoId;
   private java.lang.Long productoId;
   private java.lang.String concepto;
   private java.math.BigDecimal cantidad;
   private java.math.BigDecimal precioagencia;
   private java.math.BigDecimal precioventa;
   private java.math.BigDecimal porcentajeDescuentoAgenciaVenta;
   private java.math.BigDecimal iva;
   private java.lang.Long proveedorId;
   private java.math.BigDecimal porcentajeDescuentoAgenciaCompra;
   private java.math.BigDecimal ivaCompra;
   private java.lang.String reporte;
   private java.lang.Integer orden;
   private java.lang.Long ordenCompraId;
   private java.math.BigDecimal porcentajeNegociacionDirecta;
   private java.math.BigDecimal porcentajeComisionPura;
   private java.math.BigDecimal porcentajeDescuentosVariosCompra;
   private java.math.BigDecimal porcentajeDescuentosVariosVenta;
   private java.math.BigDecimal porcentajeDescuentoEspecialCompra;
   private java.math.BigDecimal porcentajeDescuentoEspecialVenta;
   private java.sql.Timestamp fechaPublicacion;
   private java.math.BigDecimal porcentajeNotaCreditoCompra;
   private java.math.BigDecimal porcentajeComisionAdicional;

   public PresupuestoDetalleEJB() {
   }

   public PresupuestoDetalleEJB(com.spirit.medios.entity.PresupuestoDetalleIf value) {
      setId(value.getId());
      setPresupuestoId(value.getPresupuestoId());
      setProductoId(value.getProductoId());
      setConcepto(value.getConcepto());
      setCantidad(value.getCantidad());
      setPrecioagencia(value.getPrecioagencia());
      setPrecioventa(value.getPrecioventa());
      setPorcentajeDescuentoAgenciaVenta(value.getPorcentajeDescuentoAgenciaVenta());
      setIva(value.getIva());
      setProveedorId(value.getProveedorId());
      setPorcentajeDescuentoAgenciaCompra(value.getPorcentajeDescuentoAgenciaCompra());
      setIvaCompra(value.getIvaCompra());
      setReporte(value.getReporte());
      setOrden(value.getOrden());
      setOrdenCompraId(value.getOrdenCompraId());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeComisionPura(value.getPorcentajeComisionPura());
      setPorcentajeDescuentosVariosCompra(value.getPorcentajeDescuentosVariosCompra());
      setPorcentajeDescuentosVariosVenta(value.getPorcentajeDescuentosVariosVenta());
      setPorcentajeDescuentoEspecialCompra(value.getPorcentajeDescuentoEspecialCompra());
      setPorcentajeDescuentoEspecialVenta(value.getPorcentajeDescuentoEspecialVenta());
      setFechaPublicacion(value.getFechaPublicacion());
      setPorcentajeNotaCreditoCompra(value.getPorcentajeNotaCreditoCompra());
      setPorcentajeComisionAdicional(value.getPorcentajeComisionAdicional());
   }

   public java.lang.Long create(com.spirit.medios.entity.PresupuestoDetalleIf value) {
      setId(value.getId());
      setPresupuestoId(value.getPresupuestoId());
      setProductoId(value.getProductoId());
      setConcepto(value.getConcepto());
      setCantidad(value.getCantidad());
      setPrecioagencia(value.getPrecioagencia());
      setPrecioventa(value.getPrecioventa());
      setPorcentajeDescuentoAgenciaVenta(value.getPorcentajeDescuentoAgenciaVenta());
      setIva(value.getIva());
      setProveedorId(value.getProveedorId());
      setPorcentajeDescuentoAgenciaCompra(value.getPorcentajeDescuentoAgenciaCompra());
      setIvaCompra(value.getIvaCompra());
      setReporte(value.getReporte());
      setOrden(value.getOrden());
      setOrdenCompraId(value.getOrdenCompraId());
      setPorcentajeNegociacionDirecta(value.getPorcentajeNegociacionDirecta());
      setPorcentajeComisionPura(value.getPorcentajeComisionPura());
      setPorcentajeDescuentosVariosCompra(value.getPorcentajeDescuentosVariosCompra());
      setPorcentajeDescuentosVariosVenta(value.getPorcentajeDescuentosVariosVenta());
      setPorcentajeDescuentoEspecialCompra(value.getPorcentajeDescuentoEspecialCompra());
      setPorcentajeDescuentoEspecialVenta(value.getPorcentajeDescuentoEspecialVenta());
      setFechaPublicacion(value.getFechaPublicacion());
      setPorcentajeNotaCreditoCompra(value.getPorcentajeNotaCreditoCompra());
      setPorcentajeComisionAdicional(value.getPorcentajeComisionAdicional());
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

   @Column(name = "PRESUPUESTO_ID")
   public java.lang.Long getPresupuestoId() {
      return presupuestoId;
   }

   public void setPresupuestoId(java.lang.Long presupuestoId) {
      this.presupuestoId = presupuestoId;
   }

   @Column(name = "PRODUCTO_ID")
   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   @Column(name = "CONCEPTO")
   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   @Column(name = "PRECIOAGENCIA")
   public java.math.BigDecimal getPrecioagencia() {
      return precioagencia;
   }

   public void setPrecioagencia(java.math.BigDecimal precioagencia) {
      this.precioagencia = precioagencia;
   }

   @Column(name = "PRECIOVENTA")
   public java.math.BigDecimal getPrecioventa() {
      return precioventa;
   }

   public void setPrecioventa(java.math.BigDecimal precioventa) {
      this.precioventa = precioventa;
   }

   @Column(name = "PORCENTAJE_DESCUENTO_AGENCIA_VENTA")
   public java.math.BigDecimal getPorcentajeDescuentoAgenciaVenta() {
      return porcentajeDescuentoAgenciaVenta;
   }

   public void setPorcentajeDescuentoAgenciaVenta(java.math.BigDecimal porcentajeDescuentoAgenciaVenta) {
      this.porcentajeDescuentoAgenciaVenta = porcentajeDescuentoAgenciaVenta;
   }

   @Column(name = "IVA")
   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   @Column(name = "PROVEEDOR_ID")
   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   @Column(name = "PORCENTAJE_DESCUENTO_AGENCIA_COMPRA")
   public java.math.BigDecimal getPorcentajeDescuentoAgenciaCompra() {
      return porcentajeDescuentoAgenciaCompra;
   }

   public void setPorcentajeDescuentoAgenciaCompra(java.math.BigDecimal porcentajeDescuentoAgenciaCompra) {
      this.porcentajeDescuentoAgenciaCompra = porcentajeDescuentoAgenciaCompra;
   }

   @Column(name = "IVA_COMPRA")
   public java.math.BigDecimal getIvaCompra() {
      return ivaCompra;
   }

   public void setIvaCompra(java.math.BigDecimal ivaCompra) {
      this.ivaCompra = ivaCompra;
   }

   @Column(name = "REPORTE")
   public java.lang.String getReporte() {
      return reporte;
   }

   public void setReporte(java.lang.String reporte) {
      this.reporte = reporte;
   }

   @Column(name = "ORDEN")
   public java.lang.Integer getOrden() {
      return orden;
   }

   public void setOrden(java.lang.Integer orden) {
      this.orden = orden;
   }

   @Column(name = "ORDEN_COMPRA_ID")
   public java.lang.Long getOrdenCompraId() {
      return ordenCompraId;
   }

   public void setOrdenCompraId(java.lang.Long ordenCompraId) {
      this.ordenCompraId = ordenCompraId;
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

   @Column(name = "PORCENTAJE_DESCUENTOS_VARIOS_VENTA")
   public java.math.BigDecimal getPorcentajeDescuentosVariosVenta() {
      return porcentajeDescuentosVariosVenta;
   }

   public void setPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta) {
      this.porcentajeDescuentosVariosVenta = porcentajeDescuentosVariosVenta;
   }

   @Column(name = "PORCENTAJE_DESCUENTO_ESPECIAL_COMPRA")
   public java.math.BigDecimal getPorcentajeDescuentoEspecialCompra() {
      return porcentajeDescuentoEspecialCompra;
   }

   public void setPorcentajeDescuentoEspecialCompra(java.math.BigDecimal porcentajeDescuentoEspecialCompra) {
      this.porcentajeDescuentoEspecialCompra = porcentajeDescuentoEspecialCompra;
   }

   @Column(name = "PORCENTAJE_DESCUENTO_ESPECIAL_VENTA")
   public java.math.BigDecimal getPorcentajeDescuentoEspecialVenta() {
      return porcentajeDescuentoEspecialVenta;
   }

   public void setPorcentajeDescuentoEspecialVenta(java.math.BigDecimal porcentajeDescuentoEspecialVenta) {
      this.porcentajeDescuentoEspecialVenta = porcentajeDescuentoEspecialVenta;
   }

   @Column(name = "FECHA_PUBLICACION")
   public java.sql.Timestamp getFechaPublicacion() {
      return fechaPublicacion;
   }

   public void setFechaPublicacion(java.sql.Timestamp fechaPublicacion) {
      this.fechaPublicacion = fechaPublicacion;
   }

   @Column(name = "PORCENTAJE_NOTA_CREDITO_COMPRA")
   public java.math.BigDecimal getPorcentajeNotaCreditoCompra() {
      return porcentajeNotaCreditoCompra;
   }

   public void setPorcentajeNotaCreditoCompra(java.math.BigDecimal porcentajeNotaCreditoCompra) {
      this.porcentajeNotaCreditoCompra = porcentajeNotaCreditoCompra;
   }

   @Column(name = "PORCENTAJE_COMISION_ADICIONAL")
   public java.math.BigDecimal getPorcentajeComisionAdicional() {
      return porcentajeComisionAdicional;
   }

   public void setPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional) {
      this.porcentajeComisionAdicional = porcentajeComisionAdicional;
   }
	public String toString() {
		return ToStringer.toString((PresupuestoDetalleIf)this);
	}
}
