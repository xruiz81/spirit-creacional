package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _KardexSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.KardexIf addKardex(com.spirit.inventario.entity.KardexIf model) throws GenericBusinessException;

   void saveKardex(com.spirit.inventario.entity.KardexIf model) throws GenericBusinessException;

   void deleteKardex(java.lang.Long id) throws GenericBusinessException;

   Collection findKardexByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.KardexIf getKardex(java.lang.Long id) throws GenericBusinessException;

   Collection getKardexList() throws GenericBusinessException;

   Collection getKardexList(int startIndex, int endIndex) throws GenericBusinessException;

   int getKardexListSize() throws GenericBusinessException;
    java.util.Collection findKardexByMovimientoId(java.lang.Long movimientoId) throws GenericBusinessException;
    java.util.Collection findKardexByMovimientoCodigo(java.lang.String movimientoCodigo) throws GenericBusinessException;
    java.util.Collection findKardexById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findKardexByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findKardexByEmpresaNombre(java.lang.String empresaNombre) throws GenericBusinessException;
    java.util.Collection findKardexByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findKardexByOficinaCodigo(java.lang.String oficinaCodigo) throws GenericBusinessException;
    java.util.Collection findKardexByOficinaNombre(java.lang.String oficinaNombre) throws GenericBusinessException;
    java.util.Collection findKardexByBodegaId(java.lang.Long bodegaId) throws GenericBusinessException;
    java.util.Collection findKardexByBodegaCodigo(java.lang.String bodegaCodigo) throws GenericBusinessException;
    java.util.Collection findKardexByBodegaNombre(java.lang.String bodegaNombre) throws GenericBusinessException;
    java.util.Collection findKardexByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findKardexByProductoCodigo(java.lang.String productoCodigo) throws GenericBusinessException;
    java.util.Collection findKardexByProductoNombre(java.lang.String productoNombre) throws GenericBusinessException;
    java.util.Collection findKardexByFechaMovimiento(java.sql.Timestamp fechaMovimiento) throws GenericBusinessException;
    java.util.Collection findKardexByTipodocumentoCodigo(java.lang.String tipodocumentoCodigo) throws GenericBusinessException;
    java.util.Collection findKardexByTipodocumentoNombre(java.lang.String tipodocumentoNombre) throws GenericBusinessException;
    java.util.Collection findKardexByTipodocumentoSignostock(java.lang.String tipodocumentoSignostock) throws GenericBusinessException;
    java.util.Collection findKardexByCantidadDetalle(java.math.BigDecimal cantidadDetalle) throws GenericBusinessException;
    java.util.Collection findKardexByLoteId(java.lang.Long loteId) throws GenericBusinessException;
    java.util.Collection findKardexByLoteCodigo(java.lang.String loteCodigo) throws GenericBusinessException;
    java.util.Collection findKardexByDocumentoCodigo(java.lang.String documentoCodigo) throws GenericBusinessException;
    java.util.Collection findKardexByDocumentoDescripcion(java.lang.String documentoDescripcion) throws GenericBusinessException;

}
