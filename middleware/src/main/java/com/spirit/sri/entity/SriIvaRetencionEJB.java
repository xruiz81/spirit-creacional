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

@Table(name = "SRI_IVA_RETENCION")
@Entity
public class SriIvaRetencionEJB implements Serializable, SriIvaRetencionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String concepto;
   private java.math.BigDecimal porcentaje;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFin;

   public SriIvaRetencionEJB() {
   }

   public SriIvaRetencionEJB(com.spirit.sri.entity.SriIvaRetencionIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setConcepto(value.getConcepto());
      setPorcentaje(value.getPorcentaje());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
   }

   public java.lang.Long create(com.spirit.sri.entity.SriIvaRetencionIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setConcepto(value.getConcepto());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "CONCEPTO")
   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   @Column(name = "PORCENTAJE")
   public java.math.BigDecimal getPorcentaje() {
      return porcentaje;
   }

   public void setPorcentaje(java.math.BigDecimal porcentaje) {
      this.porcentaje = porcentaje;
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
		return ToStringer.toString((SriIvaRetencionIf)this);
	}
}
