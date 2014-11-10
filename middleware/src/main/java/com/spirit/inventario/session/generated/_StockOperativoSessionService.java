package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _StockOperativoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.StockOperativoIf addStockOperativo(com.spirit.inventario.entity.StockOperativoIf model) throws GenericBusinessException;

   void saveStockOperativo(com.spirit.inventario.entity.StockOperativoIf model) throws GenericBusinessException;

   void deleteStockOperativo(java.lang.Long id) throws GenericBusinessException;

   Collection findStockOperativoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.StockOperativoIf getStockOperativo(java.lang.Long id) throws GenericBusinessException;

   Collection getStockOperativoList() throws GenericBusinessException;

   Collection getStockOperativoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getStockOperativoListSize() throws GenericBusinessException;
    java.util.Collection findStockOperativoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findStockOperativoByBodegaId(java.lang.Long bodegaId) throws GenericBusinessException;
    java.util.Collection findStockOperativoByMes(java.lang.String mes) throws GenericBusinessException;
    java.util.Collection findStockOperativoByAnio(java.lang.String anio) throws GenericBusinessException;
    java.util.Collection findStockOperativoByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findStockOperativoByMin(java.math.BigDecimal min) throws GenericBusinessException;
    java.util.Collection findStockOperativoByMax(java.math.BigDecimal max) throws GenericBusinessException;
    java.util.Collection findStockOperativoByTiempoMinimoReposicion(java.lang.Integer tiempoMinimoReposicion) throws GenericBusinessException;

}
