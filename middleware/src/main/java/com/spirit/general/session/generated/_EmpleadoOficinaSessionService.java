package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmpleadoOficinaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.EmpleadoOficinaIf addEmpleadoOficina(com.spirit.general.entity.EmpleadoOficinaIf model) throws GenericBusinessException;

   void saveEmpleadoOficina(com.spirit.general.entity.EmpleadoOficinaIf model) throws GenericBusinessException;

   void deleteEmpleadoOficina(java.lang.Long id) throws GenericBusinessException;

   Collection findEmpleadoOficinaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.EmpleadoOficinaIf getEmpleadoOficina(java.lang.Long id) throws GenericBusinessException;

   Collection getEmpleadoOficinaList() throws GenericBusinessException;

   Collection getEmpleadoOficinaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmpleadoOficinaListSize() throws GenericBusinessException;
    java.util.Collection findEmpleadoOficinaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmpleadoOficinaByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoOficinaByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;

}
