package com.spirit.medios.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.util.HibernateSequenceAllocationSize;


/**
 * The TipoBrief entity bean.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:37 $
 */

@Table(name = "TIPO_BRIEF")
@Entity
public class TipoBriefEJB implements Serializable, TipoBriefIf {

   private static final long serialVersionUID = -8924361933352697510L;


   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(TipoBriefEJB.class);


   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.String obligatorio;

   /**
    * Default constructor.
    */
   public TipoBriefEJB() {
   }

   /**
    * Value object constructor.
    */
   public TipoBriefEJB(com.spirit.medios.entity.TipoBriefIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setObligatorio(value.getObligatorio());
   }

   /**
    * Creates a new entity bean and returns the primary key.
    * If the primary key of the value object has been set, this key will be used.
    * If the primary key is set to null, the UniqueId generator will be used to generate a primary key.
    *
    * @param value a <code>TipoBriefIf</code>
    * @return the primary key for this TipoBrief
    */
   public java.lang.Long create(com.spirit.medios.entity.TipoBriefIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setObligatorio(value.getObligatorio());
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
    * Returns the value of the <code>codigo</code> property.
    *
    */
   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   /**
    * Sets the value of the <code>codigo</code> property.
    *
    * @param codigo the value for the <code>codigo</code> property
    */
   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   /**
    * Returns the value of the <code>nombre</code> property.
    *
    */
   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   /**
    * Sets the value of the <code>nombre</code> property.
    *
    * @param nombre the value for the <code>nombre</code> property
    */
   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   /**
    * Returns the value of the <code>empresaId</code> property.
    *
    */
   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   /**
    * Sets the value of the <code>empresaId</code> property.
    *
    * @param empresaId the value for the <code>empresaId</code> property
    */
   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   /**
    * Returns the value of the <code>obligatorio</code> property.
    *
    */
   @Column(name = "OBLIGATORIO")
   public java.lang.String getObligatorio() {
      return obligatorio;
   }

   /**
    * Sets the value of the <code>obligatorio</code> property.
    *
    * @param obligatorio the value for the <code>obligatorio</code> property
    */
   public void setObligatorio(java.lang.String obligatorio) {
      this.obligatorio = obligatorio;
   }
   
   public String toString(){
		return (this.getCodigo() + " - " + this.getNombre());
	}
}
