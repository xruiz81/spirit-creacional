package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PlanMedioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PlanMedioIf addPlanMedio(com.spirit.medios.entity.PlanMedioIf model) throws GenericBusinessException;

   void savePlanMedio(com.spirit.medios.entity.PlanMedioIf model) throws GenericBusinessException;

   void deletePlanMedio(java.lang.Long id) throws GenericBusinessException;

   Collection findPlanMedioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PlanMedioIf getPlanMedio(java.lang.Long id) throws GenericBusinessException;

   Collection getPlanMedioList() throws GenericBusinessException;

   Collection getPlanMedioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPlanMedioListSize() throws GenericBusinessException;
    java.util.Collection findPlanMedioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPlanMedioByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPlanMedioByConcepto(java.lang.String concepto) throws GenericBusinessException;
    java.util.Collection findPlanMedioByOrdenTrabajoDetalleId(java.lang.Long ordenTrabajoDetalleId) throws GenericBusinessException;
    java.util.Collection findPlanMedioByGrupoObjetivoId(java.lang.Long grupoObjetivoId) throws GenericBusinessException;
    java.util.Collection findPlanMedioByTipoProveedorId(java.lang.Long tipoProveedorId) throws GenericBusinessException;
    java.util.Collection findPlanMedioByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findPlanMedioByModificacion(java.lang.Integer modificacion) throws GenericBusinessException;
    java.util.Collection findPlanMedioByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findPlanMedioByFechaInicio(java.sql.Timestamp fechaInicio) throws GenericBusinessException;
    java.util.Collection findPlanMedioByFechaFin(java.sql.Timestamp fechaFin) throws GenericBusinessException;
    java.util.Collection findPlanMedioByValorSubtotal(java.math.BigDecimal valorSubtotal) throws GenericBusinessException;
    java.util.Collection findPlanMedioByValorDescuento(java.math.BigDecimal valorDescuento) throws GenericBusinessException;
    java.util.Collection findPlanMedioByPlanMedioOrigenId(java.lang.Long planMedioOrigenId) throws GenericBusinessException;
    java.util.Collection findPlanMedioByCiudad1(java.math.BigDecimal ciudad1) throws GenericBusinessException;
    java.util.Collection findPlanMedioByCiudad2(java.math.BigDecimal ciudad2) throws GenericBusinessException;
    java.util.Collection findPlanMedioByCiudad3(java.math.BigDecimal ciudad3) throws GenericBusinessException;
    java.util.Collection findPlanMedioByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findPlanMedioByCobertura(java.lang.String cobertura) throws GenericBusinessException;
    java.util.Collection findPlanMedioByConsideraciones(java.lang.String consideraciones) throws GenericBusinessException;
    java.util.Collection findPlanMedioByValorIva(java.math.BigDecimal valorIva) throws GenericBusinessException;
    java.util.Collection findPlanMedioByUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) throws GenericBusinessException;
    java.util.Collection findPlanMedioByFechaActualizacion(java.sql.Timestamp fechaActualizacion) throws GenericBusinessException;
    java.util.Collection findPlanMedioByPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) throws GenericBusinessException;
    java.util.Collection findPlanMedioByValorTotal(java.math.BigDecimal valorTotal) throws GenericBusinessException;
    java.util.Collection findPlanMedioByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) throws GenericBusinessException;
    java.util.Collection findPlanMedioByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) throws GenericBusinessException;
    java.util.Collection findPlanMedioByPlanMedioHermanoId(java.lang.Long planMedioHermanoId) throws GenericBusinessException;
    java.util.Collection findPlanMedioByOrdenMedioTipo(java.lang.String ordenMedioTipo) throws GenericBusinessException;
    java.util.Collection findPlanMedioByPlanMedioTipo(java.lang.String planMedioTipo) throws GenericBusinessException;
    java.util.Collection findPlanMedioByAutorizacionSap(java.lang.String autorizacionSap) throws GenericBusinessException;
    java.util.Collection findPlanMedioByFechaAprobacion(java.sql.Timestamp fechaAprobacion) throws GenericBusinessException;
    java.util.Collection findPlanMedioByRevision(java.lang.String revision) throws GenericBusinessException;
    java.util.Collection findPlanMedioByPrepago(java.lang.String prepago) throws GenericBusinessException;

}
