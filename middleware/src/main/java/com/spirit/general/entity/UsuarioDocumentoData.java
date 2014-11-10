package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class UsuarioDocumentoData implements UsuarioDocumentoIf, Serializable    {


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

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.lang.String permisoImpresion;

   public java.lang.String getPermisoImpresion() {
      return permisoImpresion;
   }

   public void setPermisoImpresion(java.lang.String permisoImpresion) {
      this.permisoImpresion = permisoImpresion;
   }

   private java.lang.String permisoRegistro;

   public java.lang.String getPermisoRegistro() {
      return permisoRegistro;
   }

   public void setPermisoRegistro(java.lang.String permisoRegistro) {
      this.permisoRegistro = permisoRegistro;
   }

   private java.lang.String permisoBorrado;

   public java.lang.String getPermisoBorrado() {
      return permisoBorrado;
   }

   public void setPermisoBorrado(java.lang.String permisoBorrado) {
      this.permisoBorrado = permisoBorrado;
   }

   private java.lang.String permisoAutorizar;

   public java.lang.String getPermisoAutorizar() {
      return permisoAutorizar;
   }

   public void setPermisoAutorizar(java.lang.String permisoAutorizar) {
      this.permisoAutorizar = permisoAutorizar;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
   public UsuarioDocumentoData() {
   }

   public UsuarioDocumentoData(com.spirit.general.entity.UsuarioDocumentoIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setDocumentoId(value.getDocumentoId());
      setPermisoImpresion(value.getPermisoImpresion());
      setPermisoRegistro(value.getPermisoRegistro());
      setPermisoBorrado(value.getPermisoBorrado());
      setPermisoAutorizar(value.getPermisoAutorizar());
      setEstado(value.getEstado());
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
		return ToStringer.toString((UsuarioDocumentoIf)this);
	}
}
