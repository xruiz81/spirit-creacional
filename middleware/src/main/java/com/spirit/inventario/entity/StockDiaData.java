package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class StockDiaData implements StockDiaIf, Serializable    {


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

   private java.lang.String dia;

   public java.lang.String getDia() {
      return dia;
   }

   public void setDia(java.lang.String dia) {
      this.dia = dia;
   }

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }
   public StockDiaData() {
   }

   public StockDiaData(com.spirit.inventario.entity.StockDiaIf value) {
      setId(value.getId());
      setBodegaId(value.getBodegaId());
      setProductoId(value.getProductoId());
      setAnio(value.getAnio());
      setMes(value.getMes());
      setDia(value.getDia());
      setCantidad(value.getCantidad());
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
		return ToStringer.toString((StockDiaIf)this);
	}
}
