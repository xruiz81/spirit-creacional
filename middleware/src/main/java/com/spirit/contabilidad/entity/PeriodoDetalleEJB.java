package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PERIODO_DETALLE")
@Entity
public class PeriodoDetalleEJB implements Serializable, PeriodoDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String status;
   private java.lang.Long periodoId;
   private java.lang.String mes;
   private java.lang.String anio;

   public PeriodoDetalleEJB() {
   }

   public PeriodoDetalleEJB(com.spirit.contabilidad.entity.PeriodoDetalleIf value) {
      setId(value.getId());
      setStatus(value.getStatus());
      setPeriodoId(value.getPeriodoId());
      setMes(value.getMes());
      setAnio(value.getAnio());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.PeriodoDetalleIf value) {
      setId(value.getId());
      setStatus(value.getStatus());
      setPeriodoId(value.getPeriodoId());
      setMes(value.getMes());
      setAnio(value.getAnio());
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

   @Column(name = "STATUS")
   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }

   @Column(name = "PERIODO_ID")
   public java.lang.Long getPeriodoId() {
      return periodoId;
   }

   public void setPeriodoId(java.lang.Long periodoId) {
      this.periodoId = periodoId;
   }

   @Column(name = "MES")
   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   @Column(name = "ANIO")
   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }
	public String toString() {
		return ToStringer.toString((PeriodoDetalleIf)this);
	}
}
