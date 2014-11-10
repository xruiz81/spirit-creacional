package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "MONEDA")
@Entity
public class MonedaEJB implements Serializable, MonedaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.String simbolo;
   private java.lang.String plural;
   private java.lang.String sufijoCantidadLetras;
   private java.lang.String predeterminada;

   public MonedaEJB() {
   }

   public MonedaEJB(com.spirit.general.entity.MonedaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setSimbolo(value.getSimbolo());
      setPlural(value.getPlural());
      setSufijoCantidadLetras(value.getSufijoCantidadLetras());
      setPredeterminada(value.getPredeterminada());
   }

   public java.lang.Long create(com.spirit.general.entity.MonedaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setSimbolo(value.getSimbolo());
      setPlural(value.getPlural());
      setSufijoCantidadLetras(value.getSufijoCantidadLetras());
      setPredeterminada(value.getPredeterminada());
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "SIMBOLO")
   public java.lang.String getSimbolo() {
      return simbolo;
   }

   public void setSimbolo(java.lang.String simbolo) {
      this.simbolo = simbolo;
   }

   @Column(name = "PLURAL")
   public java.lang.String getPlural() {
      return plural;
   }

   public void setPlural(java.lang.String plural) {
      this.plural = plural;
   }

   @Column(name = "SUFIJO_CANTIDAD_LETRAS")
   public java.lang.String getSufijoCantidadLetras() {
      return sufijoCantidadLetras;
   }

   public void setSufijoCantidadLetras(java.lang.String sufijoCantidadLetras) {
      this.sufijoCantidadLetras = sufijoCantidadLetras;
   }

   @Column(name = "PREDETERMINADA")
   public java.lang.String getPredeterminada() {
      return predeterminada;
   }

   public void setPredeterminada(java.lang.String predeterminada) {
      this.predeterminada = predeterminada;
   }
	public String toString() {
		return ToStringer.toString((MonedaIf)this);
	}
}
