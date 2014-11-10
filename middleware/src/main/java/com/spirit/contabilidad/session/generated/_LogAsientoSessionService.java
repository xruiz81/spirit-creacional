package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _LogAsientoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.LogAsientoIf addLogAsiento(com.spirit.contabilidad.entity.LogAsientoIf model) throws GenericBusinessException;

   void saveLogAsiento(com.spirit.contabilidad.entity.LogAsientoIf model) throws GenericBusinessException;

   void deleteLogAsiento(java.lang.Long id) throws GenericBusinessException;

   Collection findLogAsientoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.LogAsientoIf getLogAsiento(java.lang.Long id) throws GenericBusinessException;

   Collection getLogAsientoList() throws GenericBusinessException;

   Collection getLogAsientoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getLogAsientoListSize() throws GenericBusinessException;
    java.util.Collection findLogAsientoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findLogAsientoByNumero(java.lang.String numero) throws GenericBusinessException;
    java.util.Collection findLogAsientoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByPeriodoId(java.lang.Long periodoId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByPlancuentaId(java.lang.Long plancuentaId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findLogAsientoByStatus(java.lang.String status) throws GenericBusinessException;
    java.util.Collection findLogAsientoByEfectivo(java.lang.String efectivo) throws GenericBusinessException;
    java.util.Collection findLogAsientoBySubtipoasientoId(java.lang.Long subtipoasientoId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findLogAsientoByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByTipoDocumentoId(java.lang.Long tipoDocumentoId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByTransaccionId(java.lang.Long transaccionId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByLog(java.lang.String log) throws GenericBusinessException;
    java.util.Collection findLogAsientoByElaboradoPorId(java.lang.Long elaboradoPorId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByAutorizadoPorId(java.lang.Long autorizadoPorId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByCarteraAfectaId(java.lang.Long carteraAfectaId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByEventoContableId(java.lang.Long eventoContableId) throws GenericBusinessException;
    java.util.Collection findLogAsientoByAsientoCierre(java.lang.String asientoCierre) throws GenericBusinessException;
    java.util.Collection findLogAsientoByUsarNota(java.lang.String usarNota) throws GenericBusinessException;
    java.util.Collection findLogAsientoByNota(java.lang.String nota) throws GenericBusinessException;

}
