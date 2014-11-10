package com.spirit.rrhh.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmpleadoVacacionesSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.rrhh.entity.EmpleadoVacacionesIf addEmpleadoVacaciones(com.spirit.rrhh.entity.EmpleadoVacacionesIf model) throws GenericBusinessException;

   void saveEmpleadoVacaciones(com.spirit.rrhh.entity.EmpleadoVacacionesIf model) throws GenericBusinessException;

   void deleteEmpleadoVacaciones(java.lang.Long id) throws GenericBusinessException;

   Collection findEmpleadoVacacionesByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.rrhh.entity.EmpleadoVacacionesIf getEmpleadoVacaciones(java.lang.Long id) throws GenericBusinessException;

   Collection getEmpleadoVacacionesList() throws GenericBusinessException;

   Collection getEmpleadoVacacionesList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmpleadoVacacionesListSize() throws GenericBusinessException;
    java.util.Collection findEmpleadoVacacionesById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmpleadoVacacionesByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoVacacionesBySaldoDias(java.lang.Float saldoDias) throws GenericBusinessException;
    java.util.Collection findEmpleadoVacacionesByFechaInicio(java.sql.Timestamp fechaInicio) throws GenericBusinessException;
    java.util.Collection findEmpleadoVacacionesByFechaFin(java.sql.Timestamp fechaFin) throws GenericBusinessException;
    java.util.Collection findEmpleadoVacacionesByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findEmpleadoVacacionesByArchivoAdjunto(java.lang.String archivoAdjunto) throws GenericBusinessException;

}
