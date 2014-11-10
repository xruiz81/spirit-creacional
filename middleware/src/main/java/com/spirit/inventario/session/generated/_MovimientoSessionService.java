package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MovimientoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.MovimientoIf addMovimiento(com.spirit.inventario.entity.MovimientoIf model) throws GenericBusinessException;

   void saveMovimiento(com.spirit.inventario.entity.MovimientoIf model) throws GenericBusinessException;

   void deleteMovimiento(java.lang.Long id) throws GenericBusinessException;

   Collection findMovimientoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.MovimientoIf getMovimiento(java.lang.Long id) throws GenericBusinessException;

   Collection getMovimientoList() throws GenericBusinessException;

   Collection getMovimientoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMovimientoListSize() throws GenericBusinessException;
    java.util.Collection findMovimientoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMovimientoByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findMovimientoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findMovimientoByBodegaId(java.lang.Long bodegaId) throws GenericBusinessException;
    java.util.Collection findMovimientoByBodegarefId(java.lang.Long bodegarefId) throws GenericBusinessException;
    java.util.Collection findMovimientoByOrdencompraId(java.lang.Long ordencompraId) throws GenericBusinessException;
    java.util.Collection findMovimientoByCompraId(java.lang.Long compraId) throws GenericBusinessException;
    java.util.Collection findMovimientoByReferenciaId(java.lang.Long referenciaId) throws GenericBusinessException;
    java.util.Collection findMovimientoByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findMovimientoByFechaDocumento(java.sql.Timestamp fechaDocumento) throws GenericBusinessException;
    java.util.Collection findMovimientoByCosto(java.math.BigDecimal costo) throws GenericBusinessException;
    java.util.Collection findMovimientoByPrecio(java.math.BigDecimal precio) throws GenericBusinessException;
    java.util.Collection findMovimientoByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findMovimientoByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findMovimientoByUsuarioautId(java.lang.Long usuarioautId) throws GenericBusinessException;
    java.util.Collection findMovimientoByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findMovimientoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findMovimientoByTipodocumentoOrigenId(java.lang.Long tipodocumentoOrigenId) throws GenericBusinessException;

}
