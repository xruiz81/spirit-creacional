package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RubroEventualIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getContratoId();

   void setContratoId(java.lang.Long contratoId);

   java.lang.Long getRubroId();

   void setRubroId(java.lang.Long rubroId);

   java.lang.Long getTipoRolIdCobro();

   void setTipoRolIdCobro(java.lang.Long tipoRolIdCobro);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.sql.Date getFechaCobro();

   void setFechaCobro(java.sql.Date fechaCobro);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.Long getTipoRolIdPago();

   void setTipoRolIdPago(java.lang.Long tipoRolIdPago);

   java.sql.Date getFechaPago();

   void setFechaPago(java.sql.Date fechaPago);

   java.lang.Long getIdentificacion();

   void setIdentificacion(java.lang.Long identificacion);


}
