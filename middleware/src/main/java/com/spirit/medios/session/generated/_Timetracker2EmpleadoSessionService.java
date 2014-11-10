package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _Timetracker2EmpleadoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.Timetracker2EmpleadoIf addTimetracker2Empleado(com.spirit.medios.entity.Timetracker2EmpleadoIf model) throws GenericBusinessException;

   void saveTimetracker2Empleado(com.spirit.medios.entity.Timetracker2EmpleadoIf model) throws GenericBusinessException;

   void deleteTimetracker2Empleado(java.lang.Long id) throws GenericBusinessException;

   Collection findTimetracker2EmpleadoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.Timetracker2EmpleadoIf getTimetracker2Empleado(java.lang.Long id) throws GenericBusinessException;

   Collection getTimetracker2EmpleadoList() throws GenericBusinessException;

   Collection getTimetracker2EmpleadoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTimetracker2EmpleadoListSize() throws GenericBusinessException;
    java.util.Collection findTimetracker2EmpleadoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTimetracker2EmpleadoByTimetracker2Id(java.lang.Long timetracker2Id) throws GenericBusinessException;
    java.util.Collection findTimetracker2EmpleadoByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findTimetracker2EmpleadoByEstado(java.lang.String estado) throws GenericBusinessException;

}
