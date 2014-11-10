package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoDocumentoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.TipoDocumentoIf addTipoDocumento(com.spirit.general.entity.TipoDocumentoIf model) throws GenericBusinessException;

   void saveTipoDocumento(com.spirit.general.entity.TipoDocumentoIf model) throws GenericBusinessException;

   void deleteTipoDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoDocumentoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.TipoDocumentoIf getTipoDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoDocumentoList() throws GenericBusinessException;

   Collection getTipoDocumentoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoDocumentoListSize() throws GenericBusinessException;
    java.util.Collection findTipoDocumentoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByModuloId(java.lang.Long moduloId) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByMascara(java.lang.String mascara) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByNumerolineas(java.lang.Integer numerolineas) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByAfectacartera(java.lang.String afectacartera) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByAfectastock(java.lang.String afectastock) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByAfectaventa(java.lang.String afectaventa) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByExigemotivo(java.lang.String exigemotivo) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByFormapago(java.lang.String formapago) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByCliente(java.lang.String cliente) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByCaja(java.lang.String caja) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByPermiteeliminacion(java.lang.String permiteeliminacion) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByReembolso(java.lang.String reembolso) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoBySignocartera(java.lang.String signocartera) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoBySignostock(java.lang.String signostock) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoBySignoventa(java.lang.String signoventa) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByDescuentoespecial(java.lang.String descuentoespecial) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByTipocartera(java.lang.String tipocartera) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByIdSriTipoComprobante(java.lang.Long idSriTipoComprobante) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByTipoTroqueladoId(java.lang.Long tipoTroqueladoId) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByTransferible(java.lang.String transferible) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByFactura(java.lang.String factura) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByNotaVenta(java.lang.String notaVenta) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByNotaCredito(java.lang.String notaCredito) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByNotaDebito(java.lang.String notaDebito) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByLiquidacionCompras(java.lang.String liquidacionCompras) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByAbreviatura(java.lang.String abreviatura) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByEgreso(java.lang.String egreso) throws GenericBusinessException;
    java.util.Collection findTipoDocumentoByAnticipo(java.lang.String anticipo) throws GenericBusinessException;

}
