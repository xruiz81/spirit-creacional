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

@Table(name = "ROL_PAGO_DETALLE")
@Entity
public class RolPagoDetalleEJB implements Serializable, RolPagoDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long rolpagoId;
   private java.lang.Long contratoId;
   private java.lang.Long rubroId;
   private java.lang.Long rubroEventualId;
   private java.math.BigDecimal valor;
   private java.lang.String observacion;
   private java.lang.String estado;
   private java.sql.Date fechaPago;
   private java.lang.Long tipoPagoId;
   private java.lang.Long cuentaBancariaId;
   private java.lang.String preimpreso;
   private java.lang.Long asientoId;

   public RolPagoDetalleEJB() {
   }

   public RolPagoDetalleEJB(com.spirit.nomina.entity.RolPagoDetalleIf value) {
      setId(value.getId());
      setRolpagoId(value.getRolpagoId());
      setContratoId(value.getContratoId());
      setRubroId(value.getRubroId());
      setRubroEventualId(value.getRubroEventualId());
      setValor(value.getValor());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setFechaPago(value.getFechaPago());
      setTipoPagoId(value.getTipoPagoId());
      setCuentaBancariaId(value.getCuentaBancariaId());
      setPreimpreso(value.getPreimpreso());
      setAsientoId(value.getAsientoId());
   }

   public java.lang.Long create(com.spirit.nomina.entity.RolPagoDetalleIf value) {
      setId(value.getId());
      setRolpagoId(value.getRolpagoId());
      setContratoId(value.getContratoId());
      setRubroId(value.getRubroId());
      setRubroEventualId(value.getRubroEventualId());
      setValor(value.getValor());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setFechaPago(value.getFechaPago());
      setTipoPagoId(value.getTipoPagoId());
      setCuentaBancariaId(value.getCuentaBancariaId());
      setPreimpreso(value.getPreimpreso());
      setAsientoId(value.getAsientoId());
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

   @Column(name = "CONTRATO_ID")
   public java.lang.Long getContratoId() {
      return contratoId;
   }

   public void setContratoId(java.lang.Long contratoId) {
      this.contratoId = contratoId;
   }

   @Column(name = "RUBRO_ID")
   public java.lang.Long getRubroId() {
      return rubroId;
   }

   public void setRubroId(java.lang.Long rubroId) {
      this.rubroId = rubroId;
   }

   @Column(name = "RUBRO_EVENTUAL_ID")
   public java.lang.Long getRubroEventualId() {
      return rubroEventualId;
   }

   public void setRubroEventualId(java.lang.Long rubroEventualId) {
      this.rubroEventualId = rubroEventualId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "FECHA_PAGO")
   public java.sql.Date getFechaPago() {
      return fechaPago;
   }

   public void setFechaPago(java.sql.Date fechaPago) {
      this.fechaPago = fechaPago;
   }

   @Column(name = "TIPO_PAGO_ID")
   public java.lang.Long getTipoPagoId() {
      return tipoPagoId;
   }

   public void setTipoPagoId(java.lang.Long tipoPagoId) {
      this.tipoPagoId = tipoPagoId;
   }

   @Column(name = "CUENTA_BANCARIA_ID")
   public java.lang.Long getCuentaBancariaId() {
      return cuentaBancariaId;
   }

   public void setCuentaBancariaId(java.lang.Long cuentaBancariaId) {
      this.cuentaBancariaId = cuentaBancariaId;
   }

   @Column(name = "PREIMPRESO")
   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   @Column(name = "ASIENTO_ID")
   public java.lang.Long getAsientoId() {
      return asientoId;
   }

   public void setAsientoId(java.lang.Long asientoId) {
      this.asientoId = asientoId;
   }
	public String toString() {
		return ToStringer.toString((RolPagoDetalleIf)this);
	}
}
