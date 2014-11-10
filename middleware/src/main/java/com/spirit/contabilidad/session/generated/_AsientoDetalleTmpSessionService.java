package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _AsientoDetalleTmpSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.AsientoDetalleTmpIf addAsientoDetalleTmp(com.spirit.contabilidad.entity.AsientoDetalleTmpIf model) throws GenericBusinessException;

   void saveAsientoDetalleTmp(com.spirit.contabilidad.entity.AsientoDetalleTmpIf model) throws GenericBusinessException;

   void deleteAsientoDetalleTmp(java.lang.Long id) throws GenericBusinessException;

   Collection findAsientoDetalleTmpByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.AsientoDetalleTmpIf getAsientoDetalleTmp(java.lang.Long id) throws GenericBusinessException;

   Collection getAsientoDetalleTmpList() throws GenericBusinessException;

   Collection getAsientoDetalleTmpList(int startIndex, int endIndex) throws GenericBusinessException;

   int getAsientoDetalleTmpListSize() throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByAsientoId(java.lang.Long asientoId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByGlosa(java.lang.String glosa) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByCentrogastoId(java.lang.Long centrogastoId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByDepartamentoId(java.lang.Long departamentoId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByLineaId(java.lang.Long lineaId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByDebe(java.math.BigDecimal debe) throws GenericBusinessException;
    java.util.Collection findAsientoDetalleTmpByHaber(java.math.BigDecimal haber) throws GenericBusinessException;

}
