package com.spirit.cartera.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CARTERA_PAGO")
@Entity
public class CarteraPagoEJB implements Serializable, CarteraPagoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long carteraPagoId;
   private java.sql.Timestamp fechaAprobacion;
   private java.lang.Long usuarioAprobacionId;
   private java.sql.Timestamp fechaPago;
   private java.lang.Long usuarioPagoId;
   private java.lang.String secuencialMulticash;
   private java.lang.String archivoMulticash;
   private java.lang.String estado;
   private java.sql.Timestamp fechaEmision;
   private java.lang.Long usuarioEmisionId;
   private java.lang.Long empresaId;

   public CarteraPagoEJB() {
   }

   public CarteraPagoEJB(com.spirit.cartera.entity.CarteraPagoIf value) {
      setId(value.getId());
      setCarteraPagoId(value.getCarteraPagoId());
      setFechaAprobacion(value.getFechaAprobacion());
      setUsuarioAprobacionId(value.getUsuarioAprobacionId());
      setFechaPago(value.getFechaPago());
      setUsuarioPagoId(value.getUsuarioPagoId());
      setSecuencialMulticash(value.getSecuencialMulticash());
      setArchivoMulticash(value.getArchivoMulticash());
      setEstado(value.getEstado());
      setFechaEmision(value.getFechaEmision());
      setUsuarioEmisionId(value.getUsuarioEmisionId());
      setEmpresaId(value.getEmpresaId());
   }

   public java.lang.Long create(com.spirit.cartera.entity.CarteraPagoIf value) {
      setId(value.getId());
      setCarteraPagoId(value.getCarteraPagoId());
      setFechaAprobacion(value.getFechaAprobacion());
      setUsuarioAprobacionId(value.getUsuarioAprobacionId());
      setFechaPago(value.getFechaPago());
      setUsuarioPagoId(value.getUsuarioPagoId());
      setSecuencialMulticash(value.getSecuencialMulticash());
      setArchivoMulticash(value.getArchivoMulticash());
      setEstado(value.getEstado());
      setFechaEmision(value.getFechaEmision());
      setUsuarioEmisionId(value.getUsuarioEmisionId());
      setEmpresaId(value.getEmpresaId());
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

   @Column(name = "CARTERA_PAGO_ID")
   public java.lang.Long getCarteraPagoId() {
      return carteraPagoId;
   }

   public void setCarteraPagoId(java.lang.Long carteraPagoId) {
      this.carteraPagoId = carteraPagoId;
   }

   @Column(name = "FECHA_APROBACION")
   public java.sql.Timestamp getFechaAprobacion() {
      return fechaAprobacion;
   }

   public void setFechaAprobacion(java.sql.Timestamp fechaAprobacion) {
      this.fechaAprobacion = fechaAprobacion;
   }

   @Column(name = "USUARIO_APROBACION_ID")
   public java.lang.Long getUsuarioAprobacionId() {
      return usuarioAprobacionId;
   }

   public void setUsuarioAprobacionId(java.lang.Long usuarioAprobacionId) {
      this.usuarioAprobacionId = usuarioAprobacionId;
   }

   @Column(name = "FECHA_PAGO")
   public java.sql.Timestamp getFechaPago() {
      return fechaPago;
   }

   public void setFechaPago(java.sql.Timestamp fechaPago) {
      this.fechaPago = fechaPago;
   }

   @Column(name = "USUARIO_PAGO_ID")
   public java.lang.Long getUsuarioPagoId() {
      return usuarioPagoId;
   }

   public void setUsuarioPagoId(java.lang.Long usuarioPagoId) {
      this.usuarioPagoId = usuarioPagoId;
   }

   @Column(name = "SECUENCIAL_MULTICASH")
   public java.lang.String getSecuencialMulticash() {
      return secuencialMulticash;
   }

   public void setSecuencialMulticash(java.lang.String secuencialMulticash) {
      this.secuencialMulticash = secuencialMulticash;
   }

   @Column(name = "ARCHIVO_MULTICASH")
   public java.lang.String getArchivoMulticash() {
      return archivoMulticash;
   }

   public void setArchivoMulticash(java.lang.String archivoMulticash) {
      this.archivoMulticash = archivoMulticash;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "FECHA_EMISION")
   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   @Column(name = "USUARIO_EMISION_ID")
   public java.lang.Long getUsuarioEmisionId() {
      return usuarioEmisionId;
   }

   public void setUsuarioEmisionId(java.lang.Long usuarioEmisionId) {
      this.usuarioEmisionId = usuarioEmisionId;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
	public String toString() {
		return ToStringer.toString((CarteraPagoIf)this);
	}
}
