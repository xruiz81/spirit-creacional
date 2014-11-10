package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ProductoRetencionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.ProductoRetencionIf addProductoRetencion(com.spirit.inventario.entity.ProductoRetencionIf model) throws GenericBusinessException;

   void saveProductoRetencion(com.spirit.inventario.entity.ProductoRetencionIf model) throws GenericBusinessException;

   void deleteProductoRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection findProductoRetencionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.ProductoRetencionIf getProductoRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection getProductoRetencionList() throws GenericBusinessException;

   Collection getProductoRetencionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getProductoRetencionListSize() throws GenericBusinessException;
    java.util.Collection findProductoRetencionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findProductoRetencionByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findProductoRetencionByRetencion(java.lang.Long retencion) throws GenericBusinessException;
    java.util.Collection findProductoRetencionByFechaInicio(java.sql.Timestamp fechaInicio) throws GenericBusinessException;
    java.util.Collection findProductoRetencionByFechaFin(java.sql.Timestamp fechaFin) throws GenericBusinessException;
    java.util.Collection findProductoRetencionByEstado(java.lang.String estado) throws GenericBusinessException;

}
