package com.spirit.rrhh.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmpleadoFamiliaresSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.rrhh.entity.EmpleadoFamiliaresIf addEmpleadoFamiliares(com.spirit.rrhh.entity.EmpleadoFamiliaresIf model) throws GenericBusinessException;

   void saveEmpleadoFamiliares(com.spirit.rrhh.entity.EmpleadoFamiliaresIf model) throws GenericBusinessException;

   void deleteEmpleadoFamiliares(java.lang.Long id) throws GenericBusinessException;

   Collection findEmpleadoFamiliaresByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.rrhh.entity.EmpleadoFamiliaresIf getEmpleadoFamiliares(java.lang.Long id) throws GenericBusinessException;

   Collection getEmpleadoFamiliaresList() throws GenericBusinessException;

   Collection getEmpleadoFamiliaresList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmpleadoFamiliaresListSize() throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByTipo(java.lang.String tipo) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByApellidos(java.lang.String apellidos) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByNombres(java.lang.String nombres) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByFechaNacimiento(java.sql.Timestamp fechaNacimiento) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByCedulaIdentidad(java.lang.String cedulaIdentidad) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByOcupacion(java.lang.String ocupacion) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByNivelEstudios(java.lang.String nivelEstudios) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByTrabaja(java.lang.String trabaja) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByNombreInstitucion(java.lang.String nombreInstitucion) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByEmbarazo(java.lang.String embarazo) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByFechaParto(java.sql.Timestamp fechaParto) throws GenericBusinessException;
    java.util.Collection findEmpleadoFamiliaresByUltimoAnio(java.lang.String ultimoAnio) throws GenericBusinessException;

}
