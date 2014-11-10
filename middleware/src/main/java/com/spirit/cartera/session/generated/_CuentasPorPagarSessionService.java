package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CuentasPorPagarSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.CuentasPorPagarIf addCuentasPorPagar(com.spirit.cartera.entity.CuentasPorPagarIf model) throws GenericBusinessException;

   void saveCuentasPorPagar(com.spirit.cartera.entity.CuentasPorPagarIf model) throws GenericBusinessException;

   void deleteCuentasPorPagar(java.lang.Long id) throws GenericBusinessException;

   Collection findCuentasPorPagarByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.CuentasPorPagarIf getCuentasPorPagar(java.lang.Long id) throws GenericBusinessException;

   Collection getCuentasPorPagarList() throws GenericBusinessException;

   Collection getCuentasPorPagarList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCuentasPorPagarListSize() throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByCarteraId(java.lang.Long carteraId) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByFechaEmision(java.sql.Timestamp fechaEmision) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByComentario(java.lang.String comentario) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByReferenciaId(java.lang.Long referenciaId) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarBySaldo(java.math.BigDecimal saldo) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByCompraId(java.lang.Long compraId) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByFechaCompra(java.sql.Timestamp fechaCompra) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByProveedorId(java.lang.Long proveedorId) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByRazonSocial(java.lang.String razonSocial) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByIdentificacion(java.lang.String identificacion) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByProveedorOficinaId(java.lang.Long proveedorOficinaId) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByTipoProveedorId(java.lang.Long tipoProveedorId) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByCodigoTipoProveedor(java.lang.String codigoTipoProveedor) throws GenericBusinessException;
    java.util.Collection findCuentasPorPagarByTipoProveedor(java.lang.String tipoProveedor) throws GenericBusinessException;

}
