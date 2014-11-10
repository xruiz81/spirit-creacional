package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _AsientoDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.AsientoDetalleIf addAsientoDetalle(com.spirit.contabilidad.entity.AsientoDetalleIf model) throws GenericBusinessException;

   void saveAsientoDetalle(com.spirit.contabilidad.entity.AsientoDetalleIf model) throws GenericBusinessException;

   void deleteAsientoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findAsientoDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.AsientoDetalleIf getAsientoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getAsientoDetalleList() throws GenericBusinessException;

   Collection getAsientoDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getAsientoDetalleListSize() throws GenericBusinessException;
    java.util.Collection findAsientoDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByAsientoId(java.lang.Long asientoId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByGlosa(java.lang.String glosa) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByCentrogastoId(java.lang.Long centrogastoId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByDepartamentoId(java.lang.Long departamentoId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByLineaId(java.lang.Long lineaId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByDebe(java.math.BigDecimal debe) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleByHaber(java.math.BigDecimal haber) throws GenericBusinessException;

}
