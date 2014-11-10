package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _FacturaDetalleCompraAsociadaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf addFacturaDetalleCompraAsociada(com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf model) throws GenericBusinessException;

   void saveFacturaDetalleCompraAsociada(com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf model) throws GenericBusinessException;

   void deleteFacturaDetalleCompraAsociada(java.lang.Long id) throws GenericBusinessException;

   Collection findFacturaDetalleCompraAsociadaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf getFacturaDetalleCompraAsociada(java.lang.Long id) throws GenericBusinessException;

   Collection getFacturaDetalleCompraAsociadaList() throws GenericBusinessException;

   Collection getFacturaDetalleCompraAsociadaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getFacturaDetalleCompraAsociadaListSize() throws GenericBusinessException;
    java.util.Collection findFacturaDetalleCompraAsociadaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleCompraAsociadaByFacturaDetalleId(java.lang.Long facturaDetalleId) throws GenericBusinessException;
    java.util.Collection findFacturaDetalleCompraAsociadaByCompraId(java.lang.Long compraId) throws GenericBusinessException;

}
