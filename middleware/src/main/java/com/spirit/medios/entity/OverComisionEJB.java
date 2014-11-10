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

@Table(name = "OVER_COMISION")
@Entity
public class OverComisionEJB implements Serializable, OverComisionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long proveedorOficinaId;
   private java.sql.Timestamp anio;
   private java.math.BigDecimal inversionDe;
   private java.math.BigDecimal inversionA;
   private java.math.BigDecimal porcentajeOver;
   private java.lang.String objetivo;
   private java.lang.Long proveedorId;

   public OverComisionEJB() {
   }

   public OverComisionEJB(com.spirit.medios.entity.OverComisionIf value) {
      setId(value.getId());
      setProveedorOficinaId(value.getProveedorOficinaId());
      setAnio(value.getAnio());
      setInversionDe(value.getInversionDe());
      setInversionA(value.getInversionA());
      setPorcentajeOver(value.getPorcentajeOver());
      setObjetivo(value.getObjetivo());
      setProveedorId(value.getProveedorId());
   }

   public java.lang.Long create(com.spirit.medios.entity.OverComisionIf value) {
      setId(value.getId());
      setProveedorOficinaId(value.getProveedorOficinaId());
      setAnio(value.getAnio());
      setInversionDe(value.getInversionDe());
      setInversionA(value.getInversionA());
      setPorcentajeOver(value.getPorcentajeOver());
      setObjetivo(value.getObjetivo());
      setProveedorId(value.getProveedorId());
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

   @Column(name = "PROVEEDOR_OFICINA_ID")
   public java.lang.Long getProveedorOficinaId() {
      return proveedorOficinaId;
   }

   public void setProveedorOficinaId(java.lang.Long proveedorOficinaId) {
      this.proveedorOficinaId = proveedorOficinaId;
   }

   @Column(name = "ANIO")
   public java.sql.Timestamp getAnio() {
      return anio;
   }

   public void setAnio(java.sql.Timestamp anio) {
      this.anio = anio;
   }

   @Column(name = "INVERSION_DE")
   public java.math.BigDecimal getInversionDe() {
      return inversionDe;
   }

   public void setInversionDe(java.math.BigDecimal inversionDe) {
      this.inversionDe = inversionDe;
   }

   @Column(name = "INVERSION_A")
   public java.math.BigDecimal getInversionA() {
      return inversionA;
   }

   public void setInversionA(java.math.BigDecimal inversionA) {
      this.inversionA = inversionA;
   }

   @Column(name = "PORCENTAJE_OVER")
   public java.math.BigDecimal getPorcentajeOver() {
      return porcentajeOver;
   }

   public void setPorcentajeOver(java.math.BigDecimal porcentajeOver) {
      this.porcentajeOver = porcentajeOver;
   }

   @Column(name = "OBJETIVO")
   public java.lang.String getObjetivo() {
      return objetivo;
   }

   public void setObjetivo(java.lang.String objetivo) {
      this.objetivo = objetivo;
   }

   @Column(name = "PROVEEDOR_ID")
   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }
	public String toString() {
		return ToStringer.toString((OverComisionIf)this);
	}
}
