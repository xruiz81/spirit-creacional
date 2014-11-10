package com.spirit.rrhh.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmpleadoFormacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.rrhh.entity.EmpleadoFormacionIf addEmpleadoFormacion(com.spirit.rrhh.entity.EmpleadoFormacionIf model) throws GenericBusinessException;

   void saveEmpleadoFormacion(com.spirit.rrhh.entity.EmpleadoFormacionIf model) throws GenericBusinessException;

   void deleteEmpleadoFormacion(java.lang.Long id) throws GenericBusinessException;

   Collection findEmpleadoFormacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.rrhh.entity.EmpleadoFormacionIf getEmpleadoFormacion(java.lang.Long id) throws GenericBusinessException;

   Collection getEmpleadoFormacionList() throws GenericBusinessException;

   Collection getEmpleadoFormacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmpleadoFormacionListSize() throws GenericBusinessException;
    java.util.Collection findEmpleadoFormacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmpleadoFormacionByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoFormacionByNivel(java.lang.String nivel) throws GenericBusinessException;
    java.util.Collection findEmpleadoFormacionByUltimoAnio(java.lang.String ultimoAnio) throws GenericBusinessException;
    java.util.Collection findEmpleadoFormacionByFechaGraduacion(java.sql.Timestamp fechaGraduacion) throws GenericBusinessException;
    java.util.Collection findEmpleadoFormacionByTituloObtenido(java.lang.String tituloObtenido) throws GenericBusinessException;
    java.util.Collection findEmpleadoFormacionByInstitucion(java.lang.String institucion) throws GenericBusinessException;
    java.util.Collection findEmpleadoFormacionByCiudadId(java.lang.Long ciudadId) throws GenericBusinessException;

}
