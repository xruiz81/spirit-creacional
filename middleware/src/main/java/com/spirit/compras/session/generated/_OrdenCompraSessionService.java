package com.spirit.compras.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrdenCompraSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.OrdenCompraIf addOrdenCompra(com.spirit.compras.entity.OrdenCompraIf model) throws GenericBusinessException;

   void saveOrdenCompra(com.spirit.compras.entity.OrdenCompraIf model) throws GenericBusinessException;

   void deleteOrdenCompra(java.lang.Long id) throws GenericBusinessException;

   Collection findOrdenCompraByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.OrdenCompraIf getOrdenCompra(java.lang.Long id) throws GenericBusinessException;

   Collection getOrdenCompraList() throws GenericBusinessException;

   Collection getOrdenCompraList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrdenCompraListSize() throws GenericBusinessException;
    java.util.Collection findOrdenCompraById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByProveedorId(java.lang.Long proveedorId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByMonedaId(java.lang.Long monedaId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByBodegaId(java.lang.Long bodegaId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByLocalimportada(java.lang.String localimportada) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByFechaRecepcion(java.sql.Date fechaRecepcion) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByFechaVencimiento(java.sql.Date fechaVencimiento) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByDescuentoAgenciaCompra(java.math.BigDecimal descuentoAgenciaCompra) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByOtroImpuesto(java.math.BigDecimal otroImpuesto) throws GenericBusinessException;
    java.util.Collection findOrdenCompraBySolicitudcompraId(java.lang.Long solicitudcompraId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByEstadoBpm(java.lang.String estadoBpm) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) throws GenericBusinessException;
    java.util.Collection findOrdenCompraByRevision(java.lang.String revision) throws GenericBusinessException;

}
