package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionAsistenteIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getReunionId();

   void setReunionId(java.lang.Long reunionId);

   java.lang.Long getClienteContactoId();

   void setClienteContactoId(java.lang.Long clienteContactoId);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.String getClienteContacto();

   void setClienteContacto(java.lang.String clienteContacto);


}
