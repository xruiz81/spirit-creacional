package com.spirit.rrhh.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoIdiomasIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.Long getIdiomaId();

   void setIdiomaId(java.lang.Long idiomaId);

   java.lang.String getHabla();

   void setHabla(java.lang.String habla);

   java.lang.String getComprende();

   void setComprende(java.lang.String comprende);

   java.lang.String getLee();

   void setLee(java.lang.String lee);

   java.lang.String getEscribe();

   void setEscribe(java.lang.String escribe);


}
