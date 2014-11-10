package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PautaGenericoClienteData implements PautaGenericoClienteIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   private java.lang.Long tipoProductoId;

   public java.lang.Long getTipoProductoId() {
      return tipoProductoId;
   }

   public void setTipoProductoId(java.lang.Long tipoProductoId) {
      this.tipoProductoId = tipoProductoId;
   }

   private java.lang.Long genericoId;

   public java.lang.Long getGenericoId() {
      return genericoId;
   }

   public void setGenericoId(java.lang.Long genericoId) {
      this.genericoId = genericoId;
   }
   public PautaGenericoClienteData() {
   }

   public PautaGenericoClienteData(com.spirit.medios.entity.PautaGenericoClienteIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setTipoProductoId(value.getTipoProductoId());
      setGenericoId(value.getGenericoId());
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
		return ToStringer.toString((PautaGenericoClienteIf)this);
	}
}
