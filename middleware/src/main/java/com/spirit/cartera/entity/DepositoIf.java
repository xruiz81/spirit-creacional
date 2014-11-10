package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface DepositoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getCuentabancariaId();

   void setCuentabancariaId(java.lang.Long cuentabancariaId);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.sql.Date getFechaDeposito();

   void setFechaDeposito(java.sql.Date fechaDeposito);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
