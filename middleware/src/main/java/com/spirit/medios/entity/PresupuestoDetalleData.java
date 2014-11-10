package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PresupuestoDetalleData implements PresupuestoDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long presupuestoId;

   public java.lang.Long getPresupuestoId() {
      return presupuestoId;
   }

   public void setPresupuestoId(java.lang.Long presupuestoId) {
      this.presupuestoId = presupuestoId;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.lang.String concepto;

   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   private java.math.BigDecimal precioagencia;

   public java.math.BigDecimal getPrecioagencia() {
      return precioagencia;
   }

   public void setPrecioagencia(java.math.BigDecimal precioagencia) {
      this.precioagencia = precioagencia;
   }

   private java.math.BigDecimal precioventa;

   public java.math.BigDecimal getPrecioventa() {
      return precioventa;
   }

   public void setPrecioventa(java.math.BigDecimal precioventa) {
      this.precioventa = precioventa;
   }

   private java.math.BigDecimal porcentajeDescuentoAgenciaVenta;

   public java.math.BigDecimal getPorcentajeDescuentoAgenciaVenta() {
      return porcentajeDescuentoAgenciaVenta;
   }

   public void setPorcentajeDescuentoAgenciaVenta(java.math.BigDecimal porcentajeDescuentoAgenciaVenta) {
      this.porcentajeDescuentoAgenciaVenta = porcentajeDescuentoAgenciaVenta;
   }

   private java.math.BigDecimal iva;

   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   private java.lang.Long proveedorId;

   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   private java.math.BigDecimal porcentajeDescuentoAgenciaCompra;

   public java.math.BigDecimal getPorcentajeDescuentoAgenciaCompra() {
      return porcentajeDescuentoAgenciaCompra;
   }

   public void setPorcentajeDescuentoAgenciaCompra(java.math.BigDecimal porcentajeDescuentoAgenciaCompra) {
      this.porcentajeDescuentoAgenciaCompra = porcentajeDescuentoAgenciaCompra;
   }

   private java.math.BigDecimal ivaCompra;

   public java.math.BigDecimal getIvaCompra() {
      return ivaCompra;
   }

   public void setIvaCompra(java.math.BigDecimal ivaCompra) {
      this.ivaCompra = ivaCompra;
   }

   private java.lang.String reporte;

   public java.lang.String getReporte() {
      return reporte;
   }

   public void setReporte(java.lang.String reporte) {
      this.reporte = reporte;
   }

   private java.lang.Integer orden;

   public java.lang.Integer getOrden() {
      return orden;
   }

   public void setOrden(java.lang.Integer orden) {
      this.orden = orden;
   }

   private java.lang.Long ordenCompraId;

   public java.lang.Long getOrdenCompraId() {
      return ordenCompraId;
   }

   public void setOrdenCompraId(java.lang.Long ordenCompraId) {
      this.ordenCompraId = ordenCompraId;
   }

   private java.math.BigDecimal porcentajeNegociacionDirecta;

   public java.math.BigDecimal getPorcentajeNegociacionDirecta() {
      return porcentajeNegociacionDirecta;
   }

   public void setPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) {
      this.porcentajeNegociacionDirecta = porcentajeNegociacionDirecta;
   }

   private java.math.BigDecimal porcentajeComisionPura;

   public java.math.BigDecimal getPorcentajeComisionPura() {
      return porcentajeComisionPura;
   }

   public void setPorcentajeComisionPura(java.math.BigDecimal porcentajeComisionPura) {
      this.porcentajeComisionPura = porcentajeComisionPura;
   }

   private java.math.BigDecimal porcentajeDescuentosVariosCompra;

   public java.math.BigDecimal getPorcentajeDescuentosVariosCompra() {
      return porcentajeDescuentosVariosCompra;
   }

   public void setPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) {
      this.porcentajeDescuentosVariosCompra = porcentajeDescuentosVariosCompra;
   }

   private java.math.BigDecimal porcentajeDescuentosVariosVenta;

   public java.math.BigDecimal getPorcentajeDescuentosVariosVenta() {
      return porcentajeDescuentosVariosVenta;
   }

   public void setPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta) {
      this.porcentajeDescuentosVariosVenta = porcentajeDescuentosVariosVenta;
   }

   private java.math.BigDecimal porcentajeDescuentoEspecialCompra;

   public java.math.BigDecimal getPorcentajeDescuentoEspecialCompra() {
      return porcentajeDescuentoEspecialCompra;
   }

   public void setPorcentajeDescuentoEspecialCompra(java.math.BigDecimal porcentajeDescuentoEspecialCompra) {
      this.porcentajeDescuentoEspecialCompra = porcentajeDescuentoEspecialCompra;
   }

   private java.math.BigDecimal porcentajeDescuentoEspecialVenta;

   public java.math.BigDecimal getPorcentajeDescuentoEspecialVenta() {
      return porcentajeDescuentoEspecialVenta;
   }

   public void setPorcentajeDescuentoEspecialVenta(java.math.BigDecimal porcentajeDescuentoEspecialVenta) {
      this.porcentajeDescuentoEspecialVenta = porcentajeDescuentoEspecialVenta;
   }

   private java.sql.Timestamp fechaPublicacion;

   public java.sql.Timestamp getFechaPublicacion() {
      return fechaPublicacion;
   }

   public void setFechaPublicacion(java.sql.Timestamp fechaPublicacion) {
      this.fechaPublicacion = fechaPublicacion;
   }

   private java.math.BigDecimal porcentajeNotaCreditoCompra;

   public java.math.BigDecimal getPorcentajeNotaCreditoCompra() {
      return porcentajeNotaCreditoCompra;
   }

   public void setPorcentajeNotaCreditoCompra(java.math.BigDecimal porcentajeNotaCreditoCompra) {
      this.porcentajeNotaCreditoCompra = porcentajeNotaCreditoCompra;
   }

   private java.math.BigDecimal porcentajeComisionAdicional;

   public java.math.BigDecimal getPorcentajeComisionAdicional() {
      return porcentajeComisionAdicional;
   }

   public void setPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional) {
      this.porcentajeComisionAdicional = porcentajeComisionAdicional;
   }
   public PresupuestoDetalleData() {
   }

   public PresupuestoDetalleData(com.spirit.medios.entity.PresupuestoDetalleIf value) {
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
		return ToStringer.toString((PresupuestoDetalleIf)this);
	}
}
