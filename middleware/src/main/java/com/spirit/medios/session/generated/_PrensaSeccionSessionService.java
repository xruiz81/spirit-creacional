package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PrensaSeccionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PrensaSeccionIf addPrensaSeccion(com.spirit.medios.entity.PrensaSeccionIf model) throws GenericBusinessException;

   void savePrensaSeccion(com.spirit.medios.entity.PrensaSeccionIf model) throws GenericBusinessException;

   void deletePrensaSeccion(java.lang.Long id) throws GenericBusinessException;

   Collection findPrensaSeccionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PrensaSeccionIf getPrensaSeccion(java.lang.Long id) throws GenericBusinessException;

   Collection getPrensaSeccionList() throws GenericBusinessException;

   Collection getPrensaSeccionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPrensaSeccionListSize() throws GenericBusinessException;
    java.util.Collection findPrensaSeccionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPrensaSeccionByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findPrensaSeccionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPrensaSeccionBySeccion(java.lang.String seccion) throws GenericBusinessException;

}
