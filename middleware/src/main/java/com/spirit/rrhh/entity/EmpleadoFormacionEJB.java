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

@Table(name = "EMPLEADO_FORMACION")
@Entity
public class EmpleadoFormacionEJB implements Serializable, EmpleadoFormacionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empleadoId;
   private java.lang.String nivel;
   private java.lang.String ultimoAnio;
   private java.sql.Timestamp fechaGraduacion;
   private java.lang.String tituloObtenido;
   private java.lang.String institucion;
   private java.lang.Long ciudadId;

   public EmpleadoFormacionEJB() {
   }

   public EmpleadoFormacionEJB(com.spirit.rrhh.entity.EmpleadoFormacionIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setNivel(value.getNivel());
      setUltimoAnio(value.getUltimoAnio());
      setFechaGraduacion(value.getFechaGraduacion());
      setTituloObtenido(value.getTituloObtenido());
      setInstitucion(value.getInstitucion());
      setCiudadId(value.getCiudadId());
   }

   public java.lang.Long create(com.spirit.rrhh.entity.EmpleadoFormacionIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setNivel(value.getNivel());
      setUltimoAnio(value.getUltimoAnio());
      setFechaGraduacion(value.getFechaGraduacion());
      setTituloObtenido(value.getTituloObtenido());
      setInstitucion(value.getInstitucion());
      setCiudadId(value.getCiudadId());
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

   @Column(name = "NIVEL")
   public java.lang.String getNivel() {
      return nivel;
   }

   public void setNivel(java.lang.String nivel) {
      this.nivel = nivel;
   }

   @Column(name = "ULTIMO_ANIO")
   public java.lang.String getUltimoAnio() {
      return ultimoAnio;
   }

   public void setUltimoAnio(java.lang.String ultimoAnio) {
      this.ultimoAnio = ultimoAnio;
   }

   @Column(name = "FECHA_GRADUACION")
   public java.sql.Timestamp getFechaGraduacion() {
      return fechaGraduacion;
   }

   public void setFechaGraduacion(java.sql.Timestamp fechaGraduacion) {
      this.fechaGraduacion = fechaGraduacion;
   }

   @Column(name = "TITULO_OBTENIDO")
   public java.lang.String getTituloObtenido() {
      return tituloObtenido;
   }

   public void setTituloObtenido(java.lang.String tituloObtenido) {
      this.tituloObtenido = tituloObtenido;
   }

   @Column(name = "INSTITUCION")
   public java.lang.String getInstitucion() {
      return institucion;
   }

   public void setInstitucion(java.lang.String institucion) {
      this.institucion = institucion;
   }

   @Column(name = "CIUDAD_ID")
   public java.lang.Long getCiudadId() {
      return ciudadId;
   }

   public void setCiudadId(java.lang.Long ciudadId) {
      this.ciudadId = ciudadId;
   }
	public String toString() {
		return ToStringer.toString((EmpleadoFormacionIf)this);
	}
}
