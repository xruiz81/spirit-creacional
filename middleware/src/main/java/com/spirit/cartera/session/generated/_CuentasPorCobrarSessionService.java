package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CuentasPorCobrarSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.CuentasPorCobrarIf addCuentasPorCobrar(com.spirit.cartera.entity.CuentasPorCobrarIf model) throws GenericBusinessException;

   void saveCuentasPorCobrar(com.spirit.cartera.entity.CuentasPorCobrarIf model) throws GenericBusinessException;

   void deleteCuentasPorCobrar(java.lang.Long id) throws GenericBusinessException;

   Collection findCuentasPorCobrarByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.CuentasPorCobrarIf getCuentasPorCobrar(java.lang.Long id) throws GenericBusinessException;

   Collection getCuentasPorCobrarList() throws GenericBusinessException;

   Collection getCuentasPorCobrarList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCuentasPorCobrarListSize() throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByCarteraId(java.lang.Long carteraId) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByFechaEmision(java.sql.Timestamp fechaEmision) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByComentario(java.lang.String comentario) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByReferenciaId(java.lang.Long referenciaId) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarBySaldo(java.math.BigDecimal saldo) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByFacturaId(java.lang.Long facturaId) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByFechaFactura(java.sql.Timestamp fechaFactura) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByRazonSocial(java.lang.String razonSocial) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByIdentificacion(java.lang.String identificacion) throws GenericBusinessException;
    java.util.Collection findCuentasPorCobrarByClienteOficinaId(java.lang.Long clienteOficinaId) throws GenericBusinessException;

}
