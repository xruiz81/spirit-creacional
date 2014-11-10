package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _DocumentoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.DocumentoIf addDocumento(com.spirit.general.entity.DocumentoIf model) throws GenericBusinessException;

   void saveDocumento(com.spirit.general.entity.DocumentoIf model) throws GenericBusinessException;

   void deleteDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection findDocumentoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.DocumentoIf getDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection getDocumentoList() throws GenericBusinessException;

   Collection getDocumentoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getDocumentoListSize() throws GenericBusinessException;
    java.util.Collection findDocumentoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findDocumentoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findDocumentoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findDocumentoByAbreviado(java.lang.String abreviado) throws GenericBusinessException;
    java.util.Collection findDocumentoByTipoDocumentoId(java.lang.Long tipoDocumentoId) throws GenericBusinessException;
    java.util.Collection findDocumentoByPideAutorizacion(java.lang.String pideAutorizacion) throws GenericBusinessException;
    java.util.Collection findDocumentoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findDocumentoByDiferido(java.lang.String diferido) throws GenericBusinessException;
    java.util.Collection findDocumentoByBonificacion(java.lang.String bonificacion) throws GenericBusinessException;
    java.util.Collection findDocumentoByPrecioEspecial(java.lang.String precioEspecial) throws GenericBusinessException;
    java.util.Collection findDocumentoByDescuentoEspecial(java.lang.String descuentoEspecial) throws GenericBusinessException;
    java.util.Collection findDocumentoByMulta(java.lang.String multa) throws GenericBusinessException;
    java.util.Collection findDocumentoByInteres(java.lang.String interes) throws GenericBusinessException;
    java.util.Collection findDocumentoByProtesto(java.lang.String protesto) throws GenericBusinessException;
    java.util.Collection findDocumentoByCheque(java.lang.String cheque) throws GenericBusinessException;
    java.util.Collection findDocumentoByRetencionRenta(java.lang.String retencionRenta) throws GenericBusinessException;
    java.util.Collection findDocumentoByRetencionIva(java.lang.String retencionIva) throws GenericBusinessException;
    java.util.Collection findDocumentoByEfectivo(java.lang.String efectivo) throws GenericBusinessException;
    java.util.Collection findDocumentoByDebitoBancario(java.lang.String debitoBancario) throws GenericBusinessException;
    java.util.Collection findDocumentoByTarjetaCredito(java.lang.String tarjetaCredito) throws GenericBusinessException;
    java.util.Collection findDocumentoByTransaccionElectronica(java.lang.String transaccionElectronica) throws GenericBusinessException;
    java.util.Collection findDocumentoByTransferenciaBancaria(java.lang.String transferenciaBancaria) throws GenericBusinessException;
    java.util.Collection findDocumentoByNivelacion(java.lang.String nivelacion) throws GenericBusinessException;
    java.util.Collection findDocumentoByAnticipo(java.lang.String anticipo) throws GenericBusinessException;

}
