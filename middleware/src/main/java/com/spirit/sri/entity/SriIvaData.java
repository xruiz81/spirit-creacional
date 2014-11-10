package com.spirit.sri.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SriIvaData implements SriIvaIf, Serializable    {


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

   private java.lang.Integer porcentaje;

   public java.lang.Integer getPorcentaje() {
      return porcentaje;
   }

   public void setPorcentaje(java.lang.Integer porcentaje) {
      this.porcentaje = porcentaje;
   }

   private java.sql.Date fechaInicio;

   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   private java.sql.Date fechaFin;

   public java.sql.Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Date fechaFin) {
      this.fechaFin = fechaFin;
   }
   public SriIvaData() {
   }

   public SriIvaData(com.spirit.sri.entity.SriIvaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setPorcentaje(value.getPorcentaje());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
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
		return ToStringer.toString((SriIvaIf)this);
	}
}
