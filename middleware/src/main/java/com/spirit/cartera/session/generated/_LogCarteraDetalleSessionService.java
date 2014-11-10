package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _LogCarteraDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.LogCarteraDetalleIf addLogCarteraDetalle(com.spirit.cartera.entity.LogCarteraDetalleIf model) throws GenericBusinessException;

   void saveLogCarteraDetalle(com.spirit.cartera.entity.LogCarteraDetalleIf model) throws GenericBusinessException;

   void deleteLogCarteraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findLogCarteraDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.LogCarteraDetalleIf getLogCarteraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getLogCarteraDetalleList() throws GenericBusinessException;

   Collection getLogCarteraDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getLogCarteraDetalleListSize() throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByLogCarteraId(java.lang.Long logCarteraId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleBySecuencial(java.lang.Integer secuencial) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByFechaCartera(java.sql.Timestamp fechaCartera) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByFechaVencimiento(java.sql.Timestamp fechaVencimiento) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByLineaId(java.lang.Long lineaId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByFormaPagoId(java.lang.Long formaPagoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByCuentaBancariaId(java.lang.Long cuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByDepositoId(java.lang.Long depositoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleBySaldo(java.math.BigDecimal saldo) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByCotizacion(java.math.BigDecimal cotizacion) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByCartera(java.lang.String cartera) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByAutorizacion(java.lang.String autorizacion) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleBySriSustentoTributarioId(java.lang.Long sriSustentoTributarioId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByLog(java.lang.String log) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByDiferido(java.lang.String diferido) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByValorRetencionRenta(java.math.BigDecimal valorRetencionRenta) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByValorRetencionIva(java.math.BigDecimal valorRetencionIva) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByDebitoBancoId(java.lang.Long debitoBancoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleBySriClienteRetencionId(java.lang.Long sriClienteRetencionId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByChequeBancoId(java.lang.Long chequeBancoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByChequeCuentaBancariaId(java.lang.Long chequeCuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByChequeNumero(java.lang.String chequeNumero) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByDepositoBancoId(java.lang.Long depositoBancoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByDepositoCuentaBancariaId(java.lang.Long depositoCuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByRetencionNumero(java.lang.String retencionNumero) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByRetencionAutorizacion(java.lang.String retencionAutorizacion) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByDebitoCuentaBancariaId(java.lang.Long debitoCuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByDebitoReferencia(java.lang.String debitoReferencia) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByTransferenciaBancoOrigenId(java.lang.Long transferenciaBancoOrigenId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByTransferenciaCuentaOrigenId(java.lang.Long transferenciaCuentaOrigenId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByTransferenciaBancoDestinoId(java.lang.Long transferenciaBancoDestinoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByTransferenciaCuentaDestinoId(java.lang.Long transferenciaCuentaDestinoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByTarjetaCreditoBancoId(java.lang.Long tarjetaCreditoBancoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByTarjetaCreditoId(java.lang.Long tarjetaCreditoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByVoucherReferencia(java.lang.String voucherReferencia) throws GenericBusinessException;
    java.util.Collection findLogCarteraDetalleByPagoElectronicoReferencia(java.lang.String pagoElectronicoReferencia) throws GenericBusinessException;

}
