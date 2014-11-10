package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class Timetracker2EmpleadoData implements Timetracker2EmpleadoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long timetracker2Id;

   public java.lang.Long getTimetracker2Id() {
      return timetracker2Id;
   }

   public void setTimetracker2Id(java.lang.Long timetracker2Id) {
      this.timetracker2Id = timetracker2Id;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
   public Timetracker2EmpleadoData() {
   }

   public Timetracker2EmpleadoData(com.spirit.medios.entity.Timetracker2EmpleadoIf value) {
      setId(value.getId());
      setTimetracker2Id(value.getTimetracker2Id());
      setEmpleadoId(value.getEmpleadoId());
      setEstado(value.getEstado());
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
		return ToStringer.toString((Timetracker2EmpleadoIf)this);
	}
}
