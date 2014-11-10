package com.spirit.medios.entity;

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
 * The PresupuestoArchivo entity bean.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:36 $
 */

@Table(name = "PRESUPUESTO_ARCHIVO")
@Entity
public class PresupuestoArchivoEJB implements Serializable, PresupuestoArchivoIf {

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(PresupuestoArchivoEJB.class);


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long presupuestoId;
   private java.lang.Long tipoArchivoId;
   private java.lang.String url;

   /**
    * Default constructor.
    */
   public PresupuestoArchivoEJB() {
   }

   /**
    * Value object constructor.
    */
   public PresupuestoArchivoEJB(com.spirit.medios.entity.PresupuestoArchivoIf value) {
      setId(value.getId());
      setPresupuestoId(value.getPresupuestoId());
      setTipoArchivoId(value.getTipoArchivoId());
      setUrl(value.getUrl());
   }

   /**
    * Creates a new entity bean and returns the primary key.
    * If the primary key of the value object has been set, this key will be used.
    * If the primary key is set to null, the UniqueId generator will be used to generate a primary key.
    *
    * @param value a <code>PresupuestoArchivoIf</code>
    * @return the primary key for this PresupuestoArchivo
    */
   public java.lang.Long create(com.spirit.medios.entity.PresupuestoArchivoIf value) {
      setId(value.getId());
      setPresupuestoId(value.getPresupuestoId());
      setTipoArchivoId(value.getTipoArchivoId());
      setUrl(value.getUrl());
      return value.getPrimaryKey();
   }

    /**
     * Return the primary key.
     *
     * @return java.lang.Long with the primary key.
     */
   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

    /**
     * Set the primary key.
     *
     * @param primaryKey the primary key
     */
   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
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
   public java.lang.Long getId() {
      return id;
   }

   /**
    * Sets the value of the <code>id</code> property.
    *
    * @param id the value for the <code>id</code> property
    */
   public void setId(java.lang.Long id) {
      this.id = id;
   }

   /**
    * Returns the value of the <code>presupuestoId</code> property.
    *
    */
   @Column(name = "PRESUPUESTO_ID")
   public java.lang.Long getPresupuestoId() {
      return presupuestoId;
   }

   /**
    * Sets the value of the <code>presupuestoId</code> property.
    *
    * @param presupuestoId the value for the <code>presupuestoId</code> property
    */
   public void setPresupuestoId(java.lang.Long presupuestoId) {
      this.presupuestoId = presupuestoId;
   }

   /**
    * Returns the value of the <code>tipoArchivoId</code> property.
    *
    */
   @Column(name = "TIPO_ARCHIVO_ID")
   public java.lang.Long getTipoArchivoId() {
      return tipoArchivoId;
   }

   /**
    * Sets the value of the <code>tipoArchivoId</code> property.
    *
    * @param tipoArchivoId the value for the <code>tipoArchivoId</code> property
    */
   public void setTipoArchivoId(java.lang.Long tipoArchivoId) {
      this.tipoArchivoId = tipoArchivoId;
   }

   /**
    * Returns the value of the <code>url</code> property.
    *
    */
   @Column(name = "URL")
   public java.lang.String getUrl() {
      return url;
   }

   /**
    * Sets the value of the <code>url</code> property.
    *
    * @param url the value for the <code>url</code> property
    */
   public void setUrl(java.lang.String url) {
      this.url = url;
   }
}
