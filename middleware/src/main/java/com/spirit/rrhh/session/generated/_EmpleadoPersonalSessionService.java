package com.spirit.rrhh.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmpleadoPersonalSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.rrhh.entity.EmpleadoPersonalIf addEmpleadoPersonal(com.spirit.rrhh.entity.EmpleadoPersonalIf model) throws GenericBusinessException;

   void saveEmpleadoPersonal(com.spirit.rrhh.entity.EmpleadoPersonalIf model) throws GenericBusinessException;

   void deleteEmpleadoPersonal(java.lang.Long id) throws GenericBusinessException;

   Collection findEmpleadoPersonalByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.rrhh.entity.EmpleadoPersonalIf getEmpleadoPersonal(java.lang.Long id) throws GenericBusinessException;

   Collection getEmpleadoPersonalList() throws GenericBusinessException;

   Collection getEmpleadoPersonalList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmpleadoPersonalListSize() throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByTitulo(java.lang.String titulo) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByCedulaIdentidad(java.lang.String cedulaIdentidad) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByAfiliacionIess(java.lang.String afiliacionIess) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByLibretaMilitar(java.lang.String libretaMilitar) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalBySexo(java.lang.String sexo) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByTipoSangre(java.lang.String tipoSangre) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByEstadoCivil(java.lang.String estadoCivil) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByFechaNacimiento(java.sql.Timestamp fechaNacimiento) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByCiudadId(java.lang.Long ciudadId) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByCanton(java.lang.String canton) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByParroquia(java.lang.String parroquia) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByCasoEmergencia(java.lang.String casoEmergencia) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByTelefonoEmergencia(java.lang.String telefonoEmergencia) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByDireccionEmergencia(java.lang.String direccionEmergencia) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByCiudadEmergenciaId(java.lang.Long ciudadEmergenciaId) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByTallaCamisa(java.lang.String tallaCamisa) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByTallaPantalon(java.lang.String tallaPantalon) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByNumeroCalzado(java.lang.String numeroCalzado) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByEstatura(java.lang.String estatura) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByPeso(java.lang.String peso) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByColorPelo(java.lang.String colorPelo) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByColorOjos(java.lang.String colorOjos) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByColorPiel(java.lang.String colorPiel) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalBySenasParticulares(java.lang.String senasParticulares) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByExestudianteCtt(java.lang.String exestudianteCtt) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByPaisId(java.lang.Long paisId) throws GenericBusinessException;
    java.util.Collection findEmpleadoPersonalByProvinciaId(java.lang.Long provinciaId) throws GenericBusinessException;

}
