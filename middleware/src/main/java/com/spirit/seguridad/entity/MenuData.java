package com.spirit.seguridad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class MenuData implements MenuIf, Serializable    {


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

   private java.lang.Long padreId;

   public java.lang.Long getPadreId() {
      return padreId;
   }

   public void setPadreId(java.lang.Long padreId) {
      this.padreId = padreId;
   }

   private java.lang.Integer nivel;

   public java.lang.Integer getNivel() {
      return nivel;
   }

   public void setNivel(java.lang.Integer nivel) {
      this.nivel = nivel;
   }

   private java.lang.Integer favorito;

   public java.lang.Integer getFavorito() {
      return favorito;
   }

   public void setFavorito(java.lang.Integer favorito) {
      this.favorito = favorito;
   }

   private java.lang.String panel;

   public java.lang.String getPanel() {
      return panel;
   }

   public void setPanel(java.lang.String panel) {
      this.panel = panel;
   }
   public MenuData() {
   }

   public MenuData(com.spirit.seguridad.entity.MenuIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setPadreId(value.getPadreId());
      setNivel(value.getNivel());
      setFavorito(value.getFavorito());
      setPanel(value.getPanel());
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
		return ToStringer.toString((MenuIf)this);
	}
}
