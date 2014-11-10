package com.spirit.rrhh.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EmpleadoIdiomasData implements EmpleadoIdiomasIf, Serializable    {


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

   private java.lang.Long idiomaId;

   public java.lang.Long getIdiomaId() {
      return idiomaId;
   }

   public void setIdiomaId(java.lang.Long idiomaId) {
      this.idiomaId = idiomaId;
   }

   private java.lang.String habla;

   public java.lang.String getHabla() {
      return habla;
   }

   public void setHabla(java.lang.String habla) {
      this.habla = habla;
   }

   private java.lang.String comprende;

   public java.lang.String getComprende() {
      return comprende;
   }

   public void setComprende(java.lang.String comprende) {
      this.comprende = comprende;
   }

   private java.lang.String lee;

   public java.lang.String getLee() {
      return lee;
   }

   public void setLee(java.lang.String lee) {
      this.lee = lee;
   }

   private java.lang.String escribe;

   public java.lang.String getEscribe() {
      return escribe;
   }

   public void setEscribe(java.lang.String escribe) {
      this.escribe = escribe;
   }
   public EmpleadoIdiomasData() {
   }

   public EmpleadoIdiomasData(com.spirit.rrhh.entity.EmpleadoIdiomasIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setIdiomaId(value.getIdiomaId());
      setHabla(value.getHabla());
      setComprende(value.getComprende());
      setLee(value.getLee());
      setEscribe(value.getEscribe());
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
		return ToStringer.toString((EmpleadoIdiomasIf)this);
	}
}
