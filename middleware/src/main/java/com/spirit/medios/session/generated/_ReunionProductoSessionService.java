package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ReunionProductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.ReunionProductoIf addReunionProducto(com.spirit.medios.entity.ReunionProductoIf model) throws GenericBusinessException;

   void saveReunionProducto(com.spirit.medios.entity.ReunionProductoIf model) throws GenericBusinessException;

   void deleteReunionProducto(java.lang.Long id) throws GenericBusinessException;

   Collection findReunionProductoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.ReunionProductoIf getReunionProducto(java.lang.Long id) throws GenericBusinessException;

   Collection getReunionProductoList() throws GenericBusinessException;

   Collection getReunionProductoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getReunionProductoListSize() throws GenericBusinessException;
    java.util.Collection findReunionProductoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findReunionProductoByReunionId(java.lang.Long reunionId) throws GenericBusinessException;
    java.util.Collection findReunionProductoByProductoClienteId(java.lang.Long productoClienteId) throws GenericBusinessException;
    java.util.Collection findReunionProductoByProductoCliente(java.lang.String productoCliente) throws GenericBusinessException;

}
