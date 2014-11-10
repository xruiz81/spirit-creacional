package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SubtipoAsientoData implements SubtipoAsientoIf, Serializable    {


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

   private java.lang.Long tipoId;

   public java.lang.Long getTipoId() {
      return tipoId;
   }

   public void setTipoId(java.lang.Long tipoId) {
      this.tipoId = tipoId;
   }

   private java.lang.Long orden;

   public java.lang.Long getOrden() {
      return orden;
   }

   public void setOrden(java.lang.Long orden) {
      this.orden = orden;
   }

   private java.lang.String status;

   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }

   private java.lang.Long tipo;

   public java.lang.Long getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.Long tipo) {
      this.tipo = tipo;
   }
   public SubtipoAsientoData() {
   }

   public SubtipoAsientoData(com.spirit.contabilidad.entity.SubtipoAsientoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setTipoId(value.getTipoId());
      setOrden(value.getOrden());
      setStatus(value.getStatus());
      setTipo(value.getTipo());
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
		return ToStringer.toString((SubtipoAsientoIf)this);
	}
}
