package com.spirit.medios.entity;

import java.io.Serializable;

/**
 * The PresupuestoArchivoValue objects contains all fields that are available in the storage.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:38 $
 */
public class PresupuestoArchivoValue implements Serializable {


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

   private java.lang.Long tipoArchivoId;

   /**
    * Returns the value of the <code>tipoArchivoId</code> property.
    *
    * @return the value of the <code>tipoArchivoId</code> property.
    */
   public java.lang.Long getTipoArchivoId() {
      return tipoArchivoId;
   }

   /**
    * Sets the value of the <code>tipoArchivoId</code> property.
    *
    * @param tipoArchivoId a value for <code>tipoArchivoId</code>.
    */
   public void setTipoArchivoId(java.lang.Long tipoArchivoId) {
      this.tipoArchivoId = tipoArchivoId;
   }

   private java.lang.String url;

   /**
    * Returns the value of the <code>url</code> property.
    *
    * @return the value of the <code>url</code> property.
    */
   public java.lang.String getUrl() {
      return url;
   }

   /**
    * Sets the value of the <code>url</code> property.
    *
    * @param url a value for <code>url</code>.
    */
   public void setUrl(java.lang.String url) {
      this.url = url;
   }
}
