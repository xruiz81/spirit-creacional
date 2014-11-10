package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OrdenMedioDetalleData implements OrdenMedioDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long ordenMedioId;

   public java.lang.Long getOrdenMedioId() {
      return ordenMedioId;
   }

   public void setOrdenMedioId(java.lang.Long ordenMedioId) {
      this.ordenMedioId = ordenMedioId;
   }

   private java.lang.Long comercialId;

   public java.lang.Long getComercialId() {
      return comercialId;
   }

   public void setComercialId(java.lang.Long comercialId) {
      this.comercialId = comercialId;
   }

   private java.lang.String programa;

   public java.lang.String getPrograma() {
      return programa;
   }

   public void setPrograma(java.lang.String programa) {
      this.programa = programa;
   }

   private java.lang.String hora;

   public java.lang.String getHora() {
      return hora;
   }

   public void setHora(java.lang.String hora) {
      this.hora = hora;
   }

   private java.lang.String comercial;

   public java.lang.String getComercial() {
      return comercial;
   }

   public void setComercial(java.lang.String comercial) {
      this.comercial = comercial;
   }

   private java.math.BigDecimal valorTotal;

   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   private java.math.BigDecimal porcentajeDescuento;

   public java.math.BigDecimal getPorcentajeDescuento() {
      return porcentajeDescuento;
   }

   public void setPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) {
      this.porcentajeDescuento = porcentajeDescuento;
   }

   private java.math.BigDecimal valorIva;

   public java.math.BigDecimal getValorIva() {
      return valorIva;
   }

   public void setValorIva(java.math.BigDecimal valorIva) {
      this.valorIva = valorIva;
   }

   private java.math.BigDecimal valorTarifa;

   public java.math.BigDecimal getValorTarifa() {
      return valorTarifa;
   }

   public void setValorTarifa(java.math.BigDecimal valorTarifa) {
      this.valorTarifa = valorTarifa;
   }

   private java.math.BigDecimal valorSubtotal;

   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   private java.math.BigDecimal valorDescuento;

   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   private java.math.BigDecimal porcentajeDescuentoVenta;

   public java.math.BigDecimal getPorcentajeDescuentoVenta() {
      return porcentajeDescuentoVenta;
   }

   public void setPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) {
      this.porcentajeDescuentoVenta = porcentajeDescuentoVenta;
   }

   private java.math.BigDecimal porcentajeComisionAgencia;

   public java.math.BigDecimal getPorcentajeComisionAgencia() {
      return porcentajeComisionAgencia;
   }

   public void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
      this.porcentajeComisionAgencia = porcentajeComisionAgencia;
   }

   private java.math.BigDecimal valorDescuentoVenta;

   public java.math.BigDecimal getValorDescuentoVenta() {
      return valorDescuentoVenta;
   }

   public void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
      this.valorDescuentoVenta = valorDescuentoVenta;
   }

   private java.math.BigDecimal valorComisionAgencia;

   public java.math.BigDecimal getValorComisionAgencia() {
      return valorComisionAgencia;
   }

   public void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
      this.valorComisionAgencia = valorComisionAgencia;
   }

   private java.lang.Integer totalCunias;

   public java.lang.Integer getTotalCunias() {
      return totalCunias;
   }

   public void setTotalCunias(java.lang.Integer totalCunias) {
      this.totalCunias = totalCunias;
   }

   private java.lang.Long productoProveedorId;

   public java.lang.Long getProductoProveedorId() {
      return productoProveedorId;
   }

   public void setProductoProveedorId(java.lang.Long productoProveedorId) {
      this.productoProveedorId = productoProveedorId;
   }

   private java.lang.Long productoClienteId;

   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   private java.lang.String pauta;

   public java.lang.String getPauta() {
      return pauta;
   }

   public void setPauta(java.lang.String pauta) {
      this.pauta = pauta;
   }

   private java.lang.String auspicioDescripcion;

   public java.lang.String getAuspicioDescripcion() {
      return auspicioDescripcion;
   }

   public void setAuspicioDescripcion(java.lang.String auspicioDescripcion) {
      this.auspicioDescripcion = auspicioDescripcion;
   }

   private java.lang.Long auspicioPadre;

   public java.lang.Long getAuspicioPadre() {
      return auspicioPadre;
   }

   public void setAuspicioPadre(java.lang.Long auspicioPadre) {
      this.auspicioPadre = auspicioPadre;
   }

   private java.lang.Long campanaProductoVersionId;

   public java.lang.Long getCampanaProductoVersionId() {
      return campanaProductoVersionId;
   }

   public void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
      this.campanaProductoVersionId = campanaProductoVersionId;
   }
   public OrdenMedioDetalleData() {
   }

   public OrdenMedioDetalleData(com.spirit.medios.entity.OrdenMedioDetalleIf value) {
      setId(value.getId());
      setOrdenMedioId(value.getOrdenMedioId());
      setComercialId(value.getComercialId());
      setPrograma(value.getPrograma());
      setHora(value.getHora());
      setComercial(value.getComercial());
      setValorTotal(value.getValorTotal());
      setPorcentajeDescuento(value.getPorcentajeDescuento());
      setValorIva(value.getValorIva());
      setValorTarifa(value.getValorTarifa());
      setValorSubtotal(value.getValorSubtotal());
      setValorDescuento(value.getValorDescuento());
      setPorcentajeDescuentoVenta(value.getPorcentajeDescuentoVenta());
      setPorcentajeComisionAgencia(value.getPorcentajeComisionAgencia());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
      setTotalCunias(value.getTotalCunias());
      setProductoProveedorId(value.getProductoProveedorId());
      setProductoClienteId(value.getProductoClienteId());
      setPauta(value.getPauta());
      setAuspicioDescripcion(value.getAuspicioDescripcion());
      setAuspicioPadre(value.getAuspicioPadre());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
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
		return ToStringer.toString((OrdenMedioDetalleIf)this);
	}
}
