package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class StockData implements StockIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long bodegaId;

   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
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

   private java.lang.String anio;

   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }

   private java.lang.String mes;

   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   private java.math.BigDecimal reserva;

   public java.math.BigDecimal getReserva() {
      return reserva;
   }

   public void setReserva(java.math.BigDecimal reserva) {
      this.reserva = reserva;
   }

   private java.math.BigDecimal transito;

   public java.math.BigDecimal getTransito() {
      return transito;
   }

   public void setTransito(java.math.BigDecimal transito) {
      this.transito = transito;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.sql.Timestamp fhUtlModificacion;

   public java.sql.Timestamp getFhUtlModificacion() {
      return fhUtlModificacion;
   }

   public void setFhUtlModificacion(java.sql.Timestamp fhUtlModificacion) {
      this.fhUtlModificacion = fhUtlModificacion;
   }
   public StockData() {
   }

   public StockData(com.spirit.inventario.entity.StockIf value) {
      setId(value.getId());
      setBodegaId(value.getBodegaId());
      setProductoId(value.getProductoId());
      setLoteId(value.getLoteId());
      setAnio(value.getAnio());
      setMes(value.getMes());
      setCantidad(value.getCantidad());
      setReserva(value.getReserva());
      setTransito(value.getTransito());
      setEstado(value.getEstado());
      setFhUtlModificacion(value.getFhUtlModificacion());
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
		return ToStringer.toString((StockIf)this);
	}
}
