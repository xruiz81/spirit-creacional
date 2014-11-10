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

@Table(name = "TIMETRACKER2_TIEMPO")
@Entity
public class Timetracker2TiempoEJB implements Serializable, Timetracker2TiempoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empleadoId;
   private java.lang.Long clienteOficinaId;
   private java.lang.Integer tiempoDesignado;

   public Timetracker2TiempoEJB() {
   }

   public Timetracker2TiempoEJB(com.spirit.medios.entity.Timetracker2TiempoIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setClienteOficinaId(value.getClienteOficinaId());
      setTiempoDesignado(value.getTiempoDesignado());
   }

   public java.lang.Long create(com.spirit.medios.entity.Timetracker2TiempoIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setClienteOficinaId(value.getClienteOficinaId());
      setTiempoDesignado(value.getTiempoDesignado());
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

   @Column(name = "CLIENTE_OFICINA_ID")
   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   @Column(name = "TIEMPO_DESIGNADO")
   public java.lang.Integer getTiempoDesignado() {
      return tiempoDesignado;
   }

   public void setTiempoDesignado(java.lang.Integer tiempoDesignado) {
      this.tiempoDesignado = tiempoDesignado;
   }
	public String toString() {
		return ToStringer.toString((Timetracker2TiempoIf)this);
	}
}
