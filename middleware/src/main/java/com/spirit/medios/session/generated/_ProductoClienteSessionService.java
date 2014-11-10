package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ProductoClienteSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.ProductoClienteIf addProductoCliente(com.spirit.medios.entity.ProductoClienteIf model) throws GenericBusinessException;

   void saveProductoCliente(com.spirit.medios.entity.ProductoClienteIf model) throws GenericBusinessException;

   void deleteProductoCliente(java.lang.Long id) throws GenericBusinessException;

   Collection findProductoClienteByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.ProductoClienteIf getProductoCliente(java.lang.Long id) throws GenericBusinessException;

   Collection getProductoClienteList() throws GenericBusinessException;

   Collection getProductoClienteList(int startIndex, int endIndex) throws GenericBusinessException;

   int getProductoClienteListSize() throws GenericBusinessException;
    java.util.Collection findProductoClienteById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findProductoClienteByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findProductoClienteByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findProductoClienteByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findProductoClienteByCreativoId(java.lang.Long creativoId) throws GenericBusinessException;
    java.util.Collection findProductoClienteByEjecutivoId(java.lang.Long ejecutivoId) throws GenericBusinessException;
    java.util.Collection findProductoClienteByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findProductoClienteByMarcaProductoId(java.lang.Long marcaProductoId) throws GenericBusinessException;
    java.util.Collection findProductoClienteByMarcaProductoNombre(java.lang.String marcaProductoNombre) throws GenericBusinessException;

}
