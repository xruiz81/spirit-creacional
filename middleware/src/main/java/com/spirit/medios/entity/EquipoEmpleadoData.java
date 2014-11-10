package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EquipoEmpleadoData implements EquipoEmpleadoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long equipoId;

   public java.lang.Long getEquipoId() {
      return equipoId;
   }

   public void setEquipoId(java.lang.Long equipoId) {
      this.equipoId = equipoId;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.String rol;

   public java.lang.String getRol() {
      return rol;
   }

   public void setRol(java.lang.String rol) {
      this.rol = rol;
   }

   private java.lang.String jefe;

   public java.lang.String getJefe() {
      return jefe;
   }

   public void setJefe(java.lang.String jefe) {
      this.jefe = jefe;
   }
   public EquipoEmpleadoData() {
   }

   public EquipoEmpleadoData(com.spirit.medios.entity.EquipoEmpleadoIf value) {
      setId(value.getId());
      setEquipoId(value.getEquipoId());
      setEmpleadoId(value.getEmpleadoId());
      setRol(value.getRol());
      setJefe(value.getJefe());
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
		return ToStringer.toString((EquipoEmpleadoIf)this);
	}
}
