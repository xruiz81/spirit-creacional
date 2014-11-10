package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CompraAsociadaGastoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.CompraAsociadaGastoIf addCompraAsociadaGasto(com.spirit.compras.entity.CompraAsociadaGastoIf model) throws GenericBusinessException;

   void saveCompraAsociadaGasto(com.spirit.compras.entity.CompraAsociadaGastoIf model) throws GenericBusinessException;

   void deleteCompraAsociadaGasto(java.lang.Long id) throws GenericBusinessException;

   Collection findCompraAsociadaGastoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.CompraAsociadaGastoIf getCompraAsociadaGasto(java.lang.Long id) throws GenericBusinessException;

   Collection getCompraAsociadaGastoList() throws GenericBusinessException;

   Collection getCompraAsociadaGastoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCompraAsociadaGastoListSize() throws GenericBusinessException;
    java.util.Collection findCompraAsociadaGastoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCompraAsociadaGastoByCompraGastoId(java.lang.Long compraGastoId) throws GenericBusinessException;
    java.util.Collection findCompraAsociadaGastoByCompraId(java.lang.Long compraId) throws GenericBusinessException;

}
