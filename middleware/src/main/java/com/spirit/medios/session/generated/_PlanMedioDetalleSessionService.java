package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PlanMedioDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PlanMedioDetalleIf addPlanMedioDetalle(com.spirit.medios.entity.PlanMedioDetalleIf model) throws GenericBusinessException;

   void savePlanMedioDetalle(com.spirit.medios.entity.PlanMedioDetalleIf model) throws GenericBusinessException;

   void deletePlanMedioDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findPlanMedioDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PlanMedioDetalleIf getPlanMedioDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getPlanMedioDetalleList() throws GenericBusinessException;

   Collection getPlanMedioDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPlanMedioDetalleListSize() throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByPlanMedioMesId(java.lang.Long planMedioMesId) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByPrograma(java.lang.String programa) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByGeneroProgramaId(java.lang.Long generoProgramaId) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByComercialId(java.lang.Long comercialId) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByProveedorId(java.lang.Long proveedorId) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByHoraInicio(java.lang.String horaInicio) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByRaiting1(java.math.BigDecimal raiting1) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByRaiting2(java.math.BigDecimal raiting2) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByRaitingPonderado(java.math.BigDecimal raitingPonderado) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByAudiencia(java.math.BigDecimal audiencia) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByValorTarifa(java.math.BigDecimal valorTarifa) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByValorTotal(java.math.BigDecimal valorTotal) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleBySeccion(java.lang.String seccion) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByUbicacion(java.lang.String ubicacion) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByTamanio(java.lang.String tamanio) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByColor(java.lang.String color) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByTotalCunias(java.lang.Integer totalCunias) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByComercial(java.lang.String comercial) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByValorDescuento(java.math.BigDecimal valorDescuento) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByProductoProveedorId(java.lang.Long productoProveedorId) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByValorSubtotal(java.math.BigDecimal valorSubtotal) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByValorIva(java.math.BigDecimal valorIva) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByProductoClienteId(java.lang.Long productoClienteId) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByPauta(java.lang.String pauta) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByAuspicioDescripcion(java.lang.String auspicioDescripcion) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByAuspicioPadre(java.lang.Long auspicioPadre) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByPorcentajeBonificacionCompra(java.math.BigDecimal porcentajeBonificacionCompra) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByPorcentajeBonificacionVenta(java.math.BigDecimal porcentajeBonificacionVenta) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByNegociacionComision(java.lang.String negociacionComision) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion) throws GenericBusinessException;
    java.util.Collection findPlanMedioDetalleByVersion(java.lang.String version) throws GenericBusinessException;

}
