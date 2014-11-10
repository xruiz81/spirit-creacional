package com.spirit.medios.entity;

import java.io.Serializable;

/**
 * The TipoBriefValue objects contains all fields that are available in the storage.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */
public class TipoBriefValue implements Serializable {


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

   private java.lang.String codigo;

   /**
    * Returns the value of the <code>codigo</code> property.
    *
    * @return the value of the <code>codigo</code> property.
    */
   public java.lang.String getCodigo() {
      return codigo;
   }

   /**
    * Sets the value of the <code>codigo</code> property.
    *
    * @param codigo a value for <code>codigo</code>.
    */
   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombre;

   /**
    * Returns the value of the <code>nombre</code> property.
    *
    * @return the value of the <code>nombre</code> property.
    */
   public java.lang.String getNombre() {
      return nombre;
   }

   /**
    * Sets the value of the <code>nombre</code> property.
    *
    * @param nombre a value for <code>nombre</code>.
    */
   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.Long empresaId;

   /**
    * Returns the value of the <code>empresaId</code> property.
    *
    * @return the value of the <code>empresaId</code> property.
    */
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   /**
    * Sets the value of the <code>empresaId</code> property.
    *
    * @param empresaId a value for <code>empresaId</code>.
    */
   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.String obligatorio;

   /**
    * Returns the value of the <code>obligatorio</code> property.
    *
    * @return the value of the <code>obligatorio</code> property.
    */
   public java.lang.String getObligatorio() {
      return obligatorio;
   }

   /**
    * Sets the value of the <code>obligatorio</code> property.
    *
    * @param obligatorio a value for <code>obligatorio</code>.
    */
   public void setObligatorio(java.lang.String obligatorio) {
      this.obligatorio = obligatorio;
   }
}
