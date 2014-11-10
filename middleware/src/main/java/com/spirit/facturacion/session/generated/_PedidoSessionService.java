package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PedidoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PedidoIf addPedido(com.spirit.facturacion.entity.PedidoIf model) throws GenericBusinessException;

   void savePedido(com.spirit.facturacion.entity.PedidoIf model) throws GenericBusinessException;

   void deletePedido(java.lang.Long id) throws GenericBusinessException;

   Collection findPedidoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PedidoIf getPedido(java.lang.Long id) throws GenericBusinessException;

   Collection getPedidoList() throws GenericBusinessException;

   Collection getPedidoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPedidoListSize() throws GenericBusinessException;
    java.util.Collection findPedidoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPedidoByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findPedidoByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findPedidoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPedidoByClienteoficinaId(java.lang.Long clienteoficinaId) throws GenericBusinessException;
    java.util.Collection findPedidoByTipoidentificacionId(java.lang.Long tipoidentificacionId) throws GenericBusinessException;
    java.util.Collection findPedidoByIdentificacion(java.lang.String identificacion) throws GenericBusinessException;
    java.util.Collection findPedidoByFormapagoId(java.lang.Long formapagoId) throws GenericBusinessException;
    java.util.Collection findPedidoByMonedaId(java.lang.Long monedaId) throws GenericBusinessException;
    java.util.Collection findPedidoByPuntoimpresionId(java.lang.Long puntoimpresionId) throws GenericBusinessException;
    java.util.Collection findPedidoByOrigendocumentoId(java.lang.Long origendocumentoId) throws GenericBusinessException;
    java.util.Collection findPedidoByVendedorId(java.lang.Long vendedorId) throws GenericBusinessException;
    java.util.Collection findPedidoByBodegaId(java.lang.Long bodegaId) throws GenericBusinessException;
    java.util.Collection findPedidoByListaprecioId(java.lang.Long listaprecioId) throws GenericBusinessException;
    java.util.Collection findPedidoByFechaPedido(java.sql.Timestamp fechaPedido) throws GenericBusinessException;
    java.util.Collection findPedidoByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findPedidoByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findPedidoByContacto(java.lang.String contacto) throws GenericBusinessException;
    java.util.Collection findPedidoByDireccion(java.lang.String direccion) throws GenericBusinessException;
    java.util.Collection findPedidoByTelefono(java.lang.String telefono) throws GenericBusinessException;
    java.util.Collection findPedidoByTiporeferencia(java.lang.String tiporeferencia) throws GenericBusinessException;
    java.util.Collection findPedidoByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findPedidoByDiasvalidez(java.lang.Integer diasvalidez) throws GenericBusinessException;
    java.util.Collection findPedidoByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findPedidoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findPedidoByPedidoaplId(java.lang.Long pedidoaplId) throws GenericBusinessException;
    java.util.Collection findPedidoByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) throws GenericBusinessException;
    java.util.Collection findPedidoByTipopagoId(java.lang.Long tipopagoId) throws GenericBusinessException;
    java.util.Collection findPedidoByEquipoId(java.lang.Long equipoId) throws GenericBusinessException;
    java.util.Collection findPedidoByClienteNegociacionId(java.lang.Long clienteNegociacionId) throws GenericBusinessException;
    java.util.Collection findPedidoByTipoNegociacion(java.lang.String tipoNegociacion) throws GenericBusinessException;
    java.util.Collection findPedidoByPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) throws GenericBusinessException;
    java.util.Collection findPedidoByPorcentajeDescuentoNegociacion(java.math.BigDecimal porcentajeDescuentoNegociacion) throws GenericBusinessException;
    java.util.Collection findPedidoByAutorizacionSap(java.lang.String autorizacionSap) throws GenericBusinessException;
    java.util.Collection findPedidoByFechaVencimiento(java.sql.Timestamp fechaVencimiento) throws GenericBusinessException;

}
