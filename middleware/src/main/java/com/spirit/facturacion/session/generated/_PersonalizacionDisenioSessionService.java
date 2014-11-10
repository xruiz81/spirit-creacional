package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PersonalizacionDisenioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PersonalizacionDisenioIf addPersonalizacionDisenio(com.spirit.facturacion.entity.PersonalizacionDisenioIf model) throws GenericBusinessException;

   void savePersonalizacionDisenio(com.spirit.facturacion.entity.PersonalizacionDisenioIf model) throws GenericBusinessException;

   void deletePersonalizacionDisenio(java.lang.Long id) throws GenericBusinessException;

   Collection findPersonalizacionDisenioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PersonalizacionDisenioIf getPersonalizacionDisenio(java.lang.Long id) throws GenericBusinessException;

   Collection getPersonalizacionDisenioList() throws GenericBusinessException;

   Collection getPersonalizacionDisenioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPersonalizacionDisenioListSize() throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioByTipoProductoId(java.lang.Long tipoProductoId) throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioByLineaId(java.lang.Long lineaId) throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioByPersonalizacionColorId(java.lang.Long personalizacionColorId) throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioByMiniDisplay(java.lang.String miniDisplay) throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioByFront(java.lang.String front) throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioByBack(java.lang.String back) throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findPersonalizacionDisenioByEtiqueta(java.lang.String etiqueta) throws GenericBusinessException;

}
