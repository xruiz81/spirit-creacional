package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ProductoRetencionData implements ProductoRetencionIf, Serializable    {


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

   private java.lang.Long retencion;

   public java.lang.Long getRetencion() {
      return retencion;
   }

   public void setRetencion(java.lang.Long retencion) {
      this.retencion = retencion;
   }

   private java.sql.Timestamp fechaInicio;

   public java.sql.Timestamp getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Timestamp fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   private java.sql.Timestamp fechaFin;

   public java.sql.Timestamp getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Timestamp fechaFin) {
      this.fechaFin = fechaFin;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
   public ProductoRetencionData() {
   }

   public ProductoRetencionData(com.spirit.inventario.entity.ProductoRetencionIf value) {
      setId(value.getId());
      setProductoId(value.getProductoId());
      setRetencion(value.getRetencion());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
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
		return ToStringer.toString((ProductoRetencionIf)this);
	}
}
