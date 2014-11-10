package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CarteraPagoData implements CarteraPagoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long carteraPagoId;

   public java.lang.Long getCarteraPagoId() {
      return carteraPagoId;
   }

   public void setCarteraPagoId(java.lang.Long carteraPagoId) {
      this.carteraPagoId = carteraPagoId;
   }

   private java.sql.Timestamp fechaAprobacion;

   public java.sql.Timestamp getFechaAprobacion() {
      return fechaAprobacion;
   }

   public void setFechaAprobacion(java.sql.Timestamp fechaAprobacion) {
      this.fechaAprobacion = fechaAprobacion;
   }

   private java.lang.Long usuarioAprobacionId;

   public java.lang.Long getUsuarioAprobacionId() {
      return usuarioAprobacionId;
   }

   public void setUsuarioAprobacionId(java.lang.Long usuarioAprobacionId) {
      this.usuarioAprobacionId = usuarioAprobacionId;
   }

   private java.sql.Timestamp fechaPago;

   public java.sql.Timestamp getFechaPago() {
      return fechaPago;
   }

   public void setFechaPago(java.sql.Timestamp fechaPago) {
      this.fechaPago = fechaPago;
   }

   private java.lang.Long usuarioPagoId;

   public java.lang.Long getUsuarioPagoId() {
      return usuarioPagoId;
   }

   public void setUsuarioPagoId(java.lang.Long usuarioPagoId) {
      this.usuarioPagoId = usuarioPagoId;
   }

   private java.lang.String secuencialMulticash;

   public java.lang.String getSecuencialMulticash() {
      return secuencialMulticash;
   }

   public void setSecuencialMulticash(java.lang.String secuencialMulticash) {
      this.secuencialMulticash = secuencialMulticash;
   }

   private java.lang.String archivoMulticash;

   public java.lang.String getArchivoMulticash() {
      return archivoMulticash;
   }

   public void setArchivoMulticash(java.lang.String archivoMulticash) {
      this.archivoMulticash = archivoMulticash;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.sql.Timestamp fechaEmision;

   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   private java.lang.Long usuarioEmisionId;

   public java.lang.Long getUsuarioEmisionId() {
      return usuarioEmisionId;
   }

   public void setUsuarioEmisionId(java.lang.Long usuarioEmisionId) {
      this.usuarioEmisionId = usuarioEmisionId;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
   public CarteraPagoData() {
   }

   public CarteraPagoData(com.spirit.cartera.entity.CarteraPagoIf value) {
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



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((CarteraPagoIf)this);
	}
}
