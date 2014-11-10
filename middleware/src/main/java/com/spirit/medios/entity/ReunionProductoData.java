package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ReunionProductoData implements ReunionProductoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long reunionId;

   public java.lang.Long getReunionId() {
      return reunionId;
   }

   public void setReunionId(java.lang.Long reunionId) {
      this.reunionId = reunionId;
   }

   private java.lang.Long productoClienteId;

   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   private java.lang.String productoCliente;

   public java.lang.String getProductoCliente() {
      return productoCliente;
   }

   public void setProductoCliente(java.lang.String productoCliente) {
      this.productoCliente = productoCliente;
   }
   public ReunionProductoData() {
   }

   public ReunionProductoData(com.spirit.medios.entity.ReunionProductoIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setProductoClienteId(value.getProductoClienteId());
      setProductoCliente(value.getProductoCliente());
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
		return ToStringer.toString((ReunionProductoIf)this);
	}
}
