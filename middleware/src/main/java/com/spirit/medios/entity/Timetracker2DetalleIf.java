package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface Timetracker2DetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTimetracker2EmpleadoId();

   void setTimetracker2EmpleadoId(java.lang.Long timetracker2EmpleadoId);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.lang.Float getTiempo();

   void setTiempo(java.lang.Float tiempo);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getClienteOficinaId();

   void setClienteOficinaId(java.lang.Long clienteOficinaId);

   java.lang.Integer getTiempoDesignado();

   void setTiempoDesignado(java.lang.Integer tiempoDesignado);


}
