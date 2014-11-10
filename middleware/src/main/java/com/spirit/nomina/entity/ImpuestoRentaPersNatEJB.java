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

@Table(name = "IMPUESTO_RENTA_PERS_NAT")
@Entity
public class ImpuestoRentaPersNatEJB implements Serializable, ImpuestoRentaPersNatIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.math.BigDecimal fraccionBasica;
   private java.math.BigDecimal excesoHasta;
   private java.math.BigDecimal impFraccionBasica;
   private java.math.BigDecimal porcentajeImpFraccionBasica;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFin;

   public ImpuestoRentaPersNatEJB() {
   }

   public ImpuestoRentaPersNatEJB(com.spirit.nomina.entity.ImpuestoRentaPersNatIf value) {
      setId(value.getId());
      setFraccionBasica(value.getFraccionBasica());
      setExcesoHasta(value.getExcesoHasta());
      setImpFraccionBasica(value.getImpFraccionBasica());
      setPorcentajeImpFraccionBasica(value.getPorcentajeImpFraccionBasica());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
   }

   public java.lang.Long create(com.spirit.nomina.entity.ImpuestoRentaPersNatIf value) {
      setId(value.getId());
      setFraccionBasica(value.getFraccionBasica());
      setExcesoHasta(value.getExcesoHasta());
      setImpFraccionBasica(value.getImpFraccionBasica());
      setPorcentajeImpFraccionBasica(value.getPorcentajeImpFraccionBasica());
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

   @Column(name = "FRACCION_BASICA")
   public java.math.BigDecimal getFraccionBasica() {
      return fraccionBasica;
   }

   public void setFraccionBasica(java.math.BigDecimal fraccionBasica) {
      this.fraccionBasica = fraccionBasica;
   }

   @Column(name = "EXCESO_HASTA")
   public java.math.BigDecimal getExcesoHasta() {
      return excesoHasta;
   }

   public void setExcesoHasta(java.math.BigDecimal excesoHasta) {
      this.excesoHasta = excesoHasta;
   }

   @Column(name = "IMP_FRACCION_BASICA")
   public java.math.BigDecimal getImpFraccionBasica() {
      return impFraccionBasica;
   }

   public void setImpFraccionBasica(java.math.BigDecimal impFraccionBasica) {
      this.impFraccionBasica = impFraccionBasica;
   }

   @Column(name = "PORCENTAJE_IMP_FRACCION_BASICA")
   public java.math.BigDecimal getPorcentajeImpFraccionBasica() {
      return porcentajeImpFraccionBasica;
   }

   public void setPorcentajeImpFraccionBasica(java.math.BigDecimal porcentajeImpFraccionBasica) {
      this.porcentajeImpFraccionBasica = porcentajeImpFraccionBasica;
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
		return ToStringer.toString((ImpuestoRentaPersNatIf)this);
	}
}
