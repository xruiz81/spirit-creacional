package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrdenTrabajoProductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.OrdenTrabajoProductoIf addOrdenTrabajoProducto(com.spirit.medios.entity.OrdenTrabajoProductoIf model) throws GenericBusinessException;

   void saveOrdenTrabajoProducto(com.spirit.medios.entity.OrdenTrabajoProductoIf model) throws GenericBusinessException;

   void deleteOrdenTrabajoProducto(java.lang.Long id) throws GenericBusinessException;

   Collection findOrdenTrabajoProductoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.OrdenTrabajoProductoIf getOrdenTrabajoProducto(java.lang.Long id) throws GenericBusinessException;

   Collection getOrdenTrabajoProductoList() throws GenericBusinessException;

   Collection getOrdenTrabajoProductoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrdenTrabajoProductoListSize() throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoProductoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoProductoByProductoClienteId(java.lang.Long productoClienteId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoProductoByOrdenTrabajoId(java.lang.Long ordenTrabajoId) throws GenericBusinessException;

}
