package com.spirit.seguridad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class FuncionData implements FuncionIf, Serializable    {


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

   private java.lang.String icon;

   public java.lang.String getIcon() {
      return icon;
   }

   public void setIcon(java.lang.String icon) {
      this.icon = icon;
   }
   public FuncionData() {
   }

   public FuncionData(com.spirit.seguridad.entity.FuncionIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setIcon(value.getIcon());
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
		return ToStringer.toString((FuncionIf)this);
	}
}
