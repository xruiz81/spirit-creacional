package com.spirit.seguridad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _RolOpcionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.seguridad.entity.RolOpcionIf addRolOpcion(com.spirit.seguridad.entity.RolOpcionIf model) throws GenericBusinessException;

   void saveRolOpcion(com.spirit.seguridad.entity.RolOpcionIf model) throws GenericBusinessException;

   void deleteRolOpcion(java.lang.Long id) throws GenericBusinessException;

   Collection findRolOpcionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.seguridad.entity.RolOpcionIf getRolOpcion(java.lang.Long id) throws GenericBusinessException;

   Collection getRolOpcionList() throws GenericBusinessException;

   Collection getRolOpcionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getRolOpcionListSize() throws GenericBusinessException;
    java.util.Collection findRolOpcionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findRolOpcionByRolId(java.lang.Long rolId) throws GenericBusinessException;
    java.util.Collection findRolOpcionByMenuId(java.lang.Long menuId) throws GenericBusinessException;
    java.util.Collection findRolOpcionByNinguno(java.lang.String ninguno) throws GenericBusinessException;
    java.util.Collection findRolOpcionByGrabarActualizar(java.lang.String grabarActualizar) throws GenericBusinessException;
    java.util.Collection findRolOpcionByBorrar(java.lang.String borrar) throws GenericBusinessException;
    java.util.Collection findRolOpcionByConsultar(java.lang.String consultar) throws GenericBusinessException;
    java.util.Collection findRolOpcionByAutorizar(java.lang.String autorizar) throws GenericBusinessException;
    java.util.Collection findRolOpcionByImprimir(java.lang.String imprimir) throws GenericBusinessException;
    java.util.Collection findRolOpcionByGenerarGrafico(java.lang.String generarGrafico) throws GenericBusinessException;
    java.util.Collection findRolOpcionByDuplicar(java.lang.String duplicar) throws GenericBusinessException;

}
