package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PersonalizacionTamanioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PersonalizacionTamanioIf addPersonalizacionTamanio(com.spirit.facturacion.entity.PersonalizacionTamanioIf model) throws GenericBusinessException;

   void savePersonalizacionTamanio(com.spirit.facturacion.entity.PersonalizacionTamanioIf model) throws GenericBusinessException;

   void deletePersonalizacionTamanio(java.lang.Long id) throws GenericBusinessException;

   Collection findPersonalizacionTamanioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PersonalizacionTamanioIf getPersonalizacionTamanio(java.lang.Long id) throws GenericBusinessException;

   Collection getPersonalizacionTamanioList() throws GenericBusinessException;

   Collection getPersonalizacionTamanioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPersonalizacionTamanioListSize() throws GenericBusinessException;
    java.util.Collection findPersonalizacionTamanioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTamanioByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTamanioByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findPersonalizacionTamanioByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
