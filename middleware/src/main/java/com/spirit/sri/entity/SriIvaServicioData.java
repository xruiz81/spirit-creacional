package com.spirit.sri.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SriIvaServicioData implements SriIvaServicioIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Integer codigo;

   public java.lang.Integer getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.Integer codigo) {
      this.codigo = codigo;
   }

   private java.lang.String descripcionPorcentaje;

   public java.lang.String getDescripcionPorcentaje() {
      return descripcionPorcentaje;
   }

   public void setDescripcionPorcentaje(java.lang.String descripcionPorcentaje) {
      this.descripcionPorcentaje = descripcionPorcentaje;
   }
   public SriIvaServicioData() {
   }

   public SriIvaServicioData(com.spirit.sri.entity.SriIvaServicioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setDescripcionPorcentaje(value.getDescripcionPorcentaje());
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
		return ToStringer.toString((SriIvaServicioIf)this);
	}
}
