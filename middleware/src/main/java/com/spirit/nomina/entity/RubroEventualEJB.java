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

@Table(name = "RUBRO_EVENTUAL")
@Entity
public class RubroEventualEJB implements Serializable, RubroEventualIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long contratoId;
   private java.lang.Long rubroId;
   private java.lang.Long tipoRolIdCobro;
   private java.math.BigDecimal valor;
   private java.sql.Date fechaCobro;
   private java.lang.String estado;
   private java.lang.String observacion;
   private java.lang.Long tipoRolIdPago;
   private java.sql.Date fechaPago;
   private java.lang.Long identificacion;

   public RubroEventualEJB() {
   }

   public RubroEventualEJB(com.spirit.nomina.entity.RubroEventualIf value) {
      setId(value.getId());
      setContratoId(value.getContratoId());
      setRubroId(value.getRubroId());
      setTipoRolIdCobro(value.getTipoRolIdCobro());
      setValor(value.getValor());
      setFechaCobro(value.getFechaCobro());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setTipoRolIdPago(value.getTipoRolIdPago());
      setFechaPago(value.getFechaPago());
      setIdentificacion(value.getIdentificacion());
   }

   public java.lang.Long create(com.spirit.nomina.entity.RubroEventualIf value) {
      setId(value.getId());
      setContratoId(value.getContratoId());
      setRubroId(value.getRubroId());
      setTipoRolIdCobro(value.getTipoRolIdCobro());
      setValor(value.getValor());
      setFechaCobro(value.getFechaCobro());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setTipoRolIdPago(value.getTipoRolIdPago());
      setFechaPago(value.getFechaPago());
      setIdentificacion(value.getIdentificacion());
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

   @Column(name = "TIPO_ROL_ID_COBRO")
   public java.lang.Long getTipoRolIdCobro() {
      return tipoRolIdCobro;
   }

   public void setTipoRolIdCobro(java.lang.Long tipoRolIdCobro) {
      this.tipoRolIdCobro = tipoRolIdCobro;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "FECHA_COBRO")
   public java.sql.Date getFechaCobro() {
      return fechaCobro;
   }

   public void setFechaCobro(java.sql.Date fechaCobro) {
      this.fechaCobro = fechaCobro;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "TIPO_ROL_ID_PAGO")
   public java.lang.Long getTipoRolIdPago() {
      return tipoRolIdPago;
   }

   public void setTipoRolIdPago(java.lang.Long tipoRolIdPago) {
      this.tipoRolIdPago = tipoRolIdPago;
   }

   @Column(name = "FECHA_PAGO")
   public java.sql.Date getFechaPago() {
      return fechaPago;
   }

   public void setFechaPago(java.sql.Date fechaPago) {
      this.fechaPago = fechaPago;
   }

   @Column(name = "IDENTIFICACION")
   public java.lang.Long getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.Long identificacion) {
      this.identificacion = identificacion;
   }
	public String toString() {
		return ToStringer.toString((RubroEventualIf)this);
	}
}
