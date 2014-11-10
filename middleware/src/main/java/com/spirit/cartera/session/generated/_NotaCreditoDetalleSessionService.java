package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _NotaCreditoDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.NotaCreditoDetalleIf addNotaCreditoDetalle(com.spirit.cartera.entity.NotaCreditoDetalleIf model) throws GenericBusinessException;

   void saveNotaCreditoDetalle(com.spirit.cartera.entity.NotaCreditoDetalleIf model) throws GenericBusinessException;

   void deleteNotaCreditoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findNotaCreditoDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.NotaCreditoDetalleIf getNotaCreditoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getNotaCreditoDetalleList() throws GenericBusinessException;

   Collection getNotaCreditoDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getNotaCreditoDetalleListSize() throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByNotaCreditoId(java.lang.Long notaCreditoId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByOtroImpuesto(java.math.BigDecimal otroImpuesto) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByTipoReferencia(java.lang.String tipoReferencia) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByReferenciaId(java.lang.Long referenciaId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByTipoNota(java.lang.String tipoNota) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByTipoPresupuesto(java.lang.String tipoPresupuesto) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByPresupuestoId(java.lang.Long presupuestoId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoDetalleByOrdenId(java.lang.Long ordenId) throws GenericBusinessException;

}
