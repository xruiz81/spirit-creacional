package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class AsientoDescuadradoData implements AsientoDescuadradoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String asientoNumero;

   public java.lang.String getAsientoNumero() {
      return asientoNumero;
   }

   public void setAsientoNumero(java.lang.String asientoNumero) {
      this.asientoNumero = asientoNumero;
   }
   public AsientoDescuadradoData() {
   }

   public AsientoDescuadradoData(com.spirit.contabilidad.entity.AsientoDescuadradoIf value) {
      setId(value.getId());
      setAsientoNumero(value.getAsientoNumero());
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
		return ToStringer.toString((AsientoDescuadradoIf)this);
	}
}
