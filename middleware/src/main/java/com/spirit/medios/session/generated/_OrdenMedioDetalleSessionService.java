package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrdenMedioDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.OrdenMedioDetalleIf addOrdenMedioDetalle(com.spirit.medios.entity.OrdenMedioDetalleIf model) throws GenericBusinessException;

   void saveOrdenMedioDetalle(com.spirit.medios.entity.OrdenMedioDetalleIf model) throws GenericBusinessException;

   void deleteOrdenMedioDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findOrdenMedioDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.OrdenMedioDetalleIf getOrdenMedioDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getOrdenMedioDetalleList() throws GenericBusinessException;

   Collection getOrdenMedioDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrdenMedioDetalleListSize() throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByOrdenMedioId(java.lang.Long ordenMedioId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByComercialId(java.lang.Long comercialId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByPrograma(java.lang.String programa) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByHora(java.lang.String hora) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByComercial(java.lang.String comercial) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByValorTotal(java.math.BigDecimal valorTotal) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByValorIva(java.math.BigDecimal valorIva) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByValorTarifa(java.math.BigDecimal valorTarifa) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByValorSubtotal(java.math.BigDecimal valorSubtotal) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByValorDescuento(java.math.BigDecimal valorDescuento) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByTotalCunias(java.lang.Integer totalCunias) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByProductoProveedorId(java.lang.Long productoProveedorId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByProductoClienteId(java.lang.Long productoClienteId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByPauta(java.lang.String pauta) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByAuspicioDescripcion(java.lang.String auspicioDescripcion) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByAuspicioPadre(java.lang.Long auspicioPadre) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) throws GenericBusinessException;

}
