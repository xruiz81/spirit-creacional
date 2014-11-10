package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PersonalizacionUbicacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PersonalizacionUbicacionIf addPersonalizacionUbicacion(com.spirit.facturacion.entity.PersonalizacionUbicacionIf model) throws GenericBusinessException;

   void savePersonalizacionUbicacion(com.spirit.facturacion.entity.PersonalizacionUbicacionIf model) throws GenericBusinessException;

   void deletePersonalizacionUbicacion(java.lang.Long id) throws GenericBusinessException;

   Collection findPersonalizacionUbicacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PersonalizacionUbicacionIf getPersonalizacionUbicacion(java.lang.Long id) throws GenericBusinessException;

   Collection getPersonalizacionUbicacionList() throws GenericBusinessException;

   Collection getPersonalizacionUbicacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPersonalizacionUbicacionListSize() throws GenericBusinessException;
    java.util.Collection findPersonalizacionUbicacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPersonalizacionUbicacionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPersonalizacionUbicacionByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findPersonalizacionUbicacionByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
