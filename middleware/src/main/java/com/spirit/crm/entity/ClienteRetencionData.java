package com.spirit.crm.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ClienteRetencionData implements ClienteRetencionIf, Serializable    {


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

   private java.lang.Long sriAirId;

   public java.lang.Long getSriAirId() {
      return sriAirId;
   }

   public void setSriAirId(java.lang.Long sriAirId) {
      this.sriAirId = sriAirId;
   }

   private java.lang.Long sriIvaRetencionId;

   public java.lang.Long getSriIvaRetencionId() {
      return sriIvaRetencionId;
   }

   public void setSriIvaRetencionId(java.lang.Long sriIvaRetencionId) {
      this.sriIvaRetencionId = sriIvaRetencionId;
   }
   public ClienteRetencionData() {
   }

   public ClienteRetencionData(com.spirit.crm.entity.ClienteRetencionIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setSriAirId(value.getSriAirId());
      setSriIvaRetencionId(value.getSriIvaRetencionId());
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
		return ToStringer.toString((ClienteRetencionIf)this);
	}
}
