package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _LoteSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.LoteIf addLote(com.spirit.inventario.entity.LoteIf model) throws GenericBusinessException;

   void saveLote(com.spirit.inventario.entity.LoteIf model) throws GenericBusinessException;

   void deleteLote(java.lang.Long id) throws GenericBusinessException;

   Collection findLoteByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.LoteIf getLote(java.lang.Long id) throws GenericBusinessException;

   Collection getLoteList() throws GenericBusinessException;

   Collection getLoteList(int startIndex, int endIndex) throws GenericBusinessException;

   int getLoteListSize() throws GenericBusinessException;
    java.util.Collection findLoteById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findLoteByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findLoteByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findLoteByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findLoteByFechaElaboracion(java.sql.Timestamp fechaElaboracion) throws GenericBusinessException;
    java.util.Collection findLoteByFechaVencimiento(java.sql.Timestamp fechaVencimiento) throws GenericBusinessException;
    java.util.Collection findLoteByEstado(java.lang.String estado) throws GenericBusinessException;

}
