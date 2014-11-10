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

@Table(name = "SRI_PROVEEDOR_RETENCION")
@Entity
public class SriProveedorRetencionEJB implements Serializable, SriProveedorRetencionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String tipoPersona;
   private java.lang.String llevaContabilidad;
   private java.lang.String bienServicio;
   private java.math.BigDecimal retefuente;
   private java.math.BigDecimal reteiva;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFin;
   private java.lang.String estado;
   private java.lang.Long idCuentaRetefuente;
   private java.lang.Long idCuentaReteiva;
   private java.lang.String profesional;

   public SriProveedorRetencionEJB() {
   }

   public SriProveedorRetencionEJB(com.spirit.sri.entity.SriProveedorRetencionIf value) {
      setId(value.getId());
      setTipoPersona(value.getTipoPersona());
      setLlevaContabilidad(value.getLlevaContabilidad());
      setBienServicio(value.getBienServicio());
      setRetefuente(value.getRetefuente());
      setReteiva(value.getReteiva());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setIdCuentaRetefuente(value.getIdCuentaRetefuente());
      setIdCuentaReteiva(value.getIdCuentaReteiva());
      setProfesional(value.getProfesional());
   }

   public java.lang.Long create(com.spirit.sri.entity.SriProveedorRetencionIf value) {
      setId(value.getId());
      setTipoPersona(value.getTipoPersona());
      setLlevaContabilidad(value.getLlevaContabilidad());
      setBienServicio(value.getBienServicio());
      setRetefuente(value.getRetefuente());
      setReteiva(value.getReteiva());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setIdCuentaRetefuente(value.getIdCuentaRetefuente());
      setIdCuentaReteiva(value.getIdCuentaReteiva());
      setProfesional(value.getProfesional());
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

   @Column(name = "TIPO_PERSONA")
   public java.lang.String getTipoPersona() {
      return tipoPersona;
   }

   public void setTipoPersona(java.lang.String tipoPersona) {
      this.tipoPersona = tipoPersona;
   }

   @Column(name = "LLEVA_CONTABILIDAD")
   public java.lang.String getLlevaContabilidad() {
      return llevaContabilidad;
   }

   public void setLlevaContabilidad(java.lang.String llevaContabilidad) {
      this.llevaContabilidad = llevaContabilidad;
   }

   @Column(name = "BIEN_SERVICIO")
   public java.lang.String getBienServicio() {
      return bienServicio;
   }

   public void setBienServicio(java.lang.String bienServicio) {
      this.bienServicio = bienServicio;
   }

   @Column(name = "RETEFUENTE")
   public java.math.BigDecimal getRetefuente() {
      return retefuente;
   }

   public void setRetefuente(java.math.BigDecimal retefuente) {
      this.retefuente = retefuente;
   }

   @Column(name = "RETEIVA")
   public java.math.BigDecimal getReteiva() {
      return reteiva;
   }

   public void setReteiva(java.math.BigDecimal reteiva) {
      this.reteiva = reteiva;
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

   @Column(name = "ID_CUENTA_RETEFUENTE")
   public java.lang.Long getIdCuentaRetefuente() {
      return idCuentaRetefuente;
   }

   public void setIdCuentaRetefuente(java.lang.Long idCuentaRetefuente) {
      this.idCuentaRetefuente = idCuentaRetefuente;
   }

   @Column(name = "ID_CUENTA_RETEIVA")
   public java.lang.Long getIdCuentaReteiva() {
      return idCuentaReteiva;
   }

   public void setIdCuentaReteiva(java.lang.Long idCuentaReteiva) {
      this.idCuentaReteiva = idCuentaReteiva;
   }

   @Column(name = "PROFESIONAL")
   public java.lang.String getProfesional() {
      return profesional;
   }

   public void setProfesional(java.lang.String profesional) {
      this.profesional = profesional;
   }
	public String toString() {
		return ToStringer.toString((SriProveedorRetencionIf)this);
	}
}
