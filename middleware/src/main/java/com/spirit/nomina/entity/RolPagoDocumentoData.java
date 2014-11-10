package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class RolPagoDocumentoData implements RolPagoDocumentoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipoRolId;

   public java.lang.Long getTipoRolId() {
      return tipoRolId;
   }

   public void setTipoRolId(java.lang.Long tipoRolId) {
      this.tipoRolId = tipoRolId;
   }

   private java.lang.Long tipoContratoId;

   public java.lang.Long getTipoContratoId() {
      return tipoContratoId;
   }

   public void setTipoContratoId(java.lang.Long tipoContratoId) {
      this.tipoContratoId = tipoContratoId;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.sql.Date fechaCreacion;

   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.Long creacionUsuarioId;

   public java.lang.Long getCreacionUsuarioId() {
      return creacionUsuarioId;
   }

   public void setCreacionUsuarioId(java.lang.Long creacionUsuarioId) {
      this.creacionUsuarioId = creacionUsuarioId;
   }

   private java.sql.Date fechaActualizacion;

   public java.sql.Date getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.sql.Date fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   private java.lang.Long actualizacionUsuarioId;

   public java.lang.Long getActualizacionUsuarioId() {
      return actualizacionUsuarioId;
   }

   public void setActualizacionUsuarioId(java.lang.Long actualizacionUsuarioId) {
      this.actualizacionUsuarioId = actualizacionUsuarioId;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
   public RolPagoDocumentoData() {
   }

   public RolPagoDocumentoData(com.spirit.nomina.entity.RolPagoDocumentoIf value) {
      setId(value.getId());
      setTipoRolId(value.getTipoRolId());
      setTipoContratoId(value.getTipoContratoId());
      setDocumentoId(value.getDocumentoId());
      setFechaCreacion(value.getFechaCreacion());
      setCreacionUsuarioId(value.getCreacionUsuarioId());
      setFechaActualizacion(value.getFechaActualizacion());
      setActualizacionUsuarioId(value.getActualizacionUsuarioId());
      setEmpresaId(value.getEmpresaId());
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
		return ToStringer.toString((RolPagoDocumentoIf)this);
	}
}
