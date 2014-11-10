package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PuntoImpresionData implements PuntoImpresionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipodocumentoId;

   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   private java.lang.Long cajaId;

   public java.lang.Long getCajaId() {
      return cajaId;
   }

   public void setCajaId(java.lang.Long cajaId) {
      this.cajaId = cajaId;
   }

   private java.lang.String serie;

   public java.lang.String getSerie() {
      return serie;
   }

   public void setSerie(java.lang.String serie) {
      this.serie = serie;
   }

   private java.lang.String impresora;

   public java.lang.String getImpresora() {
      return impresora;
   }

   public void setImpresora(java.lang.String impresora) {
      this.impresora = impresora;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
   public PuntoImpresionData() {
   }

   public PuntoImpresionData(com.spirit.general.entity.PuntoImpresionIf value) {
      setId(value.getId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCajaId(value.getCajaId());
      setSerie(value.getSerie());
      setImpresora(value.getImpresora());
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
		return ToStringer.toString((PuntoImpresionIf)this);
	}
}
