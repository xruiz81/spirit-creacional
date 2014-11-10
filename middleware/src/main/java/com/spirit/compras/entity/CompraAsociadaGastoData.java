package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CompraAsociadaGastoData implements CompraAsociadaGastoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long compraGastoId;

   public java.lang.Long getCompraGastoId() {
      return compraGastoId;
   }

   public void setCompraGastoId(java.lang.Long compraGastoId) {
      this.compraGastoId = compraGastoId;
   }

   private java.lang.Long compraId;

   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }
   public CompraAsociadaGastoData() {
   }

   public CompraAsociadaGastoData(com.spirit.compras.entity.CompraAsociadaGastoIf value) {
      setId(value.getId());
      setCompraGastoId(value.getCompraGastoId());
      setCompraId(value.getCompraId());
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
		return ToStringer.toString((CompraAsociadaGastoIf)this);
	}
}
