package com.spirit.compras.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrdenAsociadaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.OrdenAsociadaIf addOrdenAsociada(com.spirit.compras.entity.OrdenAsociadaIf model) throws GenericBusinessException;

   void saveOrdenAsociada(com.spirit.compras.entity.OrdenAsociadaIf model) throws GenericBusinessException;

   void deleteOrdenAsociada(java.lang.Long id) throws GenericBusinessException;

   Collection findOrdenAsociadaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.OrdenAsociadaIf getOrdenAsociada(java.lang.Long id) throws GenericBusinessException;

   Collection getOrdenAsociadaList() throws GenericBusinessException;

   Collection getOrdenAsociadaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrdenAsociadaListSize() throws GenericBusinessException;
    java.util.Collection findOrdenAsociadaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrdenAsociadaByCompraId(java.lang.Long compraId) throws GenericBusinessException;
    java.util.Collection findOrdenAsociadaByTipoOrden(java.lang.String tipoOrden) throws GenericBusinessException;
    java.util.Collection findOrdenAsociadaByOrdenId(java.lang.Long ordenId) throws GenericBusinessException;

}
