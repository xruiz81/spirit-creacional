package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ChequeTransaccionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getChequeEmitidoId();

   void setChequeEmitidoId(java.lang.Long chequeEmitidoId);

   java.lang.Long getTransaccionId();

   void setTransaccionId(java.lang.Long transaccionId);

   java.lang.String getOrigen();

   void setOrigen(java.lang.String origen);


}
