package com.spirit.medios.entity;

import java.io.Serializable;

/**
 * The PresupuestoProductoValue objects contains all fields that are available in the storage.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */
public class PresupuestoProductoValue implements Serializable {


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

   private java.lang.Long productoClienteId;

   /**
    * Returns the value of the <code>productoClienteId</code> property.
    *
    * @return the value of the <code>productoClienteId</code> property.
    */
   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   /**
    * Sets the value of the <code>productoClienteId</code> property.
    *
    * @param productoClienteId a value for <code>productoClienteId</code>.
    */
   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   private java.lang.Long presupuestoId;

   /**
    * Returns the value of the <code>presupuestoId</code> property.
    *
    * @return the value of the <code>presupuestoId</code> property.
    */
   public java.lang.Long getPresupuestoId() {
      return presupuestoId;
   }

   /**
    * Sets the value of the <code>presupuestoId</code> property.
    *
    * @param presupuestoId a value for <code>presupuestoId</code>.
    */
   public void setPresupuestoId(java.lang.Long presupuestoId) {
      this.presupuestoId = presupuestoId;
   }
}
