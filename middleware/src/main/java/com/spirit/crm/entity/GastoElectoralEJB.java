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
 * The GastoElectoral entity bean.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:38 $
 */

@Table(name = "GASTO_ELECTORAL")
@Entity
public class GastoElectoralEJB implements Serializable, GastoElectoralIf {

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(GastoElectoralEJB.class);


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String campana;
   private java.sql.Date fecha;
   private java.lang.String tipo;
   private java.lang.String producto;
   private java.lang.String proveedor;
   private java.lang.String descripcion;
   private java.lang.String tamano;
   private java.math.BigDecimal cantidad;
   private java.math.BigDecimal costoUnitario;
   private java.math.BigDecimal inversionConFactura;
   private java.math.BigDecimal inversionSinFactura;

   /**
    * Default constructor.
    */
   public GastoElectoralEJB() {
   }

   /**
    * Value object constructor.
    */
   public GastoElectoralEJB(com.spirit.crm.entity.GastoElectoralIf value) {
      setId(value.getId());
      setCampana(value.getCampana());
      setFecha(value.getFecha());
      setTipo(value.getTipo());
      setProducto(value.getProducto());
      setProveedor(value.getProveedor());
      setDescripcion(value.getDescripcion());
      setTamano(value.getTamano());
      setCantidad(value.getCantidad());
      setCostoUnitario(value.getCostoUnitario());
      setInversionConFactura(value.getInversionConFactura());
      setInversionSinFactura(value.getInversionSinFactura());
   }

   /**
    * Creates a new entity bean and returns the primary key.
    * If the primary key of the value object has been set, this key will be used.
    * If the primary key is set to null, the UniqueId generator will be used to generate a primary key.
    *
    * @param value a <code>GastoElectoralIf</code>
    * @return the primary key for this GastoElectoral
    */
   public java.lang.Long create(com.spirit.crm.entity.GastoElectoralIf value) {
      setId(value.getId());
      setCampana(value.getCampana());
      setFecha(value.getFecha());
      setTipo(value.getTipo());
      setProducto(value.getProducto());
      setProveedor(value.getProveedor());
      setDescripcion(value.getDescripcion());
      setTamano(value.getTamano());
      setCantidad(value.getCantidad());
      setCostoUnitario(value.getCostoUnitario());
      setInversionConFactura(value.getInversionConFactura());
      setInversionSinFactura(value.getInversionSinFactura());
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
    * Returns the value of the <code>tipo</code> property.
    *
    */
   @Column(name = "TIPO")
   public java.lang.String getTipo() {
      return tipo;
   }

   /**
    * Sets the value of the <code>tipo</code> property.
    *
    * @param tipo the value for the <code>tipo</code> property
    */
   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   /**
    * Returns the value of the <code>producto</code> property.
    *
    */
   @Column(name = "PRODUCTO")
   public java.lang.String getProducto() {
      return producto;
   }

   /**
    * Sets the value of the <code>producto</code> property.
    *
    * @param producto the value for the <code>producto</code> property
    */
   public void setProducto(java.lang.String producto) {
      this.producto = producto;
   }

   /**
    * Returns the value of the <code>proveedor</code> property.
    *
    */
   @Column(name = "PROVEEDOR")
   public java.lang.String getProveedor() {
      return proveedor;
   }

   /**
    * Sets the value of the <code>proveedor</code> property.
    *
    * @param proveedor the value for the <code>proveedor</code> property
    */
   public void setProveedor(java.lang.String proveedor) {
      this.proveedor = proveedor;
   }

   /**
    * Returns the value of the <code>descripcion</code> property.
    *
    */
   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   /**
    * Sets the value of the <code>descripcion</code> property.
    *
    * @param descripcion the value for the <code>descripcion</code> property
    */
   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   /**
    * Returns the value of the <code>tamano</code> property.
    *
    */
   @Column(name = "TAMANO")
   public java.lang.String getTamano() {
      return tamano;
   }

   /**
    * Sets the value of the <code>tamano</code> property.
    *
    * @param tamano the value for the <code>tamano</code> property
    */
   public void setTamano(java.lang.String tamano) {
      this.tamano = tamano;
   }

   /**
    * Returns the value of the <code>cantidad</code> property.
    *
    */
   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   /**
    * Sets the value of the <code>cantidad</code> property.
    *
    * @param cantidad the value for the <code>cantidad</code> property
    */
   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   /**
    * Returns the value of the <code>costoUnitario</code> property.
    *
    */
   @Column(name = "COSTO_UNITARIO")
   public java.math.BigDecimal getCostoUnitario() {
      return costoUnitario;
   }

   /**
    * Sets the value of the <code>costoUnitario</code> property.
    *
    * @param costoUnitario the value for the <code>costoUnitario</code> property
    */
   public void setCostoUnitario(java.math.BigDecimal costoUnitario) {
      this.costoUnitario = costoUnitario;
   }

   /**
    * Returns the value of the <code>inversionConFactura</code> property.
    *
    */
   @Column(name = "INVERSION_CON_FACTURA")
   public java.math.BigDecimal getInversionConFactura() {
      return inversionConFactura;
   }

   /**
    * Sets the value of the <code>inversionConFactura</code> property.
    *
    * @param inversionConFactura the value for the <code>inversionConFactura</code> property
    */
   public void setInversionConFactura(java.math.BigDecimal inversionConFactura) {
      this.inversionConFactura = inversionConFactura;
   }

   /**
    * Returns the value of the <code>inversionSinFactura</code> property.
    *
    */
   @Column(name = "INVERSION_SIN_FACTURA")
   public java.math.BigDecimal getInversionSinFactura() {
      return inversionSinFactura;
   }

   /**
    * Sets the value of the <code>inversionSinFactura</code> property.
    *
    * @param inversionSinFactura the value for the <code>inversionSinFactura</code> property
    */
   public void setInversionSinFactura(java.math.BigDecimal inversionSinFactura) {
      this.inversionSinFactura = inversionSinFactura;
   }
}
