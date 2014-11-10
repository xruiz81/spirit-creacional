package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ReunionAsistenteData implements ReunionAsistenteIf, Serializable    {


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

   private java.lang.Long clienteContactoId;

   public java.lang.Long getClienteContactoId() {
      return clienteContactoId;
   }

   public void setClienteContactoId(java.lang.Long clienteContactoId) {
      this.clienteContactoId = clienteContactoId;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.String clienteContacto;

   public java.lang.String getClienteContacto() {
      return clienteContacto;
   }

   public void setClienteContacto(java.lang.String clienteContacto) {
      this.clienteContacto = clienteContacto;
   }
   public ReunionAsistenteData() {
   }

   public ReunionAsistenteData(com.spirit.medios.entity.ReunionAsistenteIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setClienteContactoId(value.getClienteContactoId());
      setEmpleadoId(value.getEmpleadoId());
      setClienteContacto(value.getClienteContacto());
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
		return ToStringer.toString((ReunionAsistenteIf)this);
	}
}
