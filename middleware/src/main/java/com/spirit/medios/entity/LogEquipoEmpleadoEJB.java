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

@Table(name = "LOG_EQUIPO_EMPLEADO")
@Entity
public class LogEquipoEmpleadoEJB implements Serializable, LogEquipoEmpleadoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long equipoId;
   private java.lang.Long empleadoId;
   private java.lang.String rol;
   private java.lang.String log;
   private java.lang.String jefe;

   public LogEquipoEmpleadoEJB() {
   }

   public LogEquipoEmpleadoEJB(com.spirit.medios.entity.LogEquipoEmpleadoIf value) {
      setId(value.getId());
      setEquipoId(value.getEquipoId());
      setEmpleadoId(value.getEmpleadoId());
      setRol(value.getRol());
      setLog(value.getLog());
      setJefe(value.getJefe());
   }

   public java.lang.Long create(com.spirit.medios.entity.LogEquipoEmpleadoIf value) {
      setId(value.getId());
      setEquipoId(value.getEquipoId());
      setEmpleadoId(value.getEmpleadoId());
      setRol(value.getRol());
      setLog(value.getLog());
      setJefe(value.getJefe());
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

   @Column(name = "EQUIPO_ID")
   public java.lang.Long getEquipoId() {
      return equipoId;
   }

   public void setEquipoId(java.lang.Long equipoId) {
      this.equipoId = equipoId;
   }

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "ROL")
   public java.lang.String getRol() {
      return rol;
   }

   public void setRol(java.lang.String rol) {
      this.rol = rol;
   }

   @Column(name = "LOG")
   public java.lang.String getLog() {
      return log;
   }

   public void setLog(java.lang.String log) {
      this.log = log;
   }

   @Column(name = "JEFE")
   public java.lang.String getJefe() {
      return jefe;
   }

   public void setJefe(java.lang.String jefe) {
      this.jefe = jefe;
   }
	public String toString() {
		return ToStringer.toString((LogEquipoEmpleadoIf)this);
	}
}
