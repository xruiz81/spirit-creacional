package com.spirit.compras.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CompraDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.CompraDetalleIf addCompraDetalle(com.spirit.compras.entity.CompraDetalleIf model) throws GenericBusinessException;

   void saveCompraDetalle(com.spirit.compras.entity.CompraDetalleIf model) throws GenericBusinessException;

   void deleteCompraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findCompraDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.CompraDetalleIf getCompraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getCompraDetalleList() throws GenericBusinessException;

   Collection getCompraDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCompraDetalleListSize() throws GenericBusinessException;
    java.util.Collection findCompraDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByCompraId(java.lang.Long compraId) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByCantidad(java.lang.Long cantidad) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByDescuento(java.math.BigDecimal descuento) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByOtroImpuesto(java.math.BigDecimal otroImpuesto) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByIdSriAir(java.lang.Long idSriAir) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findCompraDetalleBySriIvaRetencionId(java.lang.Long sriIvaRetencionId) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByPorcentajeDescuentosVarios(java.math.BigDecimal porcentajeDescuentosVarios) throws GenericBusinessException;
    java.util.Collection findCompraDetalleByPorcentajeDescuentoEspecial(java.math.BigDecimal porcentajeDescuentoEspecial) throws GenericBusinessException;

}
