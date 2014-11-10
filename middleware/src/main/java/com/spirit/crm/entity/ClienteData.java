package com.spirit.crm.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ClienteData implements ClienteIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipoidentificacionId;

   public java.lang.Long getTipoidentificacionId() {
      return tipoidentificacionId;
   }

   public void setTipoidentificacionId(java.lang.Long tipoidentificacionId) {
      this.tipoidentificacionId = tipoidentificacionId;
   }

   private java.lang.String identificacion;

   public java.lang.String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.String identificacion) {
      this.identificacion = identificacion;
   }

   private java.lang.String nombreLegal;

   public java.lang.String getNombreLegal() {
      return nombreLegal;
   }

   public void setNombreLegal(java.lang.String nombreLegal) {
      this.nombreLegal = nombreLegal;
   }

   private java.lang.String razonSocial;

   public java.lang.String getRazonSocial() {
      return razonSocial;
   }

   public void setRazonSocial(java.lang.String razonSocial) {
      this.razonSocial = razonSocial;
   }

   private java.lang.String representante;

   public java.lang.String getRepresentante() {
      return representante;
   }

   public void setRepresentante(java.lang.String representante) {
      this.representante = representante;
   }

   private java.lang.Long corporacionId;

   public java.lang.Long getCorporacionId() {
      return corporacionId;
   }

   public void setCorporacionId(java.lang.Long corporacionId) {
      this.corporacionId = corporacionId;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long tipoclienteId;

   public java.lang.Long getTipoclienteId() {
      return tipoclienteId;
   }

   public void setTipoclienteId(java.lang.Long tipoclienteId) {
      this.tipoclienteId = tipoclienteId;
   }

   private java.lang.Long tipoproveedorId;

   public java.lang.Long getTipoproveedorId() {
      return tipoproveedorId;
   }

   public void setTipoproveedorId(java.lang.Long tipoproveedorId) {
      this.tipoproveedorId = tipoproveedorId;
   }

   private java.lang.Long tiponegocioId;

   public java.lang.Long getTiponegocioId() {
      return tiponegocioId;
   }

   public void setTiponegocioId(java.lang.Long tiponegocioId) {
      this.tiponegocioId = tiponegocioId;
   }

   private java.lang.Long cuentaId;

   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String usuariofinal;

   public java.lang.String getUsuariofinal() {
      return usuariofinal;
   }

   public void setUsuariofinal(java.lang.String usuariofinal) {
      this.usuariofinal = usuariofinal;
   }

   private java.lang.String contribuyenteEspecial;

   public java.lang.String getContribuyenteEspecial() {
      return contribuyenteEspecial;
   }

   public void setContribuyenteEspecial(java.lang.String contribuyenteEspecial) {
      this.contribuyenteEspecial = contribuyenteEspecial;
   }

   private java.lang.String tipoPersona;

   public java.lang.String getTipoPersona() {
      return tipoPersona;
   }

   public void setTipoPersona(java.lang.String tipoPersona) {
      this.tipoPersona = tipoPersona;
   }

   private java.lang.String llevaContabilidad;

   public java.lang.String getLlevaContabilidad() {
      return llevaContabilidad;
   }

   public void setLlevaContabilidad(java.lang.String llevaContabilidad) {
      this.llevaContabilidad = llevaContabilidad;
   }

   private java.lang.String informacionAdc;

   public java.lang.String getInformacionAdc() {
      return informacionAdc;
   }

   public void setInformacionAdc(java.lang.String informacionAdc) {
      this.informacionAdc = informacionAdc;
   }

   private java.lang.String requiereSap;

   public java.lang.String getRequiereSap() {
      return requiereSap;
   }

   public void setRequiereSap(java.lang.String requiereSap) {
      this.requiereSap = requiereSap;
   }

   private java.lang.Long bancoId;

   public java.lang.Long getBancoId() {
      return bancoId;
   }

   public void setBancoId(java.lang.Long bancoId) {
      this.bancoId = bancoId;
   }

   private java.lang.String tipoCuenta;

   public java.lang.String getTipoCuenta() {
      return tipoCuenta;
   }

   public void setTipoCuenta(java.lang.String tipoCuenta) {
      this.tipoCuenta = tipoCuenta;
   }

   private java.lang.String numeroCuenta;

   public java.lang.String getNumeroCuenta() {
      return numeroCuenta;
   }

   public void setNumeroCuenta(java.lang.String numeroCuenta) {
      this.numeroCuenta = numeroCuenta;
   }
   public ClienteData() {
   }

   public ClienteData(com.spirit.crm.entity.ClienteIf value) {
      setId(value.getId());
      setTipoidentificacionId(value.getTipoidentificacionId());
      setIdentificacion(value.getIdentificacion());
      setNombreLegal(value.getNombreLegal());
      setRazonSocial(value.getRazonSocial());
      setRepresentante(value.getRepresentante());
      setCorporacionId(value.getCorporacionId());
      setFechaCreacion(value.getFechaCreacion());
      setEstado(value.getEstado());
      setTipoclienteId(value.getTipoclienteId());
      setTipoproveedorId(value.getTipoproveedorId());
      setTiponegocioId(value.getTiponegocioId());
      setCuentaId(value.getCuentaId());
      setObservacion(value.getObservacion());
      setUsuariofinal(value.getUsuariofinal());
      setContribuyenteEspecial(value.getContribuyenteEspecial());
      setTipoPersona(value.getTipoPersona());
      setLlevaContabilidad(value.getLlevaContabilidad());
      setInformacionAdc(value.getInformacionAdc());
      setRequiereSap(value.getRequiereSap());
      setBancoId(value.getBancoId());
      setTipoCuenta(value.getTipoCuenta());
      setNumeroCuenta(value.getNumeroCuenta()); 
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
		return ToStringer.toString((ClienteIf)this);
	}
}
