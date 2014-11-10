package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _DepartamentoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.DepartamentoIf addDepartamento(com.spirit.general.entity.DepartamentoIf model) throws GenericBusinessException;

   void saveDepartamento(com.spirit.general.entity.DepartamentoIf model) throws GenericBusinessException;

   void deleteDepartamento(java.lang.Long id) throws GenericBusinessException;

   Collection findDepartamentoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.DepartamentoIf getDepartamento(java.lang.Long id) throws GenericBusinessException;

   Collection getDepartamentoList() throws GenericBusinessException;

   Collection getDepartamentoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getDepartamentoListSize() throws GenericBusinessException;
    java.util.Collection findDepartamentoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findDepartamentoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findDepartamentoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findDepartamentoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findDepartamentoByDepartamentoId(java.lang.Long departamentoId) throws GenericBusinessException;

}
