package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _LogCompraRetencionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.LogCompraRetencionIf addLogCompraRetencion(com.spirit.compras.entity.LogCompraRetencionIf model) throws GenericBusinessException;

   void saveLogCompraRetencion(com.spirit.compras.entity.LogCompraRetencionIf model) throws GenericBusinessException;

   void deleteLogCompraRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection findLogCompraRetencionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.LogCompraRetencionIf getLogCompraRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection getLogCompraRetencionList() throws GenericBusinessException;

   Collection getLogCompraRetencionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getLogCompraRetencionListSize() throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByEstablecimiento(java.lang.String establecimiento) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByPuntoEmision(java.lang.String puntoEmision) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionBySecuencial(java.lang.String secuencial) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByAutorizacion(java.lang.String autorizacion) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByFechaEmision(java.sql.Date fechaEmision) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByCompraId(java.lang.Long compraId) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByEjercicioFiscal(java.lang.String ejercicioFiscal) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByBaseImponible(java.math.BigDecimal baseImponible) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByImpuesto(java.lang.String impuesto) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByValorRetenido(java.math.BigDecimal valorRetenido) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByCodigoImpuesto(java.lang.String codigoImpuesto) throws GenericBusinessException;
    java.util.Collection findLogCompraRetencionByLog(java.lang.String log) throws GenericBusinessException;

}
