package com.spirit.pos.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "VENTAS_CONSOLIDADO")
@Entity
public class VentasConsolidadoEJB implements Serializable, VentasConsolidadoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.sql.Timestamp fechaCierre;
   private java.lang.String cajeroNombre;
   private java.lang.String cajeroCedula;
   private java.lang.String cajaCodigo;
   private java.lang.String cajaNombre;
   private java.math.BigDecimal valorEfectivo;
   private java.math.BigDecimal valorTarjeta;
   private java.math.BigDecimal valorGiftcards;
   private java.math.BigDecimal valorDevoluciones;
   private java.math.BigDecimal valorCajaInicial;
   private java.math.BigDecimal valorCheque;
   private java.math.BigDecimal valorDonacion;
   private java.math.BigDecimal valorCredito;
   private java.math.BigDecimal valorCajaIngreso;
   private java.math.BigDecimal valorCajaEgreso;
   private java.math.BigDecimal valorDescuadre;
   private java.math.BigDecimal valorMultas;
   private java.math.BigDecimal valorDocumentos;
   private java.sql.Timestamp fechaApertura;

   public VentasConsolidadoEJB() {
   }

   public VentasConsolidadoEJB(com.spirit.pos.entity.VentasConsolidadoIf value) {
      setId(value.getId());
      setFechaCierre(value.getFechaCierre());
      setCajeroNombre(value.getCajeroNombre());
      setCajeroCedula(value.getCajeroCedula());
      setCajaCodigo(value.getCajaCodigo());
      setCajaNombre(value.getCajaNombre());
      setValorEfectivo(value.getValorEfectivo());
      setValorTarjeta(value.getValorTarjeta());
      setValorGiftcards(value.getValorGiftcards());
      setValorDevoluciones(value.getValorDevoluciones());
      setValorCajaInicial(value.getValorCajaInicial());
      setValorCheque(value.getValorCheque());
      setValorDonacion(value.getValorDonacion());
      setValorCredito(value.getValorCredito());
      setValorCajaIngreso(value.getValorCajaIngreso());
      setValorCajaEgreso(value.getValorCajaEgreso());
      setValorDescuadre(value.getValorDescuadre());
      setValorMultas(value.getValorMultas());
      setValorDocumentos(value.getValorDocumentos());
      setFechaApertura(value.getFechaApertura());
   }

   public java.lang.Long create(com.spirit.pos.entity.VentasConsolidadoIf value) {
      setId(value.getId());
      setFechaCierre(value.getFechaCierre());
      setCajeroNombre(value.getCajeroNombre());
      setCajeroCedula(value.getCajeroCedula());
      setCajaCodigo(value.getCajaCodigo());
      setCajaNombre(value.getCajaNombre());
      setValorEfectivo(value.getValorEfectivo());
      setValorTarjeta(value.getValorTarjeta());
      setValorGiftcards(value.getValorGiftcards());
      setValorDevoluciones(value.getValorDevoluciones());
      setValorCajaInicial(value.getValorCajaInicial());
      setValorCheque(value.getValorCheque());
      setValorDonacion(value.getValorDonacion());
      setValorCredito(value.getValorCredito());
      setValorCajaIngreso(value.getValorCajaIngreso());
      setValorCajaEgreso(value.getValorCajaEgreso());
      setValorDescuadre(value.getValorDescuadre());
      setValorMultas(value.getValorMultas());
      setValorDocumentos(value.getValorDocumentos());
      setFechaApertura(value.getFechaApertura());
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

   @Column(name = "FECHA_CIERRE")
   public java.sql.Timestamp getFechaCierre() {
      return fechaCierre;
   }

   public void setFechaCierre(java.sql.Timestamp fechaCierre) {
      this.fechaCierre = fechaCierre;
   }

   @Column(name = "CAJERO_NOMBRE")
   public java.lang.String getCajeroNombre() {
      return cajeroNombre;
   }

   public void setCajeroNombre(java.lang.String cajeroNombre) {
      this.cajeroNombre = cajeroNombre;
   }

   @Column(name = "CAJERO_CEDULA")
   public java.lang.String getCajeroCedula() {
      return cajeroCedula;
   }

   public void setCajeroCedula(java.lang.String cajeroCedula) {
      this.cajeroCedula = cajeroCedula;
   }

   @Column(name = "CAJA_CODIGO")
   public java.lang.String getCajaCodigo() {
      return cajaCodigo;
   }

   public void setCajaCodigo(java.lang.String cajaCodigo) {
      this.cajaCodigo = cajaCodigo;
   }

   @Column(name = "CAJA_NOMBRE")
   public java.lang.String getCajaNombre() {
      return cajaNombre;
   }

   public void setCajaNombre(java.lang.String cajaNombre) {
      this.cajaNombre = cajaNombre;
   }

   @Column(name = "VALOR_EFECTIVO")
   public java.math.BigDecimal getValorEfectivo() {
      return valorEfectivo;
   }

   public void setValorEfectivo(java.math.BigDecimal valorEfectivo) {
      this.valorEfectivo = valorEfectivo;
   }

   @Column(name = "VALOR_TARJETA")
   public java.math.BigDecimal getValorTarjeta() {
      return valorTarjeta;
   }

   public void setValorTarjeta(java.math.BigDecimal valorTarjeta) {
      this.valorTarjeta = valorTarjeta;
   }

   @Column(name = "VALOR_GIFTCARDS")
   public java.math.BigDecimal getValorGiftcards() {
      return valorGiftcards;
   }

   public void setValorGiftcards(java.math.BigDecimal valorGiftcards) {
      this.valorGiftcards = valorGiftcards;
   }

   @Column(name = "VALOR_DEVOLUCIONES")
   public java.math.BigDecimal getValorDevoluciones() {
      return valorDevoluciones;
   }

   public void setValorDevoluciones(java.math.BigDecimal valorDevoluciones) {
      this.valorDevoluciones = valorDevoluciones;
   }

   @Column(name = "VALOR_CAJA_INICIAL")
   public java.math.BigDecimal getValorCajaInicial() {
      return valorCajaInicial;
   }

   public void setValorCajaInicial(java.math.BigDecimal valorCajaInicial) {
      this.valorCajaInicial = valorCajaInicial;
   }

   @Column(name = "VALOR_CHEQUE")
   public java.math.BigDecimal getValorCheque() {
      return valorCheque;
   }

   public void setValorCheque(java.math.BigDecimal valorCheque) {
      this.valorCheque = valorCheque;
   }

   @Column(name = "VALOR_DONACION")
   public java.math.BigDecimal getValorDonacion() {
      return valorDonacion;
   }

   public void setValorDonacion(java.math.BigDecimal valorDonacion) {
      this.valorDonacion = valorDonacion;
   }

   @Column(name = "VALOR_CREDITO")
   public java.math.BigDecimal getValorCredito() {
      return valorCredito;
   }

   public void setValorCredito(java.math.BigDecimal valorCredito) {
      this.valorCredito = valorCredito;
   }

   @Column(name = "VALOR_CAJA_INGRESO")
   public java.math.BigDecimal getValorCajaIngreso() {
      return valorCajaIngreso;
   }

   public void setValorCajaIngreso(java.math.BigDecimal valorCajaIngreso) {
      this.valorCajaIngreso = valorCajaIngreso;
   }

   @Column(name = "VALOR_CAJA_EGRESO")
   public java.math.BigDecimal getValorCajaEgreso() {
      return valorCajaEgreso;
   }

   public void setValorCajaEgreso(java.math.BigDecimal valorCajaEgreso) {
      this.valorCajaEgreso = valorCajaEgreso;
   }

   @Column(name = "VALOR_DESCUADRE")
   public java.math.BigDecimal getValorDescuadre() {
      return valorDescuadre;
   }

   public void setValorDescuadre(java.math.BigDecimal valorDescuadre) {
      this.valorDescuadre = valorDescuadre;
   }

   @Column(name = "VALOR_MULTAS")
   public java.math.BigDecimal getValorMultas() {
      return valorMultas;
   }

   public void setValorMultas(java.math.BigDecimal valorMultas) {
      this.valorMultas = valorMultas;
   }

   @Column(name = "VALOR_DOCUMENTOS")
   public java.math.BigDecimal getValorDocumentos() {
      return valorDocumentos;
   }

   public void setValorDocumentos(java.math.BigDecimal valorDocumentos) {
      this.valorDocumentos = valorDocumentos;
   }

   @Column(name = "FECHA_APERTURA")
   public java.sql.Timestamp getFechaApertura() {
      return fechaApertura;
   }

   public void setFechaApertura(java.sql.Timestamp fechaApertura) {
      this.fechaApertura = fechaApertura;
   }
	public String toString() {
		return ToStringer.toString((VentasConsolidadoIf)this);
	}
}
