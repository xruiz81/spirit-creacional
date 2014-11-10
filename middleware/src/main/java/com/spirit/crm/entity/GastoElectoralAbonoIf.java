package com.spirit.crm.entity;


/**
 * The GastoElectoralAbonoIf interfaces exposes the persistence data.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:39 $
 */
public interface GastoElectoralAbonoIf {

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
    * Returns the value of the <code>fecha</code> property.
    *
    * @return the value of the <code>fecha</code> property
    */
   java.sql.Date getFecha();

   /**
    * Sets the value of the <code>fecha</code> property.
    *
    * @param fecha the value for the <code>fecha</code> property
    */
   void setFecha(java.sql.Date fecha);

   /**
    * Returns the value of the <code>entregadoPor</code> property.
    *
    * @return the value of the <code>entregadoPor</code> property
    */
   java.lang.String getEntregadoPor();

   /**
    * Sets the value of the <code>entregadoPor</code> property.
    *
    * @param entregadoPor the value for the <code>entregadoPor</code> property
    */
   void setEntregadoPor(java.lang.String entregadoPor);

   /**
    * Returns the value of the <code>valor</code> property.
    *
    * @return the value of the <code>valor</code> property
    */
   java.math.BigDecimal getValor();

   /**
    * Sets the value of the <code>valor</code> property.
    *
    * @param valor the value for the <code>valor</code> property
    */
   void setValor(java.math.BigDecimal valor);

   /**
    * Returns the value of the <code>campana</code> property.
    *
    * @return the value of the <code>campana</code> property
    */
   java.lang.String getCampana();

   /**
    * Sets the value of the <code>campana</code> property.
    *
    * @param campana the value for the <code>campana</code> property
    */
   void setCampana(java.lang.String campana);


}
