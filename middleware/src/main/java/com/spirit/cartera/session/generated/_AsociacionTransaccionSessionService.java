package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _AsociacionTransaccionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.AsociacionTransaccionIf addAsociacionTransaccion(com.spirit.cartera.entity.AsociacionTransaccionIf model) throws GenericBusinessException;

   void saveAsociacionTransaccion(com.spirit.cartera.entity.AsociacionTransaccionIf model) throws GenericBusinessException;

   void deleteAsociacionTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection findAsociacionTransaccionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.AsociacionTransaccionIf getAsociacionTransaccion(java.lang.Long id) throws GenericBusinessException;

   Collection getAsociacionTransaccionList() throws GenericBusinessException;

   Collection getAsociacionTransaccionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getAsociacionTransaccionListSize() throws GenericBusinessException;
    java.util.Collection findAsociacionTransaccionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findAsociacionTransaccionByFechaEmision(java.sql.Timestamp fechaEmision) throws GenericBusinessException;
    java.util.Collection findAsociacionTransaccionByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findAsociacionTransaccionByTablaOrigen(java.lang.String tablaOrigen) throws GenericBusinessException;
    java.util.Collection findAsociacionTransaccionByTipoDocumentoOrigenId(java.lang.Long tipoDocumentoOrigenId) throws GenericBusinessException;
    java.util.Collection findAsociacionTransaccionByTransaccionOrigenId(java.lang.Long transaccionOrigenId) throws GenericBusinessException;
    java.util.Collection findAsociacionTransaccionByTablaDestino(java.lang.String tablaDestino) throws GenericBusinessException;
    java.util.Collection findAsociacionTransaccionByTipoDocumentoDestinoId(java.lang.Long tipoDocumentoDestinoId) throws GenericBusinessException;
    java.util.Collection findAsociacionTransaccionByTransaccionDestinoId(java.lang.Long transaccionDestinoId) throws GenericBusinessException;

}
