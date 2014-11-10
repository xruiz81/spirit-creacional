package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PlanMedioFacturacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PlanMedioFacturacionIf addPlanMedioFacturacion(com.spirit.medios.entity.PlanMedioFacturacionIf model) throws GenericBusinessException;

   void savePlanMedioFacturacion(com.spirit.medios.entity.PlanMedioFacturacionIf model) throws GenericBusinessException;

   void deletePlanMedioFacturacion(java.lang.Long id) throws GenericBusinessException;

   Collection findPlanMedioFacturacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PlanMedioFacturacionIf getPlanMedioFacturacion(java.lang.Long id) throws GenericBusinessException;

   Collection getPlanMedioFacturacionList() throws GenericBusinessException;

   Collection getPlanMedioFacturacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPlanMedioFacturacionListSize() throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByPlanMedioId(java.lang.Long planMedioId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByComercialId(java.lang.Long comercialId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByFechaInicio(java.sql.Timestamp fechaInicio) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByFechaFin(java.sql.Timestamp fechaFin) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByPedidoId(java.lang.Long pedidoId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByCanje(java.lang.String canje) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByProveedorId(java.lang.Long proveedorId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByPorcentajeCanje(java.math.BigDecimal porcentajeCanje) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByOrdenMedioId(java.lang.Long ordenMedioId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByOrdenMedioDetalleMapaId(java.lang.Long ordenMedioDetalleMapaId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByProductoClienteId(java.lang.Long productoClienteId) throws GenericBusinessException;
    java.util.Collection findPlanMedioFacturacionByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) throws GenericBusinessException;

}
