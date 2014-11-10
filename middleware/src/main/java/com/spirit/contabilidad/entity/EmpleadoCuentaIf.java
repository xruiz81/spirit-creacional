package com.spirit.contabilidad.entity;


/**
 * The EmpleadoCuentaIf interfaces exposes the persistence data.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:31 $
 */
public interface EmpleadoCuentaIf {

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
    * Returns the value of the <code>empleadoId</code> property.
    *
    * @return the value of the <code>empleadoId</code> property
    */
   java.lang.Long getEmpleadoId();

   /**
    * Sets the value of the <code>empleadoId</code> property.
    *
    * @param empleadoId the value for the <code>empleadoId</code> property
    */
   void setEmpleadoId(java.lang.Long empleadoId);

   /**
    * Returns the value of the <code>cuentaId</code> property.
    *
    * @return the value of the <code>cuentaId</code> property
    */
   java.lang.Long getCuentaId();

   /**
    * Sets the value of the <code>cuentaId</code> property.
    *
    * @param cuentaId the value for the <code>cuentaId</code> property
    */
   void setCuentaId(java.lang.Long cuentaId);


}
