package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _Timetracker2TiempoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.Timetracker2TiempoIf addTimetracker2Tiempo(com.spirit.medios.entity.Timetracker2TiempoIf model) throws GenericBusinessException;

   void saveTimetracker2Tiempo(com.spirit.medios.entity.Timetracker2TiempoIf model) throws GenericBusinessException;

   void deleteTimetracker2Tiempo(java.lang.Long id) throws GenericBusinessException;

   Collection findTimetracker2TiempoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.Timetracker2TiempoIf getTimetracker2Tiempo(java.lang.Long id) throws GenericBusinessException;

   Collection getTimetracker2TiempoList() throws GenericBusinessException;

   Collection getTimetracker2TiempoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTimetracker2TiempoListSize() throws GenericBusinessException;
    java.util.Collection findTimetracker2TiempoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTimetracker2TiempoByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findTimetracker2TiempoByClienteOficinaId(java.lang.Long clienteOficinaId) throws GenericBusinessException;
    java.util.Collection findTimetracker2TiempoByTiempoDesignado(java.lang.Integer tiempoDesignado) throws GenericBusinessException;

}
