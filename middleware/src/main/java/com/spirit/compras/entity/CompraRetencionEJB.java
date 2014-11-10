package com.spirit.compras.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "COMPRA_RETENCION")
@Entity
public class CompraRetencionEJB implements Serializable, CompraRetencionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String establecimiento;
   private java.lang.String puntoEmision;
   private java.lang.String secuencial;
   private java.lang.String autorizacion;
   private java.sql.Date fechaEmision;
   private java.lang.Long compraId;
   private java.lang.String ejercicioFiscal;
   private java.math.BigDecimal baseImponible;
   private java.lang.String impuesto;
   private java.math.BigDecimal porcentajeRetencion;
   private java.math.BigDecimal valorRetenido;
   private java.lang.String codigoImpuesto;

   public CompraRetencionEJB() {
   }

   public CompraRetencionEJB(com.spirit.compras.entity.CompraRetencionIf value) {
      setId(value.getId());
      setEstablecimiento(value.getEstablecimiento());
      setPuntoEmision(value.getPuntoEmision());
      setSecuencial(value.getSecuencial());
      setAutorizacion(value.getAutorizacion());
      setFechaEmision(value.getFechaEmision());
      setCompraId(value.getCompraId());
      setEjercicioFiscal(value.getEjercicioFiscal());
      setBaseImponible(value.getBaseImponible());
      setImpuesto(value.getImpuesto());
      setPorcentajeRetencion(value.getPorcentajeRetencion());
      setValorRetenido(value.getValorRetenido());
      setCodigoImpuesto(value.getCodigoImpuesto());
   }

   public java.lang.Long create(com.spirit.compras.entity.CompraRetencionIf value) {
      setId(value.getId());
      setEstablecimiento(value.getEstablecimiento());
      setPuntoEmision(value.getPuntoEmision());
      setSecuencial(value.getSecuencial());
      setAutorizacion(value.getAutorizacion());
      setFechaEmision(value.getFechaEmision());
      setCompraId(value.getCompraId());
      setEjercicioFiscal(value.getEjercicioFiscal());
      setBaseImponible(value.getBaseImponible());
      setImpuesto(value.getImpuesto());
      setPorcentajeRetencion(value.getPorcentajeRetencion());
      setValorRetenido(value.getValorRetenido());
      setCodigoImpuesto(value.getCodigoImpuesto());
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

   @Column(name = "ESTABLECIMIENTO")
   public java.lang.String getEstablecimiento() {
      return establecimiento;
   }

   public void setEstablecimiento(java.lang.String establecimiento) {
      this.establecimiento = establecimiento;
   }

   @Column(name = "PUNTO_EMISION")
   public java.lang.String getPuntoEmision() {
      return puntoEmision;
   }

   public void setPuntoEmision(java.lang.String puntoEmision) {
      this.puntoEmision = puntoEmision;
   }

   @Column(name = "SECUENCIAL")
   public java.lang.String getSecuencial() {
      return secuencial;
   }

   public void setSecuencial(java.lang.String secuencial) {
      this.secuencial = secuencial;
   }

   @Column(name = "AUTORIZACION")
   public java.lang.String getAutorizacion() {
      return autorizacion;
   }

   public void setAutorizacion(java.lang.String autorizacion) {
      this.autorizacion = autorizacion;
   }

   @Column(name = "FECHA_EMISION")
   public java.sql.Date getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Date fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   @Column(name = "COMPRA_ID")
   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   @Column(name = "EJERCICIO_FISCAL")
   public java.lang.String getEjercicioFiscal() {
      return ejercicioFiscal;
   }

   public void setEjercicioFiscal(java.lang.String ejercicioFiscal) {
      this.ejercicioFiscal = ejercicioFiscal;
   }

   @Column(name = "BASE_IMPONIBLE")
   public java.math.BigDecimal getBaseImponible() {
      return baseImponible;
   }

   public void setBaseImponible(java.math.BigDecimal baseImponible) {
      this.baseImponible = baseImponible;
   }

   @Column(name = "IMPUESTO")
   public java.lang.String getImpuesto() {
      return impuesto;
   }

   public void setImpuesto(java.lang.String impuesto) {
      this.impuesto = impuesto;
   }

   @Column(name = "PORCENTAJE_RETENCION")
   public java.math.BigDecimal getPorcentajeRetencion() {
      return porcentajeRetencion;
   }

   public void setPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) {
      this.porcentajeRetencion = porcentajeRetencion;
   }

   @Column(name = "VALOR_RETENIDO")
   public java.math.BigDecimal getValorRetenido() {
      return valorRetenido;
   }

   public void setValorRetenido(java.math.BigDecimal valorRetenido) {
      this.valorRetenido = valorRetenido;
   }

   @Column(name = "CODIGO_IMPUESTO")
   public java.lang.String getCodigoImpuesto() {
      return codigoImpuesto;
   }

   public void setCodigoImpuesto(java.lang.String codigoImpuesto) {
      this.codigoImpuesto = codigoImpuesto;
   }
	public String toString() {
		return ToStringer.toString((CompraRetencionIf)this);
	}
}
