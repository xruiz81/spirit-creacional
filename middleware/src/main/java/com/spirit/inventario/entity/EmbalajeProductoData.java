package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EmbalajeProductoData implements EmbalajeProductoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.lang.Long embalajeId;

   public java.lang.Long getEmbalajeId() {
      return embalajeId;
   }

   public void setEmbalajeId(java.lang.Long embalajeId) {
      this.embalajeId = embalajeId;
   }

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   private java.math.BigDecimal areaCubica;

   public java.math.BigDecimal getAreaCubica() {
      return areaCubica;
   }

   public void setAreaCubica(java.math.BigDecimal areaCubica) {
      this.areaCubica = areaCubica;
   }

   private java.lang.String tipoManejo;

   public java.lang.String getTipoManejo() {
      return tipoManejo;
   }

   public void setTipoManejo(java.lang.String tipoManejo) {
      this.tipoManejo = tipoManejo;
   }
   public EmbalajeProductoData() {
   }

   public EmbalajeProductoData(com.spirit.inventario.entity.EmbalajeProductoIf value) {
      setId(value.getId());
      setProductoId(value.getProductoId());
      setEmbalajeId(value.getEmbalajeId());
      setCantidad(value.getCantidad());
      setAreaCubica(value.getAreaCubica());
      setTipoManejo(value.getTipoManejo());
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
		return ToStringer.toString((EmbalajeProductoIf)this);
	}
}
