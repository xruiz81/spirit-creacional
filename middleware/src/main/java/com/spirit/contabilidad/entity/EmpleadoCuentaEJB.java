package com.spirit.contabilidad.entity;

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
 * The EmpleadoCuenta entity bean.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:31 $
 */

@Table(name = "EMPLEADO_CUENTA")
@Entity
public class EmpleadoCuentaEJB implements Serializable, EmpleadoCuentaIf {

   /**
	 * 
	 */
	private static final long serialVersionUID = 3761405330759759927L;


/**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(EmpleadoCuentaEJB.class);


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empleadoId;
   private java.lang.Long cuentaId;

   /**
    * Default constructor.
    */
   public EmpleadoCuentaEJB() {
   }

   /**
    * Value object constructor.
    */
   public EmpleadoCuentaEJB(com.spirit.contabilidad.entity.EmpleadoCuentaIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setCuentaId(value.getCuentaId());
   }

   /**
    * Creates a new entity bean and returns the primary key.
    * If the primary key of the value object has been set, this key will be used.
    * If the primary key is set to null, the UniqueId generator will be used to generate a primary key.
    *
    * @param value a <code>EmpleadoCuentaIf</code>
    * @return the primary key for this EmpleadoCuenta
    */
   public java.lang.Long create(com.spirit.contabilidad.entity.EmpleadoCuentaIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setCuentaId(value.getCuentaId());
      setId(value.getId());
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
    * Returns the value of the <code>empleadoId</code> property.
    *
    */
   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   /**
    * Sets the value of the <code>empleadoId</code> property.
    *
    * @param empleadoId the value for the <code>empleadoId</code> property
    */
   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   /**
    * Returns the value of the <code>cuentaId</code> property.
    *
    */
   @Column(name = "CUENTA_ID")
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   /**
    * Sets the value of the <code>cuentaId</code> property.
    *
    * @param cuentaId the value for the <code>cuentaId</code> property
    */
   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }
}
