package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmbalajeProductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.EmbalajeProductoIf addEmbalajeProducto(com.spirit.inventario.entity.EmbalajeProductoIf model) throws GenericBusinessException;

   void saveEmbalajeProducto(com.spirit.inventario.entity.EmbalajeProductoIf model) throws GenericBusinessException;

   void deleteEmbalajeProducto(java.lang.Long id) throws GenericBusinessException;

   Collection findEmbalajeProductoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.EmbalajeProductoIf getEmbalajeProducto(java.lang.Long id) throws GenericBusinessException;

   Collection getEmbalajeProductoList() throws GenericBusinessException;

   Collection getEmbalajeProductoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmbalajeProductoListSize() throws GenericBusinessException;
    java.util.Collection findEmbalajeProductoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmbalajeProductoByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findEmbalajeProductoByEmbalajeId(java.lang.Long embalajeId) throws GenericBusinessException;
    java.util.Collection findEmbalajeProductoByCantidad(java.math.BigDecimal cantidad) throws GenericBusinessException;
    java.util.Collection findEmbalajeProductoByAreaCubica(java.math.BigDecimal areaCubica) throws GenericBusinessException;
    java.util.Collection findEmbalajeProductoByTipoManejo(java.lang.String tipoManejo) throws GenericBusinessException;

}
