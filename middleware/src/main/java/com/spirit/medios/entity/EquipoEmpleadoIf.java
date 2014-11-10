package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EquipoEmpleadoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEquipoId();

   void setEquipoId(java.lang.Long equipoId);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.String getRol();

   void setRol(java.lang.String rol);

   java.lang.String getJefe();

   void setJefe(java.lang.String jefe);


}
