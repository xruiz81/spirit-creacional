package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CarteraPagoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCarteraPagoId();

   void setCarteraPagoId(java.lang.Long carteraPagoId);

   java.sql.Timestamp getFechaAprobacion();

   void setFechaAprobacion(java.sql.Timestamp fechaAprobacion);

   java.lang.Long getUsuarioAprobacionId();

   void setUsuarioAprobacionId(java.lang.Long usuarioAprobacionId);

   java.sql.Timestamp getFechaPago();

   void setFechaPago(java.sql.Timestamp fechaPago);

   java.lang.Long getUsuarioPagoId();

   void setUsuarioPagoId(java.lang.Long usuarioPagoId);

   java.lang.String getSecuencialMulticash();

   void setSecuencialMulticash(java.lang.String secuencialMulticash);

   java.lang.String getArchivoMulticash();

   void setArchivoMulticash(java.lang.String archivoMulticash);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.sql.Timestamp getFechaEmision();

   void setFechaEmision(java.sql.Timestamp fechaEmision);

   java.lang.Long getUsuarioEmisionId();

   void setUsuarioEmisionId(java.lang.Long usuarioEmisionId);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);


}
