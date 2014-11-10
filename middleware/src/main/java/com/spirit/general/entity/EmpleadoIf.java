package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombres();

   void setNombres(java.lang.String nombres);

   java.lang.String getApellidos();

   void setApellidos(java.lang.String apellidos);

   java.lang.Long getTipoidentificacionId();

   void setTipoidentificacionId(java.lang.Long tipoidentificacionId);

   java.lang.String getIdentificacion();

   void setIdentificacion(java.lang.String identificacion);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.String getProfesion();

   void setProfesion(java.lang.String profesion);

   java.lang.String getDireccionDomicilio();

   void setDireccionDomicilio(java.lang.String direccionDomicilio);

   java.lang.String getTelefonoDomicilio();

   void setTelefonoDomicilio(java.lang.String telefonoDomicilio);

   java.lang.String getCelular();

   void setCelular(java.lang.String celular);

   java.lang.String getEmailOficina();

   void setEmailOficina(java.lang.String emailOficina);

   java.lang.Long getDepartamentoId();

   void setDepartamentoId(java.lang.Long departamentoId);

   java.lang.Long getJefeId();

   void setJefeId(java.lang.Long jefeId);

   java.lang.Long getTipoempleadoId();

   void setTipoempleadoId(java.lang.Long tipoempleadoId);

   java.lang.String getExtensionOficina();

   void setExtensionOficina(java.lang.String extensionOficina);

   java.lang.Integer getNivel();

   void setNivel(java.lang.Integer nivel);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getTipocontratoId();

   void setTipocontratoId(java.lang.Long tipocontratoId);

   java.lang.Long getBancoId();

   void setBancoId(java.lang.Long bancoId);

   java.lang.String getTipoCuenta();

   void setTipoCuenta(java.lang.String tipoCuenta);

   java.lang.String getNumeroCuenta();

   void setNumeroCuenta(java.lang.String numeroCuenta);


}
