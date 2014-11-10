package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PersonalizacionTipoPersonalizacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf addPersonalizacionTipoPersonalizacion(com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf model) throws GenericBusinessException;

   void savePersonalizacionTipoPersonalizacion(com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf model) throws GenericBusinessException;

   void deletePersonalizacionTipoPersonalizacion(java.lang.Long id) throws GenericBusinessException;

   Collection findPersonalizacionTipoPersonalizacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf getPersonalizacionTipoPersonalizacion(java.lang.Long id) throws GenericBusinessException;

   Collection getPersonalizacionTipoPersonalizacionList() throws GenericBusinessException;

   Collection getPersonalizacionTipoPersonalizacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPersonalizacionTipoPersonalizacionListSize() throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoPersonalizacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoPersonalizacionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoPersonalizacionByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoPersonalizacionByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
