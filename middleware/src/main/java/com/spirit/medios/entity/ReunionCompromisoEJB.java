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

@Table(name = "REUNION_COMPROMISO")
@Entity
public class ReunionCompromisoEJB implements Serializable, ReunionCompromisoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long reunionId;
   private java.sql.Date fecha;
   private java.lang.String descripcion;
   private java.lang.String estado;
   private java.lang.String temaTratado;

   public ReunionCompromisoEJB() {
   }

   public ReunionCompromisoEJB(com.spirit.medios.entity.ReunionCompromisoIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setFecha(value.getFecha());
      setDescripcion(value.getDescripcion());
      setEstado(value.getEstado());
      setTemaTratado(value.getTemaTratado());
   }

   public java.lang.Long create(com.spirit.medios.entity.ReunionCompromisoIf value) {
      setId(value.getId());
      setReunionId(value.getReunionId());
      setFecha(value.getFecha());
      setDescripcion(value.getDescripcion());
      setEstado(value.getEstado());
      setTemaTratado(value.getTemaTratado());
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

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "TEMA_TRATADO")
   public java.lang.String getTemaTratado() {
      return temaTratado;
   }

   public void setTemaTratado(java.lang.String temaTratado) {
      this.temaTratado = temaTratado;
   }
	public String toString() {
		return ToStringer.toString((ReunionCompromisoIf)this);
	}
}
