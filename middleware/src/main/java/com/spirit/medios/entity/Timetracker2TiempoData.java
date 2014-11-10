package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class Timetracker2TiempoData implements Timetracker2TiempoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.Long clienteOficinaId;

   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   private java.lang.Integer tiempoDesignado;

   public java.lang.Integer getTiempoDesignado() {
      return tiempoDesignado;
   }

   public void setTiempoDesignado(java.lang.Integer tiempoDesignado) {
      this.tiempoDesignado = tiempoDesignado;
   }
   public Timetracker2TiempoData() {
   }

   public Timetracker2TiempoData(com.spirit.medios.entity.Timetracker2TiempoIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setClienteOficinaId(value.getClienteOficinaId());
      setTiempoDesignado(value.getTiempoDesignado());
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
		return ToStringer.toString((Timetracker2TiempoIf)this);
	}
}
