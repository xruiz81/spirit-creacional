package com.spirit.rrhh.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoFamiliaresIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.String getTipo();

   void setTipo(java.lang.String tipo);

   java.lang.String getApellidos();

   void setApellidos(java.lang.String apellidos);

   java.lang.String getNombres();

   void setNombres(java.lang.String nombres);

   java.sql.Timestamp getFechaNacimiento();

   void setFechaNacimiento(java.sql.Timestamp fechaNacimiento);

   java.lang.String getCedulaIdentidad();

   void setCedulaIdentidad(java.lang.String cedulaIdentidad);

   java.lang.String getOcupacion();

   void setOcupacion(java.lang.String ocupacion);

   java.lang.String getNivelEstudios();

   void setNivelEstudios(java.lang.String nivelEstudios);

   java.lang.String getTrabaja();

   void setTrabaja(java.lang.String trabaja);

   java.lang.String getNombreInstitucion();

   void setNombreInstitucion(java.lang.String nombreInstitucion);

   java.lang.String getEmbarazo();

   void setEmbarazo(java.lang.String embarazo);

   java.sql.Timestamp getFechaParto();

   void setFechaParto(java.sql.Timestamp fechaParto);

   java.lang.String getUltimoAnio();

   void setUltimoAnio(java.lang.String ultimoAnio);


}
