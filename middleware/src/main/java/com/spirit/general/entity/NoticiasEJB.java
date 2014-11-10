package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "NOTICIAS")
@Entity
public class NoticiasEJB implements Serializable, NoticiasIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empresaId;
   private java.lang.Long usuarioId;
   private java.sql.Date fechaIni;
   private java.sql.Date fechaFin;
   private java.sql.Date fechaCreacion;
   private java.lang.String status;
   private java.lang.String noticia;
   private java.lang.String archivo;
   private java.lang.String asunto;

   public NoticiasEJB() {
   }

   public NoticiasEJB(com.spirit.general.entity.NoticiasIf value) {
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

   public java.lang.Long create(com.spirit.general.entity.NoticiasIf value) {
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
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "FECHA_INI")
   public java.sql.Date getFechaIni() {
      return fechaIni;
   }

   public void setFechaIni(java.sql.Date fechaIni) {
      this.fechaIni = fechaIni;
   }

   @Column(name = "FECHA_FIN")
   public java.sql.Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Date fechaFin) {
      this.fechaFin = fechaFin;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "STATUS")
   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }

   @Column(name = "NOTICIA")
   public java.lang.String getNoticia() {
      return noticia;
   }

   public void setNoticia(java.lang.String noticia) {
      this.noticia = noticia;
   }

   @Column(name = "ARCHIVO")
   public java.lang.String getArchivo() {
      return archivo;
   }

   public void setArchivo(java.lang.String archivo) {
      this.archivo = archivo;
   }

   @Column(name = "ASUNTO")
   public java.lang.String getAsunto() {
      return asunto;
   }

   public void setAsunto(java.lang.String asunto) {
      this.asunto = asunto;
   }
	public String toString() {
		return ToStringer.toString((NoticiasIf)this);
	}
}
