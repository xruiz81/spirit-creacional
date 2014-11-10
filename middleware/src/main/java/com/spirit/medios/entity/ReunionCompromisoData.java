package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ReunionCompromisoData implements ReunionCompromisoIf, Serializable    {


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

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String temaTratado;

   public java.lang.String getTemaTratado() {
      return temaTratado;
   }

   public void setTemaTratado(java.lang.String temaTratado) {
      this.temaTratado = temaTratado;
   }
   public ReunionCompromisoData() {
   }

   public ReunionCompromisoData(com.spirit.medios.entity.ReunionCompromisoIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setFecha(value.getFecha());
      setDescripcion(value.getDescripcion());
      setEstado(value.getEstado());
      setTemaTratado(value.getTemaTratado());
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
		return ToStringer.toString((ReunionCompromisoIf)this);
	}
}
