package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EquipoEmpleadoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.EquipoEmpleadoIf addEquipoEmpleado(com.spirit.medios.entity.EquipoEmpleadoIf model) throws GenericBusinessException;

   void saveEquipoEmpleado(com.spirit.medios.entity.EquipoEmpleadoIf model) throws GenericBusinessException;

   void deleteEquipoEmpleado(java.lang.Long id) throws GenericBusinessException;

   Collection findEquipoEmpleadoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.EquipoEmpleadoIf getEquipoEmpleado(java.lang.Long id) throws GenericBusinessException;

   Collection getEquipoEmpleadoList() throws GenericBusinessException;

   Collection getEquipoEmpleadoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEquipoEmpleadoListSize() throws GenericBusinessException;
    java.util.Collection findEquipoEmpleadoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEquipoEmpleadoByEquipoId(java.lang.Long equipoId) throws GenericBusinessException;
    java.util.Collection findEquipoEmpleadoByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findEquipoEmpleadoByRol(java.lang.String rol) throws GenericBusinessException;
    java.util.Collection findEquipoEmpleadoByJefe(java.lang.String jefe) throws GenericBusinessException;

}
