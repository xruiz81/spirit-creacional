package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class Timetracker2DetalleData implements Timetracker2DetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long timetracker2EmpleadoId;

   public java.lang.Long getTimetracker2EmpleadoId() {
      return timetracker2EmpleadoId;
   }

   public void setTimetracker2EmpleadoId(java.lang.Long timetracker2EmpleadoId) {
      this.timetracker2EmpleadoId = timetracker2EmpleadoId;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   private java.lang.Float tiempo;

   public java.lang.Float getTiempo() {
      return tiempo;
   }

   public void setTiempo(java.lang.Float tiempo) {
      this.tiempo = tiempo;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
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
   public Timetracker2DetalleData() {
   }

   public Timetracker2DetalleData(com.spirit.medios.entity.Timetracker2DetalleIf value) {
      setId(value.getId());
      setTimetracker2EmpleadoId(value.getTimetracker2EmpleadoId());
      setFecha(value.getFecha());
      setTiempo(value.getTiempo());
      setEstado(value.getEstado());
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
		return ToStringer.toString((Timetracker2DetalleIf)this);
	}
}
