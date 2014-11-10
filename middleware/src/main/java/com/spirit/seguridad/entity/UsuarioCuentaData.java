package com.spirit.seguridad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class UsuarioCuentaData implements UsuarioCuentaIf, Serializable    {


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

   private java.lang.Long cuentaId;

   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }
   public UsuarioCuentaData() {
   }

   public UsuarioCuentaData(com.spirit.seguridad.entity.UsuarioCuentaIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setCuentaId(value.getCuentaId());
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
		return ToStringer.toString((UsuarioCuentaIf)this);
	}
}
