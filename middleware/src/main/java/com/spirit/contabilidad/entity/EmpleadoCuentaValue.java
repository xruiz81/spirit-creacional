package com.spirit.contabilidad.entity;

import java.io.Serializable;

/**
 * The EmpleadoCuentaValue objects contains all fields that are available in the storage.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:31 $
 */
public class EmpleadoCuentaValue implements Serializable {


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

   private java.lang.Long empleadoId;

   /**
    * Returns the value of the <code>empleadoId</code> property.
    *
    * @return the value of the <code>empleadoId</code> property.
    */
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   /**
    * Sets the value of the <code>empleadoId</code> property.
    *
    * @param empleadoId a value for <code>empleadoId</code>.
    */
   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.Long cuentaId;

   /**
    * Returns the value of the <code>cuentaId</code> property.
    *
    * @return the value of the <code>cuentaId</code> property.
    */
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   /**
    * Sets the value of the <code>cuentaId</code> property.
    *
    * @param cuentaId a value for <code>cuentaId</code>.
    */
   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }
}
