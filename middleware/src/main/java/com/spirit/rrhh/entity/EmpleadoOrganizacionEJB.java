package com.spirit.rrhh.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "EMPLEADO_ORGANIZACION")
@Entity
public class EmpleadoOrganizacionEJB implements Serializable, EmpleadoOrganizacionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empleadoId;
   private java.lang.Long departamento;
   private java.lang.Long tipoEmpleadoId;
   private java.sql.Timestamp fechaInicio;
   private java.sql.Timestamp fechaFin;
   private java.lang.String descripcion;
   private java.lang.String archivoAdjunto;

   public EmpleadoOrganizacionEJB() {
   }

   public EmpleadoOrganizacionEJB(com.spirit.rrhh.entity.EmpleadoOrganizacionIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setDepartamento(value.getDepartamento());
      setTipoEmpleadoId(value.getTipoEmpleadoId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setDescripcion(value.getDescripcion());
      setArchivoAdjunto(value.getArchivoAdjunto());
   }

   public java.lang.Long create(com.spirit.rrhh.entity.EmpleadoOrganizacionIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setDepartamento(value.getDepartamento());
      setTipoEmpleadoId(value.getTipoEmpleadoId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setDescripcion(value.getDescripcion());
      setArchivoAdjunto(value.getArchivoAdjunto());
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

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "DEPARTAMENTO")
   public java.lang.Long getDepartamento() {
      return departamento;
   }

   public void setDepartamento(java.lang.Long departamento) {
      this.departamento = departamento;
   }

   @Column(name = "TIPO_EMPLEADO_ID")
   public java.lang.Long getTipoEmpleadoId() {
      return tipoEmpleadoId;
   }

   public void setTipoEmpleadoId(java.lang.Long tipoEmpleadoId) {
      this.tipoEmpleadoId = tipoEmpleadoId;
   }

   @Column(name = "FECHA_INICIO")
   public java.sql.Timestamp getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Timestamp fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FIN")
   public java.sql.Timestamp getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Timestamp fechaFin) {
      this.fechaFin = fechaFin;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "ARCHIVO_ADJUNTO")
   public java.lang.String getArchivoAdjunto() {
      return archivoAdjunto;
   }

   public void setArchivoAdjunto(java.lang.String archivoAdjunto) {
      this.archivoAdjunto = archivoAdjunto;
   }
	public String toString() {
		return ToStringer.toString((EmpleadoOrganizacionIf)this);
	}
}
