package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CompraGastoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.CompraGastoIf addCompraGasto(com.spirit.compras.entity.CompraGastoIf model) throws GenericBusinessException;

   void saveCompraGasto(com.spirit.compras.entity.CompraGastoIf model) throws GenericBusinessException;

   void deleteCompraGasto(java.lang.Long id) throws GenericBusinessException;

   Collection findCompraGastoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.CompraGastoIf getCompraGasto(java.lang.Long id) throws GenericBusinessException;

   Collection getCompraGastoList() throws GenericBusinessException;

   Collection getCompraGastoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCompraGastoListSize() throws GenericBusinessException;
    java.util.Collection findCompraGastoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCompraGastoByGastoId(java.lang.Long gastoId) throws GenericBusinessException;
    java.util.Collection findCompraGastoByCompraId(java.lang.Long compraId) throws GenericBusinessException;
    java.util.Collection findCompraGastoByValor(java.math.BigDecimal valor) throws GenericBusinessException;

}
