package com.spirit.poscola.entity;

import java.io.Serializable;

/**
 * The PosColaValue objects contains all fields that are available in the storage.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:42 $
 */
public class PosColaValue implements Serializable {


   private java.lang.Integer id;

   /**
    * Returns the value of the <code>id</code> property.
    *
    * @return the value of the <code>id</code> property.
    */
   public java.lang.Integer getId() {
      return id;
   }

   /**
    * Sets the value of the <code>id</code> property.
    *
    * @param id a value for <code>id</code>.
    */
   public void setId(java.lang.Integer id) {
      this.id = id;
   }

   private java.lang.String direccionIp;

   /**
    * Returns the value of the <code>direccionIp</code> property.
    *
    * @return the value of the <code>direccionIp</code> property.
    */
   public java.lang.String getDireccionIp() {
      return direccionIp;
   }

   /**
    * Sets the value of the <code>direccionIp</code> property.
    *
    * @param direccionIp a value for <code>direccionIp</code>.
    */
   public void setDireccionIp(java.lang.String direccionIp) {
      this.direccionIp = direccionIp;
   }

   private java.lang.String hostName;

   /**
    * Returns the value of the <code>hostName</code> property.
    *
    * @return the value of the <code>hostName</code> property.
    */
   public java.lang.String getHostName() {
      return hostName;
   }

   /**
    * Sets the value of the <code>hostName</code> property.
    *
    * @param hostName a value for <code>hostName</code>.
    */
   public void setHostName(java.lang.String hostName) {
      this.hostName = hostName;
   }

   private java.lang.String port;

   /**
    * Returns the value of the <code>port</code> property.
    *
    * @return the value of the <code>port</code> property.
    */
   public java.lang.String getPort() {
      return port;
   }

   /**
    * Sets the value of the <code>port</code> property.
    *
    * @param port a value for <code>port</code>.
    */
   public void setPort(java.lang.String port) {
      this.port = port;
   }

   private java.lang.String factory;

   /**
    * Returns the value of the <code>factory</code> property.
    *
    * @return the value of the <code>factory</code> property.
    */
   public java.lang.String getFactory() {
      return factory;
   }

   /**
    * Sets the value of the <code>factory</code> property.
    *
    * @param factory a value for <code>factory</code>.
    */
   public void setFactory(java.lang.String factory) {
      this.factory = factory;
   }

   private java.lang.String qname;

   /**
    * Returns the value of the <code>qname</code> property.
    *
    * @return the value of the <code>qname</code> property.
    */
   public java.lang.String getQname() {
      return qname;
   }

   /**
    * Sets the value of the <code>qname</code> property.
    *
    * @param qname a value for <code>qname</code>.
    */
   public void setQname(java.lang.String qname) {
      this.qname = qname;
   }

   private java.lang.String posName;

   /**
    * Returns the value of the <code>posName</code> property.
    *
    * @return the value of the <code>posName</code> property.
    */
   public java.lang.String getPosName() {
      return posName;
   }

   /**
    * Sets the value of the <code>posName</code> property.
    *
    * @param posName a value for <code>posName</code>.
    */
   public void setPosName(java.lang.String posName) {
      this.posName = posName;
   }

   private java.lang.Long bodegaId;

   /**
    * Returns the value of the <code>bodegaId</code> property.
    *
    * @return the value of the <code>bodegaId</code> property.
    */
   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   /**
    * Sets the value of the <code>bodegaId</code> property.
    *
    * @param bodegaId a value for <code>bodegaId</code>.
    */
   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   private java.lang.Long oficinaId;

   /**
    * Returns the value of the <code>oficinaId</code> property.
    *
    * @return the value of the <code>oficinaId</code> property.
    */
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   /**
    * Sets the value of the <code>oficinaId</code> property.
    *
    * @param oficinaId a value for <code>oficinaId</code>.
    */
   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.String tipoServer;

   /**
    * Returns the value of the <code>tipoServer</code> property.
    *
    * @return the value of the <code>tipoServer</code> property.
    */
   public java.lang.String getTipoServer() {
      return tipoServer;
   }

   /**
    * Sets the value of the <code>tipoServer</code> property.
    *
    * @param tipoServer a value for <code>tipoServer</code>.
    */
   public void setTipoServer(java.lang.String tipoServer) {
      this.tipoServer = tipoServer;
   }

   private java.lang.String me;

   /**
    * Returns the value of the <code>me</code> property.
    *
    * @return the value of the <code>me</code> property.
    */
   public java.lang.String getMe() {
      return me;
   }

   /**
    * Sets the value of the <code>me</code> property.
    *
    * @param me a value for <code>me</code>.
    */
   public void setMe(java.lang.String me) {
      this.me = me;
   }
}
