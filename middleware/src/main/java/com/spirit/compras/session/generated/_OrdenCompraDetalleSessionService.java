package com.spirit.compras.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrdenCompraDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.OrdenCompraDetalleIf addOrdenCompraDetalle(com.spirit.compras.entity.OrdenCompraDetalleIf model) throws GenericBusinessException;

   void saveOrdenCompraDetalle(com.spirit.compras.entity.OrdenCompraDetalleIf model) throws GenericBusinessException;

   void deleteOrdenCompraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findOrdenCompraDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.OrdenCompraDetalleIf getOrdenCompraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getOrdenCompraDetalleList() throws GenericBusinessException;

   Collection getOrdenCompraDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrdenCompraDetalleListSize() throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByOrdencompraId(java.lang.Long ordencompraId) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByCantidad(java.lang.Long cantidad) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByDescuentoAgenciaCompra(java.math.BigDecimal descuentoAgenciaCompra) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByOtroImpuesto(java.math.BigDecimal otroImpuesto) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByPorcentajeComisionPura(java.math.BigDecimal porcentajeComisionPura) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByPorcentajeDescuentosVariosCompra(java.math.BigDecimal porcentajeDescuentosVariosCompra) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) throws GenericBusinessException;
    java.util.Collection findOrdenCompraDetalleByFechaPublicacion(java.sql.Timestamp fechaPublicacion) throws GenericBusinessException;

}
