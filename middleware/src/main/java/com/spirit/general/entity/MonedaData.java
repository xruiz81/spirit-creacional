package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class MonedaData implements MonedaIf, Serializable    {


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

   private java.lang.String simbolo;

   public java.lang.String getSimbolo() {
      return simbolo;
   }

   public void setSimbolo(java.lang.String simbolo) {
      this.simbolo = simbolo;
   }

   private java.lang.String plural;

   public java.lang.String getPlural() {
      return plural;
   }

   public void setPlural(java.lang.String plural) {
      this.plural = plural;
   }

   private java.lang.String sufijoCantidadLetras;

   public java.lang.String getSufijoCantidadLetras() {
      return sufijoCantidadLetras;
   }

   public void setSufijoCantidadLetras(java.lang.String sufijoCantidadLetras) {
      this.sufijoCantidadLetras = sufijoCantidadLetras;
   }

   private java.lang.String predeterminada;

   public java.lang.String getPredeterminada() {
      return predeterminada;
   }

   public void setPredeterminada(java.lang.String predeterminada) {
      this.predeterminada = predeterminada;
   }
   public MonedaData() {
   }

   public MonedaData(com.spirit.general.entity.MonedaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setSimbolo(value.getSimbolo());
      setPlural(value.getPlural());
      setSufijoCantidadLetras(value.getSufijoCantidadLetras());
      setPredeterminada(value.getPredeterminada());
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
		return ToStringer.toString((MonedaIf)this);
	}
}
