package com.spirit.nomina.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "SALARIO_MINIMO_VITAL")
@Entity
public class SalarioMinimoVitalEJB implements Serializable, SalarioMinimoVitalIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.math.BigDecimal valor;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFin;

   public SalarioMinimoVitalEJB() {
   }

   public SalarioMinimoVitalEJB(com.spirit.nomina.entity.SalarioMinimoVitalIf value) {
      setId(value.getId());
      setValor(value.getValor());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
   }

   public java.lang.Long create(com.spirit.nomina.entity.SalarioMinimoVitalIf value) {
      setId(value.getId());
      setValor(value.getValor());
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

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "FECHA_INICIO")
   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FIN")
   public java.sql.Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Date fechaFin) {
      this.fechaFin = fechaFin;
   }
	public String toString() {
		return ToStringer.toString((SalarioMinimoVitalIf)this);
	}
}
