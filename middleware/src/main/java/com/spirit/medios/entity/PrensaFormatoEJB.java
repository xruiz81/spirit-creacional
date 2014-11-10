package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PRENSA_FORMATO")
@Entity
public class PrensaFormatoEJB implements Serializable, PrensaFormatoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteId;
   private java.lang.String codigo;
   private java.lang.String formato;
   private java.math.BigDecimal anchoColumnas;
   private java.math.BigDecimal altoModulos;
   private java.math.BigDecimal anchoCm;
   private java.math.BigDecimal altoCm;

   public PrensaFormatoEJB() {
   }

   public PrensaFormatoEJB(com.spirit.medios.entity.PrensaFormatoIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setCodigo(value.getCodigo());
      setFormato(value.getFormato());
      setAnchoColumnas(value.getAnchoColumnas());
      setAltoModulos(value.getAltoModulos());
      setAnchoCm(value.getAnchoCm());
      setAltoCm(value.getAltoCm());
   }

   public java.lang.Long create(com.spirit.medios.entity.PrensaFormatoIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setCodigo(value.getCodigo());
      setFormato(value.getFormato());
      setAnchoColumnas(value.getAnchoColumnas());
      setAltoModulos(value.getAltoModulos());
      setAnchoCm(value.getAnchoCm());
      setAltoCm(value.getAltoCm());
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

   @Column(name = "CLIENTE_ID")
   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "FORMATO")
   public java.lang.String getFormato() {
      return formato;
   }

   public void setFormato(java.lang.String formato) {
      this.formato = formato;
   }

   @Column(name = "ANCHO_COLUMNAS")
   public java.math.BigDecimal getAnchoColumnas() {
      return anchoColumnas;
   }

   public void setAnchoColumnas(java.math.BigDecimal anchoColumnas) {
      this.anchoColumnas = anchoColumnas;
   }

   @Column(name = "ALTO_MODULOS")
   public java.math.BigDecimal getAltoModulos() {
      return altoModulos;
   }

   public void setAltoModulos(java.math.BigDecimal altoModulos) {
      this.altoModulos = altoModulos;
   }

   @Column(name = "ANCHO_CM")
   public java.math.BigDecimal getAnchoCm() {
      return anchoCm;
   }

   public void setAnchoCm(java.math.BigDecimal anchoCm) {
      this.anchoCm = anchoCm;
   }

   @Column(name = "ALTO_CM")
   public java.math.BigDecimal getAltoCm() {
      return altoCm;
   }

   public void setAltoCm(java.math.BigDecimal altoCm) {
      this.altoCm = altoCm;
   }
	public String toString() {
		return ToStringer.toString((PrensaFormatoIf)this);
	}
}
