package com.spirit.seguridad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class RolUsuarioData implements RolUsuarioIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long rolId;

   public java.lang.Long getRolId() {
      return rolId;
   }

   public void setRolId(java.lang.Long rolId) {
      this.rolId = rolId;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }
   public RolUsuarioData() {
   }

   public RolUsuarioData(com.spirit.seguridad.entity.RolUsuarioIf value) {
      setId(value.getId());
      setRolId(value.getRolId());
      setUsuarioId(value.getUsuarioId());
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
		return ToStringer.toString((RolUsuarioIf)this);
	}
}
