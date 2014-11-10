package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TiempoParcialDotDetalleData implements TiempoParcialDotDetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long fecha;

   public java.lang.Long getFecha() {
      return fecha;
   }

   public void setFecha(java.lang.Long fecha) {
      this.fecha = fecha;
   }

   private java.lang.Long horaInicio;

   public java.lang.Long getHoraInicio() {
      return horaInicio;
   }

   public void setHoraInicio(java.lang.Long horaInicio) {
      this.horaInicio = horaInicio;
   }

   private java.lang.Long horaFin;

   public java.lang.Long getHoraFin() {
      return horaFin;
   }

   public void setHoraFin(java.lang.Long horaFin) {
      this.horaFin = horaFin;
   }

   private java.lang.Long tiempo;

   public java.lang.Long getTiempo() {
      return tiempo;
   }

   public void setTiempo(java.lang.Long tiempo) {
      this.tiempo = tiempo;
   }

   private java.lang.Long idTiempoParcialDot;

   public java.lang.Long getIdTiempoParcialDot() {
      return idTiempoParcialDot;
   }

   public void setIdTiempoParcialDot(java.lang.Long idTiempoParcialDot) {
      this.idTiempoParcialDot = idTiempoParcialDot;
   }
   public TiempoParcialDotDetalleData() {
   }

   public TiempoParcialDotDetalleData(com.spirit.medios.entity.TiempoParcialDotDetalleIf value) {
      setId(value.getId());
      setFecha(value.getFecha());
      setHoraInicio(value.getHoraInicio());
      setHoraFin(value.getHoraFin());
      setTiempo(value.getTiempo());
      setIdTiempoParcialDot(value.getIdTiempoParcialDot());
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
		return ToStringer.toString((TiempoParcialDotDetalleIf)this);
	}
}
