package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _NotaCreditoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.NotaCreditoIf addNotaCredito(com.spirit.cartera.entity.NotaCreditoIf model) throws GenericBusinessException;

   void saveNotaCredito(com.spirit.cartera.entity.NotaCreditoIf model) throws GenericBusinessException;

   void deleteNotaCredito(java.lang.Long id) throws GenericBusinessException;

   Collection findNotaCreditoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.NotaCreditoIf getNotaCredito(java.lang.Long id) throws GenericBusinessException;

   Collection getNotaCreditoList() throws GenericBusinessException;

   Collection getNotaCreditoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getNotaCreditoListSize() throws GenericBusinessException;
    java.util.Collection findNotaCreditoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByTipoDocumentoId(java.lang.Long tipoDocumentoId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByOperadorNegocioId(java.lang.Long operadorNegocioId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByMonedaId(java.lang.Long monedaId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByFechaEmision(java.sql.Timestamp fechaEmision) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByFechaVencimiento(java.sql.Timestamp fechaVencimiento) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByFechaCaducidad(java.sql.Timestamp fechaCaducidad) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByAutorizacion(java.lang.String autorizacion) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByOtroImpuesto(java.math.BigDecimal otroImpuesto) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByTipoCartera(java.lang.String tipoCartera) throws GenericBusinessException;
    java.util.Collection findNotaCreditoByReferenciaId(java.lang.Long referenciaId) throws GenericBusinessException;

}
