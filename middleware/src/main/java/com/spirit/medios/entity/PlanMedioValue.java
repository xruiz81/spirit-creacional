package com.spirit.medios.entity;

import java.io.Serializable;

/**
 * The PlanMedioValue objects contains all fields that are available in the storage.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */
public class PlanMedioValue implements Serializable {


   private java.lang.Long id;

   /**
    * Returns the value of the <code>id</code> property.
    *
    * @return the value of the <code>id</code> property.
    */
   public java.lang.Long getId() {
      return id;
   }

   /**
    * Sets the value of the <code>id</code> property.
    *
    * @param id a value for <code>id</code>.
    */
   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   /**
    * Returns the value of the <code>codigo</code> property.
    *
    * @return the value of the <code>codigo</code> property.
    */
   public java.lang.String getCodigo() {
      return codigo;
   }

   /**
    * Sets the value of the <code>codigo</code> property.
    *
    * @param codigo a value for <code>codigo</code>.
    */
   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String concepto;

   /**
    * Returns the value of the <code>concepto</code> property.
    *
    * @return the value of the <code>concepto</code> property.
    */
   public java.lang.String getConcepto() {
      return concepto;
   }

   /**
    * Sets the value of the <code>concepto</code> property.
    *
    * @param concepto a value for <code>concepto</code>.
    */
   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   private java.lang.Long ordentrabajodetId;

   /**
    * Returns the value of the <code>ordentrabajodetId</code> property.
    *
    * @return the value of the <code>ordentrabajodetId</code> property.
    */
   public java.lang.Long getOrdentrabajodetId() {
      return ordentrabajodetId;
   }

   /**
    * Sets the value of the <code>ordentrabajodetId</code> property.
    *
    * @param ordentrabajodetId a value for <code>ordentrabajodetId</code>.
    */
   public void setOrdentrabajodetId(java.lang.Long ordentrabajodetId) {
      this.ordentrabajodetId = ordentrabajodetId;
   }

   private java.lang.Long grupoobjetivoId;

   /**
    * Returns the value of the <code>grupoobjetivoId</code> property.
    *
    * @return the value of the <code>grupoobjetivoId</code> property.
    */
   public java.lang.Long getGrupoobjetivoId() {
      return grupoobjetivoId;
   }

   /**
    * Sets the value of the <code>grupoobjetivoId</code> property.
    *
    * @param grupoobjetivoId a value for <code>grupoobjetivoId</code>.
    */
   public void setGrupoobjetivoId(java.lang.Long grupoobjetivoId) {
      this.grupoobjetivoId = grupoobjetivoId;
   }

   private java.lang.Long tipoproveedorId;

   /**
    * Returns the value of the <code>tipoproveedorId</code> property.
    *
    * @return the value of the <code>tipoproveedorId</code> property.
    */
   public java.lang.Long getTipoproveedorId() {
      return tipoproveedorId;
   }

   /**
    * Sets the value of the <code>tipoproveedorId</code> property.
    *
    * @param tipoproveedorId a value for <code>tipoproveedorId</code>.
    */
   public void setTipoproveedorId(java.lang.Long tipoproveedorId) {
      this.tipoproveedorId = tipoproveedorId;
   }

   private java.lang.Long usuarioId;

   /**
    * Returns the value of the <code>usuarioId</code> property.
    *
    * @return the value of the <code>usuarioId</code> property.
    */
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   /**
    * Sets the value of the <code>usuarioId</code> property.
    *
    * @param usuarioId a value for <code>usuarioId</code>.
    */
   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.Integer modificacion;

   /**
    * Returns the value of the <code>modificacion</code> property.
    *
    * @return the value of the <code>modificacion</code> property.
    */
   public java.lang.Integer getModificacion() {
      return modificacion;
   }

   /**
    * Sets the value of the <code>modificacion</code> property.
    *
    * @param modificacion a value for <code>modificacion</code>.
    */
   public void setModificacion(java.lang.Integer modificacion) {
      this.modificacion = modificacion;
   }

   private java.sql.Date fecha;

   /**
    * Returns the value of the <code>fecha</code> property.
    *
    * @return the value of the <code>fecha</code> property.
    */
   public java.sql.Date getFecha() {
      return fecha;
   }

   /**
    * Sets the value of the <code>fecha</code> property.
    *
    * @param fecha a value for <code>fecha</code>.
    */
   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.sql.Date fechaini;

   /**
    * Returns the value of the <code>fechaini</code> property.
    *
    * @return the value of the <code>fechaini</code> property.
    */
   public java.sql.Date getFechaini() {
      return fechaini;
   }

   /**
    * Sets the value of the <code>fechaini</code> property.
    *
    * @param fechaini a value for <code>fechaini</code>.
    */
   public void setFechaini(java.sql.Date fechaini) {
      this.fechaini = fechaini;
   }

   private java.sql.Date fechafin;

   /**
    * Returns the value of the <code>fechafin</code> property.
    *
    * @return the value of the <code>fechafin</code> property.
    */
   public java.sql.Date getFechafin() {
      return fechafin;
   }

   /**
    * Sets the value of the <code>fechafin</code> property.
    *
    * @param fechafin a value for <code>fechafin</code>.
    */
   public void setFechafin(java.sql.Date fechafin) {
      this.fechafin = fechafin;
   }

   private java.math.BigDecimal valorTarifa;

   /**
    * Returns the value of the <code>valorTarifa</code> property.
    *
    * @return the value of the <code>valorTarifa</code> property.
    */
   public java.math.BigDecimal getValorTarifa() {
      return valorTarifa;
   }

   /**
    * Sets the value of the <code>valorTarifa</code> property.
    *
    * @param valorTarifa a value for <code>valorTarifa</code>.
    */
   public void setValorTarifa(java.math.BigDecimal valorTarifa) {
      this.valorTarifa = valorTarifa;
   }

   private java.math.BigDecimal valorDescuento;

   /**
    * Returns the value of the <code>valorDescuento</code> property.
    *
    * @return the value of the <code>valorDescuento</code> property.
    */
   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   /**
    * Sets the value of the <code>valorDescuento</code> property.
    *
    * @param valorDescuento a value for <code>valorDescuento</code>.
    */
   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   private java.lang.Long planmedioorigen;

   /**
    * Returns the value of the <code>planmedioorigen</code> property.
    *
    * @return the value of the <code>planmedioorigen</code> property.
    */
   public java.lang.Long getPlanmedioorigen() {
      return planmedioorigen;
   }

   /**
    * Sets the value of the <code>planmedioorigen</code> property.
    *
    * @param planmedioorigen a value for <code>planmedioorigen</code>.
    */
   public void setPlanmedioorigen(java.lang.Long planmedioorigen) {
      this.planmedioorigen = planmedioorigen;
   }

   private java.math.BigDecimal ciudad1;

   /**
    * Returns the value of the <code>ciudad1</code> property.
    *
    * @return the value of the <code>ciudad1</code> property.
    */
   public java.math.BigDecimal getCiudad1() {
      return ciudad1;
   }

   /**
    * Sets the value of the <code>ciudad1</code> property.
    *
    * @param ciudad1 a value for <code>ciudad1</code>.
    */
   public void setCiudad1(java.math.BigDecimal ciudad1) {
      this.ciudad1 = ciudad1;
   }

   private java.math.BigDecimal ciudad2;

   /**
    * Returns the value of the <code>ciudad2</code> property.
    *
    * @return the value of the <code>ciudad2</code> property.
    */
   public java.math.BigDecimal getCiudad2() {
      return ciudad2;
   }

   /**
    * Sets the value of the <code>ciudad2</code> property.
    *
    * @param ciudad2 a value for <code>ciudad2</code>.
    */
   public void setCiudad2(java.math.BigDecimal ciudad2) {
      this.ciudad2 = ciudad2;
   }

   private java.math.BigDecimal ciudad3;

   /**
    * Returns the value of the <code>ciudad3</code> property.
    *
    * @return the value of the <code>ciudad3</code> property.
    */
   public java.math.BigDecimal getCiudad3() {
      return ciudad3;
   }

   /**
    * Sets the value of the <code>ciudad3</code> property.
    *
    * @param ciudad3 a value for <code>ciudad3</code>.
    */
   public void setCiudad3(java.math.BigDecimal ciudad3) {
      this.ciudad3 = ciudad3;
   }

   private java.lang.String estado;

   /**
    * Returns the value of the <code>estado</code> property.
    *
    * @return the value of the <code>estado</code> property.
    */
   public java.lang.String getEstado() {
      return estado;
   }

   /**
    * Sets the value of the <code>estado</code> property.
    *
    * @param estado a value for <code>estado</code>.
    */
   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String cobertura;

   /**
    * Returns the value of the <code>cobertura</code> property.
    *
    * @return the value of the <code>cobertura</code> property.
    */
   public java.lang.String getCobertura() {
      return cobertura;
   }

   /**
    * Sets the value of the <code>cobertura</code> property.
    *
    * @param cobertura a value for <code>cobertura</code>.
    */
   public void setCobertura(java.lang.String cobertura) {
      this.cobertura = cobertura;
   }

   private java.lang.String consideraciones;

   /**
    * Returns the value of the <code>consideraciones</code> property.
    *
    * @return the value of the <code>consideraciones</code> property.
    */
   public java.lang.String getConsideraciones() {
      return consideraciones;
   }

   /**
    * Sets the value of the <code>consideraciones</code> property.
    *
    * @param consideraciones a value for <code>consideraciones</code>.
    */
   public void setConsideraciones(java.lang.String consideraciones) {
      this.consideraciones = consideraciones;
   }
}
