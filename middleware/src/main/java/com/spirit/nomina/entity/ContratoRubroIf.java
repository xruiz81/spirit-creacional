package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoRubroIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.sql.Date getFechaFin();

   void setFechaFin(java.sql.Date fechaFin);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.sql.Date getFechaInicio();

   void setFechaInicio(java.sql.Date fechaInicio);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.lang.Long getContratoId();

   void setContratoId(java.lang.Long contratoId);

   java.lang.Long getRubroId();

   void setRubroId(java.lang.Long rubroId);


}
