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

@Table(name = "PROPUESTA_DETALLE")
@Entity
public class PropuestaDetalleEJB implements Serializable, PropuestaDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long propuestaId;
   private java.lang.Long presupuestoId;
   private java.lang.Long planmedioId;

   public PropuestaDetalleEJB() {
   }

   public PropuestaDetalleEJB(com.spirit.medios.entity.PropuestaDetalleIf value) {
      setId(value.getId());
      setPropuestaId(value.getPropuestaId());
      setPresupuestoId(value.getPresupuestoId());
      setPlanmedioId(value.getPlanmedioId());
   }

   public java.lang.Long create(com.spirit.medios.entity.PropuestaDetalleIf value) {
      setId(value.getId());
      setPropuestaId(value.getPropuestaId());
      setPresupuestoId(value.getPresupuestoId());
      setPlanmedioId(value.getPlanmedioId());
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

   @Column(name = "PROPUESTA_ID")
   public java.lang.Long getPropuestaId() {
      return propuestaId;
   }

   public void setPropuestaId(java.lang.Long propuestaId) {
      this.propuestaId = propuestaId;
   }

   @Column(name = "PRESUPUESTO_ID")
   public java.lang.Long getPresupuestoId() {
      return presupuestoId;
   }

   public void setPresupuestoId(java.lang.Long presupuestoId) {
      this.presupuestoId = presupuestoId;
   }

   @Column(name = "PLANMEDIO_ID")
   public java.lang.Long getPlanmedioId() {
      return planmedioId;
   }

   public void setPlanmedioId(java.lang.Long planmedioId) {
      this.planmedioId = planmedioId;
   }
	public String toString() {
		return ToStringer.toString((PropuestaDetalleIf)this);
	}
}
