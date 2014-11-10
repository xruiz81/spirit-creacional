package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PrensaUbicacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PrensaUbicacionIf addPrensaUbicacion(com.spirit.medios.entity.PrensaUbicacionIf model) throws GenericBusinessException;

   void savePrensaUbicacion(com.spirit.medios.entity.PrensaUbicacionIf model) throws GenericBusinessException;

   void deletePrensaUbicacion(java.lang.Long id) throws GenericBusinessException;

   Collection findPrensaUbicacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PrensaUbicacionIf getPrensaUbicacion(java.lang.Long id) throws GenericBusinessException;

   Collection getPrensaUbicacionList() throws GenericBusinessException;

   Collection getPrensaUbicacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPrensaUbicacionListSize() throws GenericBusinessException;
    java.util.Collection findPrensaUbicacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPrensaUbicacionByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findPrensaUbicacionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPrensaUbicacionByUbicacion(java.lang.String ubicacion) throws GenericBusinessException;

}
