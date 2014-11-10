package com.spirit.crm.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ClienteIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId(); 

   void setId(java.lang.Long id);

   java.lang.Long getTipoidentificacionId();

   void setTipoidentificacionId(java.lang.Long tipoidentificacionId);

   java.lang.String getIdentificacion();

   void setIdentificacion(java.lang.String identificacion);

   java.lang.String getNombreLegal();

   void setNombreLegal(java.lang.String nombreLegal);

   java.lang.String getRazonSocial();

   void setRazonSocial(java.lang.String razonSocial);

   java.lang.String getRepresentante();

   void setRepresentante(java.lang.String representante);

   java.lang.Long getCorporacionId();

   void setCorporacionId(java.lang.Long corporacionId);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getTipoclienteId();

   void setTipoclienteId(java.lang.Long tipoclienteId);

   java.lang.Long getTipoproveedorId();

   void setTipoproveedorId(java.lang.Long tipoproveedorId);

   java.lang.Long getTiponegocioId();

   void setTiponegocioId(java.lang.Long tiponegocioId);

   java.lang.Long getCuentaId();

   void setCuentaId(java.lang.Long cuentaId);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getUsuariofinal();

   void setUsuariofinal(java.lang.String usuariofinal);

   java.lang.String getContribuyenteEspecial();

   void setContribuyenteEspecial(java.lang.String contribuyenteEspecial);

   java.lang.String getTipoPersona();

   void setTipoPersona(java.lang.String tipoPersona);

   java.lang.String getLlevaContabilidad();

   void setLlevaContabilidad(java.lang.String llevaContabilidad);

   java.lang.String getInformacionAdc();

   void setInformacionAdc(java.lang.String informacionAdc);

   java.lang.String getRequiereSap();

   void setRequiereSap(java.lang.String requiereSap);

   java.lang.Long getBancoId();

   void setBancoId(java.lang.Long bancoId);

   java.lang.String getTipoCuenta();

   void setTipoCuenta(java.lang.String tipoCuenta);

   java.lang.String getNumeroCuenta();

   void setNumeroCuenta(java.lang.String numeroCuenta);


}
