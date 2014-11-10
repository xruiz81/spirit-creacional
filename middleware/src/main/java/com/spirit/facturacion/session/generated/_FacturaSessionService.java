package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _FacturaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.FacturaIf addFactura(com.spirit.facturacion.entity.FacturaIf model) throws GenericBusinessException;

   void saveFactura(com.spirit.facturacion.entity.FacturaIf model) throws GenericBusinessException;

   void deleteFactura(java.lang.Long id) throws GenericBusinessException;

   Collection findFacturaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.FacturaIf getFactura(java.lang.Long id) throws GenericBusinessException;

   Collection getFacturaList() throws GenericBusinessException;

   Collection getFacturaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getFacturaListSize() throws GenericBusinessException;
    java.util.Collection findFacturaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findFacturaByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findFacturaByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findFacturaByNumero(java.math.BigDecimal numero) throws GenericBusinessException;
    java.util.Collection findFacturaByClienteoficinaId(java.lang.Long clienteoficinaId) throws GenericBusinessException;
    java.util.Collection findFacturaByTipoidentificacionId(java.lang.Long tipoidentificacionId) throws GenericBusinessException;
    java.util.Collection findFacturaByIdentificacion(java.lang.String identificacion) throws GenericBusinessException;
    java.util.Collection findFacturaByFormapagoId(java.lang.Long formapagoId) throws GenericBusinessException;
    java.util.Collection findFacturaByMonedaId(java.lang.Long monedaId) throws GenericBusinessException;
    java.util.Collection findFacturaByPuntoImpresionId(java.lang.Long puntoImpresionId) throws GenericBusinessException;
    java.util.Collection findFacturaByOrigendocumentoId(java.lang.Long origendocumentoId) throws GenericBusinessException;
    java.util.Collection findFacturaByVendedorId(java.lang.Long vendedorId) throws GenericBusinessException;
    java.util.Collection findFacturaByBodegaId(java.lang.Long bodegaId) throws GenericBusinessException;
    java.util.Collection findFacturaByPedidoId(java.lang.Long pedidoId) throws GenericBusinessException;
    java.util.Collection findFacturaByListaPrecioId(java.lang.Long listaPrecioId) throws GenericBusinessException;
    java.util.Collection findFacturaByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findFacturaByFechaFactura(java.sql.Timestamp fechaFactura) throws GenericBusinessException;
    java.util.Collection findFacturaByFechaVencimiento(java.sql.Timestamp fechaVencimiento) throws GenericBusinessException;
    java.util.Collection findFacturaByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findFacturaByContacto(java.lang.String contacto) throws GenericBusinessException;
    java.util.Collection findFacturaByDireccion(java.lang.String direccion) throws GenericBusinessException;
    java.util.Collection findFacturaByTelefono(java.lang.String telefono) throws GenericBusinessException;
    java.util.Collection findFacturaByPreimpresoNumero(java.lang.String preimpresoNumero) throws GenericBusinessException;
    java.util.Collection findFacturaByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findFacturaByDescuento(java.math.BigDecimal descuento) throws GenericBusinessException;
    java.util.Collection findFacturaByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findFacturaByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findFacturaByOtroImpuesto(java.math.BigDecimal otroImpuesto) throws GenericBusinessException;
    java.util.Collection findFacturaByCosto(java.math.BigDecimal costo) throws GenericBusinessException;
    java.util.Collection findFacturaByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findFacturaByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findFacturaByFacturaaplId(java.lang.Long facturaaplId) throws GenericBusinessException;
    java.util.Collection findFacturaByDescuentoGlobal(java.math.BigDecimal descuentoGlobal) throws GenericBusinessException;
    java.util.Collection findFacturaByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) throws GenericBusinessException;
    java.util.Collection findFacturaByEquipoId(java.lang.Long equipoId) throws GenericBusinessException;
    java.util.Collection findFacturaByClienteNegociacionId(java.lang.Long clienteNegociacionId) throws GenericBusinessException;
    java.util.Collection findFacturaByTipoNegociacion(java.lang.String tipoNegociacion) throws GenericBusinessException;
    java.util.Collection findFacturaByPorcentajeNegociacionDirecta(java.math.BigDecimal porcentajeNegociacionDirecta) throws GenericBusinessException;
    java.util.Collection findFacturaByPorcentajeDescuentoNegociacion(java.math.BigDecimal porcentajeDescuentoNegociacion) throws GenericBusinessException;
    java.util.Collection findFacturaByAutorizacionSap(java.lang.String autorizacionSap) throws GenericBusinessException;
    java.util.Collection findFacturaByDescuentosVarios(java.math.BigDecimal descuentosVarios) throws GenericBusinessException;

}
