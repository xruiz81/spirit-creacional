package com.spirit.sri.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriClienteRetencionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getTipoRetencion();

   void setTipoRetencion(java.lang.String tipoRetencion);

   java.math.BigDecimal getPorcentajeRetencion();

   void setPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion);

   java.sql.Date getFechaInicio();

   void setFechaInicio(java.sql.Date fechaInicio);

   java.sql.Date getFechaFin();

   void setFechaFin(java.sql.Date fechaFin);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getCuentaId();

   void setCuentaId(java.lang.Long cuentaId);


}
