package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class VentasConsolidadoData implements VentasConsolidadoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.sql.Timestamp fechaCierre;

   public java.sql.Timestamp getFechaCierre() {
      return fechaCierre;
   }

   public void setFechaCierre(java.sql.Timestamp fechaCierre) {
      this.fechaCierre = fechaCierre;
   }

   private java.lang.String cajeroNombre;

   public java.lang.String getCajeroNombre() {
      return cajeroNombre;
   }

   public void setCajeroNombre(java.lang.String cajeroNombre) {
      this.cajeroNombre = cajeroNombre;
   }

   private java.lang.String cajeroCedula;

   public java.lang.String getCajeroCedula() {
      return cajeroCedula;
   }

   public void setCajeroCedula(java.lang.String cajeroCedula) {
      this.cajeroCedula = cajeroCedula;
   }

   private java.lang.String cajaCodigo;

   public java.lang.String getCajaCodigo() {
      return cajaCodigo;
   }

   public void setCajaCodigo(java.lang.String cajaCodigo) {
      this.cajaCodigo = cajaCodigo;
   }

   private java.lang.String cajaNombre;

   public java.lang.String getCajaNombre() {
      return cajaNombre;
   }

   public void setCajaNombre(java.lang.String cajaNombre) {
      this.cajaNombre = cajaNombre;
   }

   private java.math.BigDecimal valorEfectivo;

   public java.math.BigDecimal getValorEfectivo() {
      return valorEfectivo;
   }

   public void setValorEfectivo(java.math.BigDecimal valorEfectivo) {
      this.valorEfectivo = valorEfectivo;
   }

   private java.math.BigDecimal valorTarjeta;

   public java.math.BigDecimal getValorTarjeta() {
      return valorTarjeta;
   }

   public void setValorTarjeta(java.math.BigDecimal valorTarjeta) {
      this.valorTarjeta = valorTarjeta;
   }

   private java.math.BigDecimal valorGiftcards;

   public java.math.BigDecimal getValorGiftcards() {
      return valorGiftcards;
   }

   public void setValorGiftcards(java.math.BigDecimal valorGiftcards) {
      this.valorGiftcards = valorGiftcards;
   }

   private java.math.BigDecimal valorDevoluciones;

   public java.math.BigDecimal getValorDevoluciones() {
      return valorDevoluciones;
   }

   public void setValorDevoluciones(java.math.BigDecimal valorDevoluciones) {
      this.valorDevoluciones = valorDevoluciones;
   }

   private java.math.BigDecimal valorCajaInicial;

   public java.math.BigDecimal getValorCajaInicial() {
      return valorCajaInicial;
   }

   public void setValorCajaInicial(java.math.BigDecimal valorCajaInicial) {
      this.valorCajaInicial = valorCajaInicial;
   }

   private java.math.BigDecimal valorCheque;

   public java.math.BigDecimal getValorCheque() {
      return valorCheque;
   }

   public void setValorCheque(java.math.BigDecimal valorCheque) {
      this.valorCheque = valorCheque;
   }

   private java.math.BigDecimal valorDonacion;

   public java.math.BigDecimal getValorDonacion() {
      return valorDonacion;
   }

   public void setValorDonacion(java.math.BigDecimal valorDonacion) {
      this.valorDonacion = valorDonacion;
   }

   private java.math.BigDecimal valorCredito;

   public java.math.BigDecimal getValorCredito() {
      return valorCredito;
   }

   public void setValorCredito(java.math.BigDecimal valorCredito) {
      this.valorCredito = valorCredito;
   }

   private java.math.BigDecimal valorCajaIngreso;

   public java.math.BigDecimal getValorCajaIngreso() {
      return valorCajaIngreso;
   }

   public void setValorCajaIngreso(java.math.BigDecimal valorCajaIngreso) {
      this.valorCajaIngreso = valorCajaIngreso;
   }

   private java.math.BigDecimal valorCajaEgreso;

   public java.math.BigDecimal getValorCajaEgreso() {
      return valorCajaEgreso;
   }

   public void setValorCajaEgreso(java.math.BigDecimal valorCajaEgreso) {
      this.valorCajaEgreso = valorCajaEgreso;
   }

   private java.math.BigDecimal valorDescuadre;

   public java.math.BigDecimal getValorDescuadre() {
      return valorDescuadre;
   }

   public void setValorDescuadre(java.math.BigDecimal valorDescuadre) {
      this.valorDescuadre = valorDescuadre;
   }

   private java.math.BigDecimal valorMultas;

   public java.math.BigDecimal getValorMultas() {
      return valorMultas;
   }

   public void setValorMultas(java.math.BigDecimal valorMultas) {
      this.valorMultas = valorMultas;
   }

   private java.math.BigDecimal valorDocumentos;

   public java.math.BigDecimal getValorDocumentos() {
      return valorDocumentos;
   }

   public void setValorDocumentos(java.math.BigDecimal valorDocumentos) {
      this.valorDocumentos = valorDocumentos;
   }

   private java.sql.Timestamp fechaApertura;

   public java.sql.Timestamp getFechaApertura() {
      return fechaApertura;
   }

   public void setFechaApertura(java.sql.Timestamp fechaApertura) {
      this.fechaApertura = fechaApertura;
   }
   public VentasConsolidadoData() {
   }

   public VentasConsolidadoData(com.spirit.pos.entity.VentasConsolidadoIf value) {
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



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((VentasConsolidadoIf)this);
	}
}
