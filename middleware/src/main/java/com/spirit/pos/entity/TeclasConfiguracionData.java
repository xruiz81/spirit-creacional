package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TeclasConfiguracionData implements TeclasConfiguracionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String teclasNombre;

   public java.lang.String getTeclasNombre() {
      return teclasNombre;
   }

   public void setTeclasNombre(java.lang.String teclasNombre) {
      this.teclasNombre = teclasNombre;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String mascara;

   public java.lang.String getMascara() {
      return mascara;
   }

   public void setMascara(java.lang.String mascara) {
      this.mascara = mascara;
   }
   public TeclasConfiguracionData() {
   }

   public TeclasConfiguracionData(com.spirit.pos.entity.TeclasConfiguracionIf value) {
      setId(value.getId());
      setTeclasNombre(value.getTeclasNombre());
      setDescripcion(value.getDescripcion());
      setCodigo(value.getCodigo());
      setEstado(value.getEstado());
      setMascara(value.getMascara());
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
		return ToStringer.toString((TeclasConfiguracionIf)this);
	}
}
