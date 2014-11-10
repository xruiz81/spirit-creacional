package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrdenMedioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.OrdenMedioIf addOrdenMedio(com.spirit.medios.entity.OrdenMedioIf model) throws GenericBusinessException;

   void saveOrdenMedio(com.spirit.medios.entity.OrdenMedioIf model) throws GenericBusinessException;

   void deleteOrdenMedio(java.lang.Long id) throws GenericBusinessException;

   Collection findOrdenMedioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.OrdenMedioIf getOrdenMedio(java.lang.Long id) throws GenericBusinessException;

   Collection getOrdenMedioList() throws GenericBusinessException;

   Collection getOrdenMedioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrdenMedioListSize() throws GenericBusinessException;
    java.util.Collection findOrdenMedioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByClienteOficinaId(java.lang.Long clienteOficinaId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByPlanMedioId(java.lang.Long planMedioId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByProveedorId(java.lang.Long proveedorId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByValorTotal(java.math.BigDecimal valorTotal) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByProductoProveedorId(java.lang.Long productoProveedorId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByFechaOrden(java.sql.Timestamp fechaOrden) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByValorDescuento(java.math.BigDecimal valorDescuento) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByValorIva(java.math.BigDecimal valorIva) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByValorSubtotal(java.math.BigDecimal valorSubtotal) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByPorcentajeCanje(java.math.BigDecimal porcentajeCanje) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByProductoClienteId(java.lang.Long productoClienteId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByOrdenMedioTipo(java.lang.String ordenMedioTipo) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByPorcentajeNegociacionComision(java.math.BigDecimal porcentajeNegociacionComision) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByComisionPura(java.lang.String comisionPura) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByPorcentajeBonificacionCompra(java.math.BigDecimal porcentajeBonificacionCompra) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByPorcentajeBonificacionVenta(java.math.BigDecimal porcentajeBonificacionVenta) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByRevision(java.lang.String revision) throws GenericBusinessException;
    java.util.Collection findOrdenMedioByPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional) throws GenericBusinessException;

}
