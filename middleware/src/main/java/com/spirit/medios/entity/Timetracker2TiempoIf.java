package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface Timetracker2TiempoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.Long getClienteOficinaId();

   void setClienteOficinaId(java.lang.Long clienteOficinaId);

   java.lang.Integer getTiempoDesignado();

   void setTiempoDesignado(java.lang.Integer tiempoDesignado);


}
