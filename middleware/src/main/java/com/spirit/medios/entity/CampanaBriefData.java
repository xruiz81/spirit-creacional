package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CampanaBriefData implements CampanaBriefIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipoBriefId;

   public java.lang.Long getTipoBriefId() {
      return tipoBriefId;
   }

   public void setTipoBriefId(java.lang.Long tipoBriefId) {
      this.tipoBriefId = tipoBriefId;
   }

   private java.lang.Long campanaId;

   public java.lang.Long getCampanaId() {
      return campanaId;
   }

   public void setCampanaId(java.lang.Long campanaId) {
      this.campanaId = campanaId;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.String urlDescripcion;

   public java.lang.String getUrlDescripcion() {
      return urlDescripcion;
   }

   public void setUrlDescripcion(java.lang.String urlDescripcion) {
      this.urlDescripcion = urlDescripcion;
   }
   public CampanaBriefData() {
   }

   public CampanaBriefData(com.spirit.medios.entity.CampanaBriefIf value) {
      setId(value.getId());
      setTipoBriefId(value.getTipoBriefId());
      setCampanaId(value.getCampanaId());
      setDescripcion(value.getDescripcion());
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
		return ToStringer.toString((CampanaBriefIf)this);
	}
}
