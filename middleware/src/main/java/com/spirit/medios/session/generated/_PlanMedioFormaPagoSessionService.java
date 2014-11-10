package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PlanMedioFormaPagoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PlanMedioFormaPagoIf addPlanMedioFormaPago(com.spirit.medios.entity.PlanMedioFormaPagoIf model) throws GenericBusinessException;

   void savePlanMedioFormaPago(com.spirit.medios.entity.PlanMedioFormaPagoIf model) throws GenericBusinessException;

   void deletePlanMedioFormaPago(java.lang.Long id) throws GenericBusinessException;

   Collection findPlanMedioFormaPagoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PlanMedioFormaPagoIf getPlanMedioFormaPago(java.lang.Long id) throws GenericBusinessException;

   Collection getPlanMedioFormaPagoList() throws GenericBusinessException;

   Collection getPlanMedioFormaPagoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPlanMedioFormaPagoListSize() throws GenericBusinessException;
    java.util.Collection findPlanMedioFormaPagoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPlanMedioFormaPagoByPedidoId(java.lang.Long pedidoId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFormaPagoByPlanMedioId(java.lang.Long planMedioId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFormaPagoByFechaInicio(java.sql.Timestamp fechaInicio) throws GenericBusinessException;
    java.util.Collection findPlanMedioFormaPagoByFechaFin(java.sql.Timestamp fechaFin) throws GenericBusinessException;
    java.util.Collection findPlanMedioFormaPagoByTipoFormaPago(java.lang.String tipoFormaPago) throws GenericBusinessException;
    java.util.Collection findPlanMedioFormaPagoByFormaPagoId(java.lang.Long formaPagoId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFormaPagoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findPlanMedioFormaPagoByFormaPagoCampanaProductoVersionId(java.lang.Long formaPagoCampanaProductoVersionId) throws GenericBusinessException;

}
