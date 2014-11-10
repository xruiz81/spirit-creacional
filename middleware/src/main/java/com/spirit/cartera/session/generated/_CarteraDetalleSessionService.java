package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CarteraDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.CarteraDetalleIf addCarteraDetalle(com.spirit.cartera.entity.CarteraDetalleIf model) throws GenericBusinessException;

   void saveCarteraDetalle(com.spirit.cartera.entity.CarteraDetalleIf model) throws GenericBusinessException;

   void deleteCarteraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findCarteraDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.CarteraDetalleIf getCarteraDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getCarteraDetalleList() throws GenericBusinessException;

   Collection getCarteraDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCarteraDetalleListSize() throws GenericBusinessException;
    java.util.Collection findCarteraDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByCarteraId(java.lang.Long carteraId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleBySecuencial(java.lang.Integer secuencial) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByFechaCartera(java.sql.Timestamp fechaCartera) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByFechaVencimiento(java.sql.Timestamp fechaVencimiento) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByLineaId(java.lang.Long lineaId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByFormaPagoId(java.lang.Long formaPagoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByCuentaBancariaId(java.lang.Long cuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByDepositoId(java.lang.Long depositoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleBySaldo(java.math.BigDecimal saldo) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByCotizacion(java.math.BigDecimal cotizacion) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByCartera(java.lang.String cartera) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByAutorizacion(java.lang.String autorizacion) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleBySriSustentoTributarioId(java.lang.Long sriSustentoTributarioId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByDiferido(java.lang.String diferido) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleBySriClienteRetencionId(java.lang.Long sriClienteRetencionId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByChequeBancoId(java.lang.Long chequeBancoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByChequeCuentaBancariaId(java.lang.Long chequeCuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByChequeNumero(java.lang.String chequeNumero) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByDepositoBancoId(java.lang.Long depositoBancoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByDepositoCuentaBancariaId(java.lang.Long depositoCuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByRetencionNumero(java.lang.String retencionNumero) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByRetencionAutorizacion(java.lang.String retencionAutorizacion) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByValorRetencionRenta(java.math.BigDecimal valorRetencionRenta) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByValorRetencionIva(java.math.BigDecimal valorRetencionIva) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByDebitoBancoId(java.lang.Long debitoBancoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByDebitoCuentaBancariaId(java.lang.Long debitoCuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByDebitoReferencia(java.lang.String debitoReferencia) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByTransferenciaBancoOrigenId(java.lang.Long transferenciaBancoOrigenId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByTransferenciaCuentaOrigenId(java.lang.Long transferenciaCuentaOrigenId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByTransferenciaBancoDestinoId(java.lang.Long transferenciaBancoDestinoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByTransferenciaCuentaDestinoId(java.lang.Long transferenciaCuentaDestinoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByTarjetaCreditoBancoId(java.lang.Long tarjetaCreditoBancoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByTarjetaCreditoId(java.lang.Long tarjetaCreditoId) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByVoucherReferencia(java.lang.String voucherReferencia) throws GenericBusinessException;
    java.util.Collection findCarteraDetalleByPagoElectronicoReferencia(java.lang.String pagoElectronicoReferencia) throws GenericBusinessException;

}
