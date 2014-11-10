package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _StockSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.StockIf addStock(com.spirit.inventario.entity.StockIf model) throws GenericBusinessException;

   void saveStock(com.spirit.inventario.entity.StockIf model) throws GenericBusinessException;

   void deleteStock(java.lang.Long id) throws GenericBusinessException;

   Collection findStockByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.StockIf getStock(java.lang.Long id) throws GenericBusinessException;

   Collection getStockList() throws GenericBusinessException;

   Collection getStockList(int startIndex, int endIndex) throws GenericBusinessException;

   int getStockListSize() throws GenericBusinessException;
    java.util.Collection findStockById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findStockByBodegaId(java.lang.Long bodegaId) throws GenericBusinessException;
    java.util.Collection findStockByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findStockByLoteId(java.lang.Long loteId) throws GenericBusinessException;
    java.util.Collection findStockByAnio(java.lang.String anio) throws GenericBusinessException;
    java.util.Collection findStockByMes(java.lang.String mes) throws GenericBusinessException;
    java.util.Collection findStockByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;
    java.util.Collection findStockByReserva(java.math.BigDecimal reserva) throws GenericBusinessException;
    java.util.Collection findStockByTransito(java.math.BigDecimal transito) throws GenericBusinessException;
    java.util.Collection findStockByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findStockByFhUtlModificacion(java.sql.Timestamp fhUtlModificacion) throws GenericBusinessException;

}
