package com.spirit.rrhh.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoFormacionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.String getNivel();

   void setNivel(java.lang.String nivel);

   java.lang.String getUltimoAnio();

   void setUltimoAnio(java.lang.String ultimoAnio);

   java.sql.Timestamp getFechaGraduacion();

   void setFechaGraduacion(java.sql.Timestamp fechaGraduacion);

   java.lang.String getTituloObtenido();

   void setTituloObtenido(java.lang.String tituloObtenido);

   java.lang.String getInstitucion();

   void setInstitucion(java.lang.String institucion);

   java.lang.Long getCiudadId();

   void setCiudadId(java.lang.Long ciudadId);


}
