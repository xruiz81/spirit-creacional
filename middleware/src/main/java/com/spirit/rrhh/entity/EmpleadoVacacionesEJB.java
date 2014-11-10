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

@Table(name = "EMPLEADO_VACACIONES")
@Entity
public class EmpleadoVacacionesEJB implements Serializable, EmpleadoVacacionesIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empleadoId;
   private java.lang.Float saldoDias;
   private java.sql.Timestamp fechaInicio;
   private java.sql.Timestamp fechaFin;
   private java.lang.String observacion;
   private java.lang.String archivoAdjunto;

   public EmpleadoVacacionesEJB() {
   }

   public EmpleadoVacacionesEJB(com.spirit.rrhh.entity.EmpleadoVacacionesIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setSaldoDias(value.getSaldoDias());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setObservacion(value.getObservacion());
      setArchivoAdjunto(value.getArchivoAdjunto());
   }

   public java.lang.Long create(com.spirit.rrhh.entity.EmpleadoVacacionesIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setSaldoDias(value.getSaldoDias());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setObservacion(value.getObservacion());
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

   @Column(name = "SALDO_DIAS")
   public java.lang.Float getSaldoDias() {
      return saldoDias;
   }

   public void setSaldoDias(java.lang.Float saldoDias) {
      this.saldoDias = saldoDias;
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

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "ARCHIVO_ADJUNTO")
   public java.lang.String getArchivoAdjunto() {
      return archivoAdjunto;
   }

   public void setArchivoAdjunto(java.lang.String archivoAdjunto) {
      this.archivoAdjunto = archivoAdjunto;
   }
	public String toString() {
		return ToStringer.toString((EmpleadoVacacionesIf)this);
	}
}
