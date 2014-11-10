package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PresupuestoDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PresupuestoDetalleIf addPresupuestoDetalle(com.spirit.medios.entity.PresupuestoDetalleIf model) throws GenericBusinessException;

   void savePresupuestoDetalle(com.spirit.medios.entity.PresupuestoDetalleIf model) throws GenericBusinessException;

   void deletePresupuestoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findPresupuestoDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PresupuestoDetalleIf getPresupuestoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getPresupuestoDetalleList() throws GenericBusinessException;

   Collection getPresupuestoDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPresupuestoDetalleListSize() throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPresupuestoId(java.lang.Long presupuestoId) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByConcepto(java.lang.String concepto) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPrecioagencia(java.math.BigDecimal precioagencia) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPrecioventa(java.math.BigDecimal precioventa) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeDescuentoAgenciaVenta(java.math.BigDecimal porcentajeDescuentoAgenciaVenta) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByProveedorId(java.lang.Long proveedorId) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeDescuentoAgenciaCompra(java.math.BigDecimal porcentajeDescuentoAgenciaCompra) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByIvaCompra(java.math.BigDecimal ivaCompra) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByReporte(java.lang.String reporte) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByOrden(java.lang.Integer orden) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByOrdenCompraId(java.lang.Long ordenCompraId) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeComisionPura(java.math.BigDecimal porcentajeComisionPura) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeDescuentosVariosVenta(java.math.BigDecimal porcentajeDescuentosVariosVenta) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeDescuentoEspecialCompra(java.math.BigDecimal porcentajeDescuentoEspecialCompra) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeDescuentoEspecialVenta(java.math.BigDecimal porcentajeDescuentoEspecialVenta) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByFechaPublicacion(java.sql.Timestamp fechaPublicacion) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeNotaCreditoCompra(java.math.BigDecimal porcentajeNotaCreditoCompra) throws GenericBusinessException;
    java.util.Collection findPresupuestoDetalleByPorcentajeComisionAdicional(java.math.BigDecimal porcentajeComisionAdicional) throws GenericBusinessException;

}
