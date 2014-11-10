package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CampanaArchivoData implements CampanaArchivoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipoArchivoId;

   public java.lang.Long getTipoArchivoId() {
      return tipoArchivoId;
   }

   public void setTipoArchivoId(java.lang.Long tipoArchivoId) {
      this.tipoArchivoId = tipoArchivoId;
   }

   private java.lang.Long campanaId;

   public java.lang.Long getCampanaId() {
      return campanaId;
   }

   public void setCampanaId(java.lang.Long campanaId) {
      this.campanaId = campanaId;
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
   public CampanaArchivoData() {
   }

   public CampanaArchivoData(com.spirit.medios.entity.CampanaArchivoIf value) {
      setId(value.getId());
      setTipoArchivoId(value.getTipoArchivoId());
      setCampanaId(value.getCampanaId());
      setFecha(value.getFecha());
      setUrlDescripcion(value.getUrlDescripcion());
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
		return ToStringer.toString((CampanaArchivoIf)this);
	}
}
