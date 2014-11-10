package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface AsientoTmpIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getAsientoCierre();

   void setAsientoCierre(java.lang.String asientoCierre);

   java.lang.String getNumero();

   void setNumero(java.lang.String numero);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.Long getPeriodoId();

   void setPeriodoId(java.lang.Long periodoId);

   java.lang.Long getPlancuentaId();

   void setPlancuentaId(java.lang.Long plancuentaId);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.lang.String getStatus();

   void setStatus(java.lang.String status);

   java.lang.String getEfectivo();

   void setEfectivo(java.lang.String efectivo);

   java.lang.Long getSubtipoasientoId();

   void setSubtipoasientoId(java.lang.Long subtipoasientoId);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getTipoDocumentoId();

   void setTipoDocumentoId(java.lang.Long tipoDocumentoId);

   java.lang.Long getTransaccionId();

   void setTransaccionId(java.lang.Long transaccionId);

   java.lang.Long getElaboradoPorId();

   void setElaboradoPorId(java.lang.Long elaboradoPorId);

   java.lang.Long getAutorizadoPorId();

   void setAutorizadoPorId(java.lang.Long autorizadoPorId);

   java.lang.Long getCarteraAfectaId();

   void setCarteraAfectaId(java.lang.Long carteraAfectaId);

   java.lang.Long getEventoContableId();

   void setEventoContableId(java.lang.Long eventoContableId);

   java.lang.String getUsarNota();

   void setUsarNota(java.lang.String usarNota);


}
