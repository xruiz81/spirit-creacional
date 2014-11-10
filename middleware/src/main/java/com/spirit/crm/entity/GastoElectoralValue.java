package com.spirit.crm.entity;

import java.io.Serializable;

/**
 * The GastoElectoralValue objects contains all fields that are available in the storage.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:39 $
 */
public class GastoElectoralValue implements Serializable {


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

   private java.lang.String producto;

   /**
    * Returns the value of the <code>producto</code> property.
    *
    * @return the value of the <code>producto</code> property.
    */
   public java.lang.String getProducto() {
      return producto;
   }

   /**
    * Sets the value of the <code>producto</code> property.
    *
    * @param producto a value for <code>producto</code>.
    */
   public void setProducto(java.lang.String producto) {
      this.producto = producto;
   }

   private java.lang.String proveedor;

   /**
    * Returns the value of the <code>proveedor</code> property.
    *
    * @return the value of the <code>proveedor</code> property.
    */
   public java.lang.String getProveedor() {
      return proveedor;
   }

   /**
    * Sets the value of the <code>proveedor</code> property.
    *
    * @param proveedor a value for <code>proveedor</code>.
    */
   public void setProveedor(java.lang.String proveedor) {
      this.proveedor = proveedor;
   }

   private java.lang.String descripcion;

   /**
    * Returns the value of the <code>descripcion</code> property.
    *
    * @return the value of the <code>descripcion</code> property.
    */
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   /**
    * Sets the value of the <code>descripcion</code> property.
    *
    * @param descripcion a value for <code>descripcion</code>.
    */
   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.String tamano;

   /**
    * Returns the value of the <code>tamano</code> property.
    *
    * @return the value of the <code>tamano</code> property.
    */
   public java.lang.String getTamano() {
      return tamano;
   }

   /**
    * Sets the value of the <code>tamano</code> property.
    *
    * @param tamano a value for <code>tamano</code>.
    */
   public void setTamano(java.lang.String tamano) {
      this.tamano = tamano;
   }

   private java.math.BigDecimal cantidad;

   /**
    * Returns the value of the <code>cantidad</code> property.
    *
    * @return the value of the <code>cantidad</code> property.
    */
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   /**
    * Sets the value of the <code>cantidad</code> property.
    *
    * @param cantidad a value for <code>cantidad</code>.
    */
   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   private java.math.BigDecimal costoUnitario;

   /**
    * Returns the value of the <code>costoUnitario</code> property.
    *
    * @return the value of the <code>costoUnitario</code> property.
    */
   public java.math.BigDecimal getCostoUnitario() {
      return costoUnitario;
   }

   /**
    * Sets the value of the <code>costoUnitario</code> property.
    *
    * @param costoUnitario a value for <code>costoUnitario</code>.
    */
   public void setCostoUnitario(java.math.BigDecimal costoUnitario) {
      this.costoUnitario = costoUnitario;
   }

   private java.math.BigDecimal inversionConFactura;

   /**
    * Returns the value of the <code>inversionConFactura</code> property.
    *
    * @return the value of the <code>inversionConFactura</code> property.
    */
   public java.math.BigDecimal getInversionConFactura() {
      return inversionConFactura;
   }

   /**
    * Sets the value of the <code>inversionConFactura</code> property.
    *
    * @param inversionConFactura a value for <code>inversionConFactura</code>.
    */
   public void setInversionConFactura(java.math.BigDecimal inversionConFactura) {
      this.inversionConFactura = inversionConFactura;
   }

   private java.math.BigDecimal inversionSinFactura;

   /**
    * Returns the value of the <code>inversionSinFactura</code> property.
    *
    * @return the value of the <code>inversionSinFactura</code> property.
    */
   public java.math.BigDecimal getInversionSinFactura() {
      return inversionSinFactura;
   }

   /**
    * Sets the value of the <code>inversionSinFactura</code> property.
    *
    * @param inversionSinFactura a value for <code>inversionSinFactura</code>.
    */
   public void setInversionSinFactura(java.math.BigDecimal inversionSinFactura) {
      this.inversionSinFactura = inversionSinFactura;
   }
}
