package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SolicitudTransferenciaDetalleData implements SolicitudTransferenciaDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long solicitudTransferenciaId;

   public java.lang.Long getSolicitudTransferenciaId() {
      return solicitudTransferenciaId;
   }

   public void setSolicitudTransferenciaId(java.lang.Long solicitudTransferenciaId) {
      this.solicitudTransferenciaId = solicitudTransferenciaId;
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

   private java.math.BigDecimal cantidad;

   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }
   public SolicitudTransferenciaDetalleData() {
   }

   public SolicitudTransferenciaDetalleData(com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf value) {
      setId(value.getId());
      setSolicitudTransferenciaId(value.getSolicitudTransferenciaId());
      setProductoId(value.getProductoId());
      setLoteId(value.getLoteId());
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
		return ToStringer.toString((SolicitudTransferenciaDetalleIf)this);
	}
}
