package com.spirit.crm.entity;

import java.io.Serializable;

/**
 * The GastoElectoralAbonoValue objects contains all fields that are available in the storage.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:38 $
 */
public class GastoElectoralAbonoValue implements Serializable {


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

   private java.lang.String entregadoPor;

   /**
    * Returns the value of the <code>entregadoPor</code> property.
    *
    * @return the value of the <code>entregadoPor</code> property.
    */
   public java.lang.String getEntregadoPor() {
      return entregadoPor;
   }

   /**
    * Sets the value of the <code>entregadoPor</code> property.
    *
    * @param entregadoPor a value for <code>entregadoPor</code>.
    */
   public void setEntregadoPor(java.lang.String entregadoPor) {
      this.entregadoPor = entregadoPor;
   }

   private java.math.BigDecimal valor;

   /**
    * Returns the value of the <code>valor</code> property.
    *
    * @return the value of the <code>valor</code> property.
    */
   public java.math.BigDecimal getValor() {
      return valor;
   }

   /**
    * Sets the value of the <code>valor</code> property.
    *
    * @param valor a value for <code>valor</code>.
    */
   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.lang.String campana;

   /**
    * Returns the value of the <code>campana</code> property.
    *
    * @return the value of the <code>campana</code> property.
    */
   public java.lang.String getCampana() {
      return campana;
   }

   /**
    * Sets the value of the <code>campana</code> property.
    *
    * @param campana a value for <code>campana</code>.
    */
   public void setCampana(java.lang.String campana) {
      this.campana = campana;
   }
}
