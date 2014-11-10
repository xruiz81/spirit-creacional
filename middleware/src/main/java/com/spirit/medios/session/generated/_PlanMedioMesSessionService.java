package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PlanMedioMesSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PlanMedioMesIf addPlanMedioMes(com.spirit.medios.entity.PlanMedioMesIf model) throws GenericBusinessException;

   void savePlanMedioMes(com.spirit.medios.entity.PlanMedioMesIf model) throws GenericBusinessException;

   void deletePlanMedioMes(java.lang.Long id) throws GenericBusinessException;

   Collection findPlanMedioMesByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PlanMedioMesIf getPlanMedioMes(java.lang.Long id) throws GenericBusinessException;

   Collection getPlanMedioMesList() throws GenericBusinessException;

   Collection getPlanMedioMesList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPlanMedioMesListSize() throws GenericBusinessException;
    java.util.Collection findPlanMedioMesById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByPlanMedioId(java.lang.Long planMedioId) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByFechaInicio(java.sql.Timestamp fechaInicio) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByFechaFin(java.sql.Timestamp fechaFin) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByValorSubtotal(java.math.BigDecimal valorSubtotal) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByValorDescuento(java.math.BigDecimal valorDescuento) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByTipo(java.lang.String tipo) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByValorIva(java.math.BigDecimal valorIva) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByValorTotal(java.math.BigDecimal valorTotal) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) throws GenericBusinessException;
    java.util.Collection findPlanMedioMesByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) throws GenericBusinessException;

}
