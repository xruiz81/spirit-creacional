package com.spirit.crm.entity;

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
 * The GastoElectoralAbono entity bean.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:38 $
 */

@Table(name = "GASTO_ELECTORAL_ABONO")
@Entity
public class GastoElectoralAbonoEJB implements Serializable, GastoElectoralAbonoIf {

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(GastoElectoralAbonoEJB.class);


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.sql.Date fecha;
   private java.lang.String entregadoPor;
   private java.math.BigDecimal valor;
   private java.lang.String campana;

   /**
    * Default constructor.
    */
   public GastoElectoralAbonoEJB() {
   }

   /**
    * Value object constructor.
    */
   public GastoElectoralAbonoEJB(com.spirit.crm.entity.GastoElectoralAbonoIf value) {
      setId(value.getId());
      setFecha(value.getFecha());
      setEntregadoPor(value.getEntregadoPor());
      setValor(value.getValor());
      setCampana(value.getCampana());
   }

   /**
    * Creates a new entity bean and returns the primary key.
    * If the primary key of the value object has been set, this key will be used.
    * If the primary key is set to null, the UniqueId generator will be used to generate a primary key.
    *
    * @param value a <code>GastoElectoralAbonoIf</code>
    * @return the primary key for this GastoElectoralAbono
    */
   public java.lang.Long create(com.spirit.crm.entity.GastoElectoralAbonoIf value) {
      setId(value.getId());
      setFecha(value.getFecha());
      setEntregadoPor(value.getEntregadoPor());
      setValor(value.getValor());
      setCampana(value.getCampana());
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
    * Returns the value of the <code>fecha</code> property.
    *
    */
   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   /**
    * Sets the value of the <code>fecha</code> property.
    *
    * @param fecha the value for the <code>fecha</code> property
    */
   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   /**
    * Returns the value of the <code>entregadoPor</code> property.
    *
    */
   @Column(name = "ENTREGADO_POR")
   public java.lang.String getEntregadoPor() {
      return entregadoPor;
   }

   /**
    * Sets the value of the <code>entregadoPor</code> property.
    *
    * @param entregadoPor the value for the <code>entregadoPor</code> property
    */
   public void setEntregadoPor(java.lang.String entregadoPor) {
      this.entregadoPor = entregadoPor;
   }

   /**
    * Returns the value of the <code>valor</code> property.
    *
    */
   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   /**
    * Sets the value of the <code>valor</code> property.
    *
    * @param valor the value for the <code>valor</code> property
    */
   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   /**
    * Returns the value of the <code>campana</code> property.
    *
    */
   @Column(name = "CAMPANA")
   public java.lang.String getCampana() {
      return campana;
   }

   /**
    * Sets the value of the <code>campana</code> property.
    *
    * @param campana the value for the <code>campana</code> property
    */
   public void setCampana(java.lang.String campana) {
      this.campana = campana;
   }
}
