package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OrdenTrabajoProductoData implements OrdenTrabajoProductoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long productoClienteId;

   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   private java.lang.Long ordenTrabajoId;

   public java.lang.Long getOrdenTrabajoId() {
      return ordenTrabajoId;
   }

   public void setOrdenTrabajoId(java.lang.Long ordenTrabajoId) {
      this.ordenTrabajoId = ordenTrabajoId;
   }
   public OrdenTrabajoProductoData() {
   }

   public OrdenTrabajoProductoData(com.spirit.medios.entity.OrdenTrabajoProductoIf value) {
      setId(value.getId());
      setProductoClienteId(value.getProductoClienteId());
      setOrdenTrabajoId(value.getOrdenTrabajoId());
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
		return ToStringer.toString((OrdenTrabajoProductoIf)this);
	}
}
