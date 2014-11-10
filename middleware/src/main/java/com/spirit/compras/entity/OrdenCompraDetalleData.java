package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OrdenCompraDetalleData implements OrdenCompraDetalleIf, Serializable    {


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

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.lang.Long ordencompraId;

   public java.lang.Long getOrdencompraId() {
      return ordencompraId;
   }

   public void setOrdencompraId(java.lang.Long ordencompraId) {
      this.ordencompraId = ordencompraId;
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

   private java.math.BigDecimal descuentoAgenciaCompra;

   public java.math.BigDecimal getDescuentoAgenciaCompra() {
      return descuentoAgenciaCompra;
   }

   public void setDescuentoAgenciaCompra(java.math.BigDecimal descuentoAgenciaCompra) {
      this.descuentoAgenciaCompra = descuentoAgenciaCompra;
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

   private java.math.BigDecimal porcentajeDescuentoEspecial;

   public java.math.BigDecimal getPorcentajeDescuentoEspecial() {
      return porcentajeDescuentoEspecial;
   }

   public void setPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) {
      this.porcentajeDescuentoEspecial = porcentajeDescuentoEspecial;
   }

   private java.sql.Timestamp fechaPublicacion;

   public java.sql.Timestamp getFechaPublicacion() {
      return fechaPublicacion;
   }

   public void setFechaPublicacion(java.sql.Timestamp fechaPublicacion) {
      this.fechaPublicacion = fechaPublicacion;
   }
   public OrdenCompraDetalleData() {
   }

   public OrdenCompraDetalleData(com.spirit.compras.entity.OrdenCompraDetalleIf value) {
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
		return ToStringer.toString((OrdenCompraDetalleIf)this);
	}
}
