package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PersonalizacionColorSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PersonalizacionColorIf addPersonalizacionColor(com.spirit.facturacion.entity.PersonalizacionColorIf model) throws GenericBusinessException;

   void savePersonalizacionColor(com.spirit.facturacion.entity.PersonalizacionColorIf model) throws GenericBusinessException;

   void deletePersonalizacionColor(java.lang.Long id) throws GenericBusinessException;

   Collection findPersonalizacionColorByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PersonalizacionColorIf getPersonalizacionColor(java.lang.Long id) throws GenericBusinessException;

   Collection getPersonalizacionColorList() throws GenericBusinessException;

   Collection getPersonalizacionColorList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPersonalizacionColorListSize() throws GenericBusinessException;
    java.util.Collection findPersonalizacionColorById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPersonalizacionColorByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPersonalizacionColorByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findPersonalizacionColorByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
