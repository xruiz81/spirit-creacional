package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _StockDiaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.StockDiaIf addStockDia(com.spirit.inventario.entity.StockDiaIf model) throws GenericBusinessException;

   void saveStockDia(com.spirit.inventario.entity.StockDiaIf model) throws GenericBusinessException;

   void deleteStockDia(java.lang.Long id) throws GenericBusinessException;

   Collection findStockDiaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.StockDiaIf getStockDia(java.lang.Long id) throws GenericBusinessException;

   Collection getStockDiaList() throws GenericBusinessException;

   Collection getStockDiaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getStockDiaListSize() throws GenericBusinessException;
    java.util.Collection findStockDiaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findStockDiaByBodegaId(java.lang.Long bodegaId) throws GenericBusinessException;
    java.util.Collection findStockDiaByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findStockDiaByAnio(java.lang.String anio) throws GenericBusinessException;
    java.util.Collection findStockDiaByMes(java.lang.String mes) throws GenericBusinessException;
    java.util.Collection findStockDiaByDia(java.lang.String dia) throws GenericBusinessException;
    java.util.Collection findStockDiaByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;

}
