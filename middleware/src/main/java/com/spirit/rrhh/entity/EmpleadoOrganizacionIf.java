package com.spirit.rrhh.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoOrganizacionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.Long getDepartamento();

   void setDepartamento(java.lang.Long departamento);

   java.lang.Long getTipoEmpleadoId();

   void setTipoEmpleadoId(java.lang.Long tipoEmpleadoId);

   java.sql.Timestamp getFechaInicio();

   void setFechaInicio(java.sql.Timestamp fechaInicio);

   java.sql.Timestamp getFechaFin();

   void setFechaFin(java.sql.Timestamp fechaFin);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.String getArchivoAdjunto();

   void setArchivoAdjunto(java.lang.String archivoAdjunto);


}
