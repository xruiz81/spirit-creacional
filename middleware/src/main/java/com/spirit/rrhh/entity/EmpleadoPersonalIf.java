package com.spirit.rrhh.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoPersonalIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.String getTitulo();

   void setTitulo(java.lang.String titulo);

   java.lang.String getCedulaIdentidad();

   void setCedulaIdentidad(java.lang.String cedulaIdentidad);

   java.lang.String getAfiliacionIess();

   void setAfiliacionIess(java.lang.String afiliacionIess);

   java.lang.String getLibretaMilitar();

   void setLibretaMilitar(java.lang.String libretaMilitar);

   java.lang.String getSexo();

   void setSexo(java.lang.String sexo);

   java.lang.String getTipoSangre();

   void setTipoSangre(java.lang.String tipoSangre);

   java.lang.String getEstadoCivil();

   void setEstadoCivil(java.lang.String estadoCivil);

   java.sql.Timestamp getFechaNacimiento();

   void setFechaNacimiento(java.sql.Timestamp fechaNacimiento);

   java.lang.Long getCiudadId();

   void setCiudadId(java.lang.Long ciudadId);

   java.lang.String getCanton();

   void setCanton(java.lang.String canton);

   java.lang.String getParroquia();

   void setParroquia(java.lang.String parroquia);

   java.lang.String getCasoEmergencia();

   void setCasoEmergencia(java.lang.String casoEmergencia);

   java.lang.String getTelefonoEmergencia();

   void setTelefonoEmergencia(java.lang.String telefonoEmergencia);

   java.lang.String getDireccionEmergencia();

   void setDireccionEmergencia(java.lang.String direccionEmergencia);

   java.lang.Long getCiudadEmergenciaId();

   void setCiudadEmergenciaId(java.lang.Long ciudadEmergenciaId);

   java.lang.String getTallaCamisa();

   void setTallaCamisa(java.lang.String tallaCamisa);

   java.lang.String getTallaPantalon();

   void setTallaPantalon(java.lang.String tallaPantalon);

   java.lang.String getNumeroCalzado();

   void setNumeroCalzado(java.lang.String numeroCalzado);

   java.lang.String getEstatura();

   void setEstatura(java.lang.String estatura);

   java.lang.String getPeso();

   void setPeso(java.lang.String peso);

   java.lang.String getColorPelo();

   void setColorPelo(java.lang.String colorPelo);

   java.lang.String getColorOjos();

   void setColorOjos(java.lang.String colorOjos);

   java.lang.String getColorPiel();

   void setColorPiel(java.lang.String colorPiel);

   java.lang.String getSenasParticulares();

   void setSenasParticulares(java.lang.String senasParticulares);

   java.lang.String getExestudianteCtt();

   void setExestudianteCtt(java.lang.String exestudianteCtt);

   java.lang.Long getPaisId();

   void setPaisId(java.lang.Long paisId);

   java.lang.Long getProvinciaId();

   void setProvinciaId(java.lang.Long provinciaId);


}
