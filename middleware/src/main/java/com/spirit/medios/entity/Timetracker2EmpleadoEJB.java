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

@Table(name = "TIMETRACKER2_EMPLEADO")
@Entity
public class Timetracker2EmpleadoEJB implements Serializable, Timetracker2EmpleadoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long timetracker2Id;
   private java.lang.Long empleadoId;
   private java.lang.String estado;

   public Timetracker2EmpleadoEJB() {
   }

   public Timetracker2EmpleadoEJB(com.spirit.medios.entity.Timetracker2EmpleadoIf value) {
      setId(value.getId());
      setTimetracker2Id(value.getTimetracker2Id());
      setEmpleadoId(value.getEmpleadoId());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.medios.entity.Timetracker2EmpleadoIf value) {
      setId(value.getId());
      setTimetracker2Id(value.getTimetracker2Id());
      setEmpleadoId(value.getEmpleadoId());
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

   @Column(name = "TIMETRACKER2_ID")
   public java.lang.Long getTimetracker2Id() {
      return timetracker2Id;
   }

   public void setTimetracker2Id(java.lang.Long timetracker2Id) {
      this.timetracker2Id = timetracker2Id;
   }

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((Timetracker2EmpleadoIf)this);
	}
}
