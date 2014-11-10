package com.spirit.medios.entity;


/**
 * The TipoBriefIf interfaces exposes the persistence data.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */
public interface TipoBriefIf {

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
    * Returns the value of the <code>codigo</code> property.
    *
    * @return the value of the <code>codigo</code> property
    */
   java.lang.String getCodigo();

   /**
    * Sets the value of the <code>codigo</code> property.
    *
    * @param codigo the value for the <code>codigo</code> property
    */
   void setCodigo(java.lang.String codigo);

   /**
    * Returns the value of the <code>nombre</code> property.
    *
    * @return the value of the <code>nombre</code> property
    */
   java.lang.String getNombre();

   /**
    * Sets the value of the <code>nombre</code> property.
    *
    * @param nombre the value for the <code>nombre</code> property
    */
   void setNombre(java.lang.String nombre);

   /**
    * Returns the value of the <code>empresaId</code> property.
    *
    * @return the value of the <code>empresaId</code> property
    */
   java.lang.Long getEmpresaId();

   /**
    * Sets the value of the <code>empresaId</code> property.
    *
    * @param empresaId the value for the <code>empresaId</code> property
    */
   void setEmpresaId(java.lang.Long empresaId);

   /**
    * Returns the value of the <code>obligatorio</code> property.
    *
    * @return the value of the <code>obligatorio</code> property
    */
   java.lang.String getObligatorio();

   /**
    * Sets the value of the <code>obligatorio</code> property.
    *
    * @param obligatorio the value for the <code>obligatorio</code> property
    */
   void setObligatorio(java.lang.String obligatorio);


}
