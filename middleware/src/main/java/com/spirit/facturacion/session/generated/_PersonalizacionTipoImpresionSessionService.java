package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PersonalizacionTipoImpresionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PersonalizacionTipoImpresionIf addPersonalizacionTipoImpresion(com.spirit.facturacion.entity.PersonalizacionTipoImpresionIf model) throws GenericBusinessException;

   void savePersonalizacionTipoImpresion(com.spirit.facturacion.entity.PersonalizacionTipoImpresionIf model) throws GenericBusinessException;

   void deletePersonalizacionTipoImpresion(java.lang.Long id) throws GenericBusinessException;

   Collection findPersonalizacionTipoImpresionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PersonalizacionTipoImpresionIf getPersonalizacionTipoImpresion(java.lang.Long id) throws GenericBusinessException;

   Collection getPersonalizacionTipoImpresionList() throws GenericBusinessException;

   Collection getPersonalizacionTipoImpresionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPersonalizacionTipoImpresionListSize() throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoImpresionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoImpresionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoImpresionByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTipoImpresionByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
