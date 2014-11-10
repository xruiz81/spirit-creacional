package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TarjetaTransaccionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.TarjetaTransaccionIf addTarjetaTransaccion(com.spirit.pos.entity.TarjetaTransaccionIf model) throws GenericBusinessException;

   void saveTarjetaTransaccion(com.spirit.pos.entity.TarjetaTransaccionIf model) throws GenericBusinessException;

   void deleteTarjetaTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection findTarjetaTransaccionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.TarjetaTransaccionIf getTarjetaTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection getTarjetaTransaccionList() throws GenericBusinessException;

   Collection getTarjetaTransaccionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTarjetaTransaccionListSize() throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionByTipoDocumentoId(java.lang.Long tipoDocumentoId) throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionByTarjetaId(java.lang.Long tarjetaId) throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionByReferido(java.lang.String referido) throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionByReferidoPor(java.lang.Long referidoPor) throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionByFacturaId(java.lang.Long facturaId) throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionByPuntosGanados(java.math.BigDecimal puntosGanados) throws GenericBusinessException;
    java.util.Collection findTarjetaTransaccionByPuntosUtilizados(java.math.BigDecimal puntosUtilizados) throws GenericBusinessException;

}
