package com.spirit.poscola.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.util.HibernateSequenceAllocationSize;


/**
 * The PosCola entity bean.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:41 $
 */

@Table(name = "POS_COLA")
@Entity
public class PosColaEJB implements Serializable, PosColaIf {

   /**
    * The logger object.
    */
   //private static Logger log = LogService.getLogger(PosColaEJB.class);


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Integer id;
   private java.lang.String direccionIp;
   private java.lang.String hostName;
   private java.lang.String port;
   private java.lang.String factory;
   private java.lang.String qname;
   private java.lang.String posName;
   private java.lang.Long bodegaId;
   private java.lang.Long oficinaId;
   private java.lang.String tipoServer;
   private java.lang.String me;

   /**
    * Default constructor.
    */
   public PosColaEJB() {
   }

   /**
    * Value object constructor.
    */
   public PosColaEJB(com.spirit.poscola.entity.PosColaIf value) {
      setId(value.getId());
      setDireccionIp(value.getDireccionIp());
      setHostName(value.getHostName());
      setPort(value.getPort());
      setFactory(value.getFactory());
      setQname(value.getQname());
      setPosName(value.getPosName());
      setBodegaId(value.getBodegaId());
      setOficinaId(value.getOficinaId());
      setTipoServer(value.getTipoServer());
      setMe(value.getMe());
   }

   /**
    * Creates a new entity bean and returns the primary key.
    * If the primary key of the value object has been set, this key will be used.
    * If the primary key is set to null, the UniqueId generator will be used to generate a primary key.
    *
    * @param value a <code>PosColaIf</code>
    * @return the primary key for this PosCola
    */
   public java.lang.Integer create(com.spirit.poscola.entity.PosColaIf value) {
      setId(value.getId());
      setDireccionIp(value.getDireccionIp());
      setHostName(value.getHostName());
      setPort(value.getPort());
      setFactory(value.getFactory());
      setQname(value.getQname());
      setPosName(value.getPosName());
      setBodegaId(value.getBodegaId());
      setOficinaId(value.getOficinaId());
      setTipoServer(value.getTipoServer());
      setMe(value.getMe());
      return value.getPrimaryKey();
   }

    /**
     * Return the primary key.
     *
     * @return java.lang.Integer with the primary key.
     */
   @javax.persistence.Transient public java.lang.Integer getPrimaryKey() {
        return getId();
    }

    /**
     * Set the primary key.
     *
     * @param primaryKey the primary key
     */
   @javax.persistence.Transient public void setPrimaryKey(java.lang.Integer primaryKey) {
       setId(primaryKey);
    }

   /**
    * Returns the value of the <code>id</code> property.
    *
    */
   @Column(name = "ID")
   @TableGenerator(
		   name="SEQ_GEN",
		   allocationSize=HibernateSequenceAllocationSize.allocationSize
   )
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Integer getId() {
      return id;
   }

   /**
    * Sets the value of the <code>id</code> property.
    *
    * @param id the value for the <code>id</code> property
    */
   public void setId(java.lang.Integer id) {
      this.id = id;
   }

   /**
    * Returns the value of the <code>direccionIp</code> property.
    *
    */
   @Column(name = "DIRECCION_IP")
   public java.lang.String getDireccionIp() {
      return direccionIp;
   }

   /**
    * Sets the value of the <code>direccionIp</code> property.
    *
    * @param direccionIp the value for the <code>direccionIp</code> property
    */
   public void setDireccionIp(java.lang.String direccionIp) {
      this.direccionIp = direccionIp;
   }

   /**
    * Returns the value of the <code>hostName</code> property.
    *
    */
   @Column(name = "HOST_NAME")
   public java.lang.String getHostName() {
      return hostName;
   }

   /**
    * Sets the value of the <code>hostName</code> property.
    *
    * @param hostName the value for the <code>hostName</code> property
    */
   public void setHostName(java.lang.String hostName) {
      this.hostName = hostName;
   }

   /**
    * Returns the value of the <code>port</code> property.
    *
    */
   @Column(name = "PORT")
   public java.lang.String getPort() {
      return port;
   }

   /**
    * Sets the value of the <code>port</code> property.
    *
    * @param port the value for the <code>port</code> property
    */
   public void setPort(java.lang.String port) {
      this.port = port;
   }

   /**
    * Returns the value of the <code>factory</code> property.
    *
    */
   @Column(name = "FACTORY")
   public java.lang.String getFactory() {
      return factory;
   }

   /**
    * Sets the value of the <code>factory</code> property.
    *
    * @param factory the value for the <code>factory</code> property
    */
   public void setFactory(java.lang.String factory) {
      this.factory = factory;
   }

   /**
    * Returns the value of the <code>qname</code> property.
    *
    */
   @Column(name = "QNAME")
   public java.lang.String getQname() {
      return qname;
   }

   /**
    * Sets the value of the <code>qname</code> property.
    *
    * @param qname the value for the <code>qname</code> property
    */
   public void setQname(java.lang.String qname) {
      this.qname = qname;
   }

   /**
    * Returns the value of the <code>posName</code> property.
    *
    */
   @Column(name = "POS_NAME")
   public java.lang.String getPosName() {
      return posName;
   }

   /**
    * Sets the value of the <code>posName</code> property.
    *
    * @param posName the value for the <code>posName</code> property
    */
   public void setPosName(java.lang.String posName) {
      this.posName = posName;
   }

   /**
    * Returns the value of the <code>bodegaId</code> property.
    *
    */
   @Column(name = "BODEGA_ID")
   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   /**
    * Sets the value of the <code>bodegaId</code> property.
    *
    * @param bodegaId the value for the <code>bodegaId</code> property
    */
   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   /**
    * Returns the value of the <code>oficinaId</code> property.
    *
    */
   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   /**
    * Sets the value of the <code>oficinaId</code> property.
    *
    * @param oficinaId the value for the <code>oficinaId</code> property
    */
   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   /**
    * Returns the value of the <code>tipoServer</code> property.
    *
    */
   @Column(name = "TIPO_SERVER")
   public java.lang.String getTipoServer() {
      return tipoServer;
   }

   /**
    * Sets the value of the <code>tipoServer</code> property.
    *
    * @param tipoServer the value for the <code>tipoServer</code> property
    */
   public void setTipoServer(java.lang.String tipoServer) {
      this.tipoServer = tipoServer;
   }

   /**
    * Returns the value of the <code>me</code> property.
    *
    */
   @Column(name = "ME")
   public java.lang.String getMe() {
      return me;
   }

   /**
    * Sets the value of the <code>me</code> property.
    *
    * @param me the value for the <code>me</code> property
    */
   public void setMe(java.lang.String me) {
      this.me = me;
   }
}
