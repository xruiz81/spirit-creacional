package com.spirit.poscola.entity;


/**
 * The PosColaIf interfaces exposes the persistence data.
 *
 * @author Rudie Ekkelenkamp
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:41 $
 */
public interface PosColaIf {

    /**
     * Return the primary key.
     *
     * @return java.lang.Integer he primary key.
     */
    java.lang.Integer getPrimaryKey();

    /**
     * Set the primary key.
     *
     * @param pk return java.lang.Integer with the primary key.
     */
    void setPrimaryKey(java.lang.Integer pk);


   /**
    * Returns the value of the <code>id</code> property.
    *
    * @return the value of the <code>id</code> property
    */
   java.lang.Integer getId();

   /**
    * Sets the value of the <code>id</code> property.
    *
    * @param id the value for the <code>id</code> property
    */
   void setId(java.lang.Integer id);

   /**
    * Returns the value of the <code>direccionIp</code> property.
    *
    * @return the value of the <code>direccionIp</code> property
    */
   java.lang.String getDireccionIp();

   /**
    * Sets the value of the <code>direccionIp</code> property.
    *
    * @param direccionIp the value for the <code>direccionIp</code> property
    */
   void setDireccionIp(java.lang.String direccionIp);

   /**
    * Returns the value of the <code>hostName</code> property.
    *
    * @return the value of the <code>hostName</code> property
    */
   java.lang.String getHostName();

   /**
    * Sets the value of the <code>hostName</code> property.
    *
    * @param hostName the value for the <code>hostName</code> property
    */
   void setHostName(java.lang.String hostName);

   /**
    * Returns the value of the <code>port</code> property.
    *
    * @return the value of the <code>port</code> property
    */
   java.lang.String getPort();

   /**
    * Sets the value of the <code>port</code> property.
    *
    * @param port the value for the <code>port</code> property
    */
   void setPort(java.lang.String port);

   /**
    * Returns the value of the <code>factory</code> property.
    *
    * @return the value of the <code>factory</code> property
    */
   java.lang.String getFactory();

   /**
    * Sets the value of the <code>factory</code> property.
    *
    * @param factory the value for the <code>factory</code> property
    */
   void setFactory(java.lang.String factory);

   /**
    * Returns the value of the <code>qname</code> property.
    *
    * @return the value of the <code>qname</code> property
    */
   java.lang.String getQname();

   /**
    * Sets the value of the <code>qname</code> property.
    *
    * @param qname the value for the <code>qname</code> property
    */
   void setQname(java.lang.String qname);

   /**
    * Returns the value of the <code>posName</code> property.
    *
    * @return the value of the <code>posName</code> property
    */
   java.lang.String getPosName();

   /**
    * Sets the value of the <code>posName</code> property.
    *
    * @param posName the value for the <code>posName</code> property
    */
   void setPosName(java.lang.String posName);

   /**
    * Returns the value of the <code>bodegaId</code> property.
    *
    * @return the value of the <code>bodegaId</code> property
    */
   java.lang.Long getBodegaId();

   /**
    * Sets the value of the <code>bodegaId</code> property.
    *
    * @param bodegaId the value for the <code>bodegaId</code> property
    */
   void setBodegaId(java.lang.Long bodegaId);

   /**
    * Returns the value of the <code>oficinaId</code> property.
    *
    * @return the value of the <code>oficinaId</code> property
    */
   java.lang.Long getOficinaId();

   /**
    * Sets the value of the <code>oficinaId</code> property.
    *
    * @param oficinaId the value for the <code>oficinaId</code> property
    */
   void setOficinaId(java.lang.Long oficinaId);

   /**
    * Returns the value of the <code>tipoServer</code> property.
    *
    * @return the value of the <code>tipoServer</code> property
    */
   java.lang.String getTipoServer();

   /**
    * Sets the value of the <code>tipoServer</code> property.
    *
    * @param tipoServer the value for the <code>tipoServer</code> property
    */
   void setTipoServer(java.lang.String tipoServer);

   /**
    * Returns the value of the <code>me</code> property.
    *
    * @return the value of the <code>me</code> property
    */
   java.lang.String getMe();

   /**
    * Sets the value of the <code>me</code> property.
    *
    * @param me the value for the <code>me</code> property
    */
   void setMe(java.lang.String me);


}
