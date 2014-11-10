package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class UsuarioData implements UsuarioIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String usuario;

   public java.lang.String getUsuario() {
      return usuario;
   }

   public void setUsuario(java.lang.String usuario) {
      this.usuario = usuario;
   }

   private java.lang.String clave;

   public java.lang.String getClave() {
      return clave;
   }

   public void setClave(java.lang.String clave) {
      this.clave = clave;
   }

   private java.lang.String tipousuario;

   public java.lang.String getTipousuario() {
      return tipousuario;
   }

   public void setTipousuario(java.lang.String tipousuario) {
      this.tipousuario = tipousuario;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.String tipousuarioTimetracker;

   public java.lang.String getTipousuarioTimetracker() {
      return tipousuarioTimetracker;
   }

   public void setTipousuarioTimetracker(java.lang.String tipousuarioTimetracker) {
      this.tipousuarioTimetracker = tipousuarioTimetracker;
   }
   public UsuarioData() {
   }

   public UsuarioData(com.spirit.general.entity.UsuarioIf value) {
      setId(value.getId());
      setUsuario(value.getUsuario());
      setClave(value.getClave());
      setTipousuario(value.getTipousuario());
      setEmpleadoId(value.getEmpleadoId());
      setEmpresaId(value.getEmpresaId());
      setTipousuarioTimetracker(value.getTipousuarioTimetracker());
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
		return ToStringer.toString((UsuarioIf)this);
	}
}
