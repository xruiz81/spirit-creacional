package com.spirit.sri.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "IMPUESTO_RENTA")
@Entity
public class ImpuestoRentaEJB implements Serializable, ImpuestoRentaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.math.BigDecimal porcentaje;
   private java.sql.Timestamp fechaInicio;
   private java.sql.Timestamp fechaFin;

   public ImpuestoRentaEJB() {
   }

   public ImpuestoRentaEJB(com.spirit.sri.entity.ImpuestoRentaIf value) {
      setId(value.getId());
      setPorcentaje(value.getPorcentaje());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
   }

   public java.lang.Long create(com.spirit.sri.entity.ImpuestoRentaIf value) {
      setId(value.getId());
      setPorcentaje(value.getPorcentaje());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
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

   @Column(name = "PORCENTAJE")
   public java.math.BigDecimal getPorcentaje() {
      return porcentaje;
   }

   public void setPorcentaje(java.math.BigDecimal porcentaje) {
      this.porcentaje = porcentaje;
   }

   @Column(name = "FECHA_INICIO")
   public java.sql.Timestamp getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Timestamp fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FIN")
   public java.sql.Timestamp getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Timestamp fechaFin) {
      this.fechaFin = fechaFin;
   }
	public String toString() {
		return ToStringer.toString((ImpuestoRentaIf)this);
	}
}
