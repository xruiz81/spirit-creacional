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

@Table(name = "ROL_PAGO_DETALLE_UTILIDAD")
@Entity
public class RolPagoDetalleUtilidadEJB implements Serializable, RolPagoDetalleUtilidadIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long rolpagoId;
   private java.lang.Long contratoUtilidadId;
   private java.lang.Long contratoId;
   private java.lang.String cargo;
   private java.sql.Date fechaIngreso;
   private java.sql.Date fechaSalida;
   private java.lang.String genero;
   private java.lang.Integer diasLaborados;
   private java.math.BigDecimal utilidadContrato;
   private java.lang.Integer numeroCargas;
   private java.lang.Integer diasCargas;
   private java.math.BigDecimal utilidadCargas;
   private java.math.BigDecimal total;

   public RolPagoDetalleUtilidadEJB() {
   }

   public RolPagoDetalleUtilidadEJB(com.spirit.nomina.entity.RolPagoDetalleUtilidadIf value) {
      setId(value.getId());
      setRolpagoId(value.getRolpagoId());
      setContratoUtilidadId(value.getContratoUtilidadId());
      setContratoId(value.getContratoId());
      setCargo(value.getCargo());
      setFechaIngreso(value.getFechaIngreso());
      setFechaSalida(value.getFechaSalida());
      setGenero(value.getGenero());
      setDiasLaborados(value.getDiasLaborados());
      setUtilidadContrato(value.getUtilidadContrato());
      setNumeroCargas(value.getNumeroCargas());
      setDiasCargas(value.getDiasCargas());
      setUtilidadCargas(value.getUtilidadCargas());
      setTotal(value.getTotal());
   }

   public java.lang.Long create(com.spirit.nomina.entity.RolPagoDetalleUtilidadIf value) {
      setId(value.getId());
      setRolpagoId(value.getRolpagoId());
      setContratoUtilidadId(value.getContratoUtilidadId());
      setContratoId(value.getContratoId());
      setCargo(value.getCargo());
      setFechaIngreso(value.getFechaIngreso());
      setFechaSalida(value.getFechaSalida());
      setGenero(value.getGenero());
      setDiasLaborados(value.getDiasLaborados());
      setUtilidadContrato(value.getUtilidadContrato());
      setNumeroCargas(value.getNumeroCargas());
      setDiasCargas(value.getDiasCargas());
      setUtilidadCargas(value.getUtilidadCargas());
      setTotal(value.getTotal());
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

   @Column(name = "ROLPAGO_ID")
   public java.lang.Long getRolpagoId() {
      return rolpagoId;
   }

   public void setRolpagoId(java.lang.Long rolpagoId) {
      this.rolpagoId = rolpagoId;
   }

   @Column(name = "CONTRATO_UTILIDAD_ID")
   public java.lang.Long getContratoUtilidadId() {
      return contratoUtilidadId;
   }

   public void setContratoUtilidadId(java.lang.Long contratoUtilidadId) {
      this.contratoUtilidadId = contratoUtilidadId;
   }

   @Column(name = "CONTRATO_ID")
   public java.lang.Long getContratoId() {
      return contratoId;
   }

   public void setContratoId(java.lang.Long contratoId) {
      this.contratoId = contratoId;
   }

   @Column(name = "CARGO")
   public java.lang.String getCargo() {
      return cargo;
   }

   public void setCargo(java.lang.String cargo) {
      this.cargo = cargo;
   }

   @Column(name = "FECHA_INGRESO")
   public java.sql.Date getFechaIngreso() {
      return fechaIngreso;
   }

   public void setFechaIngreso(java.sql.Date fechaIngreso) {
      this.fechaIngreso = fechaIngreso;
   }

   @Column(name = "FECHA_SALIDA")
   public java.sql.Date getFechaSalida() {
      return fechaSalida;
   }

   public void setFechaSalida(java.sql.Date fechaSalida) {
      this.fechaSalida = fechaSalida;
   }

   @Column(name = "GENERO")
   public java.lang.String getGenero() {
      return genero;
   }

   public void setGenero(java.lang.String genero) {
      this.genero = genero;
   }

   @Column(name = "DIAS_LABORADOS")
   public java.lang.Integer getDiasLaborados() {
      return diasLaborados;
   }

   public void setDiasLaborados(java.lang.Integer diasLaborados) {
      this.diasLaborados = diasLaborados;
   }

   @Column(name = "UTILIDAD_CONTRATO")
   public java.math.BigDecimal getUtilidadContrato() {
      return utilidadContrato;
   }

   public void setUtilidadContrato(java.math.BigDecimal utilidadContrato) {
      this.utilidadContrato = utilidadContrato;
   }

   @Column(name = "NUMERO_CARGAS")
   public java.lang.Integer getNumeroCargas() {
      return numeroCargas;
   }

   public void setNumeroCargas(java.lang.Integer numeroCargas) {
      this.numeroCargas = numeroCargas;
   }

   @Column(name = "DIAS_CARGAS")
   public java.lang.Integer getDiasCargas() {
      return diasCargas;
   }

   public void setDiasCargas(java.lang.Integer diasCargas) {
      this.diasCargas = diasCargas;
   }

   @Column(name = "UTILIDAD_CARGAS")
   public java.math.BigDecimal getUtilidadCargas() {
      return utilidadCargas;
   }

   public void setUtilidadCargas(java.math.BigDecimal utilidadCargas) {
      this.utilidadCargas = utilidadCargas;
   }

   @Column(name = "TOTAL")
   public java.math.BigDecimal getTotal() {
      return total;
   }

   public void setTotal(java.math.BigDecimal total) {
      this.total = total;
   }
	public String toString() {
		return ToStringer.toString((RolPagoDetalleUtilidadIf)this);
	}
}
