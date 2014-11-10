package com.spirit.medios.entity;

import java.io.Serializable;

/**
 * The PlanMedioMesValue objects contains all fields that are available in the storage.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */
public class PlanMedioMesValue implements Serializable {


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

   private java.lang.Long planmedioId;

   /**
    * Returns the value of the <code>planmedioId</code> property.
    *
    * @return the value of the <code>planmedioId</code> property.
    */
   public java.lang.Long getPlanmedioId() {
      return planmedioId;
   }

   /**
    * Sets the value of the <code>planmedioId</code> property.
    *
    * @param planmedioId a value for <code>planmedioId</code>.
    */
   public void setPlanmedioId(java.lang.Long planmedioId) {
      this.planmedioId = planmedioId;
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

   private java.lang.String tipo;

   /**
    * Returns the value of the <code>tipo</code> property.
    *
    * @return the value of the <code>tipo</code> property.
    */
   public java.lang.String getTipo() {
      return tipo;
   }

   /**
    * Sets the value of the <code>tipo</code> property.
    *
    * @param tipo a value for <code>tipo</code>.
    */
   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }
}
