package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _AsientoTmpSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.AsientoTmpIf addAsientoTmp(com.spirit.contabilidad.entity.AsientoTmpIf model) throws GenericBusinessException;

   void saveAsientoTmp(com.spirit.contabilidad.entity.AsientoTmpIf model) throws GenericBusinessException;

   void deleteAsientoTmp(java.lang.Long id) throws GenericBusinessException;

   Collection findAsientoTmpByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.AsientoTmpIf getAsientoTmp(java.lang.Long id) throws GenericBusinessException;

   Collection getAsientoTmpList() throws GenericBusinessException;

   Collection getAsientoTmpList(int startIndex, int endIndex) throws GenericBusinessException;

   int getAsientoTmpListSize() throws GenericBusinessException;
    java.util.Collection findAsientoTmpById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByAsientoCierre(java.lang.String asientoCierre) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByNumero(java.lang.String numero) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByPeriodoId(java.lang.Long periodoId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByPlancuentaId(java.lang.Long plancuentaId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByStatus(java.lang.String status) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByEfectivo(java.lang.String efectivo) throws GenericBusinessException;
    java.util.Collection findAsientoTmpBySubtipoasientoId(java.lang.Long subtipoasientoId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByTipoDocumentoId(java.lang.Long tipoDocumentoId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByTransaccionId(java.lang.Long transaccionId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByElaboradoPorId(java.lang.Long elaboradoPorId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByAutorizadoPorId(java.lang.Long autorizadoPorId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByCarteraAfectaId(java.lang.Long carteraAfectaId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByEventoContableId(java.lang.Long eventoContableId) throws GenericBusinessException;
    java.util.Collection findAsientoTmpByUsarNota(java.lang.String usarNota) throws GenericBusinessException;

}
