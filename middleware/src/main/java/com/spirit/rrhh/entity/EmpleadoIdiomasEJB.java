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

@Table(name = "EMPLEADO_IDIOMAS")
@Entity
public class EmpleadoIdiomasEJB implements Serializable, EmpleadoIdiomasIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empleadoId;
   private java.lang.Long idiomaId;
   private java.lang.String habla;
   private java.lang.String comprende;
   private java.lang.String lee;
   private java.lang.String escribe;

   public EmpleadoIdiomasEJB() {
   }

   public EmpleadoIdiomasEJB(com.spirit.rrhh.entity.EmpleadoIdiomasIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setIdiomaId(value.getIdiomaId());
      setHabla(value.getHabla());
      setComprende(value.getComprende());
      setLee(value.getLee());
      setEscribe(value.getEscribe());
   }

   public java.lang.Long create(com.spirit.rrhh.entity.EmpleadoIdiomasIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setIdiomaId(value.getIdiomaId());
      setHabla(value.getHabla());
      setComprende(value.getComprende());
      setLee(value.getLee());
      setEscribe(value.getEscribe());
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

   @Column(name = "IDIOMA_ID")
   public java.lang.Long getIdiomaId() {
      return idiomaId;
   }

   public void setIdiomaId(java.lang.Long idiomaId) {
      this.idiomaId = idiomaId;
   }

   @Column(name = "HABLA")
   public java.lang.String getHabla() {
      return habla;
   }

   public void setHabla(java.lang.String habla) {
      this.habla = habla;
   }

   @Column(name = "COMPRENDE")
   public java.lang.String getComprende() {
      return comprende;
   }

   public void setComprende(java.lang.String comprende) {
      this.comprende = comprende;
   }

   @Column(name = "LEE")
   public java.lang.String getLee() {
      return lee;
   }

   public void setLee(java.lang.String lee) {
      this.lee = lee;
   }

   @Column(name = "ESCRIBE")
   public java.lang.String getEscribe() {
      return escribe;
   }

   public void setEscribe(java.lang.String escribe) {
      this.escribe = escribe;
   }
	public String toString() {
		return ToStringer.toString((EmpleadoIdiomasIf)this);
	}
}
