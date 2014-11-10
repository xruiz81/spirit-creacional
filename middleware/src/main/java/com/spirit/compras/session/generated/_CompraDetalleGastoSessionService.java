package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CompraDetalleGastoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.CompraDetalleGastoIf addCompraDetalleGasto(com.spirit.compras.entity.CompraDetalleGastoIf model) throws GenericBusinessException;

   void saveCompraDetalleGasto(com.spirit.compras.entity.CompraDetalleGastoIf model) throws GenericBusinessException;

   void deleteCompraDetalleGasto(java.lang.Long id) throws GenericBusinessException;

   Collection findCompraDetalleGastoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.CompraDetalleGastoIf getCompraDetalleGasto(java.lang.Long id) throws GenericBusinessException;

   Collection getCompraDetalleGastoList() throws GenericBusinessException;

   Collection getCompraDetalleGastoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCompraDetalleGastoListSize() throws GenericBusinessException;
    java.util.Collection findCompraDetalleGastoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCompraDetalleGastoByCompraGastoId(java.lang.Long compraGastoId) throws GenericBusinessException;
    java.util.Collection findCompraDetalleGastoByCompraDetalleId(java.lang.Long compraDetalleId) throws GenericBusinessException;
    java.util.Collection findCompraDetalleGastoByValor(java.math.BigDecimal valor) throws GenericBusinessException;

}
