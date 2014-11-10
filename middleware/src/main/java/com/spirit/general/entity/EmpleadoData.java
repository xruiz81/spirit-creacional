package com.spirit.general.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EmpleadoData implements EmpleadoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombres;

   public java.lang.String getNombres() {
      return nombres;
   }

   public void setNombres(java.lang.String nombres) {
      this.nombres = nombres;
   }

   private java.lang.String apellidos;

   public java.lang.String getApellidos() {
      return apellidos;
   }

   public void setApellidos(java.lang.String apellidos) {
      this.apellidos = apellidos;
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

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.String profesion;

   public java.lang.String getProfesion() {
      return profesion;
   }

   public void setProfesion(java.lang.String profesion) {
      this.profesion = profesion;
   }

   private java.lang.String direccionDomicilio;

   public java.lang.String getDireccionDomicilio() {
      return direccionDomicilio;
   }

   public void setDireccionDomicilio(java.lang.String direccionDomicilio) {
      this.direccionDomicilio = direccionDomicilio;
   }

   private java.lang.String telefonoDomicilio;

   public java.lang.String getTelefonoDomicilio() {
      return telefonoDomicilio;
   }

   public void setTelefonoDomicilio(java.lang.String telefonoDomicilio) {
      this.telefonoDomicilio = telefonoDomicilio;
   }

   private java.lang.String celular;

   public java.lang.String getCelular() {
      return celular;
   }

   public void setCelular(java.lang.String celular) {
      this.celular = celular;
   }

   private java.lang.String emailOficina;

   public java.lang.String getEmailOficina() {
      return emailOficina;
   }

   public void setEmailOficina(java.lang.String emailOficina) {
      this.emailOficina = emailOficina;
   }

   private java.lang.Long departamentoId;

   public java.lang.Long getDepartamentoId() {
      return departamentoId;
   }

   public void setDepartamentoId(java.lang.Long departamentoId) {
      this.departamentoId = departamentoId;
   }

   private java.lang.Long jefeId;

   public java.lang.Long getJefeId() {
      return jefeId;
   }

   public void setJefeId(java.lang.Long jefeId) {
      this.jefeId = jefeId;
   }

   private java.lang.Long tipoempleadoId;

   public java.lang.Long getTipoempleadoId() {
      return tipoempleadoId;
   }

   public void setTipoempleadoId(java.lang.Long tipoempleadoId) {
      this.tipoempleadoId = tipoempleadoId;
   }

   private java.lang.String extensionOficina;

   public java.lang.String getExtensionOficina() {
      return extensionOficina;
   }

   public void setExtensionOficina(java.lang.String extensionOficina) {
      this.extensionOficina = extensionOficina;
   }

   private java.lang.Integer nivel;

   public java.lang.Integer getNivel() {
      return nivel;
   }

   public void setNivel(java.lang.Integer nivel) {
      this.nivel = nivel;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.Long tipocontratoId;

   public java.lang.Long getTipocontratoId() {
      return tipocontratoId;
   }

   public void setTipocontratoId(java.lang.Long tipocontratoId) {
      this.tipocontratoId = tipocontratoId;
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
   public EmpleadoData() {
   }

   public EmpleadoData(com.spirit.general.entity.EmpleadoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombres(value.getNombres());
      setApellidos(value.getApellidos());
      setTipoidentificacionId(value.getTipoidentificacionId());
      setIdentificacion(value.getIdentificacion());
      setEmpresaId(value.getEmpresaId());
      setProfesion(value.getProfesion());
      setDireccionDomicilio(value.getDireccionDomicilio());
      setTelefonoDomicilio(value.getTelefonoDomicilio());
      setCelular(value.getCelular());
      setEmailOficina(value.getEmailOficina());
      setDepartamentoId(value.getDepartamentoId());
      setJefeId(value.getJefeId());
      setTipoempleadoId(value.getTipoempleadoId());
      setExtensionOficina(value.getExtensionOficina());
      setNivel(value.getNivel());
      setEstado(value.getEstado());
      setOficinaId(value.getOficinaId());
      setTipocontratoId(value.getTipocontratoId());
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
		return ToStringer.toString((EmpleadoIf)this);
	}
}
