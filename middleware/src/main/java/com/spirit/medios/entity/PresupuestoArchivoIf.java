package com.spirit.medios.entity;


/**
 * The PresupuestoArchivoIf interfaces exposes the persistence data.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */
public interface PresupuestoArchivoIf {

    /**
     * Return the primary key.
     *
     * @return java.lang.Long he primary key.
     */
    java.lang.Long getPrimaryKey();

    /**
     * Set the primary key.
     *
     * @param pk return java.lang.Long with the primary key.
     */
    void setPrimaryKey(java.lang.Long pk);


   /**
    * Returns the value of the <code>id</code> property.
    *
    * @return the value of the <code>id</code> property
    */
   java.lang.Long getId();

   /**
    * Sets the value of the <code>id</code> property.
    *
    * @param id the value for the <code>id</code> property
    */
   void setId(java.lang.Long id);

   /**
    * Returns the value of the <code>presupuestoId</code> property.
    *
    * @return the value of the <code>presupuestoId</code> property
    */
   java.lang.Long getPresupuestoId();

   /**
    * Sets the value of the <code>presupuestoId</code> property.
    *
    * @param presupuestoId the value for the <code>presupuestoId</code> property
    */
   void setPresupuestoId(java.lang.Long presupuestoId);

   /**
    * Returns the value of the <code>tipoArchivoId</code> property.
    *
    * @return the value of the <code>tipoArchivoId</code> property
    */
   java.lang.Long getTipoArchivoId();

   /**
    * Sets the value of the <code>tipoArchivoId</code> property.
    *
    * @param tipoArchivoId the value for the <code>tipoArchivoId</code> property
    */
   void setTipoArchivoId(java.lang.Long tipoArchivoId);

   /**
    * Returns the value of the <code>url</code> property.
    *
    * @return the value of the <code>url</code> property
    */
   java.lang.String getUrl();

   /**
    * Sets the value of the <code>url</code> property.
    *
    * @param url the value for the <code>url</code> property
    */
   void setUrl(java.lang.String url);


}
