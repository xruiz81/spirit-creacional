package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SaldoCuentaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCuentaId();

   void setCuentaId(java.lang.Long cuentaId);

   java.lang.Long getPeriodoId();

   void setPeriodoId(java.lang.Long periodoId);

   java.lang.String getAnio();

   void setAnio(java.lang.String anio);

   java.lang.String getMes();

   void setMes(java.lang.String mes);

   java.math.BigDecimal getValordebe();

   void setValordebe(java.math.BigDecimal valordebe);

   java.math.BigDecimal getValorhaber();

   void setValorhaber(java.math.BigDecimal valorhaber);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);


}
