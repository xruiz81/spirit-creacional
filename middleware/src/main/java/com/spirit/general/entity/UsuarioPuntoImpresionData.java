package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class UsuarioPuntoImpresionData implements UsuarioPuntoImpresionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long puntoimpresionId;

   public java.lang.Long getPuntoimpresionId() {
      return puntoimpresionId;
   }

   public void setPuntoimpresionId(java.lang.Long puntoimpresionId) {
      this.puntoimpresionId = puntoimpresionId;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }
   public UsuarioPuntoImpresionData() {
   }

   public UsuarioPuntoImpresionData(com.spirit.general.entity.UsuarioPuntoImpresionIf value) {
      setId(value.getId());
      setPuntoimpresionId(value.getPuntoimpresionId());
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
		return ToStringer.toString((UsuarioPuntoImpresionIf)this);
	}
}
