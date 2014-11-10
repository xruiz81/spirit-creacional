package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class OrdenAsociadaData implements OrdenAsociadaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long compraId;

   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   private java.lang.String tipoOrden;

   public java.lang.String getTipoOrden() {
      return tipoOrden;
   }

   public void setTipoOrden(java.lang.String tipoOrden) {
      this.tipoOrden = tipoOrden;
   }

   private java.lang.Long ordenId;

   public java.lang.Long getOrdenId() {
      return ordenId;
   }

   public void setOrdenId(java.lang.Long ordenId) {
      this.ordenId = ordenId;
   }
   public OrdenAsociadaData() {
   }

   public OrdenAsociadaData(com.spirit.compras.entity.OrdenAsociadaIf value) {
      setId(value.getId());
      setCompraId(value.getCompraId());
      setTipoOrden(value.getTipoOrden());
      setOrdenId(value.getOrdenId());
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
		return ToStringer.toString((OrdenAsociadaIf)this);
	}
}
