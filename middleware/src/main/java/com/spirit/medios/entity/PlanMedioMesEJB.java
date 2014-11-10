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

@Table(name = "PLAN_MEDIO_MES")
@Entity
public class PlanMedioMesEJB implements Serializable, PlanMedioMesIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long planMedioId;
   private java.sql.Timestamp fechaInicio;
   private java.sql.Timestamp fechaFin;
   private java.math.BigDecimal valorSubtotal;
   private java.math.BigDecimal valorDescuento;
   private java.lang.String tipo;
   private java.math.BigDecimal valorIva;
   private java.math.BigDecimal valorTotal;
   private java.math.BigDecimal valorDescuentoVenta;
   private java.math.BigDecimal valorComisionAgencia;

   public PlanMedioMesEJB() {
   }

   public PlanMedioMesEJB(com.spirit.medios.entity.PlanMedioMesIf value) {
      setId(value.getId());
      setPlanMedioId(value.getPlanMedioId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setValorSubtotal(value.getValorSubtotal());
      setValorDescuento(value.getValorDescuento());
      setTipo(value.getTipo());
      setValorIva(value.getValorIva());
      setValorTotal(value.getValorTotal());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
   }

   public java.lang.Long create(com.spirit.medios.entity.PlanMedioMesIf value) {
      setId(value.getId());
      setPlanMedioId(value.getPlanMedioId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setValorSubtotal(value.getValorSubtotal());
      setValorDescuento(value.getValorDescuento());
      setTipo(value.getTipo());
      setValorIva(value.getValorIva());
      setValorTotal(value.getValorTotal());
      setValorDescuentoVenta(value.getValorDescuentoVenta());
      setValorComisionAgencia(value.getValorComisionAgencia());
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

   @Column(name = "PLAN_MEDIO_ID")
   public java.lang.Long getPlanMedioId() {
      return planMedioId;
   }

   public void setPlanMedioId(java.lang.Long planMedioId) {
      this.planMedioId = planMedioId;
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

   @Column(name = "VALOR_SUBTOTAL")
   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   @Column(name = "VALOR_DESCUENTO")
   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   @Column(name = "TIPO")
   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   @Column(name = "VALOR_IVA")
   public java.math.BigDecimal getValorIva() {
      return valorIva;
   }

   public void setValorIva(java.math.BigDecimal valorIva) {
      this.valorIva = valorIva;
   }

   @Column(name = "VALOR_TOTAL")
   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   @Column(name = "VALOR_DESCUENTO_VENTA")
   public java.math.BigDecimal getValorDescuentoVenta() {
      return valorDescuentoVenta;
   }

   public void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
      this.valorDescuentoVenta = valorDescuentoVenta;
   }

   @Column(name = "VALOR_COMISION_AGENCIA")
   public java.math.BigDecimal getValorComisionAgencia() {
      return valorComisionAgencia;
   }

   public void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
      this.valorComisionAgencia = valorComisionAgencia;
   }
	public String toString() {
		return ToStringer.toString((PlanMedioMesIf)this);
	}
}
