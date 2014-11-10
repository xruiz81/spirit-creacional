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

@Table(name = "TIEMPO_PARCIAL_DOT")
@Entity
public class TiempoParcialDotEJB implements Serializable, TiempoParcialDotIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String descripcion;
   private java.lang.Long fechaInicio;
   private java.lang.Long fechaFin;
   private java.lang.Long tiempo;
   private java.lang.Long idOrdenTrabajoDetalle;
   private java.lang.Long usuarioAsignadoId;

   public TiempoParcialDotEJB() {
   }

   public TiempoParcialDotEJB(com.spirit.medios.entity.TiempoParcialDotIf value) {
      setId(value.getId());
      setDescripcion(value.getDescripcion());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setTiempo(value.getTiempo());
      setIdOrdenTrabajoDetalle(value.getIdOrdenTrabajoDetalle());
      setUsuarioAsignadoId(value.getUsuarioAsignadoId());
   }

   public java.lang.Long create(com.spirit.medios.entity.TiempoParcialDotIf value) {
      setId(value.getId());
      setDescripcion(value.getDescripcion());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setTiempo(value.getTiempo());
      setIdOrdenTrabajoDetalle(value.getIdOrdenTrabajoDetalle());
      setUsuarioAsignadoId(value.getUsuarioAsignadoId());
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

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "FECHA_INICIO")
   public java.lang.Long getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.lang.Long fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FIN")
   public java.lang.Long getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.lang.Long fechaFin) {
      this.fechaFin = fechaFin;
   }

   @Column(name = "TIEMPO")
   public java.lang.Long getTiempo() {
      return tiempo;
   }

   public void setTiempo(java.lang.Long tiempo) {
      this.tiempo = tiempo;
   }

   @Column(name = "ID_ORDEN_TRABAJO_DETALLE")
   public java.lang.Long getIdOrdenTrabajoDetalle() {
      return idOrdenTrabajoDetalle;
   }

   public void setIdOrdenTrabajoDetalle(java.lang.Long idOrdenTrabajoDetalle) {
      this.idOrdenTrabajoDetalle = idOrdenTrabajoDetalle;
   }
	public String toString() {
		return ToStringer.toString((TiempoParcialDotIf)this);
	}

	@Column(name = "USUARIO_ASIGNADO_ID")
	public java.lang.Long getUsuarioAsignadoId() {
		return usuarioAsignadoId;
	}

	public void setUsuarioAsignadoId(java.lang.Long usuarioAsignadoId) {
		this.usuarioAsignadoId = usuarioAsignadoId;
	}
}
