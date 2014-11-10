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

@Table(name = "EMPLEADO_OFICINA")
@Entity
public class EmpleadoOficinaEJB implements Serializable, EmpleadoOficinaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empleadoId;
   private java.lang.Long oficinaId;

   public EmpleadoOficinaEJB() {
   }

   public EmpleadoOficinaEJB(com.spirit.general.entity.EmpleadoOficinaIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setOficinaId(value.getOficinaId());
   }

   public java.lang.Long create(com.spirit.general.entity.EmpleadoOficinaIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setOficinaId(value.getOficinaId());
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

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }
	public String toString() {
		return ToStringer.toString((EmpleadoOficinaIf)this);
	}
}
