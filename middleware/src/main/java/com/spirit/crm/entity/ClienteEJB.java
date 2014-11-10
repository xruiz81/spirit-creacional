package com.spirit.crm.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CLIENTE")
@Entity
public class ClienteEJB implements Serializable, ClienteIf {


   @PersistenceContext
   private EntityManager manager; 

   private java.lang.Long id;
   private java.lang.Long tipoidentificacionId;
   private java.lang.String identificacion;
   private java.lang.String nombreLegal;
   private java.lang.String razonSocial;
   private java.lang.String representante;
   private java.lang.Long corporacionId;
   private java.sql.Timestamp fechaCreacion;
   private java.lang.String estado;
   private java.lang.Long tipoclienteId;
   private java.lang.Long tipoproveedorId;
   private java.lang.Long tiponegocioId;
   private java.lang.Long cuentaId;
   private java.lang.String observacion;
   private java.lang.String usuariofinal;
   private java.lang.String contribuyenteEspecial;
   private java.lang.String tipoPersona;
   private java.lang.String llevaContabilidad;
   private java.lang.String informacionAdc;
   private java.lang.String requiereSap;
   private java.lang.Long bancoId;
   private java.lang.String tipoCuenta;
   private java.lang.String numeroCuenta;

   public ClienteEJB() {
   }

   public ClienteEJB(com.spirit.crm.entity.ClienteIf value) {
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

   public java.lang.Long create(com.spirit.crm.entity.ClienteIf value) {
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

   @Column(name = "TIPOIDENTIFICACION_ID")
   public java.lang.Long getTipoidentificacionId() {
      return tipoidentificacionId;
   }

   public void setTipoidentificacionId(java.lang.Long tipoidentificacionId) {
      this.tipoidentificacionId = tipoidentificacionId;
   }

   @Column(name = "IDENTIFICACION")
   public java.lang.String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.String identificacion) {
      this.identificacion = identificacion;
   }

   @Column(name = "NOMBRE_LEGAL")
   public java.lang.String getNombreLegal() {
      return nombreLegal;
   }

   public void setNombreLegal(java.lang.String nombreLegal) {
      this.nombreLegal = nombreLegal;
   }

   @Column(name = "RAZON_SOCIAL")
   public java.lang.String getRazonSocial() {
      return razonSocial;
   }

   public void setRazonSocial(java.lang.String razonSocial) {
      this.razonSocial = razonSocial;
   }

   @Column(name = "REPRESENTANTE")
   public java.lang.String getRepresentante() {
      return representante;
   }

   public void setRepresentante(java.lang.String representante) {
      this.representante = representante;
   }

   @Column(name = "CORPORACION_ID")
   public java.lang.Long getCorporacionId() {
      return corporacionId;
   }

   public void setCorporacionId(java.lang.Long corporacionId) {
      this.corporacionId = corporacionId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "TIPOCLIENTE_ID")
   public java.lang.Long getTipoclienteId() {
      return tipoclienteId;
   }

   public void setTipoclienteId(java.lang.Long tipoclienteId) {
      this.tipoclienteId = tipoclienteId;
   }

   @Column(name = "TIPOPROVEEDOR_ID")
   public java.lang.Long getTipoproveedorId() {
      return tipoproveedorId;
   }

   public void setTipoproveedorId(java.lang.Long tipoproveedorId) {
      this.tipoproveedorId = tipoproveedorId;
   }

   @Column(name = "TIPONEGOCIO_ID")
   public java.lang.Long getTiponegocioId() {
      return tiponegocioId;
   }

   public void setTiponegocioId(java.lang.Long tiponegocioId) {
      this.tiponegocioId = tiponegocioId;
   }

   @Column(name = "CUENTA_ID")
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "USUARIOFINAL")
   public java.lang.String getUsuariofinal() {
      return usuariofinal;
   }

   public void setUsuariofinal(java.lang.String usuariofinal) {
      this.usuariofinal = usuariofinal;
   }

   @Column(name = "CONTRIBUYENTE_ESPECIAL")
   public java.lang.String getContribuyenteEspecial() {
      return contribuyenteEspecial;
   }

   public void setContribuyenteEspecial(java.lang.String contribuyenteEspecial) {
      this.contribuyenteEspecial = contribuyenteEspecial;
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

   @Column(name = "INFORMACION_ADC")
   public java.lang.String getInformacionAdc() {
      return informacionAdc;
   }

   public void setInformacionAdc(java.lang.String informacionAdc) {
      this.informacionAdc = informacionAdc;
   }

   @Column(name = "REQUIERE_SAP")
   public java.lang.String getRequiereSap() {
      return requiereSap;
   }

   public void setRequiereSap(java.lang.String requiereSap) {
      this.requiereSap = requiereSap;
   }

   @Column(name = "BANCO_ID")
   public java.lang.Long getBancoId() {
      return bancoId;
   }

   public void setBancoId(java.lang.Long bancoId) {
      this.bancoId = bancoId;
   }

   @Column(name = "TIPO_CUENTA")
   public java.lang.String getTipoCuenta() {
      return tipoCuenta;
   }

   public void setTipoCuenta(java.lang.String tipoCuenta) {
      this.tipoCuenta = tipoCuenta;
   }

   @Column(name = "NUMERO_CUENTA")
   public java.lang.String getNumeroCuenta() {
      return numeroCuenta;
   }

   public void setNumeroCuenta(java.lang.String numeroCuenta) {
      this.numeroCuenta = numeroCuenta;
   }
	public String toString() {
		return ToStringer.toString((ClienteIf)this);
	}
}
