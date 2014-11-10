package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _LogEquipoEmpleadoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.LogEquipoEmpleadoIf addLogEquipoEmpleado(com.spirit.medios.entity.LogEquipoEmpleadoIf model) throws GenericBusinessException;

   void saveLogEquipoEmpleado(com.spirit.medios.entity.LogEquipoEmpleadoIf model) throws GenericBusinessException;

   void deleteLogEquipoEmpleado(java.lang.Long id) throws GenericBusinessException;

   Collection findLogEquipoEmpleadoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.LogEquipoEmpleadoIf getLogEquipoEmpleado(java.lang.Long id) throws GenericBusinessException;

   Collection getLogEquipoEmpleadoList() throws GenericBusinessException;

   Collection getLogEquipoEmpleadoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getLogEquipoEmpleadoListSize() throws GenericBusinessException;
    java.util.Collection findLogEquipoEmpleadoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findLogEquipoEmpleadoByEquipoId(java.lang.Long equipoId) throws GenericBusinessException;
    java.util.Collection findLogEquipoEmpleadoByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findLogEquipoEmpleadoByRol(java.lang.String rol) throws GenericBusinessException;
    java.util.Collection findLogEquipoEmpleadoByLog(java.lang.String log) throws GenericBusinessException;
    java.util.Collection findLogEquipoEmpleadoByJefe(java.lang.String jefe) throws GenericBusinessException;

}
