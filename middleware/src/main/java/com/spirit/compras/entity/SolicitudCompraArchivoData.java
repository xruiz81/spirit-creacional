package com.spirit.compras.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SolicitudCompraArchivoData implements SolicitudCompraArchivoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long solicitudCompraId;

   public java.lang.Long getSolicitudCompraId() {
      return solicitudCompraId;
   }

   public void setSolicitudCompraId(java.lang.Long solicitudCompraId) {
      this.solicitudCompraId = solicitudCompraId;
   }

   private java.lang.Long tipoArchivoId;

   public java.lang.Long getTipoArchivoId() {
      return tipoArchivoId;
   }

   public void setTipoArchivoId(java.lang.Long tipoArchivoId) {
      this.tipoArchivoId = tipoArchivoId;
   }

   private java.lang.String url;

   public java.lang.String getUrl() {
      return url;
   }

   public void setUrl(java.lang.String url) {
      this.url = url;
   }
   public SolicitudCompraArchivoData() {
   }

   public SolicitudCompraArchivoData(com.spirit.compras.entity.SolicitudCompraArchivoIf value) {
      setId(value.getId());
      setSolicitudCompraId(value.getSolicitudCompraId());
      setTipoArchivoId(value.getTipoArchivoId());
      setUrl(value.getUrl());
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
		return ToStringer.toString((SolicitudCompraArchivoIf)this);
	}
}
