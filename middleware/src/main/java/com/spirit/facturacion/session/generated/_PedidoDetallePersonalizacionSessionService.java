package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PedidoDetallePersonalizacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf addPedidoDetallePersonalizacion(com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf model) throws GenericBusinessException;

   void savePedidoDetallePersonalizacion(com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf model) throws GenericBusinessException;

   void deletePedidoDetallePersonalizacion(java.lang.Long id) throws GenericBusinessException;

   Collection findPedidoDetallePersonalizacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf getPedidoDetallePersonalizacion(java.lang.Long id) throws GenericBusinessException;

   Collection getPedidoDetallePersonalizacionList() throws GenericBusinessException;

   Collection getPedidoDetallePersonalizacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPedidoDetallePersonalizacionListSize() throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByPedidoDetalleId(java.lang.Long pedidoDetalleId) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByTipoPersonalizacionId(java.lang.Long tipoPersonalizacionId) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByImpresionPersonalizacionId(java.lang.Long impresionPersonalizacionId) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByTamanioPersonalizacionId(java.lang.Long tamanioPersonalizacionId) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByColorPersonalizacionId(java.lang.Long colorPersonalizacionId) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByUbicacionPersonalizacionId(java.lang.Long ubicacionPersonalizacionId) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByTipoLetraPersonalizacionId(java.lang.Long tipoLetraPersonalizacionId) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByLogoDisenioPersonalizacionId(java.lang.Long logoDisenioPersonalizacionId) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByMensaje(java.lang.String mensaje) throws GenericBusinessException;
    java.util.Collection findPedidoDetallePersonalizacionByDisenioPersonalizacionId(java.lang.Long disenioPersonalizacionId) throws GenericBusinessException;

}
