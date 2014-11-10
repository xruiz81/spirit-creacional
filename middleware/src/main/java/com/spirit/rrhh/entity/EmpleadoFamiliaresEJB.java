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

@Table(name = "EMPLEADO_FAMILIARES")
@Entity
public class EmpleadoFamiliaresEJB implements Serializable, EmpleadoFamiliaresIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empleadoId;
   private java.lang.String tipo;
   private java.lang.String apellidos;
   private java.lang.String nombres;
   private java.sql.Timestamp fechaNacimiento;
   private java.lang.String cedulaIdentidad;
   private java.lang.String ocupacion;
   private java.lang.String nivelEstudios;
   private java.lang.String trabaja;
   private java.lang.String nombreInstitucion;
   private java.lang.String embarazo;
   private java.sql.Timestamp fechaParto;
   private java.lang.String ultimoAnio;

   public EmpleadoFamiliaresEJB() {
   }

   public EmpleadoFamiliaresEJB(com.spirit.rrhh.entity.EmpleadoFamiliaresIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setTipo(value.getTipo());
      setApellidos(value.getApellidos());
      setNombres(value.getNombres());
      setFechaNacimiento(value.getFechaNacimiento());
      setCedulaIdentidad(value.getCedulaIdentidad());
      setOcupacion(value.getOcupacion());
      setNivelEstudios(value.getNivelEstudios());
      setTrabaja(value.getTrabaja());
      setNombreInstitucion(value.getNombreInstitucion());
      setEmbarazo(value.getEmbarazo());
      setFechaParto(value.getFechaParto());
      setUltimoAnio(value.getUltimoAnio());
   }

   public java.lang.Long create(com.spirit.rrhh.entity.EmpleadoFamiliaresIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setTipo(value.getTipo());
      setApellidos(value.getApellidos());
      setNombres(value.getNombres());
      setFechaNacimiento(value.getFechaNacimiento());
      setCedulaIdentidad(value.getCedulaIdentidad());
      setOcupacion(value.getOcupacion());
      setNivelEstudios(value.getNivelEstudios());
      setTrabaja(value.getTrabaja());
      setNombreInstitucion(value.getNombreInstitucion());
      setEmbarazo(value.getEmbarazo());
      setFechaParto(value.getFechaParto());
      setUltimoAnio(value.getUltimoAnio());
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

   @Column(name = "TIPO")
   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   @Column(name = "APELLIDOS")
   public java.lang.String getApellidos() {
      return apellidos;
   }

   public void setApellidos(java.lang.String apellidos) {
      this.apellidos = apellidos;
   }

   @Column(name = "NOMBRES")
   public java.lang.String getNombres() {
      return nombres;
   }

   public void setNombres(java.lang.String nombres) {
      this.nombres = nombres;
   }

   @Column(name = "FECHA_NACIMIENTO")
   public java.sql.Timestamp getFechaNacimiento() {
      return fechaNacimiento;
   }

   public void setFechaNacimiento(java.sql.Timestamp fechaNacimiento) {
      this.fechaNacimiento = fechaNacimiento;
   }

   @Column(name = "CEDULA_IDENTIDAD")
   public java.lang.String getCedulaIdentidad() {
      return cedulaIdentidad;
   }

   public void setCedulaIdentidad(java.lang.String cedulaIdentidad) {
      this.cedulaIdentidad = cedulaIdentidad;
   }

   @Column(name = "OCUPACION")
   public java.lang.String getOcupacion() {
      return ocupacion;
   }

   public void setOcupacion(java.lang.String ocupacion) {
      this.ocupacion = ocupacion;
   }

   @Column(name = "NIVEL_ESTUDIOS")
   public java.lang.String getNivelEstudios() {
      return nivelEstudios;
   }

   public void setNivelEstudios(java.lang.String nivelEstudios) {
      this.nivelEstudios = nivelEstudios;
   }

   @Column(name = "TRABAJA")
   public java.lang.String getTrabaja() {
      return trabaja;
   }

   public void setTrabaja(java.lang.String trabaja) {
      this.trabaja = trabaja;
   }

   @Column(name = "NOMBRE_INSTITUCION")
   public java.lang.String getNombreInstitucion() {
      return nombreInstitucion;
   }

   public void setNombreInstitucion(java.lang.String nombreInstitucion) {
      this.nombreInstitucion = nombreInstitucion;
   }

   @Column(name = "EMBARAZO")
   public java.lang.String getEmbarazo() {
      return embarazo;
   }

   public void setEmbarazo(java.lang.String embarazo) {
      this.embarazo = embarazo;
   }

   @Column(name = "FECHA_PARTO")
   public java.sql.Timestamp getFechaParto() {
      return fechaParto;
   }

   public void setFechaParto(java.sql.Timestamp fechaParto) {
      this.fechaParto = fechaParto;
   }

   @Column(name = "ULTIMO_ANIO")
   public java.lang.String getUltimoAnio() {
      return ultimoAnio;
   }

   public void setUltimoAnio(java.lang.String ultimoAnio) {
      this.ultimoAnio = ultimoAnio;
   }
	public String toString() {
		return ToStringer.toString((EmpleadoFamiliaresIf)this);
	}
}
