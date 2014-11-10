package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class UsuarioNoticiasData implements UsuarioNoticiasIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.Long noticiasId;

   public java.lang.Long getNoticiasId() {
      return noticiasId;
   }

   public void setNoticiasId(java.lang.Long noticiasId) {
      this.noticiasId = noticiasId;
   }
   public UsuarioNoticiasData() {
   }

   public UsuarioNoticiasData(com.spirit.general.entity.UsuarioNoticiasIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setNoticiasId(value.getNoticiasId());
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
		return ToStringer.toString((UsuarioNoticiasIf)this);
	}
}
