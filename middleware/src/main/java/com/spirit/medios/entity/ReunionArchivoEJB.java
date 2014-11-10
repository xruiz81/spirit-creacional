package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "REUNION_ARCHIVO")
@Entity
public class ReunionArchivoEJB implements Serializable, ReunionArchivoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long reunionId;
   private java.lang.Long tipoArchivoId;
   private java.sql.Date fecha;
   private java.lang.String urlDescripcion;
   private java.lang.String estado;

   public ReunionArchivoEJB() {
   }

   public ReunionArchivoEJB(com.spirit.medios.entity.ReunionArchivoIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setTipoArchivoId(value.getTipoArchivoId());
      setFecha(value.getFecha());
      setUrlDescripcion(value.getUrlDescripcion());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.medios.entity.ReunionArchivoIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setTipoArchivoId(value.getTipoArchivoId());
      setFecha(value.getFecha());
      setUrlDescripcion(value.getUrlDescripcion());
      setEstado(value.getEstado());
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

   @Column(name = "REUNION_ID")
   public java.lang.Long getReunionId() {
      return reunionId;
   }

   public void setReunionId(java.lang.Long reunionId) {
      this.reunionId = reunionId;
   }

   @Column(name = "TIPO_ARCHIVO_ID")
   public java.lang.Long getTipoArchivoId() {
      return tipoArchivoId;
   }

   public void setTipoArchivoId(java.lang.Long tipoArchivoId) {
      this.tipoArchivoId = tipoArchivoId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "URL_DESCRIPCION")
   public java.lang.String getUrlDescripcion() {
      return urlDescripcion;
   }

   public void setUrlDescripcion(java.lang.String urlDescripcion) {
      this.urlDescripcion = urlDescripcion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((ReunionArchivoIf)this);
	}
}
