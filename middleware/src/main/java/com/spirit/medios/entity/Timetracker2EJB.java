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

@Table(name = "TIMETRACKER2")
@Entity
public class Timetracker2EJB implements Serializable, Timetracker2If {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String actividad;
   private java.lang.String estado;
   private java.lang.Long empresaId;

   public Timetracker2EJB() {
   }

   public Timetracker2EJB(com.spirit.medios.entity.Timetracker2If value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setActividad(value.getActividad());
      setEstado(value.getEstado());
      setEmpresaId(value.getEmpresaId());
   }

   public java.lang.Long create(com.spirit.medios.entity.Timetracker2If value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setActividad(value.getActividad());
      setEstado(value.getEstado());
      setEmpresaId(value.getEmpresaId());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "ACTIVIDAD")
   public java.lang.String getActividad() {
      return actividad;
   }

   public void setActividad(java.lang.String actividad) {
      this.actividad = actividad;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
	public String toString() {
		return ToStringer.toString((Timetracker2If)this);
	}
}
