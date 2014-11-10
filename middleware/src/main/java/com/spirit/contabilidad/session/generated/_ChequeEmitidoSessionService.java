package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ChequeEmitidoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.ChequeEmitidoIf addChequeEmitido(com.spirit.contabilidad.entity.ChequeEmitidoIf model) throws GenericBusinessException;

   void saveChequeEmitido(com.spirit.contabilidad.entity.ChequeEmitidoIf model) throws GenericBusinessException;

   void deleteChequeEmitido(java.lang.Long id) throws GenericBusinessException;

   Collection findChequeEmitidoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.ChequeEmitidoIf getChequeEmitido(java.lang.Long id) throws GenericBusinessException;

   Collection getChequeEmitidoList() throws GenericBusinessException;

   Collection getChequeEmitidoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getChequeEmitidoListSize() throws GenericBusinessException;
    java.util.Collection findChequeEmitidoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByFechaEmision(java.sql.Date fechaEmision) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByCuentaBancariaId(java.lang.Long cuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByNumero(java.lang.String numero) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByDetalle(java.lang.String detalle) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByTipoDocumentoId(java.lang.Long tipoDocumentoId) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByTransaccionId(java.lang.Long transaccionId) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByBeneficiario(java.lang.String beneficiario) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByFechaCobro(java.sql.Date fechaCobro) throws GenericBusinessException;
    java.util.Collection findChequeEmitidoByOrigen(java.lang.String origen) throws GenericBusinessException;

}
