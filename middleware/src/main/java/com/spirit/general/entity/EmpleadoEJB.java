package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "EMPLEADO")
@Entity
public class EmpleadoEJB implements Serializable, EmpleadoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombres;
   private java.lang.String apellidos;
   private java.lang.Long tipoidentificacionId;
   private java.lang.String identificacion;
   private java.lang.Long empresaId;
   private java.lang.String profesion;
   private java.lang.String direccionDomicilio;
   private java.lang.String telefonoDomicilio;
   private java.lang.String celular;
   private java.lang.String emailOficina;
   private java.lang.Long departamentoId;
   private java.lang.Long jefeId;
   private java.lang.Long tipoempleadoId;
   private java.lang.String extensionOficina;
   private java.lang.Integer nivel;
   private java.lang.String estado;
   private java.lang.Long oficinaId;
   private java.lang.Long tipocontratoId;
   private java.lang.Long bancoId;
   private java.lang.String tipoCuenta;
   private java.lang.String numeroCuenta;

   public EmpleadoEJB() {
   }

   public EmpleadoEJB(com.spirit.general.entity.EmpleadoIf value) {
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

   public java.lang.Long create(com.spirit.general.entity.EmpleadoIf value) {
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "NOMBRES")
   public java.lang.String getNombres() {
      return nombres;
   }

   public void setNombres(java.lang.String nombres) {
      this.nombres = nombres;
   }

   @Column(name = "APELLIDOS")
   public java.lang.String getApellidos() {
      return apellidos;
   }

   public void setApellidos(java.lang.String apellidos) {
      this.apellidos = apellidos;
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

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "PROFESION")
   public java.lang.String getProfesion() {
      return profesion;
   }

   public void setProfesion(java.lang.String profesion) {
      this.profesion = profesion;
   }

   @Column(name = "DIRECCION_DOMICILIO")
   public java.lang.String getDireccionDomicilio() {
      return direccionDomicilio;
   }

   public void setDireccionDomicilio(java.lang.String direccionDomicilio) {
      this.direccionDomicilio = direccionDomicilio;
   }

   @Column(name = "TELEFONO_DOMICILIO")
   public java.lang.String getTelefonoDomicilio() {
      return telefonoDomicilio;
   }

   public void setTelefonoDomicilio(java.lang.String telefonoDomicilio) {
      this.telefonoDomicilio = telefonoDomicilio;
   }

   @Column(name = "CELULAR")
   public java.lang.String getCelular() {
      return celular;
   }

   public void setCelular(java.lang.String celular) {
      this.celular = celular;
   }

   @Column(name = "EMAIL_OFICINA")
   public java.lang.String getEmailOficina() {
      return emailOficina;
   }

   public void setEmailOficina(java.lang.String emailOficina) {
      this.emailOficina = emailOficina;
   }

   @Column(name = "DEPARTAMENTO_ID")
   public java.lang.Long getDepartamentoId() {
      return departamentoId;
   }

   public void setDepartamentoId(java.lang.Long departamentoId) {
      this.departamentoId = departamentoId;
   }

   @Column(name = "JEFE_ID")
   public java.lang.Long getJefeId() {
      return jefeId;
   }

   public void setJefeId(java.lang.Long jefeId) {
      this.jefeId = jefeId;
   }

   @Column(name = "TIPOEMPLEADO_ID")
   public java.lang.Long getTipoempleadoId() {
      return tipoempleadoId;
   }

   public void setTipoempleadoId(java.lang.Long tipoempleadoId) {
      this.tipoempleadoId = tipoempleadoId;
   }

   @Column(name = "EXTENSION_OFICINA")
   public java.lang.String getExtensionOficina() {
      return extensionOficina;
   }

   public void setExtensionOficina(java.lang.String extensionOficina) {
      this.extensionOficina = extensionOficina;
   }

   @Column(name = "NIVEL")
   public java.lang.Integer getNivel() {
      return nivel;
   }

   public void setNivel(java.lang.Integer nivel) {
      this.nivel = nivel;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "TIPOCONTRATO_ID")
   public java.lang.Long getTipocontratoId() {
      return tipocontratoId;
   }

   public void setTipocontratoId(java.lang.Long tipocontratoId) {
      this.tipocontratoId = tipocontratoId;
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
		return ToStringer.toString((EmpleadoIf)this);
	}
}
