package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ReunionArchivoData implements ReunionArchivoIf, Serializable    {


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

   private java.lang.Long tipoArchivoId;

   public java.lang.Long getTipoArchivoId() {
      return tipoArchivoId;
   }

   public void setTipoArchivoId(java.lang.Long tipoArchivoId) {
      this.tipoArchivoId = tipoArchivoId;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.lang.String urlDescripcion;

   public java.lang.String getUrlDescripcion() {
      return urlDescripcion;
   }

   public void setUrlDescripcion(java.lang.String urlDescripcion) {
      this.urlDescripcion = urlDescripcion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
   public ReunionArchivoData() {
   }

   public ReunionArchivoData(com.spirit.medios.entity.ReunionArchivoIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setTipoArchivoId(value.getTipoArchivoId());
      setFecha(value.getFecha());
      setUrlDescripcion(value.getUrlDescripcion());
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
		return ToStringer.toString((ReunionArchivoIf)this);
	}
}
