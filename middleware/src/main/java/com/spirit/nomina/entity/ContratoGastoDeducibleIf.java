package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoGastoDeducibleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getContratoId();

   void setContratoId(java.lang.Long contratoId);

   java.lang.Long getGastoDeducibleId();

   void setGastoDeducibleId(java.lang.Long gastoDeducibleId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);


}
