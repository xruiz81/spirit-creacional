package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PrecioData implements PrecioIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long listaprecioId;

   public java.lang.Long getListaprecioId() {
      return listaprecioId;
   }

   public void setListaprecioId(java.lang.Long listaprecioId) {
      this.listaprecioId = listaprecioId;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.math.BigDecimal precio;

   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }

   private java.lang.String cambiarPrecio;

   public java.lang.String getCambiarPrecio() {
      return cambiarPrecio;
   }

   public void setCambiarPrecio(java.lang.String cambiarPrecio) {
      this.cambiarPrecio = cambiarPrecio;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
   public PrecioData() {
   }

   public PrecioData(com.spirit.facturacion.entity.PrecioIf value) {
      setId(value.getId());
      setListaprecioId(value.getListaprecioId());
      setProductoId(value.getProductoId());
      setPrecio(value.getPrecio());
      setCambiarPrecio(value.getCambiarPrecio());
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
		return ToStringer.toString((PrecioIf)this);
	}
}
