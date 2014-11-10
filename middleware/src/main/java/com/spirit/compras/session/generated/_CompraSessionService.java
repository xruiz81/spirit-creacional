package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CompraSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.CompraIf addCompra(com.spirit.compras.entity.CompraIf model) throws GenericBusinessException;

   void saveCompra(com.spirit.compras.entity.CompraIf model) throws GenericBusinessException;

   void deleteCompra(java.lang.Long id) throws GenericBusinessException;

   Collection findCompraByQuery(Map aMap) throws GenericBusinessException;

   com.spirit.compras.entity.CompraIf getCompra(java.lang.Long id) throws GenericBusinessException;

   Collection getCompraList() throws GenericBusinessException;

   Collection getCompraList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCompraListSize() throws GenericBusinessException;

    java.util.Collection findCompraById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCompraByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findCompraByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findCompraByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCompraByProveedorId(java.lang.Long proveedorId) throws GenericBusinessException;
    java.util.Collection findCompraByMonedaId(java.lang.Long monedaId) throws GenericBusinessException;
    java.util.Collection findCompraByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findCompraByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findCompraByFechaVencimiento(java.sql.Date fechaVencimiento) throws GenericBusinessException;
    java.util.Collection findCompraByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findCompraByAutorizacion(java.lang.String autorizacion) throws GenericBusinessException;
    java.util.Collection findCompraByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findCompraByLocalimportada(java.lang.String localimportada) throws GenericBusinessException;
    java.util.Collection findCompraByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findCompraByDescuento(java.math.BigDecimal descuento) throws GenericBusinessException;
    java.util.Collection findCompraByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findCompraByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findCompraByOtroImpuesto(java.math.BigDecimal otroImpuesto) throws GenericBusinessException;
    java.util.Collection findCompraByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findCompraByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findCompraByEstadoBpm(java.lang.String estadoBpm) throws GenericBusinessException;
    java.util.Collection findCompraByRetencion(java.math.BigDecimal retencion) throws GenericBusinessException;
    java.util.Collection findCompraByIdSriSustentoTributario(java.lang.Long idSriSustentoTributario) throws GenericBusinessException;
    java.util.Collection findCompraByFechaCaducidad(java.sql.Date fechaCaducidad) throws GenericBusinessException;
    java.util.Collection findCompraByTipoCompra(java.lang.String tipoCompra) throws GenericBusinessException;
}
