package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class StockOperativoData implements StockOperativoIf, Serializable    {


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

   private java.lang.String mes;

   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   private java.lang.String anio;

   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.math.BigDecimal min;

   public java.math.BigDecimal getMin() {
      return min;
   }

   public void setMin(java.math.BigDecimal min) {
      this.min = min;
   }

   private java.math.BigDecimal max;

   public java.math.BigDecimal getMax() {
      return max;
   }

   public void setMax(java.math.BigDecimal max) {
      this.max = max;
   }

   private java.lang.Integer tiempoMinimoReposicion;

   public java.lang.Integer getTiempoMinimoReposicion() {
      return tiempoMinimoReposicion;
   }

   public void setTiempoMinimoReposicion(java.lang.Integer tiempoMinimoReposicion) {
      this.tiempoMinimoReposicion = tiempoMinimoReposicion;
   }
   public StockOperativoData() {
   }

   public StockOperativoData(com.spirit.inventario.entity.StockOperativoIf value) {
      setId(value.getId());
      setBodegaId(value.getBodegaId());
      setMes(value.getMes());
      setAnio(value.getAnio());
      setProductoId(value.getProductoId());
      setMin(value.getMin());
      setMax(value.getMax());
      setTiempoMinimoReposicion(value.getTiempoMinimoReposicion());
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
		return ToStringer.toString((StockOperativoIf)this);
	}
}
