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

@Table(name = "CONTRATO_UTILIDAD")
@Entity
public class ContratoUtilidadEJB implements Serializable, ContratoUtilidadIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFin;
   private java.math.BigDecimal utilidad;
   private java.math.BigDecimal porcentajeContrato;
   private java.math.BigDecimal porcentajeCarga;
   private java.lang.String codigo;
   private java.lang.Long empresaId;

   public ContratoUtilidadEJB() {
   }

   public ContratoUtilidadEJB(com.spirit.nomina.entity.ContratoUtilidadIf value) {
      setId(value.getId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setUtilidad(value.getUtilidad());
      setPorcentajeContrato(value.getPorcentajeContrato());
      setPorcentajeCarga(value.getPorcentajeCarga());
      setCodigo(value.getCodigo());
      setEmpresaId(value.getEmpresaId());
   }

   public java.lang.Long create(com.spirit.nomina.entity.ContratoUtilidadIf value) {
      setId(value.getId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setUtilidad(value.getUtilidad());
      setPorcentajeContrato(value.getPorcentajeContrato());
      setPorcentajeCarga(value.getPorcentajeCarga());
      setCodigo(value.getCodigo());
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

   @Column(name = "UTILIDAD")
   public java.math.BigDecimal getUtilidad() {
      return utilidad;
   }

   public void setUtilidad(java.math.BigDecimal utilidad) {
      this.utilidad = utilidad;
   }

   @Column(name = "PORCENTAJE_CONTRATO")
   public java.math.BigDecimal getPorcentajeContrato() {
      return porcentajeContrato;
   }

   public void setPorcentajeContrato(java.math.BigDecimal porcentajeContrato) {
      this.porcentajeContrato = porcentajeContrato;
   }

   @Column(name = "PORCENTAJE_CARGA")
   public java.math.BigDecimal getPorcentajeCarga() {
      return porcentajeCarga;
   }

   public void setPorcentajeCarga(java.math.BigDecimal porcentajeCarga) {
      this.porcentajeCarga = porcentajeCarga;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
	public String toString() {
		return ToStringer.toString((ContratoUtilidadIf)this);
	}
}
