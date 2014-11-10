package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class MovimientoDetalleData implements MovimientoDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long movimientoId;

   public java.lang.Long getMovimientoId() {
      return movimientoId;
   }

   public void setMovimientoId(java.lang.Long movimientoId) {
      this.movimientoId = movimientoId;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.lang.Long loteId;

   public java.lang.Long getLoteId() {
      return loteId;
   }

   public void setLoteId(java.lang.Long loteId) {
      this.loteId = loteId;
   }

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   private java.math.BigDecimal costo;

   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   private java.math.BigDecimal precio;

   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }

   private java.math.BigDecimal costoLifo;

   public java.math.BigDecimal getCostoLifo() {
      return costoLifo;
   }

   public void setCostoLifo(java.math.BigDecimal costoLifo) {
      this.costoLifo = costoLifo;
   }

   private java.math.BigDecimal costoFifo;

   public java.math.BigDecimal getCostoFifo() {
      return costoFifo;
   }

   public void setCostoFifo(java.math.BigDecimal costoFifo) {
      this.costoFifo = costoFifo;
   }

   private java.math.BigDecimal valorUnitario;

   public java.math.BigDecimal getValorUnitario() {
      return valorUnitario;
   }

   public void setValorUnitario(java.math.BigDecimal valorUnitario) {
      this.valorUnitario = valorUnitario;
   }

   private java.math.BigDecimal promedioUnitario;

   public java.math.BigDecimal getPromedioUnitario() {
      return promedioUnitario;
   }

   public void setPromedioUnitario(java.math.BigDecimal promedioUnitario) {
      this.promedioUnitario = promedioUnitario;
   }

   private java.math.BigDecimal stockValorizado;

   public java.math.BigDecimal getStockValorizado() {
      return stockValorizado;
   }

   public void setStockValorizado(java.math.BigDecimal stockValorizado) {
      this.stockValorizado = stockValorizado;
   }

   private java.math.BigDecimal stockAnterior;

   public java.math.BigDecimal getStockAnterior() {
      return stockAnterior;
   }

   public void setStockAnterior(java.math.BigDecimal stockAnterior) {
      this.stockAnterior = stockAnterior;
   }
   public MovimientoDetalleData() {
   }

   public MovimientoDetalleData(com.spirit.inventario.entity.MovimientoDetalleIf value) {
      setId(value.getId());
      setMovimientoId(value.getMovimientoId());
      setDocumentoId(value.getDocumentoId());
      setLoteId(value.getLoteId());
      setCantidad(value.getCantidad());
      setCosto(value.getCosto());
      setPrecio(value.getPrecio());
      setCostoLifo(value.getCostoLifo());
      setCostoFifo(value.getCostoFifo());
      setValorUnitario(value.getValorUnitario());
      setPromedioUnitario(value.getPromedioUnitario());
      setStockValorizado(value.getStockValorizado());
      setStockAnterior(value.getStockAnterior());
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
		return ToStringer.toString((MovimientoDetalleIf)this);
	}
}
