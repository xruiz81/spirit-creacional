package com.spirit.sri.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "SRI_CLIENTE_RETENCION")
@Entity
public class SriClienteRetencionEJB implements Serializable, SriClienteRetencionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String tipoRetencion;
   private java.math.BigDecimal porcentajeRetencion;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFin;
   private java.lang.String estado;
   private java.lang.Long cuentaId;

   public SriClienteRetencionEJB() {
   }

   public SriClienteRetencionEJB(com.spirit.sri.entity.SriClienteRetencionIf value) {
      setId(value.getId());
      setTipoRetencion(value.getTipoRetencion());
      setPorcentajeRetencion(value.getPorcentajeRetencion());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setCuentaId(value.getCuentaId());
   }

   public java.lang.Long create(com.spirit.sri.entity.SriClienteRetencionIf value) {
      setId(value.getId());
      setTipoRetencion(value.getTipoRetencion());
      setPorcentajeRetencion(value.getPorcentajeRetencion());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setCuentaId(value.getCuentaId());
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

   @Column(name = "TIPO_RETENCION")
   public java.lang.String getTipoRetencion() {
      return tipoRetencion;
   }

   public void setTipoRetencion(java.lang.String tipoRetencion) {
      this.tipoRetencion = tipoRetencion;
   }

   @Column(name = "PORCENTAJE_RETENCION")
   public java.math.BigDecimal getPorcentajeRetencion() {
      return porcentajeRetencion;
   }

   public void setPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) {
      this.porcentajeRetencion = porcentajeRetencion;
   }

   @Column(name = "FECHA_INICIO")
   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FIN")
   public java.sql.Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Date fechaFin) {
      this.fechaFin = fechaFin;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "CUENTA_ID")
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }
	public String toString() {
		return ToStringer.toString((SriClienteRetencionIf)this);
	}
}
