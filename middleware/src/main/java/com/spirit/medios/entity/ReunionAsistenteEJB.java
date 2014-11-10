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

@Table(name = "REUNION_ASISTENTE")
@Entity
public class ReunionAsistenteEJB implements Serializable, ReunionAsistenteIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long reunionId;
   private java.lang.Long clienteContactoId;
   private java.lang.Long empleadoId;
   private java.lang.String clienteContacto;

   public ReunionAsistenteEJB() {
   }

   public ReunionAsistenteEJB(com.spirit.medios.entity.ReunionAsistenteIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setClienteContactoId(value.getClienteContactoId());
      setEmpleadoId(value.getEmpleadoId());
      setClienteContacto(value.getClienteContacto());
   }

   public java.lang.Long create(com.spirit.medios.entity.ReunionAsistenteIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setClienteContactoId(value.getClienteContactoId());
      setEmpleadoId(value.getEmpleadoId());
      setClienteContacto(value.getClienteContacto());
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

   @Column(name = "REUNION_ID")
   public java.lang.Long getReunionId() {
      return reunionId;
   }

   public void setReunionId(java.lang.Long reunionId) {
      this.reunionId = reunionId;
   }

   @Column(name = "CLIENTE_CONTACTO_ID")
   public java.lang.Long getClienteContactoId() {
      return clienteContactoId;
   }

   public void setClienteContactoId(java.lang.Long clienteContactoId) {
      this.clienteContactoId = clienteContactoId;
   }

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "CLIENTE_CONTACTO")
   public java.lang.String getClienteContacto() {
      return clienteContacto;
   }

   public void setClienteContacto(java.lang.String clienteContacto) {
      this.clienteContacto = clienteContacto;
   }
	public String toString() {
		return ToStringer.toString((ReunionAsistenteIf)this);
	}
}
