package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _LogAsientoDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.LogAsientoDetalleIf addLogAsientoDetalle(com.spirit.contabilidad.entity.LogAsientoDetalleIf model) throws GenericBusinessException;

   void saveLogAsientoDetalle(com.spirit.contabilidad.entity.LogAsientoDetalleIf model) throws GenericBusinessException;

   void deleteLogAsientoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findLogAsientoDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.LogAsientoDetalleIf getLogAsientoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getLogAsientoDetalleList() throws GenericBusinessException;

   Collection getLogAsientoDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getLogAsientoDetalleListSize() throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByLogAsientoId(java.lang.Long logAsientoId) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByGlosa(java.lang.String glosa) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByCentrogastoId(java.lang.Long centrogastoId) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByDepartamentoId(java.lang.Long departamentoId) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByLineaId(java.lang.Long lineaId) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByDebe(java.math.BigDecimal debe) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByHaber(java.math.BigDecimal haber) throws GenericBusinessException;
    java.util.Collection findLogAsientoDetalleByLog(java.lang.String log) throws GenericBusinessException;

}
