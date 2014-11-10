package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoUtilidadIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.sql.Date getFechaInicio();

   void setFechaInicio(java.sql.Date fechaInicio);

   java.sql.Date getFechaFin();

   void setFechaFin(java.sql.Date fechaFin);

   java.math.BigDecimal getUtilidad();

   void setUtilidad(java.math.BigDecimal utilidad);

   java.math.BigDecimal getPorcentajeContrato();

   void setPorcentajeContrato(java.math.BigDecimal porcentajeContrato);

   java.math.BigDecimal getPorcentajeCarga();

   void setPorcentajeCarga(java.math.BigDecimal porcentajeCarga);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);


}
