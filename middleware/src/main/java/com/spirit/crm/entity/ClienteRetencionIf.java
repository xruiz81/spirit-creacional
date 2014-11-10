package com.spirit.crm.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ClienteRetencionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.Long getSriAirId();

   void setSriAirId(java.lang.Long sriAirId);

   java.lang.Long getSriIvaRetencionId();

   void setSriIvaRetencionId(java.lang.Long sriIvaRetencionId);


}
