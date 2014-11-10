package com.spirit.sri.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SriTipoComprobanteData implements SriTipoComprobanteIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.Long anulacionTipoComprobanteId;

   public java.lang.Long getAnulacionTipoComprobanteId() {
      return anulacionTipoComprobanteId;
   }

   public void setAnulacionTipoComprobanteId(java.lang.Long anulacionTipoComprobanteId) {
      this.anulacionTipoComprobanteId = anulacionTipoComprobanteId;
   }
   public SriTipoComprobanteData() {
   }

   public SriTipoComprobanteData(com.spirit.sri.entity.SriTipoComprobanteIf value) {
      setId(value.getId());
      setNombre(value.getNombre());
      setCodigo(value.getCodigo());
      setAnulacionTipoComprobanteId(value.getAnulacionTipoComprobanteId());
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
		return ToStringer.toString((SriTipoComprobanteIf)this);
	}
}
