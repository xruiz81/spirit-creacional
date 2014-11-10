package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TipoResultadoData implements TipoResultadoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.Long orden;

   public java.lang.Long getOrden() {
      return orden;
   }

   public void setOrden(java.lang.Long orden) {
      this.orden = orden;
   }

   private java.lang.String utilidad;

   public java.lang.String getUtilidad() {
      return utilidad;
   }

   public void setUtilidad(java.lang.String utilidad) {
      this.utilidad = utilidad;
   }

   private java.lang.String leyendaResultado;

   public java.lang.String getLeyendaResultado() {
      return leyendaResultado;
   }

   public void setLeyendaResultado(java.lang.String leyendaResultado) {
      this.leyendaResultado = leyendaResultado;
   }
   public TipoResultadoData() {
   }

   public TipoResultadoData(com.spirit.contabilidad.entity.TipoResultadoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setOrden(value.getOrden());
      setUtilidad(value.getUtilidad());
      setLeyendaResultado(value.getLeyendaResultado());
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
		return ToStringer.toString((TipoResultadoIf)this);
	}
}
