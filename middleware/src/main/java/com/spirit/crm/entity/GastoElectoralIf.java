package com.spirit.crm.entity;


/**
 * The GastoElectoralIf interfaces exposes the persistence data.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:39 $
 */
public interface GastoElectoralIf {

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
    * Returns the value of the <code>tipo</code> property.
    *
    * @return the value of the <code>tipo</code> property
    */
   java.lang.String getTipo();

   /**
    * Sets the value of the <code>tipo</code> property.
    *
    * @param tipo the value for the <code>tipo</code> property
    */
   void setTipo(java.lang.String tipo);

   /**
    * Returns the value of the <code>producto</code> property.
    *
    * @return the value of the <code>producto</code> property
    */
   java.lang.String getProducto();

   /**
    * Sets the value of the <code>producto</code> property.
    *
    * @param producto the value for the <code>producto</code> property
    */
   void setProducto(java.lang.String producto);

   /**
    * Returns the value of the <code>proveedor</code> property.
    *
    * @return the value of the <code>proveedor</code> property
    */
   java.lang.String getProveedor();

   /**
    * Sets the value of the <code>proveedor</code> property.
    *
    * @param proveedor the value for the <code>proveedor</code> property
    */
   void setProveedor(java.lang.String proveedor);

   /**
    * Returns the value of the <code>descripcion</code> property.
    *
    * @return the value of the <code>descripcion</code> property
    */
   java.lang.String getDescripcion();

   /**
    * Sets the value of the <code>descripcion</code> property.
    *
    * @param descripcion the value for the <code>descripcion</code> property
    */
   void setDescripcion(java.lang.String descripcion);

   /**
    * Returns the value of the <code>tamano</code> property.
    *
    * @return the value of the <code>tamano</code> property
    */
   java.lang.String getTamano();

   /**
    * Sets the value of the <code>tamano</code> property.
    *
    * @param tamano the value for the <code>tamano</code> property
    */
   void setTamano(java.lang.String tamano);

   /**
    * Returns the value of the <code>cantidad</code> property.
    *
    * @return the value of the <code>cantidad</code> property
    */
   java.math.BigDecimal getCantidad();

   /**
    * Sets the value of the <code>cantidad</code> property.
    *
    * @param cantidad the value for the <code>cantidad</code> property
    */
   void setCantidad(java.math.BigDecimal cantidad);

   /**
    * Returns the value of the <code>costoUnitario</code> property.
    *
    * @return the value of the <code>costoUnitario</code> property
    */
   java.math.BigDecimal getCostoUnitario();

   /**
    * Sets the value of the <code>costoUnitario</code> property.
    *
    * @param costoUnitario the value for the <code>costoUnitario</code> property
    */
   void setCostoUnitario(java.math.BigDecimal costoUnitario);

   /**
    * Returns the value of the <code>inversionConFactura</code> property.
    *
    * @return the value of the <code>inversionConFactura</code> property
    */
   java.math.BigDecimal getInversionConFactura();

   /**
    * Sets the value of the <code>inversionConFactura</code> property.
    *
    * @param inversionConFactura the value for the <code>inversionConFactura</code> property
    */
   void setInversionConFactura(java.math.BigDecimal inversionConFactura);

   /**
    * Returns the value of the <code>inversionSinFactura</code> property.
    *
    * @return the value of the <code>inversionSinFactura</code> property
    */
   java.math.BigDecimal getInversionSinFactura();

   /**
    * Sets the value of the <code>inversionSinFactura</code> property.
    *
    * @param inversionSinFactura the value for the <code>inversionSinFactura</code> property
    */
   void setInversionSinFactura(java.math.BigDecimal inversionSinFactura);


}
