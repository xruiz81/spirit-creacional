package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PersonalizacionLogoDisenioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PersonalizacionLogoDisenioIf addPersonalizacionLogoDisenio(com.spirit.facturacion.entity.PersonalizacionLogoDisenioIf model) throws GenericBusinessException;

   void savePersonalizacionLogoDisenio(com.spirit.facturacion.entity.PersonalizacionLogoDisenioIf model) throws GenericBusinessException;

   void deletePersonalizacionLogoDisenio(java.lang.Long id) throws GenericBusinessException;

   Collection findPersonalizacionLogoDisenioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PersonalizacionLogoDisenioIf getPersonalizacionLogoDisenio(java.lang.Long id) throws GenericBusinessException;

   Collection getPersonalizacionLogoDisenioList() throws GenericBusinessException;

   Collection getPersonalizacionLogoDisenioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPersonalizacionLogoDisenioListSize() throws GenericBusinessException;
    java.util.Collection findPersonalizacionLogoDisenioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPersonalizacionLogoDisenioByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPersonalizacionLogoDisenioByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findPersonalizacionLogoDisenioByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
