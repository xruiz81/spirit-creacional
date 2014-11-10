package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _LogCarteraSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.LogCarteraIf addLogCartera(com.spirit.cartera.entity.LogCarteraIf model) throws GenericBusinessException;

   void saveLogCartera(com.spirit.cartera.entity.LogCarteraIf model) throws GenericBusinessException;

   void deleteLogCartera(java.lang.Long id) throws GenericBusinessException;

   Collection findLogCarteraByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.LogCarteraIf getLogCartera(java.lang.Long id) throws GenericBusinessException;

   Collection getLogCarteraList() throws GenericBusinessException;

   Collection getLogCarteraList(int startIndex, int endIndex) throws GenericBusinessException;

   int getLogCarteraListSize() throws GenericBusinessException;
    java.util.Collection findLogCarteraById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findLogCarteraByTipo(java.lang.String tipo) throws GenericBusinessException;
    java.util.Collection findLogCarteraByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findLogCarteraByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findLogCarteraByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findLogCarteraByReferenciaId(java.lang.Long referenciaId) throws GenericBusinessException;
    java.util.Collection findLogCarteraByClienteoficinaId(java.lang.Long clienteoficinaId) throws GenericBusinessException;
    java.util.Collection findLogCarteraByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findLogCarteraByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findLogCarteraByVendedorId(java.lang.Long vendedorId) throws GenericBusinessException;
    java.util.Collection findLogCarteraByMonedaId(java.lang.Long monedaId) throws GenericBusinessException;
    java.util.Collection findLogCarteraByFechaEmision(java.sql.Timestamp fechaEmision) throws GenericBusinessException;
    java.util.Collection findLogCarteraByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findLogCarteraBySaldo(java.math.BigDecimal saldo) throws GenericBusinessException;
    java.util.Collection findLogCarteraByFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) throws GenericBusinessException;
    java.util.Collection findLogCarteraByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findLogCarteraByComentario(java.lang.String comentario) throws GenericBusinessException;
    java.util.Collection findLogCarteraByAprobado(java.lang.String aprobado) throws GenericBusinessException;
    java.util.Collection findLogCarteraByLog(java.lang.String log) throws GenericBusinessException;
    java.util.Collection findLogCarteraByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findLogCarteraByFechaLog(java.sql.Timestamp fechaLog) throws GenericBusinessException;

}
