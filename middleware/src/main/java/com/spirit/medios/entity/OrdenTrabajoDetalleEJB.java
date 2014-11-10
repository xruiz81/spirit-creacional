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

@Table(name = "ORDEN_TRABAJO_DETALLE")
@Entity
public class OrdenTrabajoDetalleEJB implements Serializable, OrdenTrabajoDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long ordenId;
   private java.lang.Long subtipoId;
   private java.lang.Long equipoId;
   private java.lang.Long asignadoaId;
   private java.sql.Date fechalimite;
   private java.sql.Date fechaentrega;
   private java.lang.String urlDescripcion;
   private java.lang.String urlPropuesta;
   private java.lang.String descripcion;
   private java.lang.String estado;
   private java.sql.Timestamp fecha;

   public OrdenTrabajoDetalleEJB() {
   }

   public OrdenTrabajoDetalleEJB(com.spirit.medios.entity.OrdenTrabajoDetalleIf value) {
      setId(value.getId());
      setOrdenId(value.getOrdenId());
      setSubtipoId(value.getSubtipoId());
      setEquipoId(value.getEquipoId());
      setAsignadoaId(value.getAsignadoaId());
      setFechalimite(value.getFechalimite());
      setFechaentrega(value.getFechaentrega());
      setUrlDescripcion(value.getUrlDescripcion());
      setUrlPropuesta(value.getUrlPropuesta());
      setDescripcion(value.getDescripcion());
      setEstado(value.getEstado());
      setFecha(value.getFecha());
   }

   public java.lang.Long create(com.spirit.medios.entity.OrdenTrabajoDetalleIf value) {
      setId(value.getId());
      setOrdenId(value.getOrdenId());
      setSubtipoId(value.getSubtipoId());
      setEquipoId(value.getEquipoId());
      setAsignadoaId(value.getAsignadoaId());
      setFechalimite(value.getFechalimite());
      setFechaentrega(value.getFechaentrega());
      setUrlDescripcion(value.getUrlDescripcion());
      setUrlPropuesta(value.getUrlPropuesta());
      setDescripcion(value.getDescripcion());
      setEstado(value.getEstado());
      setFecha(value.getFecha());
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

   @Column(name = "ORDEN_ID")
   public java.lang.Long getOrdenId() {
      return ordenId;
   }

   public void setOrdenId(java.lang.Long ordenId) {
      this.ordenId = ordenId;
   }

   @Column(name = "SUBTIPO_ID")
   public java.lang.Long getSubtipoId() {
      return subtipoId;
   }

   public void setSubtipoId(java.lang.Long subtipoId) {
      this.subtipoId = subtipoId;
   }

   @Column(name = "EQUIPO_ID")
   public java.lang.Long getEquipoId() {
      return equipoId;
   }

   public void setEquipoId(java.lang.Long equipoId) {
      this.equipoId = equipoId;
   }

   @Column(name = "ASIGNADOA_ID")
   public java.lang.Long getAsignadoaId() {
      return asignadoaId;
   }

   public void setAsignadoaId(java.lang.Long asignadoaId) {
      this.asignadoaId = asignadoaId;
   }

   @Column(name = "FECHALIMITE")
   public java.sql.Date getFechalimite() {
      return fechalimite;
   }

   public void setFechalimite(java.sql.Date fechalimite) {
      this.fechalimite = fechalimite;
   }

   @Column(name = "FECHAENTREGA")
   public java.sql.Date getFechaentrega() {
      return fechaentrega;
   }

   public void setFechaentrega(java.sql.Date fechaentrega) {
      this.fechaentrega = fechaentrega;
   }

   @Column(name = "URL_DESCRIPCION")
   public java.lang.String getUrlDescripcion() {
      return urlDescripcion;
   }

   public void setUrlDescripcion(java.lang.String urlDescripcion) {
      this.urlDescripcion = urlDescripcion;
   }

   @Column(name = "URL_PROPUESTA")
   public java.lang.String getUrlPropuesta() {
      return urlPropuesta;
   }

   public void setUrlPropuesta(java.lang.String urlPropuesta) {
      this.urlPropuesta = urlPropuesta;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }
	public String toString() {
		return ToStringer.toString((OrdenTrabajoDetalleIf)this);
	}
}
