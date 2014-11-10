package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ASIENTO_DETALLE_TMP")
@Entity
public class AsientoDetalleTmpEJB implements Serializable, AsientoDetalleTmpIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long cuentaId;
   private java.lang.Long asientoId;
   private java.lang.String referencia;
   private java.lang.String glosa;
   private java.lang.Long centrogastoId;
   private java.lang.Long empleadoId;
   private java.lang.Long departamentoId;
   private java.lang.Long lineaId;
   private java.lang.Long clienteId;
   private java.math.BigDecimal debe;
   private java.math.BigDecimal haber;

   public AsientoDetalleTmpEJB() {
   }

   public AsientoDetalleTmpEJB(com.spirit.contabilidad.entity.AsientoDetalleTmpIf value) {
      setId(value.getId());
      setCuentaId(value.getCuentaId());
      setAsientoId(value.getAsientoId());
      setReferencia(value.getReferencia());
      setGlosa(value.getGlosa());
      setCentrogastoId(value.getCentrogastoId());
      setEmpleadoId(value.getEmpleadoId());
      setDepartamentoId(value.getDepartamentoId());
      setLineaId(value.getLineaId());
      setClienteId(value.getClienteId());
      setDebe(value.getDebe());
      setHaber(value.getHaber());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.AsientoDetalleTmpIf value) {
      setId(value.getId());
      setCuentaId(value.getCuentaId());
      setAsientoId(value.getAsientoId());
      setReferencia(value.getReferencia());
      setGlosa(value.getGlosa());
      setCentrogastoId(value.getCentrogastoId());
      setEmpleadoId(value.getEmpleadoId());
      setDepartamentoId(value.getDepartamentoId());
      setLineaId(value.getLineaId());
      setClienteId(value.getClienteId());
      setDebe(value.getDebe());
      setHaber(value.getHaber());
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

   @Column(name = "CUENTA_ID")
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   @Column(name = "ASIENTO_ID")
   public java.lang.Long getAsientoId() {
      return asientoId;
   }

   public void setAsientoId(java.lang.Long asientoId) {
      this.asientoId = asientoId;
   }

   @Column(name = "REFERENCIA")
   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   @Column(name = "GLOSA")
   public java.lang.String getGlosa() {
      return glosa;
   }

   public void setGlosa(java.lang.String glosa) {
      this.glosa = glosa;
   }

   @Column(name = "CENTROGASTO_ID")
   public java.lang.Long getCentrogastoId() {
      return centrogastoId;
   }

   public void setCentrogastoId(java.lang.Long centrogastoId) {
      this.centrogastoId = centrogastoId;
   }

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "DEPARTAMENTO_ID")
   public java.lang.Long getDepartamentoId() {
      return departamentoId;
   }

   public void setDepartamentoId(java.lang.Long departamentoId) {
      this.departamentoId = departamentoId;
   }

   @Column(name = "LINEA_ID")
   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   @Column(name = "CLIENTE_ID")
   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   @Column(name = "DEBE")
   public java.math.BigDecimal getDebe() {
      return debe;
   }

   public void setDebe(java.math.BigDecimal debe) {
      this.debe = debe;
   }

   @Column(name = "HABER")
   public java.math.BigDecimal getHaber() {
      return haber;
   }

   public void setHaber(java.math.BigDecimal haber) {
      this.haber = haber;
   }
	public String toString() {
		return ToStringer.toString((AsientoDetalleTmpIf)this);
	}
}
