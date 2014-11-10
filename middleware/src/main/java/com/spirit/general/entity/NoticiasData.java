package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class NoticiasData implements NoticiasIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.sql.Date fechaIni;

   public java.sql.Date getFechaIni() {
      return fechaIni;
   }

   public void setFechaIni(java.sql.Date fechaIni) {
      this.fechaIni = fechaIni;
   }

   private java.sql.Date fechaFin;

   public java.sql.Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Date fechaFin) {
      this.fechaFin = fechaFin;
   }

   private java.sql.Date fechaCreacion;

   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.String status;

   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }

   private java.lang.String noticia;

   public java.lang.String getNoticia() {
      return noticia;
   }

   public void setNoticia(java.lang.String noticia) {
      this.noticia = noticia;
   }

   private java.lang.String archivo;

   public java.lang.String getArchivo() {
      return archivo;
   }

   public void setArchivo(java.lang.String archivo) {
      this.archivo = archivo;
   }

   private java.lang.String asunto;

   public java.lang.String getAsunto() {
      return asunto;
   }

   public void setAsunto(java.lang.String asunto) {
      this.asunto = asunto;
   }
   public NoticiasData() {
   }

   public NoticiasData(com.spirit.general.entity.NoticiasIf value) {
      setId(value.getId());
      setEmpresaId(value.getEmpresaId());
      setUsuarioId(value.getUsuarioId());
      setFechaIni(value.getFechaIni());
      setFechaFin(value.getFechaFin());
      setFechaCreacion(value.getFechaCreacion());
      setStatus(value.getStatus());
      setNoticia(value.getNoticia());
      setArchivo(value.getArchivo());
      setAsunto(value.getAsunto());
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
		return ToStringer.toString((NoticiasIf)this);
	}
}
