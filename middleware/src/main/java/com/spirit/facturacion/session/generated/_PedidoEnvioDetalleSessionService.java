package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PedidoEnvioDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PedidoEnvioDetalleIf addPedidoEnvioDetalle(com.spirit.facturacion.entity.PedidoEnvioDetalleIf model) throws GenericBusinessException;

   void savePedidoEnvioDetalle(com.spirit.facturacion.entity.PedidoEnvioDetalleIf model) throws GenericBusinessException;

   void deletePedidoEnvioDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findPedidoEnvioDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PedidoEnvioDetalleIf getPedidoEnvioDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getPedidoEnvioDetalleList() throws GenericBusinessException;

   Collection getPedidoEnvioDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPedidoEnvioDetalleListSize() throws GenericBusinessException;
    java.util.Collection findPedidoEnvioDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioDetalleByPedidoEnvioId(java.lang.Long pedidoEnvioId) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioDetalleByCodigoBarras(java.lang.String codigoBarras) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioDetalleByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioDetalleByIncluyeIva(java.lang.String incluyeIva) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;

}
